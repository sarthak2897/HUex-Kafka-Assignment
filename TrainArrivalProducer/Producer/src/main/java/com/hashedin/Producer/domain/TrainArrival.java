package com.hashedin.Producer.domain;

public class TrainArrival {

    private int stationId;
    private int trainId;
    private Character direction;
    private String line;
    private Character trainStatus;
    private int prevStationId;
    private Character prevDirection;

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public Character getDirection() {
        return direction;
    }

    public void setDirection(Character direction) {
        this.direction = direction;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public Character getTrainStatus() {
        return trainStatus;
    }

    public void setTrainStatus(Character trainStatus) {
        this.trainStatus = trainStatus;
    }

    public int getPrevStationId() {
        return prevStationId;
    }

    public void setPrevStationId(int prevStationId) {
        this.prevStationId = prevStationId;
    }

    public Character getPrevDirection() {
        return prevDirection;
    }

    public void setPrevDirection(Character prevDirection) {
        this.prevDirection = prevDirection;
    }
}
