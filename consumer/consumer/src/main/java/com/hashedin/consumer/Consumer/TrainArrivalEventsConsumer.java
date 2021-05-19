package com.hashedin.consumer.Consumer;

import com.hashedin.consumer.config.AppConstants;
import com.hashedin.consumer.domain.*;
import com.hashedin.consumer.serde.TransformedStationSerde;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.function.Consumer;
import java.util.function.Function;

@Component
@Configuration
public class TrainArrivalEventsConsumer {

    private static Logger logger = LoggerFactory.getLogger(TrainArrivalEventsConsumer.class);

    @KafkaListener(topics = AppConstants.TRAIN_ARRIVAL_TOPIC,groupId = AppConstants.CONSUMER_GROUP)
    public void consumeTrainArrivalEvents(ConsumerRecord<Integer, TrainArrival> consumerRecord){
        logger.info("Consumed train events successfully "+consumerRecord.value());
    }

    @KafkaListener(topics = AppConstants.WEATHER_TOPIC,groupId = AppConstants.CONSUMER_GROUP)
    public void consumeWeatherEvents(ConsumerRecord<Integer, Weather> consumerRecord){
        logger.info("Consumed weather events successfully "+consumerRecord.value());
    }

    @Bean
    public KStream<Integer,Station> consumeStationDataEvents(@Qualifier("stationStreamBuilder") StreamsBuilder stationStreamBuilder){
        KTable<Integer, Station> stationRecords = stationStreamBuilder.table(AppConstants.STATION_DATA_TOPIC);

        stationRecords.toStream().print(Printed.<Integer,Station>toSysOut().withLabel("Station Records"));
        stationRecords.toStream().mapValues(station -> {
            TransformedStation stn = new TransformedStation();
            stn.setStationId(station.getStationId());
            stn.setStationName(station.getStationName());
            stn.setOrder(station.getOrder());
            if(station.isRed())
                stn.setLine("red");
            else if(station.isBlue())
                stn.setLine("blue");
            else
                stn.setLine("green");
            return stn;
        }).to(AppConstants.STATION_TRANSFORMED_DATA_TOPIC);
        logger.info("Printing transformed table",Produced.with(Serdes.Integer(),new TransformedStationSerde()));
        KTable<Integer, TransformedStation> transformedStationTable = stationStreamBuilder.table(AppConstants.STATION_TRANSFORMED_DATA_TOPIC,Consumed.with(Serdes.Integer(),new TransformedStationSerde()));
        transformedStationTable.toStream().print(Printed.<Integer,TransformedStation>toSysOut().withLabel("Transformed Station Records"));
        return stationRecords.toStream();
    }
    @Bean
    public KStream<Integer,Turnstile> consumeTurnstileEvents(@Qualifier("turnstileStreamBuilder") StreamsBuilder turnstileStreamBuilder){
        KTable<Integer, Turnstile> turnstiles = turnstileStreamBuilder.table(AppConstants.TURNSTILE_TOPIC);
        turnstiles.toStream().print(Printed.<Integer,Turnstile>toSysOut().withLabel("turnstiles"));
        KTable<Integer, Long> turnstilesSummary = turnstiles.toStream().groupByKey().count();
        turnstilesSummary.toStream().print(Printed.<Integer,Long>toSysOut().withLabel("turnstilesSummary"));
        return turnstiles.toStream();
    }



}
