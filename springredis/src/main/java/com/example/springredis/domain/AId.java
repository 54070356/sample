package com.example.springredis.domain;

import java.io.Serializable;

public class AId implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long subId1;
	public long getSubId1() {
		return subId1;
	}
	public void setSubId1(long subId1) {
		this.subId1 = subId1;
	}
	public String getSubId2() {
		return subId2;
	}
	public void setSubId2(String subId2) {
		this.subId2 = subId2;
	}
	private String subId2;

}
