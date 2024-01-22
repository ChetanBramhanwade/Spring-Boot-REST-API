package com.cdac.training.springboot_restapi_crud.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdac.training.springboot_restapi_crud.model.Dealer;

public interface DealerRepository extends JpaRepository<Dealer, Long>{
	public Optional<Dealer> findByEmail(String email);
}
