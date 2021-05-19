package com.hashedin.Producer.producer;


import com.hashedin.Producer.domain.Station;
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
public class StationDataProducer {

    String TOPIC_NAME = "org.station.stations";
    @Autowired
    private KafkaTemplate<Integer,Station> kafkaTemplate;

    private static Logger logger = LoggerFactory.getLogger(StationDataProducer.class);

    public void sendCSVDataToKafkaCluster(Station station){
        ListenableFuture<SendResult<Integer, Station>> future = kafkaTemplate.send(new ProducerRecord<>(TOPIC_NAME, station.getStationId(), station));

        future.addCallback(new ListenableFutureCallback<SendResult<Integer, Station>>() {
            @Override
            public void onFailure(Throwable throwable) {
                logger.error("Error sending message : "+throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<Integer,Station> result) {
                logger.info("Message Sent SuccessFully for the key : {} and the value is {} , partition is {}", station.getStationId(),station, result.getRecordMetadata().partition());
            }
        });
    }

}
