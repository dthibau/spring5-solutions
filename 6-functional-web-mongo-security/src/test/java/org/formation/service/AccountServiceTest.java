package org.formation.service;

import org.formation.model.Account;
import org.formation.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
public class AccountServiceTest {

	@Autowired
	AccountService accountService;

	@Test
	public void testService() {

		accountService.save(Mono.just(new Account("Bill", 12.3))).block();
		accountService.save(Mono.just(new Account("Mary", 13.3))).block();

		// fetch all accounts
		accountService.findAll().as(StepVerifier::create).expectNextCount(2).verifyComplete();
	}

}
