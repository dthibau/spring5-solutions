package org.formation.service;

import static org.springframework.data.relational.core.query.Criteria.where;

import org.formation.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountService {

	@Autowired
	R2dbcEntityTemplate template;

	public Mono<Account> findById(String id) {
		return template.selectOne(Query.query(where("id").is(id)), Account.class);
	}

	public Flux<Account> findAll() {
		return template.select(Account.class).all();
	}

	public Mono<Account> save(Account account) {
		return template.insert(account);
	}

	public Mono<Account> first() {
		return template.select(Account.class).first();
	}

}
