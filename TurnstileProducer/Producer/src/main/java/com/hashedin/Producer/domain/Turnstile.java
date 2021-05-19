package com.hashedin.Producer.domain;

public class Turnstile {
    private int stationId;
    private String stationName;
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

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    @Override
    public String toString() {
        return "Turnstile{" +
                "stationId=" + stationId +
                ", stationName='" + stationName + '\'' +
                ", line='" + line + '\'' +
                '}';
    }
}
