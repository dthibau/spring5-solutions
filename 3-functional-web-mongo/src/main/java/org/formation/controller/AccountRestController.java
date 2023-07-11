package org.formation.controller;

import org.formation.model.Account;
import org.formation.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/acounts")
public class AccountRestController {

	
	@Autowired
	AccountService accountService;
	
	
	@GetMapping(path = "/{id}")
	public Mono<Account> findById(@PathVariable String id) {
		
		return accountService.findById(id).switchIfEmpty(Mono.error(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
		
	}
}
