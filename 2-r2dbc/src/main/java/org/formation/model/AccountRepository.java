package org.formation.model;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountRepository  extends ReactiveCrudRepository<Account, Long> {

	@Query("SELECT * FROM account WHERE amount = :amount")
	Flux<Account> findAllByValue(Double amount);
	
	@Query("SELECT * FROM account WHERE owner = :owner limit 1")
    Mono<Account> findFirstByOwner(String owner);
}
