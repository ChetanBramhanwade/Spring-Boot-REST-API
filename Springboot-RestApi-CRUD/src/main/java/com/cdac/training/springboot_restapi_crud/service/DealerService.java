package com.cdac.training.springboot_restapi_crud.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.training.springboot_restapi_crud.model.Dealer;
import com.cdac.training.springboot_restapi_crud.repository.DealerRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DealerService {

		@Autowired
		private DealerRepository dRepository;
		
		public Dealer registDealer(Dealer d) {
			return dRepository.save(d);
		}
		
		public Optional<Dealer> loginDealer(String email){
			return dRepository.findByEmail(email);
		}
}
