package org.formation.service;

import org.formation.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountService {

	@Autowired
	ReactiveMongoTemplate template;

	@PreAuthorize("hasRole('ADMIN')")
	public Mono<Account> findById(String id) {
		return template.findById(id, Account.class);
	}

	public Flux<Account> findAll() {
		return template.findAll(Account.class);
	}

	public Mono<Account> save(Mono<Account> account) {
		return template.save(account);
	}
	
	public Mono<Account> first() {
		return template.query(Account.class).first();
	}


}
