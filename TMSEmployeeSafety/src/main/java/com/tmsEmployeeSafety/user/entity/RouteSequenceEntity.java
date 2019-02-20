package com.tmsEmployeeSafety.user.entity;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.util.List;

import javax.persistence.*;
@Entity
@Table(name = "route_sequence_entity")
public class RouteSequenceEntity {

	
	private int sequenceNumber;

	private String dropLocation;
	
	private boolean boarded;
	
	private boolean deboarded;
    @Id
	private String userName;
	
	private String oracleId;
	
	private int routeId;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getRouteId() {
		return routeId;
	}

	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}

	public int getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public String getDropLocation() {
		return dropLocation;
	}

	public void setDropLocation(String dropLocation) {
		this.dropLocation = dropLocation;
	}

	public boolean isBoarded() {
		return boarded;
	}

	public void setBoarded(boolean boarded) {
		this.boarded = boarded;
	}

	public boolean isDeboarded() {
		return deboarded;
	}

	public void setDeboarded(boolean deboarded) {
		this.deboarded = deboarded;
	}

	public String getOracleId() {
		return oracleId;
	}

	public void setOracleId(String oracleId) {
		this.oracleId = oracleId;
	}
 
	

}
