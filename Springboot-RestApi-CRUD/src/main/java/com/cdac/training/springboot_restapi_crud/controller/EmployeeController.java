package com.cdac.training.springboot_restapi_crud.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.chirag.firstspringproject.exception.ResourceNotFoundException;
import com.cdac.training.springboot_restapi_crud.model.Employee;
import com.cdac.training.springboot_restapi_crud.service.EmployeeService;

@RestController
@RequestMapping(value = "/api")
public class EmployeeController {

	@Autowired
	private EmployeeService eService;

	@PostMapping("/employee")
	public ResponseEntity<Employee> saveEmployee(@Validated @RequestBody Employee employee){
		
	try {
		Employee e= eService.saveEmployee(employee);
		return ResponseEntity.status(HttpStatus.CREATED).body(null);
	}catch(Exception e) {
		e.printStackTrace();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
	@GetMapping("/employee")
    public ResponseEntity<Iterable<Employee>> getAllEmployees() {
        try {
            Iterable<Employee> employees = eService.getEmployee();
            return ResponseEntity.ok(employees);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

	 @GetMapping("/employee/{id}")
	    public ResponseEntity<Employee> getEmpById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
	        Employee emp = eService.getEmployeeById(id)
	                .orElseThrow(() -> new ResourceNotFoundException("Employee not found: " + id));

	        return ResponseEntity.ok().body(emp);
	    }
	 
	 @PutMapping("/employee/{id}")
	    public ResponseEntity<Employee> updateProduct(@PathVariable(value = "id") Long id,
	            @Validated @RequestBody Employee e) throws ResourceNotFoundException {
	        Employee emp = eService.getEmployeeById(id)
	                .orElseThrow(() -> new ResourceNotFoundException("Employee not found " + id));

	        emp.setName(e.getName());
	        emp.setDept(e.getDept());

	        final Employee updatedEmployee = eService.saveEmployee(emp);
	        return ResponseEntity.ok().body(updatedEmployee);
	    }
	
	 @DeleteMapping("/employee/{id}")
	    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable(value = "id") Long id)
	            throws ResourceNotFoundException {
	        eService.getEmployeeById(id)
	                .orElseThrow(() -> new ResourceNotFoundException("Employee of " + id + " not found"));
	        eService.deleteEmployee(id);
	        Map<String, Boolean> response = new HashMap<String, Boolean>();
	        response.put("Deleted", Boolean.TRUE);
	        return ResponseEntity.ok().body(response);
	    }

}
