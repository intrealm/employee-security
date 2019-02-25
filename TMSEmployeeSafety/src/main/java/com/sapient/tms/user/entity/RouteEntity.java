package com.sapient.tms.user.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sapient.tms.enums.ShiftEnum;

import javax.persistence.Enumerated;

import java.sql.Timestamp;

import javax.persistence.Column;
@Entity
@Table(name = "route_entity")
public class RouteEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private int routeNumber;

	private String vehicleNumber;

	private boolean isStarted;

	private boolean isCompleted;
	
	private long delayedBy;
	
	public Long getEtaInMinutes() {
		return etaInMinutes;
	}

	public void setEtaInMinutes(Long etaInMinutes) {
		this.etaInMinutes = etaInMinutes;
	}

	private Timestamp startTime;
	
	private Long etaInMinutes;

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public ShiftEnum getShift() {
		return shift;
	}

	public void setShift(ShiftEnum shift) {
		this.shift = shift;
	}

	public int getId() {
		return id;
	}

	@Enumerated(EnumType.STRING)
	private ShiftEnum shift;
	 
	public int getRouteNumber() {
		return routeNumber;
	}

	public void setRouteNumber(int routeNumber) {
		this.routeNumber = routeNumber;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public boolean isStarted() {
		return isStarted;
	}

	public void setStarted(boolean isStarted) {
		this.isStarted = isStarted;
	}

	public boolean isCompleted() {
		return isCompleted;
	}

	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	public long getDelayedBy() {
		return delayedBy;
	}

	public void setDelayedBy(long delayedBy) {
		this.delayedBy = delayedBy;
	}
	
}
