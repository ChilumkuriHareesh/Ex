package com.capgemini.busscheduling.beans;

public class Feedback {

	private Integer customerId;
	private String feedback;

	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	@Override
	public String toString() {
		return "Feedback [customerId=" + customerId + ", feedback=" + feedback + "]";
	}
}
