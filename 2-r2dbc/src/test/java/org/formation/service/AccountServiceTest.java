package org.formation.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.formation.model.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import reactor.test.StepVerifier;

@SpringBootTest
public class AccountServiceTest {

	@Autowired
	AccountService accountService;

	@Test
	public void testService() {

		accountService.save(new Account("Bill", 12.3)).block();
		accountService.save(new Account( "Mary", 13.3)).block();

		// fetch all accounts
		accountService.findAll().as(StepVerifier::create).expectNextCount(2).verifyComplete();
		
		StepVerifier.create(accountService.save(new Account("Lucy",14.3))).assertNext(a -> {
			assertNotNull(a.getId());		
		})
		;
	}

}
