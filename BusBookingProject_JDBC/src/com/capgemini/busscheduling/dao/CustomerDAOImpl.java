package com.capgemini.busscheduling.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Random;

import com.capgemini.busscheduling.beans.Customer;
import com.capgemini.busscheduling.beans.Feedback;
import com.capgemini.busscheduling.beans.Ticket;
import com.capgemini.busscheduling.common.BusBookingSystem;

public class CustomerDAOImpl implements CustomerDAO{
	
	public CustomerDAOImpl() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	BusBookingDAO dao = new BusBookingDAOImpl();
	@Override
	public Customer registerCustomer(Customer customer) {
		String query = "INSERT INTO customer_info VALUES (?,?,?,?,?)";
		
		try (Connection conn = DriverManager.getConnection(BusBookingSystem.DB_URL,BusBookingSystem.DB_USER, BusBookingSystem.DB_PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(query);) {
				pstmt.setInt(1, customer.getCustomerId());
				pstmt.setString(2, customer.getCustomerName());
				pstmt.setString(3, customer.getCustomerEmail());
				pstmt.setString(4, customer.getCustomerPassword());
				pstmt.setLong(5, customer.getCustomerContact());
				pstmt.executeUpdate();
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customer;
	}
	
	@Override
	public Customer loginCustomer(Integer customerId, String password) {
		String query = "SELECT * FROM customer_details where customer_id='" + customerId + "' and customer_password='" + password + "'";

		try (Connection conn = DriverManager.getConnection(BusBookingSystem.DB_URL,BusBookingSystem.DB_USER, BusBookingSystem.DB_PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(query);
				ResultSet rs = pstmt.executeQuery(query)) {

			Customer user = new Customer();

			if (rs.next()) {
				user.setCustomerId(rs.getInt("customer_id"));
				user.setCustomerName(rs.getString("customer_name"));
				user.setCustomerEmail(rs.getString("customer_email"));
				user.setCustomerPassword(rs.getString("customer_password"));
				user.setCustomerContact(rs.getLong("customer_contact"));
				return user;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Boolean updateCustomer(Customer customer) {
		String query = "UPDATE customer_info SET customer_name=?,customer_email=?,customer_password=?,customer_contact=? WHERE customer_id=?";
		
		try (Connection conn = DriverManager.getConnection(BusBookingSystem.DB_URL,BusBookingSystem.DB_USER, BusBookingSystem.DB_PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(query);) {
				pstmt.setString(1, customer.getCustomerName());
				pstmt.setString(2, customer.getCustomerEmail());
				pstmt.setString(3, customer.getCustomerPassword());
				pstmt.setLong(4, customer.getCustomerContact());
				pstmt.setInt(5, customer.getCustomerId());
				pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public Boolean deleteCustomer(Integer customerId) {
		// delete user if already exists
				String query = "DELETE FROM customer_info WHERE customer_id="+customerId;
				try (Connection conn = DriverManager.getConnection(BusBookingSystem.DB_URL,BusBookingSystem.DB_USER, BusBookingSystem.DB_PASSWORD);
						PreparedStatement pstmt = conn.prepareStatement(query);) {
						pstmt.setInt(1, customerId);
						pstmt.executeUpdate();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return true;
	}

	@Override
	public Ticket bookTicket(Ticket ticket) {
		java.sql.Date date=new java.sql.Date(ticket.getJourneyDate().getTime());
		ticket.setJourneyDate(date);

		String query = "INSERT INTO booking_info  VALUES (?,?,?,?,?,?)";
		String availQuery = "UPDATE  availability SET avail_seats=? WHERE avail_date=? and bus_id=?";
		int totalAvailSeats = dao.checkAvailability(ticket.getBusId(), (java.sql.Date) ticket.getJourneyDate());

		try (Connection conn = DriverManager.getConnection(BusBookingSystem.DB_URL,BusBookingSystem.DB_USER, BusBookingSystem.DB_PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(query);
				PreparedStatement availpstmt = conn.prepareStatement(availQuery);) {
			if (ticket.getNumOfSeats() <= totalAvailSeats) {

				//get timestamp
				ticket.setBookingDateTime(new java.sql.Timestamp(new java.util.Date().getTime()));
				pstmt.setInt(1, ticket.getBookingId());
				pstmt.setInt(2, ticket.getBusId());
				pstmt.setInt(3, ticket.getUserId());
				pstmt.setDate(4, date);
				pstmt.setInt(5, ticket.getNumOfSeats());
				pstmt.setTimestamp(6, (Timestamp)ticket.getBookingDateTime() );
				int tic = pstmt.executeUpdate();
				if (tic > -1) {
					// to decrement number of seats from availability table
					availpstmt.setInt(1, totalAvailSeats - ticket.getNumOfSeats());
					availpstmt.setDate(2, (java.sql.Date) ticket.getJourneyDate());
					availpstmt.setInt(3, ticket.getBusId());
					availpstmt.executeUpdate();

					
					if(ticket != null) {
					return ticket;
					}
				}
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ticket;
	}

	@Override
	public Boolean cancelTicket(Integer bookingId, Integer customerId) {
		String query = "DELETE FROM booking_info WHERE booking_id=? and customer_id=?";
		String availQuery = "UPDATE  availability SET avail_seats=? WHERE avail_date=? and bus_id=?";
		Ticket ticket = getTicketInfo(bookingId);
		System.out.println(ticket);

		int totalAvailSeats = dao.checkAvailability(ticket.getBusId(), (java.sql.Date) ticket.getJourneyDate());

		try (Connection conn = DriverManager.getConnection(BusBookingSystem.DB_URL,BusBookingSystem.DB_USER, BusBookingSystem.DB_PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(query);
				PreparedStatement availpstmt = conn.prepareStatement(availQuery);) {
			if (ticket != null) {
				pstmt.setInt(1, bookingId);
				pstmt.setInt(2, customerId);
				pstmt.executeUpdate();

				availpstmt.setInt(1, ticket.getNumOfSeats() + totalAvailSeats);
				availpstmt.setDate(2, (java.sql.Date) ticket.getJourneyDate());
				availpstmt.setInt(3, ticket.getBusId());
				availpstmt.executeUpdate();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Ticket getTicketInfo(Integer bookingId) {
		String query = "SELECT  * FROM booking_info where booking_id=" + bookingId;

		try (Connection conn = DriverManager.getConnection(BusBookingSystem.DB_URL,BusBookingSystem.DB_USER, BusBookingSystem.DB_PASSWORD);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {

			if (rs.next()) {

				Ticket ticket = new Ticket();
				ticket.setBookingId(rs.getInt("booking_id"));
				ticket.setBusId(rs.getInt("bus_id"));
				ticket.setUserId(rs.getInt("customer_id"));
				ticket.setJourneyDate(rs.getDate("journey_date"));
				ticket.setNumOfSeats(rs.getInt("num_of_seats"));
				ticket.setBookingDateTime(rs.getTimestamp("booking_date_time"));
				return ticket;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Boolean writeFeedback(Feedback feed) {
		String query = "INSERT INTO suggestion (customer_id,feedback) VALUES (?,?)";
		
		try (Connection conn = DriverManager.getConnection(BusBookingSystem.DB_URL,BusBookingSystem.DB_USER, BusBookingSystem.DB_PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(query);) {
			pstmt.setInt(1, feed.getCustomerId());
			pstmt.setString(2, feed.getFeedback());
			int res = pstmt.executeUpdate();
			if (res > -1)
				return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
