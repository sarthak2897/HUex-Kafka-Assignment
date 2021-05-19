package com.hashedin.Producer.producer;

import com.hashedin.Producer.domain.TrainArrival;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class TrainArrivalProducer {

        @Autowired
        private KafkaTemplate<Integer,TrainArrival> kafkaTemplate;

        String TOPIC_NAME="org.station.arrivals";
        private Logger logger = LoggerFactory.getLogger(TrainArrivalProducer.class);


        public void sendTrainArrivalDataToKafkaCluster(TrainArrival trainArrival){
                ListenableFuture<SendResult<Integer, TrainArrival>> future = kafkaTemplate.send(new ProducerRecord<>(TOPIC_NAME, trainArrival.getTrainId(), trainArrival));

                future.addCallback(new ListenableFutureCallback<SendResult<Integer, TrainArrival>>() {
                        @Override
                        public void onFailure(Throwable throwable) {
                                logger.error("Error sending message : "+throwable.getMessage());
                        }

                        @Override
                        public void onSuccess(SendResult<Integer, TrainArrival> result) {
                                logger.info("Message Sent SuccessFully for the key : {} and the value is {} , partition is {}", trainArrival.getTrainId(),trainArrival, result.getRecordMetadata().partition());
                        }
                });
        }
}
