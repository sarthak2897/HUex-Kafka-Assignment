package com.hashedin.consumer.serde;

import com.hashedin.consumer.domain.Station;
import com.hashedin.consumer.domain.TransformedStation;

public class TransformedStationSerde extends CustomSerde<TransformedStation>{



    public TransformedStationSerde() {
        super(new JsonSerializer<TransformedStation>(), new JsonDeserializer<TransformedStation>(TransformedStation.class));
    }


}
