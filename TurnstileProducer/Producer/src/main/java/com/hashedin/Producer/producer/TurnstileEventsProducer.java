package com.hashedin.Producer.producer;


import com.hashedin.Producer.domain.Turnstile;
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
public class TurnstileEventsProducer {

    @Autowired
    private KafkaTemplate<Integer,Turnstile> kafkaTemplate;
    String TOPIC_NAME="org.station.turnstiles";
    private static Logger logger = LoggerFactory.getLogger(TurnstileEventsProducer.class);

    public void sendTurnstileEventsToKafkaCluster(Turnstile turnstile){
        ListenableFuture<SendResult<Integer, Turnstile>> future = kafkaTemplate.send(new ProducerRecord<>(TOPIC_NAME, turnstile.getStationId(), turnstile));

        future.addCallback(new ListenableFutureCallback<SendResult<Integer, Turnstile>>() {
            @Override
            public void onFailure(Throwable throwable) {
                logger.error("Error sending message",throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<Integer, Turnstile> result) {
                logger.info("Message Sent SuccessFully for the key : {} and the value is {} , partition is {}", turnstile.getStationId(),turnstile, result.getRecordMetadata().partition());
            }
        });
    }
}
