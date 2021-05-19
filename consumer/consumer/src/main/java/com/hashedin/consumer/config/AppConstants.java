package com.hashedin.consumer.config;

public class AppConstants {

    public static final String TRAIN_ARRIVAL_TOPIC = "org.station.arrivals";
    public static final String TURNSTILE_TOPIC = "org.station.turnstiles";
    public static final String WEATHER_TOPIC = "org.station.weather";
    public static final String STATION_DATA_TOPIC = "org.station.stations";
    public static final String STATION_TRANSFORMED_DATA_TOPIC = "org.station.transformedStations";
    public static final String BOOTSTRAP_SERVERS = "localhost:9092,localhost:9093,localhost:9094";
    public static final String CONSUMER_GROUP = "group_id";
}
