package com.capgemini.busscheduling.beans;

public class Customer {

	private Integer customerId;
	private String customerName;
	private String customerEmail;
	private String customerPassword;
	private Long customerContact;

	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getCustomerPassword() {
		return customerPassword;
	}
	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}
	public Long getCustomerContact() {
		return customerContact;
	}
	public void setCustomerContact(Long customerContact) {
		this.customerContact = customerContact;
	}
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerId=" + customerId + ", customerEmail=" + customerEmail + ", customerContact="
				+ customerContact + "]";
	}
}
