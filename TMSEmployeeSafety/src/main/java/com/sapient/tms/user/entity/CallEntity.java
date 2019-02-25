package com.sapient.tms.user.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.sapient.tms.enums.CallTypeEnum;
@Entity
@Table(name = "call_entity")
public class CallEntity {
	@Id
	private String sid;
	private String userName;
	private int routeId;
	private Timestamp callTime;
	@Enumerated(EnumType.STRING)
	private CallTypeEnum callType;
    public Timestamp getCallTime() {
		return callTime;
	}

	public CallTypeEnum getCallType() {
		return callType;
	}

	public void setCallType(CallTypeEnum callType) {
		this.callType = callType;
	}

	public void setCallTime(Timestamp callTime) {
		this.callTime = callTime;
	}

	public int getRouteId() {
		return routeId;
	}

	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	private String status;
	private String mobileNumber;
	
	public String getSid() {
		return sid;
	}

	public void setSid(String string) {
		this.sid = string;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	
}
