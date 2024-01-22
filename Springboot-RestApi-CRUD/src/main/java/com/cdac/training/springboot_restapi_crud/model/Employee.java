package com.cdac.training.springboot_restapi_crud.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long eId;
	
	private String name;
	private String dept;
	
	
	public Employee() {
		super();
	}


	public Employee(long eId, String name, String dept) {
		super();
		this.eId = eId;
		this.name = name;
		this.dept = dept;
	}


	public long geteId() {
		return eId;
	}


	public void seteId(long eId) {
		this.eId = eId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDept() {
		return dept;
	}


	public void setDept(String dept) {
		this.dept = dept;
	}
	
	
}
