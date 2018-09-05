package com.example.springredis;

public class ResponseStat {
	private String msgKey;
	private String channelKey;
	private String respKey;
	private String msgGroupKey;
	public String getMsgGroupKey() {
		return msgGroupKey;
	}
	public void setMsgGroupKey(String msgGroupKey) {
		this.msgGroupKey = msgGroupKey;
	}
	private int days;
	public String getMsgKey() {
		return msgKey;
	}
	public void setMsgKey(String msgKey) {
		this.msgKey = msgKey;
	}
	public String getChannelKey() {
		return channelKey;
	}
	public void setChannelKey(String channelKey) {
		this.channelKey = channelKey;
	}
	public String getRespKey() {
		return respKey;
	}
	public void setRespKey(String respKey) {
		this.respKey = respKey;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	private int count;
}
