package com.cdac.training.springboot_restapi_crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdac.training.springboot_restapi_crud.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
