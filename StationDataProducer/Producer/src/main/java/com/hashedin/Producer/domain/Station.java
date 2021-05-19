package com.hashedin.Producer.domain;

public class Station {

    private int stopId;
    private Character directionId;
    private String stopName;
    private String stationName;
    private String stationDescription;
    private int stationId;
    private int order;
    private boolean red;
    private boolean blue;
    private boolean green;

    public int getStopId() {
        return stopId;
    }

    public void setStopId(int stopId) {
        this.stopId = stopId;
    }

    public Character getDirectionId() {
        return directionId;
    }

    public void setDirectionId(Character directionId) {
        this.directionId = directionId;
    }

    public String getStopName() {
        return stopName;
    }

    public void setStopName(String stopName) {
        this.stopName = stopName;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getStationDescription() {
        return stationDescription;
    }

    public void setStationDescription(String stationDescription) {
        this.stationDescription = stationDescription;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }


    public boolean isRed() {
        return red;
    }

    public void setRed(boolean red) {
        this.red = red;
    }

    public boolean isBlue() {
        return blue;
    }

    public void setBlue(boolean blue) {
        this.blue = blue;
    }

    public boolean isGreen() {
        return green;
    }

    public void setGreen(boolean green) {
        this.green = green;
    }

    @Override
    public String toString() {
        return "Station{" +
                "stopId=" + stopId +
                ", directionId=" + directionId +
                ", stopName='" + stopName + '\'' +
                ", stationName='" + stationName + '\'' +
                ", stationDescription='" + stationDescription + '\'' +
                ", stationId=" + stationId +
                ", order=" + order +
                ", red=" + red +
                ", blue=" + blue +
                ", green=" + green +
                '}';
    }
}
