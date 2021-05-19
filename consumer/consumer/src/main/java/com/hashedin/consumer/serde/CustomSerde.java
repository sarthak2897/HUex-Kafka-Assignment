package com.hashedin.consumer.serde;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

class CustomSerde<T> implements Serde<T> {

    private JsonSerializer<T> serializer;
    private JsonDeserializer<T> deserializer;

    public CustomSerde(JsonSerializer<T> serializer,JsonDeserializer<T> deserializer){
        super();
        this.serializer = serializer;
        this.deserializer = deserializer;
    }

    @Override
    public Serializer serializer() {
        return serializer;
    }

    @Override
    public Deserializer deserializer() {
        return deserializer;
    }
}
