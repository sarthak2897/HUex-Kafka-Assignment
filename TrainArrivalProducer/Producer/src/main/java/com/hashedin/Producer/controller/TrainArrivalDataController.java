package com.hashedin.Producer.controller;

import com.hashedin.Producer.domain.TrainArrival;
import com.hashedin.Producer.producer.TrainArrivalProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Component
public class TrainArrivalDataController {

    @Autowired
    public TrainArrivalProducer trainArrivalProducer;

    @Scheduled(cron = "0 */2 * * * ?")
    public void sendTrainArrivalData(){
        TrainArrival trainArrival = new TrainArrival();
        trainArrival.setStationId(40020);
        trainArrival.setTrainId(101);
        trainArrival.setDirection('W');
        trainArrival.setLine("green");
        trainArrival.setTrainStatus('A');
        trainArrival.setPrevStationId(41350);
        trainArrival.setPrevDirection('S');
        trainArrivalProducer.sendTrainArrivalDataToKafkaCluster(trainArrival);
    }

}
