package com.tmsEmployeeSafety.user.entity;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.util.List;

import javax.persistence.*;
@Entity
@Table(name = "route_entity")
public class RouteEntity {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private int routeNumber;

	private String vehicleNumber;
	
	private boolean isStarted;
	
	private boolean isCompleted;
 
	/* private ShiftEntity shift; */

	/*
	 * public List<UserEntity> getListOfEmployees() { return listOfEmployees; }
	 * 
	 * public void setListOfEmployees(List<UserEntity> listOfEmployees) {
	 * this.listOfEmployees = listOfEmployees; }
	 */
	
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
	/*
	 * public ShiftEntity getShift() { return shift; }
	 * 
	 * public void setShift(ShiftEntity shift) { this.shift = shift; }
	 */
	
	

}
