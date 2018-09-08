package com.example.springredis.domain;

public class Address {
	
	public Address(String addr1, int addr2) {
		this.addr1 = addr1;
		this.addr2 = addr2;
	}
	
	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public int getAddr2() {
		return addr2;
	}

	public void setAddr2(int addr2) {
		this.addr2 = addr2;
	}

	private String addr1;
	
	private int addr2;
}
