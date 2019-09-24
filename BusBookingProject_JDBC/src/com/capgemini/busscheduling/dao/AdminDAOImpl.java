package com.capgemini.busscheduling.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.capgemini.busscheduling.beans.Admin;
import com.capgemini.busscheduling.beans.Owner;
import com.capgemini.busscheduling.common.BusBookingSystem;

public class AdminDAOImpl implements AdminDAO {

	public AdminDAOImpl() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Admin adminLogin(Integer adminId, String password) {		                 
		/* ADMIN LOGIN*/
		String query = "SELECT * FROM admin_details where admin_id=" + adminId + " and admin_password='" + password + "'";
		Admin admin=null;

		try (Connection connection = DriverManager.getConnection(BusBookingSystem.DB_URL,BusBookingSystem.DB_USER, BusBookingSystem.DB_PASSWORD);
				Statement statement = connection.createStatement();
				ResultSet resultset = statement.executeQuery(query)) {
			if (resultset.next()) {
				admin=new Admin();
				admin.setAdminId(resultset.getInt("admin_id"));
				admin.setAdminPassword(resultset.getString("admin_password"));
				admin.setAdminName(resultset.getString("admin_name"));
				admin.setAdminEmail(resultset.getString("admin_email"));
				admin.setAdminContact(resultset.getLong("admin_contact"));
				return admin;
			} else {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return admin;
	}

	@Override
	public Owner registerOwner(Owner owner) {
		String query = "INSERT INTO owner_details VALUES (?,?,?,?,?)";
		try (Connection connection = DriverManager.getConnection(BusBookingSystem.DB_URL,BusBookingSystem.DB_USER, BusBookingSystem.DB_PASSWORD);
				PreparedStatement pstmt = connection.prepareStatement(query);) {
			pstmt.setInt(1, owner.getOwnerId());
			pstmt.setString(2, owner.getOwnerName());
			pstmt.setString(3, owner.getOwnerEmail());
			pstmt.setString(4, owner.getOwnerPassword());
			pstmt.setLong(5, owner.getOwnerContact());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return owner;
	}

	@Override
	public Boolean deleteCustomer(Integer customerId) {
		String query = "DELETE FROM customer_details WHERE customer_id=?";
		try (Connection connection = DriverManager.getConnection(BusBookingSystem.DB_URL,BusBookingSystem.DB_USER, BusBookingSystem.DB_PASSWORD);
				PreparedStatement pstmt = connection.prepareStatement(query);) {
			pstmt.setInt(1, customerId);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}


	@Override
	public Boolean deleteOwner(Integer ownerId) {
		String query = "DELETE FROM owner_details WHERE owner_id=?";
		try (Connection connection = DriverManager.getConnection(BusBookingSystem.DB_URL,BusBookingSystem.DB_USER, BusBookingSystem.DB_PASSWORD);
				PreparedStatement pstmt = connection.prepareStatement(query);) {
			pstmt.setInt(1, ownerId);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
}


