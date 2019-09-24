package com.capgemini.busscheduling.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

	public Integer validateId(String id) {

		Pattern pat = Pattern.compile("\\d+");
		Matcher mat = pat.matcher(id);
		if(mat.matches()){
			return Integer.parseInt(id);
		}else{
			return null;
		}
	}

	public String validateEmail(String email) {
		Pattern pat = Pattern.compile("\\w+\\@\\w+\\.\\w+");
		Matcher	mat = pat.matcher(email);
		if(mat.matches()){
			return email;
		}else{
			return null;
		}
	}

	public Long validateContact(String contact) {
		Pattern pat = Pattern.compile("\\d{10}");
		Matcher mat = pat.matcher(contact);
		if(mat.matches()) {
			return Long.parseLong(contact);
		}else {
			return null;
		}
	}
}
