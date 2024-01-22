package com.cdac.training.springboot_restapi_crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.training.springboot_restapi_crud.exception.ResourceNotFoundException;
import com.cdac.training.springboot_restapi_crud.model.Address;
import com.cdac.training.springboot_restapi_crud.model.Dealer;
import com.cdac.training.springboot_restapi_crud.service.DealerService;

@RestController
@RequestMapping("/api")
public class DealerController {

	@Autowired
	private DealerService dService;

	
	/*
	 * { "email":"admin@gmail.com", "fname":"john", "lname":"wick",
	 * "password":"admin", "dob":"1999-11-03", "phoneNo":"99498508574",
	 * "address": { "street":"phase 1", "city":"e city", "pincode":560100 } }
	 * 
	 * 127.0.0.1:8087/inventory/api/register
	 */
	@PostMapping("/register")
	public ResponseEntity<String> createDealer(@Validated @RequestBody Dealer dealer) {
		try {
			Address address = dealer.getAddress();
			address.setDealer(dealer);
			dealer.setAddress(address);

			Dealer registeredDealer = dService.registDealer(dealer);

			if (registeredDealer != null) {
				return ResponseEntity.ok("Registration Successful");
			} else {
				return ResponseEntity.badRequest().body("Registration failed");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occured " + e.getMessage());
		}
	}
	
	/*
	 * { "email":"admin@gmail.com",
	 *  "password":"admin" }
	 *  
	 *  127.0.0.1:8087/inventory/api/login
	 */
	@PostMapping("/login")
	public ResponseEntity<Boolean> loginDealer(@Validated @RequestBody Dealer dealer) throws ResourceNotFoundException {
		Boolean isAuthenticated = false;
		String email = dealer.getEmail();
		String password = dealer.getPassword();

		Dealer d = dService.loginDealer(email)
				.orElseThrow(() -> new ResourceNotFoundException("Dealer not found for this email " + email));
		if (email.equals(d.getEmail()) && password.equals(d.getPassword())) {
			isAuthenticated = true;
		}
		return ResponseEntity.ok(isAuthenticated);
	}
}
