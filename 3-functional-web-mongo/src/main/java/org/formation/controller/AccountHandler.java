package org.formation.controller;

import org.formation.model.Account;
import org.formation.model.AccountRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class AccountHandler {

	private final AccountRepository accountRepository;

	public AccountHandler(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	public Mono<ServerResponse> listAccounts(ServerRequest request) {
		Flux<Account> accounts = accountRepository.findAll();
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(accounts, Account.class);
	}

	public Mono<ServerResponse> createAccount(ServerRequest request) {
		Mono<Account> account = request.bodyToMono(Account.class);
		return ServerResponse.ok().body(accountRepository.saveAll(account), Account.class);
	}

	public Mono<ServerResponse> getAccount(ServerRequest request) {
		String id = request.pathVariable("id");
		Mono<ServerResponse> notFound = ServerResponse.notFound().build();
		Mono<Account> accountMono = this.accountRepository.findById(Mono.just(id));

		return accountMono
				.then(ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(accountMono, Account.class))
				.switchIfEmpty(notFound);
	}

}
