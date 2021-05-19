package com.hashedin.Producer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class AutoCreateTopic {

    final public String TOPIC_NAME = "org.station.stations";
    final public String TRANSFORMED_TOPIC_NAME = "org.station.transformedStations";
    @Bean
    public NewTopic createTopic(){
        return TopicBuilder.name(TOPIC_NAME).partitions(3).replicas(3).build();
    }

    @Bean
    public NewTopic createTransformedTopic(){
        return TopicBuilder.name(TRANSFORMED_TOPIC_NAME).partitions(3).replicas(3).build();
    }
}
