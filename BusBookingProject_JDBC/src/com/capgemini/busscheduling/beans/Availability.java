package com.capgemini.busscheduling.beans;

import java.util.Date;

public class Availability {

	private Date availDate;
	private Integer availSeat;
	private Integer busId;

	public Date getAvailDate() {
		return availDate;
	}
	public void setAvailDate(Date availDate) {
		this.availDate = availDate;
	}
	public Integer getAvailSeat() {
		return availSeat;
	}
	public void setAvailSeat(Integer availSeat) {
		this.availSeat = availSeat;
	}
	public Integer getBusId() {
		return busId;
	}
	public void setBusId(Integer busId) {
		this.busId = busId;
	}

	@Override
	public String toString() {
		return "Availability [availDate=" + availDate + ", availSeat=" + availSeat + ", busId="
				+ busId + "]";
	}
}
