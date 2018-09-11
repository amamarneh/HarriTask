package com.amarnehsoft.harritask.model;

public class Weather {
    private long date; //in milliseconds
    private float maxTemp;
    private float minTemp;
    private float pressure;
    private int humidity;


    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public float getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(float maxTemp) {
        this.maxTemp = maxTemp;
    }

    public float getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(float minTemp) {
        this.minTemp = minTemp;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }
}
