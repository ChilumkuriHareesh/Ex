package com.capgemini.busscheduling.services;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.capgemini.busscheduling.beans.Availability;
import com.capgemini.busscheduling.beans.Bus;
import com.capgemini.busscheduling.beans.Feedback;
import com.capgemini.busscheduling.dao.BusBookingDAO;
import com.capgemini.busscheduling.dao.BusBookingDAOImpl;

public class BusBookingServiceImpl implements BusBookingService {

	BusBookingDAO dao = new BusBookingDAOImpl();

	@Override
	public Bus searchBus(Integer busId) {
		return dao.searchBus(busId);
	}

	@Override
	public Integer checkAvailability(Integer busId, Date date) {
		return dao.checkAvailability(busId, date);
	}

	@Override
	public List<Availability> checkAvailability(String source, String destination, Date date) {
		return dao.checkAvailability(source, destination, date);
	}

	@Override
	public List<Feedback> viewFeedback() {
		return dao.viewFeedback();
	}
	@Override
	public Integer validateId(String id) {

		Pattern pat = Pattern.compile("\\d+");
		Matcher mat = pat.matcher(id);
		if(mat.matches()){
			return Integer.parseInt(id);
		}else{
			return null;
		}
	}

	@Override
	public String validateEmail(String email) {
		Pattern pat = Pattern.compile("\\w+\\@\\w+\\.\\w+");
		Matcher	mat = pat.matcher(email);
		if(mat.matches()){
			return email;
		}else{
			return null;
		}
	}

	@Override
	public Long validateContact(String contact) {
		Pattern pat = Pattern.compile("\\d{10}");
		Matcher mat = pat.matcher(contact);
		if(mat.matches()) {
			return Long.parseLong(contact);
		}else {
			return null;
		}
	}

}