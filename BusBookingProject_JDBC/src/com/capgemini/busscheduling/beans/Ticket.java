package com.capgemini.busscheduling.beans;

import java.sql.Timestamp;
import java.util.Date;

public class Ticket {

	private Integer bookingId;
	private Integer busId;
	private Integer userId;
	private Date journeyDate;
	private Integer numOfSeats;
	private Timestamp bookingDateTime;

	public Integer getBookingId() {
		return bookingId;
	}
	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}
	public Integer getBusId() {
		return busId;
	}
	public void setBusId(Integer busId) {
		this.busId = busId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Date getJourneyDate() {
		return journeyDate;
	}
	public void setJourneyDate(Date journeyDate) {
		this.journeyDate = journeyDate;
	}
	public Integer getNumOfSeats() {
		return numOfSeats;
	}
	public void setNumOfSeats(Integer numOfSeats) {
		this.numOfSeats = numOfSeats;
	}
	public Timestamp getBookingDateTime() {
		return bookingDateTime;
	}
	public void setBookingDateTime(Timestamp bookingDateTime) {
		this.bookingDateTime = bookingDateTime;
	}
	@Override
	public String toString() {
		return "Ticket [bookingId=" + bookingId + ", busId=" + busId + ", userId=" + userId + ", journeyDate="
				+ journeyDate + ", numOfSeats=" + numOfSeats + ", bookingDateTime=" + bookingDateTime + "]";
	}
}
