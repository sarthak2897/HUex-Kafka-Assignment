package com.hashedin.Producer.controller;

import com.hashedin.Producer.domain.Station;
import com.hashedin.Producer.producer.StationDataProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class StationDataController {

    @Autowired
    StationDataProducer stationDataProducer;

    @Scheduled(cron = "0 */10 * * * ?")
    public void sendCSVData() throws IOException {

        File file = new File("C:\\Users\\sartnagpal\\Downloads\\StationDataProducer\\Producer\\src\\main\\resources\\stations.csv");
        BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String row = bufferedReader.readLine();
        while((row = bufferedReader.readLine()) != null){
            String[] rowData = row.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
            Station station = new Station();
            station.setStopId(Integer.parseInt(rowData[0]));
            station.setDirectionId(rowData[1].charAt(0));
            station.setStopName(rowData[2]);
            station.setStationName(rowData[3]);
            station.setStationDescription(rowData[4]);
            station.setStationId(Integer.parseInt(rowData[5]));
            station.setOrder(Integer.parseInt(rowData[6]));
            station.setRed(Boolean.parseBoolean(rowData[7]));
            station.setBlue(Boolean.parseBoolean(rowData[8]));
            station.setGreen(Boolean.parseBoolean(rowData[9]));
            stationDataProducer.sendCSVDataToKafkaCluster(station);
        }
    }
}
