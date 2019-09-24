package com.capgemini.busscheduling.services;

import com.capgemini.busscheduling.beans.Admin;
import com.capgemini.busscheduling.beans.Owner;

public interface AdminService {

	public Admin adminLogin(Integer adminId, String password);
	public Owner registerOwner( Owner owner);
	public Boolean deleteCustomer(Integer userId);
	public Boolean deleteOwner(Integer userId);
}
