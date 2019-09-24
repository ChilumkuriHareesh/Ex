package com.capgemini.busscheduling.beans;

public class Owner {

	private Integer ownerId;
	private String ownerPassword;
	private String ownerName;
	private String ownerEmail;
	private Long ownerContact;
	
	public Integer getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}
	public String getOwnerPassword() {
		return ownerPassword;
	}
	public void setOwnerPassword(String ownerPassword) {
		this.ownerPassword = ownerPassword;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getOwnerEmail() {
		return ownerEmail;
	}
	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}
	public Long getOwnerContact() {
		return ownerContact;
	}
	public void setOwnerContact(Long ownerContact) {
		this.ownerContact = ownerContact;
	}
	@Override
	public String toString() {
		return "Owner [ownerId=" + ownerId + ", ownerName=" + ownerName + ", ownerEmail=" + ownerEmail
				+ ", ownerContact=" + ownerContact + "]";
	}

	
}
