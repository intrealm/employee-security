package com.sapient.tms.user.entity;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sos_entity")
public class SOSEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String userName;
	private int routeNumber;
	private Date raisedOn;
	private Time raisedAt;
	private Date resolvedOn;
	private Time resolvedAt;
	private boolean resolved;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getRouteNumber() {
		return routeNumber;
	}
	public Date getRaisedOn() {
		return raisedOn;
	}
	public void setRaisedOn(Date raisedOn) {
		this.raisedOn = raisedOn;
	}
	public Date getResolvedOn() {
		return resolvedOn;
	}
	public void setResolvedOn(Date resolvedOn) {
		this.resolvedOn = resolvedOn;
	}
	public void setRouteNumber(int routeNumber) {
		this.routeNumber = routeNumber;
	}
	public Time getRaisedAt() {
		return raisedAt;
	}
	public void setRaisedAt(Time raisedAt) {
		this.raisedAt = raisedAt;
	}
	public Time getResolvedAt() {
		return resolvedAt;
	}
	public void setResolvedAt(Time resolvedAt) {
		this.resolvedAt = resolvedAt;
	}
	public boolean isResolved() {
		return resolved;
	}
	public void setResolved(boolean resolved) {
		this.resolved = resolved;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
