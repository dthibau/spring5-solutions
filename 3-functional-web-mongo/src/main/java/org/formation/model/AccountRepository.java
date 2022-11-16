package org.formation.model;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountRepository  extends ReactiveCrudRepository<Account, String> {

	Flux<Account> findAllByAmount(Double amount);
    Mono<Account> findFirstByOwner(Mono<String> owner);
}
