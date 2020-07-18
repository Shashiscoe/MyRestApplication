package com.example.MyRestApplication.generic.exception;

import java.util.Date;

public class ExceptionResponse {

	private Date createddate;
	private String message;
	private String details;

	public Date getCreateddate() {
		return createddate;
	}

	public ExceptionResponse(Date createddate, String message, String details) {
		super();
		this.createddate = createddate;
		this.message = message;
		this.details = details;
	}

	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

}
