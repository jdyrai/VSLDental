package com.example.vsldental;

public class Appointment {

    private String date;
    private String month;
    private String serviceName;
    private String time;
    private boolean isConfirmed;

    public Appointment(String date, String month, String serviceName, String time, boolean isConfirmed) {
        this.date = date;
        this.month = month;
        this.serviceName = serviceName;
        this.time = time;
        this.isConfirmed = isConfirmed;
    }

    public String getDate() {
        return date;
    }

    public String getMonth() {
        return month;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getTime() {
        return time;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }
}
