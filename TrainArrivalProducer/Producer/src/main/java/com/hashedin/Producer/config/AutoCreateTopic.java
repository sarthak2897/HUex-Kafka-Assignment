package com.hashedin.Producer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class AutoCreateTopic {

    final public String TOPIC_NAME = "org.station.arrivals";
    @Bean
    public NewTopic createTopic(){
        return TopicBuilder.name(TOPIC_NAME).partitions(3).replicas(3).build();
    }
}
