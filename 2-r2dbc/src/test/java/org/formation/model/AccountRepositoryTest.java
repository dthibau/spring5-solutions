package org.formation.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
public class AccountRepositoryTest {

	@Autowired
	AccountRepository accountRepository;
	
	@Test
	public void testSaveAndFind() {
		accountRepository.deleteAll().block();

		// save a couple of customers
		accountRepository.save(new Account("Bill", 12.3)).block();
		accountRepository.save(new Account("Mary", 13.3)).block();
		
		accountRepository.saveAll(Flux.just(new Account("David", 13.3))).blockLast();

		accountRepository.findAll()
	      .as(StepVerifier::create)
	      .expectNextCount(3)
	      .verifyComplete();
		
//		// fetch all byValue
		accountRepository.findAllByValue(12.3)
						.as(StepVerifier::create)
						.expectNextCount(1)
						.verifyComplete();
////	
////		// fetch all byValue
		accountRepository.findFirstByOwner("Bill")
						.as(StepVerifier::create)
						.expectNextCount(1)
						.verifyComplete();
////
		accountRepository.deleteAll().block();
////		
		accountRepository.findAll()
	      .as(StepVerifier::create)
	      .expectNextCount(0)
	      .verifyComplete();
	}
	

}
