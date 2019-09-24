package com.capgemini.busscheduling.dao;

import java.util.Date;
import java.util.List;

import com.capgemini.busscheduling.beans.Availability;
import com.capgemini.busscheduling.beans.Customer;
import com.capgemini.busscheduling.beans.Feedback;
import com.capgemini.busscheduling.beans.Ticket;

public interface CustomerDAO {

	//user operations	
	public Customer registerCustomer( Customer customer);
	public Customer loginCustomer(Integer customerId, String password);
	public Boolean updateCustomer(Customer customer);
	public Boolean deleteCustomer(Integer customerId);
	public Ticket bookTicket(Ticket ticket);
	public Boolean cancelTicket(Integer bookingId, Integer customerId);
	public Ticket getTicketInfo(Integer bookingId);
	public Boolean writeFeedback(Feedback feed);
}
