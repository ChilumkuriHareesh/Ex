package com.capgemini.busscheduling.dao;

import java.util.Date;
import java.util.List;

import com.capgemini.busscheduling.beans.Admin;
import com.capgemini.busscheduling.beans.Availability;
import com.capgemini.busscheduling.beans.Bus;
import com.capgemini.busscheduling.beans.Customer;
import com.capgemini.busscheduling.beans.Feedback;
import com.capgemini.busscheduling.beans.Ticket;

public interface BusBookingDAO {

	public Bus searchBus(Integer busId);
	public Integer checkAvailability(Integer busId, Date date);
	public List<Availability>checkAvailability(String source, String destination, Date date);
	public List<Feedback> viewFeedback();



}