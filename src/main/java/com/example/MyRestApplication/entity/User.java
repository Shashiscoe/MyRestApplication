package com.example.MyRestApplication.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class User implements Serializable {

	private static final long serialVersionUID = 2033875254717031526L;

	@Id
	@GeneratedValue
	private int id;

	@NotNull(message = "Please provide a name ,message should not null")
	@NotEmpty(message = "Please provide a name, message should not empty")
	@Size(min = 2, max = 10, message = "Name should be atleast of 2 Character or atmost 10 character")
	private String name;
	private String city;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createddate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Date getCreateddate() {
		return createddate;
	}

	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}

	public User(int id, String name, String city, Date createddate) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.createddate = createddate;
	}

	public User() {
		super();
	}

}
