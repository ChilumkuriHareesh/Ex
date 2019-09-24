package com.capgemini.busscheduling.services;

import com.capgemini.busscheduling.beans.Admin;
import com.capgemini.busscheduling.beans.Owner;
import com.capgemini.busscheduling.dao.AdminDAO;
import com.capgemini.busscheduling.dao.AdminDAOImpl;

public class AdminServiceImpl implements AdminService{
	
	AdminDAO dao = new AdminDAOImpl();

	@Override
	public Admin adminLogin(Integer adminId, String password) {
		return dao.adminLogin(adminId, password);
	}

	@Override
	public Boolean deleteCustomer(Integer customerId) {
		return dao.deleteCustomer(customerId);
	}

	@Override
	public Boolean deleteOwner(Integer ownerId) {
		return dao.deleteOwner(ownerId);
	}

	@Override
	public Owner registerOwner(Owner owner) {
		return dao.registerOwner(owner);
	}

}
