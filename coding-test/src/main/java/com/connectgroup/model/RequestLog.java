package com.connectgroup.model;

import java.util.Date;

public class RequestLog {

	Date requestMade;
	String country;
	Integer requestDuration;

	public Date getRequestMade() {
		return requestMade;
	}

	public void setRequestMade(Date requestMade) {
		this.requestMade = requestMade;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Integer getRequestDuration() {
		return requestDuration;
	}

	public void setRequestDuration(Integer requestDuration) {
		this.requestDuration = requestDuration;
	}

}
