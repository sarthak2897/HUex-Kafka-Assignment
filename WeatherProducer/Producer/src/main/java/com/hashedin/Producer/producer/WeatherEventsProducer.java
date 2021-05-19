package com.hashedin.Producer.producer;

import com.hashedin.Producer.domain.Weather;
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
public class WeatherEventsProducer {

    @Autowired
    private KafkaTemplate<Integer, Weather> kafkaTemplate;
    String TOPIC_NAME="org.station.weather";
    private static Logger logger = LoggerFactory.getLogger(WeatherEventsProducer.class);

    public void sendTurnstileEventsToKafkaCluster(Weather weather){
        ListenableFuture<SendResult<Integer, Weather>> future = kafkaTemplate.send(new ProducerRecord<>(TOPIC_NAME,null, weather));

        future.addCallback(new ListenableFutureCallback<SendResult<Integer, Weather>>() {
            @Override
            public void onFailure(Throwable throwable) {
                logger.error("Error sending message",throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<Integer, Weather> result) {
                logger.info("Message Sent SuccessFully for the key : {} and the value is {} , partition is {}", null,weather, result.getRecordMetadata().partition());
            }
        });
    }
}
