package com.sapient.tms.user.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "escalation_entity")
public class EscalationMatrixEntity {
	@Id
	private String userName;
	private String phoneNumber;
	private String  escalationPhoneNumbers;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEscalationPhoneNumbers() {
		return escalationPhoneNumbers;
	}
	public void setEscalationPhoneNumbers(String escalationPhoneNumbers) {
		this.escalationPhoneNumbers = escalationPhoneNumbers;
	}
  
	
}
