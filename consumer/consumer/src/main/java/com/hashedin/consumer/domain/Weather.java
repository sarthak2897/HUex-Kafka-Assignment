package com.hashedin.consumer.domain;

public class Weather {

    private Double Temperature;
    private String status;

    public Double getTemperature() {
        return Temperature;
    }

    public void setTemperature(Double temperature) {
        Temperature = temperature;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "Temperature=" + Temperature +
                ", status='" + status + '\'' +
                '}';
    }
}
