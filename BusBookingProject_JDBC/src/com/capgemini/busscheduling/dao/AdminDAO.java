package com.capgemini.busscheduling.dao;


import com.capgemini.busscheduling.beans.Admin;
import com.capgemini.busscheduling.beans.Owner;

public interface AdminDAO {

	public Admin adminLogin(Integer adminId, String password);
	public Owner registerOwner( Owner owner);
	public Boolean deleteCustomer(Integer customerId);
	public Boolean deleteOwner(Integer ownerId);
	
}
