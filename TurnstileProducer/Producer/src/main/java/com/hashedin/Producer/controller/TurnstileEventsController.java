package com.hashedin.Producer.controller;

import com.hashedin.Producer.domain.Turnstile;
import com.hashedin.Producer.producer.TurnstileEventsProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TurnstileEventsController {

    @Autowired
    TurnstileEventsProducer turnstileEventsProducer;

    @Scheduled(cron = "0 */1 * * * ?")
    public void sendTurnstileEventData(){
        Turnstile turnstile = new Turnstile();
        turnstile.setStationId(40610);
        turnstile.setStationName("Ridgeland (Green Line)");
        turnstile.setLine("green");
        turnstileEventsProducer.sendTurnstileEventsToKafkaCluster(turnstile);
    }
}
