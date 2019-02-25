package com.publicissapient.psemployeesecurity;

public class RouteData  implements Comparable{

    private String routeNumber;

    private String delayedBy;

    private String vehicleNumber;

    private String eta;

    private String startTime;

    private String started;

    private String completed;


    public String getRouteNumber() {
        return routeNumber;
    }

    public void setRouteNumber(String routeNumber) {
        this.routeNumber = routeNumber;
    }

    public String getDelayedBy() {
        return delayedBy;
    }

    public void setDelayedBy(String delayedBy) {
        this.delayedBy = delayedBy;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStarted() {
        return started;
    }

    public void setStarted(String started) {
        this.started = started;
    }

    public String getCompleted() {
        return completed;
    }

    public void setCompleted(String completed) {
        this.completed = completed;
    }


    @Override
    public int compareTo(Object o) {
        RouteData other = (RouteData)o;
        return ((RouteData) o).getDelayedBy().compareTo(this.getDelayedBy());

    }
}
