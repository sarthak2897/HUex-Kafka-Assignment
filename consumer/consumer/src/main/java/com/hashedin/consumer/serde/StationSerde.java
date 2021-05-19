package com.hashedin.consumer.serde;

import com.hashedin.consumer.domain.Station;

public class StationSerde extends CustomSerde<Station>{



    public StationSerde() {
        super(new JsonSerializer<Station>(), new JsonDeserializer<Station>(Station.class));
    }
}
