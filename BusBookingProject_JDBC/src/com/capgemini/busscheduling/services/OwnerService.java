package com.capgemini.busscheduling.services;

import java.util.Date;
import java.util.List;

import com.capgemini.busscheduling.beans.Availability;
import com.capgemini.busscheduling.beans.Bus;
import com.capgemini.busscheduling.beans.Owner;
import com.capgemini.busscheduling.beans.Ticket;

public interface OwnerService {

	public Owner loginOwner(Integer ownerId, String password);
	public Bus addBus(Bus bus);
	public Boolean updateBus(Bus bus);
	public Boolean deleteBus(Integer busId);
	public List<Ticket>getTicketByBus(Integer busId,Date date);
	public List<Integer>getAllTicket(Integer userId);
	public Boolean setBusAvailability(Availability availability);
}
