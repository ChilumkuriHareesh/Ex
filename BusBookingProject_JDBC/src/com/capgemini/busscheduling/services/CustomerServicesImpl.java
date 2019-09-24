package com.capgemini.busscheduling.services;

import java.util.Date;
import java.util.List;

import com.capgemini.busscheduling.beans.Availability;
import com.capgemini.busscheduling.beans.Customer;
import com.capgemini.busscheduling.beans.Feedback;
import com.capgemini.busscheduling.beans.Ticket;
import com.capgemini.busscheduling.dao.BusBookingDAO;
import com.capgemini.busscheduling.dao.BusBookingDAOImpl;
import com.capgemini.busscheduling.dao.CustomerDAO;
import com.capgemini.busscheduling.dao.CustomerDAOImpl;

public class CustomerServicesImpl implements CustomerService{

	CustomerDAO dao = new CustomerDAOImpl();
	BusBookingDAO bdao = new BusBookingDAOImpl();
	@Override
	public Customer registerCustomer(Customer customer) {
		return dao.registerCustomer(customer);
	}

	@Override
	public Customer loginCustomer(Integer customerId, String password) {
		return dao.loginCustomer(customerId, password);
	}

	@Override
	public Boolean updateCustomer(Customer customer) {
		return dao.updateCustomer(customer);
	}

	@Override
	public Boolean deleteCustomer(Integer customerId) {
		return dao.deleteCustomer(customerId);
	}

	@Override
	public Ticket bookTicket(Ticket ticket) {
		return dao.bookTicket(ticket);
	}

	@Override
	public Boolean cancelTicket(Integer bookingId, Integer customerId) {
		return dao.cancelTicket(bookingId, customerId);
	}

	@Override
	public Ticket getTicketInfo(Integer bookingId) {
		return dao.getTicketInfo(bookingId);
	}
	
	@Override
	public List<Availability> checkAvailability(String source, String destination, Date date) {
		return bdao.checkAvailability(source, destination, date);
	}

	@Override
	public Boolean writeFeedback(Feedback feed) {
		return dao.writeFeedback(feed);
	}

	@Override
	public Integer checkAvailability(Integer busId, Date date) {
		return bdao.checkAvailability(busId, date);
	}

}
