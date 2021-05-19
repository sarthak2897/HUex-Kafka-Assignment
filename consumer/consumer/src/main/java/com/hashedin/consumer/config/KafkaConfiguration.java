package com.hashedin.consumer.config;

import com.hashedin.consumer.serde.JsonSerializer;
import com.hashedin.consumer.serde.StationSerde;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaStreamsConfiguration;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import javax.management.MXBean;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
//@EnableKafkaStreams
public class KafkaConfiguration {

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,AppConstants.BOOTSTRAP_SERVERS);
        props.put(ConsumerConfig.GROUP_ID_CONFIG,AppConstants.CONSUMER_GROUP);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(props));

        return factory;
    }

//    @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
//    //@Bean("stationStreamBuilder")
//    public KafkaStreamsConfiguration stationStreamBuilder(){
//                 Map<String, Object> props = new HashMap<>();
//        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "stationEvents");
//        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,AppConstants.BOOTSTRAP_SERVERS);
//        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.Integer().getClass());
//        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, StationSerde.class);
//        return new StreamsConfig()
//        return new KafkaStreamsConfiguration(props);
//    }
//
//    @Bean(name = "turnstileStreamBuilder")
//    @Primary
//    public KafkaStreamsConfiguration turnstileStreamBuilder(){
//        Map<String, Object> props = new HashMap<>();
//        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "turnstileEvents");
//        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,AppConstants.BOOTSTRAP_SERVERS);
//        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.Integer().getClass());
//        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
//        return new KafkaStreamsConfiguration(props);
//    }

    @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
    public StreamsConfig kStreamsConfigs() {
        Map<String, Object> config = new HashMap<>();
        config.put(StreamsConfig.APPLICATION_ID_CONFIG, "default");
        config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,AppConstants.BOOTSTRAP_SERVERS);
        return new StreamsConfig(config);
    }

    @Bean("stationStreamBuilder")
    public StreamsBuilderFactoryBean stationStreamBuilderFactoryBean() {
                         Map<String, Object> props = new HashMap<>();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "stationEvents");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,AppConstants.BOOTSTRAP_SERVERS);
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.Integer().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, StationSerde.class);
        return new StreamsBuilderFactoryBean(new KafkaStreamsConfiguration(props));
    }

    @Bean("turnstileStreamBuilder")
    public StreamsBuilderFactoryBean turnstileStreamBuilderFactoryBean(){
                Map<String, Object> props = new HashMap<>();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "turnstileEvents");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,AppConstants.BOOTSTRAP_SERVERS);
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.Integer().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        return new StreamsBuilderFactoryBean(new KafkaStreamsConfiguration(props));
    }




}
