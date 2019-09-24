package com.capgemini.busscheduling.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.capgemini.busscheduling.beans.Admin;
import com.capgemini.busscheduling.beans.Availability;
import com.capgemini.busscheduling.beans.Bus;
import com.capgemini.busscheduling.beans.Customer;
import com.capgemini.busscheduling.beans.Feedback;
import com.capgemini.busscheduling.beans.Ticket;
import com.capgemini.busscheduling.common.BusBookingSystem;

public class BusBookingDAOImpl implements BusBookingDAO{

	public BusBookingDAOImpl() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	@Override
	public Bus searchBus(Integer busId) {
		// return Bus detail
		String query = "SELECT  * FROM bus_info where bus_id=" + busId;
		Bus bus = null;

		try (Connection conn = DriverManager.getConnection(BusBookingSystem.DB_URL,BusBookingSystem.DB_USER, BusBookingSystem.DB_PASSWORD);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {

			if (rs.next()) {
				bus = new Bus();
				bus.setBusId(rs.getInt("bus_id"));
				bus.setBusName(rs.getString("bus_name"));
				bus.setSource(rs.getString("source"));
				bus.setDestination(rs.getString("destination"));
				bus.setBusType(rs.getString("bus_type"));
				bus.setTotalSeats(rs.getInt("total_seats"));
				bus.setPrice(rs.getDouble("price"));
				return bus;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Availability> checkAvailability(String source, String destination, Date date1) {
		java.sql.Date date=new java.sql.Date(date1.getTime());

		String query = "SELECT bus_id from bus_details where source='" + source + "'" + " and destination='" + destination
				+ "'";
		List<Availability> availList = new ArrayList<Availability>();
		Availability availability = null;
		
		try (Connection conn = DriverManager.getConnection(BusBookingSystem.DB_URL,BusBookingSystem.DB_USER, BusBookingSystem.DB_PASSWORD);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				availability = new Availability();
				availability.setBusId(rs.getInt("bus_id"));
				availability.setAvailSeat(checkAvailability(rs.getInt("bus_id"), date));
				availList.add(availability);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return availList;
	}

	@Override
	public List<Feedback> viewFeedback() {
		String query = "select * from suggestion";
		List<Feedback> feedList = new ArrayList<>();
		Feedback feed = null;
		
		try (Connection conn = DriverManager.getConnection(BusBookingSystem.DB_URL,BusBookingSystem.DB_USER, BusBookingSystem.DB_PASSWORD);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);) {
			while (rs.next()) {
				feed = new Feedback();
				feed.setCustomerId(rs.getInt("customer_id"));
				feed.setFeedback(rs.getString("feedback"));

				feedList.add(feed);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return feedList;
	}

	@Override
	public Integer checkAvailability(Integer busId, Date tempDate) {
		java.sql.Date date=new java.sql.Date(tempDate.getTime());

		String query = "SELECT avail_seats  FROM availability where bus_id=" + busId + " and avail_date='" + date + "'";
		int seats = 0;
		
		try (Connection conn = DriverManager.getConnection(BusBookingSystem.DB_URL,BusBookingSystem.DB_USER, BusBookingSystem.DB_PASSWORD);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			if (rs.next()) {
				seats = rs.getInt("avail_seats");
				return seats;
			} else {
				return seats;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return seats;
	}

	}

