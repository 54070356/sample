package com.example.springredis.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("SimpleEntity")
@TypeAlias("simple")
public class SimpleEntity {
	@Id
	private String id="simpleEntity";
	private String stringField = "stringField";
	private boolean booleanField = true;
	private int intField = 1;
	private double doubleField =1.0d;
	private Double ddoubleField =1.0d;
	public Double getDdoubleField() {
		return ddoubleField;
	}
	public void setDdoubleField(Double ddoubleField) {
		this.ddoubleField = ddoubleField;
	}
	private Date dateField = new Date();
	private Address address = new Address("addr1",2);
	
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getId() {
		return id;
	}
	public Date getDateField() {
		return dateField;
	}
	public void setDateField(Date dateField) {
		this.dateField = dateField;
	}
	public void setId(String compositeId) {
		this.id = compositeId;
	}
	public String getStringField() {
		return stringField;
	}
	public void setStringField(String stringField) {
		this.stringField = stringField;
	}
	public boolean isBooleanField() {
		return booleanField;
	}
	public void setBooleanField(boolean booleanField) {
		this.booleanField = booleanField;
	}
	public int getIntField() {
		return intField;
	}
	public void setIntField(int intField) {
		this.intField = intField;
	}
	public double getDoubleField() {
		return doubleField;
	}
	public void setDoubleField(double doubleField) {
		this.doubleField = doubleField;
	}
	
}
