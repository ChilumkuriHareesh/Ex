package com.capgemini.busscheduling.services;

import java.util.Date;
import java.util.List;

import com.capgemini.busscheduling.beans.Availability;
import com.capgemini.busscheduling.beans.Customer;
import com.capgemini.busscheduling.beans.Feedback;
import com.capgemini.busscheduling.beans.Ticket;

public interface CustomerService {

	public Customer registerCustomer( Customer user);
	public Customer loginCustomer(Integer userId, String password);
	public Boolean updateCustomer(Customer user);
	public Boolean deleteCustomer(Integer userId);
	public Ticket bookTicket(Ticket ticket);
	public Boolean cancelTicket(Integer bookingId, Integer userId);
	public Ticket getTicketInfo(Integer bookingId);
	public Integer checkAvailability(Integer busId, Date date);
	public List<Availability>checkAvailability(String source, String destination, Date date);
	public Boolean writeFeedback(Feedback feed);
}
