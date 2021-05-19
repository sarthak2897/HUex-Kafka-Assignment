package com.hashedin.Producer.controller;

import com.hashedin.Producer.domain.Weather;
import com.hashedin.Producer.producer.WeatherEventsProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableKafka
public class WeatherEventsController {

    @Autowired
    WeatherEventsProducer weatherEventsProducer;

    @PostMapping("/kafka")
    public ResponseEntity<Weather> sendWeatherData(@RequestParam String temperature){
        Weather weather = new Weather();
        weather.setTemperature(Double.parseDouble(temperature));
        if(weather.getTemperature() >= 30)
            weather.setStatus("summer");
        else if(weather.getTemperature() >= 15 && weather.getTemperature() < 30)
            weather.setStatus("spring");
        else
            weather.setStatus("winter");
        weatherEventsProducer.sendTurnstileEventsToKafkaCluster(weather);
        return new ResponseEntity<>(weather,HttpStatus.CREATED);
    }
}
