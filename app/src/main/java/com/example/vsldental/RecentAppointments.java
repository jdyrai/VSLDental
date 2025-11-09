package com.example.vsldental;

public class RecentAppointments {
    private String day;
    private String month;
    private String serviceName;
    private String time;
    private String status;

    public RecentAppointments(String day, String month, String serviceName, String time, String status) {
        this.day = day;
        this.month = month;
        this.serviceName = serviceName;
        this.time = time;
        this.status = status;
    }

    public String getDay() { return day; }
    public String getMonth() { return month; }
    public String getServiceName() { return serviceName; }
    public String getTime() { return time; }
    public String getStatus() { return status; }

}
