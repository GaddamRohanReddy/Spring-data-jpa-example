package com.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Customer {
	
	@Id
	@GeneratedValue
	private long custId;
	private String custName;
	private String custCity;
	
	public Customer() {
		// TODO Auto-generated constructor stub
	}
	
	public Customer(String custName, String custCity) {
		super();
		this.custName = custName;
		this.custCity = custCity;
	}
	
	public long getCustId() {
		return custId;
	}
	public String getCustName() {
		return custName;
	}
	public String getCustCity() {
		return custCity;
	}

}
