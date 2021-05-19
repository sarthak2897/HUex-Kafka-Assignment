package com.hashedin.consumer.domain;

public class TransformedStation {

    private int stationId;
    private String stationName;
    private int order;
    private String line;

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    @Override
    public String toString() {
        return "TransformedStation{" +
                "stationId=" + stationId +
                ", stationName='" + stationName + '\'' +
                ", order=" + order +
                ", line='" + line + '\'' +
                '}';
    }
}
