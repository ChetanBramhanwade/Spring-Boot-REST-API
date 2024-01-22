package com.cdac.training.springboot_restapi_crud.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.training.springboot_restapi_crud.model.Employee;
import com.cdac.training.springboot_restapi_crud.repository.EmployeeRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository eRepo;
	
	public Employee saveEmployee(Employee e) {
		return eRepo.save(e);
	}
	
	public Iterable<Employee> getEmployee(){
		return eRepo.findAll();
	}
	
	public Optional<Employee> getEmployeeById(long id){
		return eRepo.findById(id);
	}
	
	public void deleteEmployee(long id) {
		eRepo.deleteById(id);
	}
	
	public Employee updateEmployee(Employee e) {
		return eRepo.save(e);
	}
}
