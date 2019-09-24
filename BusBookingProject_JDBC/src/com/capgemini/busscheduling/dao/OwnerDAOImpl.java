package com.capgemini.busscheduling.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.capgemini.busscheduling.beans.Admin;
import com.capgemini.busscheduling.beans.Availability;
import com.capgemini.busscheduling.beans.Bus;
import com.capgemini.busscheduling.beans.Customer;
import com.capgemini.busscheduling.beans.Owner;
import com.capgemini.busscheduling.beans.Ticket;
import com.capgemini.busscheduling.common.BusBookingSystem;

public class OwnerDAOImpl implements OwnerDAO {

	BusBookingDAO bookingDAO = new BusBookingDAOImpl();

	public OwnerDAOImpl() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Owner loginOwner(Integer ownerId, String password) {
		String query = "SELECT * FROM owner_details where owner_id=" + ownerId + " and owner_password='" + password + "'";
		Owner owner=null;

		try (Connection connection = DriverManager.getConnection(BusBookingSystem.DB_URL,BusBookingSystem.DB_USER, BusBookingSystem.DB_PASSWORD);
				Statement statement = connection.createStatement();
				ResultSet resultset = statement.executeQuery(query)) {
			if (resultset.next()) {
				owner=new Owner();
				owner.setOwnerId(resultset.getInt("owner_id"));
				owner.setOwnerPassword(resultset.getString("owner_password"));
				owner.setOwnerName(resultset.getString("owner_name"));
				owner.setOwnerEmail(resultset.getString("owner_email"));
				owner.setOwnerContact(resultset.getLong("owner_contact"));
				return owner;
			} else {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return owner;
	}


	@Override
	public Bus addBus(Bus bus) {

		String query = "INSERT INTO bus_details VALUES (?,?,?,?,?,?,?)";
		Bus tempbus = bookingDAO.searchBus(bus.getBusId());

		try (Connection connection = DriverManager.getConnection(BusBookingSystem.DB_URL,BusBookingSystem.DB_USER, BusBookingSystem.DB_PASSWORD);
				PreparedStatement  pstmt = connection.prepareStatement(query);) {
			if (tempbus != null) {
				return null;
			} else {
				pstmt.setInt(1, bus.getBusId());
				pstmt.setString(2, bus.getBusName());
				pstmt.setString(3, bus.getSource());
				pstmt.setString(4, bus.getDestination());
				pstmt.setString(5, bus.getBusType());
				pstmt.setInt(6, bus.getTotalSeats());
				pstmt.setDouble(7, bus.getPrice());

				pstmt.executeUpdate();	
				return bus;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Boolean updateBus(Bus bus) {
		// Insert user into user_details table if user is not there
		String query = "UPDATE bus_details SET bus_name=?,source=?,destination=?,"
				+ "bus_type=?,total_seats=?,price=? WHERE bus_id=?";
		Bus tempbus = bookingDAO.searchBus(bus.getBusId());

		try (Connection connection = DriverManager.getConnection(BusBookingSystem.DB_URL,BusBookingSystem.DB_USER, BusBookingSystem.DB_PASSWORD);
				PreparedStatement pstmt = connection.prepareStatement(query);) {
			if (tempbus != null) {
				pstmt.setString(1, bus.getBusName());
				pstmt.setString(2, bus.getSource());
				pstmt.setString(3, bus.getDestination());
				pstmt.setString(4, bus.getBusType());
				pstmt.setInt(5, bus.getTotalSeats());
				pstmt.setDouble(6, bus.getPrice());
				pstmt.setInt(7, bus.getBusId());

				pstmt.executeUpdate();
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean deleteBus(Integer busId) {
		// delete bus if already exists
		String query = "DELETE FROM bus_details WHERE bus_id=?";
		Bus bus = bookingDAO.searchBus(busId);

		try (Connection connection = DriverManager.getConnection(BusBookingSystem.DB_URL,BusBookingSystem.DB_USER, BusBookingSystem.DB_PASSWORD);
				PreparedStatement pstmt = connection.prepareStatement(query);) {
			if (bus != null) {
				pstmt.setInt(1, busId);
				pstmt.executeUpdate();
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public List<Integer> getAllTicket(Integer customerId) {
		String query = "SELECT  * FROM booking_details where customer_id=" + customerId;
		List<Integer> ticketAl = new ArrayList<>();

		try (Connection connection = DriverManager.getConnection(BusBookingSystem.DB_URL,BusBookingSystem.DB_USER, BusBookingSystem.DB_PASSWORD);
				Statement statement = connection.createStatement();
				ResultSet resultset = statement.executeQuery(query)) {
			while (resultset.next()) {
				ticketAl.add(resultset.getInt("booking_id"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ticketAl;
	}
	@Override
	public Boolean setBusAvailability(Availability availability) {
		java.sql.Date date=new java.sql.Date(availability.getAvailDate().getTime());

		String query = "INSERT INTO availability (avail_date,avail_seats,bus_id) VALUES (?,?,?)";


		try (Connection connection = DriverManager.getConnection(BusBookingSystem.DB_URL,BusBookingSystem.DB_USER, BusBookingSystem.DB_PASSWORD);
				PreparedStatement pstmt = connection.prepareStatement(query)) {
			pstmt.setDate(1, date);
			pstmt.setInt(2, availability.getAvailSeat());
			pstmt.setInt(3, availability.getBusId());
			int res = pstmt.executeUpdate();
			if (res > -1)
				return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public List<Ticket> getTicketByBus(Integer busId, Date tempDate) {
		java.sql.Date date=new java.sql.Date(tempDate.getTime());

		String query = "SELECT  * FROM booking_details where bus_id="+busId+" and journey_date='"+date+"'";
		List<Ticket> ticketAl = new ArrayList<>();

		Ticket ticket=null;
		try (Connection connection = DriverManager.getConnection(BusBookingSystem.DB_URL,BusBookingSystem.DB_USER, BusBookingSystem.DB_PASSWORD);
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(query)) {
			while (rs.next()) {
				ticket=new Ticket();
				ticket.setBookingId(rs.getInt("booking_id"));
				ticket.setUserId(rs.getInt("customer_id"));
				ticket.setBusId(rs.getInt("bus_id"));
				ticket.setJourneyDate(rs.getDate("journey_date"));
				ticket.setNumOfSeats(rs.getInt("num_of_seats"));
				ticket.setBookingDateTime(rs.getTimestamp("booking_date_time"));
				ticketAl.add(ticket);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ticketAl;

	}
}
