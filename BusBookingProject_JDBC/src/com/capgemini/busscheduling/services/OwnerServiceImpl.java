package com.capgemini.busscheduling.services;

import java.util.Date;
import java.util.List;

import com.capgemini.busscheduling.beans.Availability;
import com.capgemini.busscheduling.beans.Bus;
import com.capgemini.busscheduling.beans.Owner;
import com.capgemini.busscheduling.beans.Ticket;
import com.capgemini.busscheduling.dao.OwnerDAO;
import com.capgemini.busscheduling.dao.OwnerDAOImpl;

public class OwnerServiceImpl implements OwnerService{

	OwnerDAO dao = new OwnerDAOImpl();
	
	@Override
	public Owner loginOwner(Integer ownerId, String password) {
		return dao.loginOwner(ownerId, password);
	}

	@Override
	public Bus addBus(Bus bus) {
		return dao.addBus(bus);
	}

	@Override
	public Boolean updateBus(Bus bus) {
		return dao.updateBus(bus);
	}

	@Override
	public Boolean deleteBus(Integer busId) {
		return dao.deleteBus(busId);
	}

	@Override
	public List<Integer> getAllTicket(Integer customerId) {
		return dao.getAllTicket(customerId);
	}

	@Override
	public Boolean setBusAvailability(Availability availability) {
		return dao.setBusAvailability(availability);
	}

	@Override
	public List<Ticket> getTicketByBus(Integer busId, Date date) {
		return dao.getTicketByBus(busId, date);
	}
	
	

}
