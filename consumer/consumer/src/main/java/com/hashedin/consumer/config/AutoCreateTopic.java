package com.hashedin.consumer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

//@Configuration
public class AutoCreateTopic {

    //final public String TOPIC_NAME = "org.station.arrivals";
    //@Bean
    public NewTopic createTopic(){
        return TopicBuilder.name(AppConstants.STATION_TRANSFORMED_DATA_TOPIC).partitions(3).replicas(3).build();
    }
}
