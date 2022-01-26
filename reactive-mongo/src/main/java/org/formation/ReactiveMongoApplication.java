package org.formation;

import org.formation.model.Account;
import org.formation.model.AccountCrudRepository;
import org.formation.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class ReactiveMongoApplication implements CommandLineRunner {

	@Autowired
	AccountCrudRepository accountCrudRepository;
	
	@Autowired
	AccountService accountService;
	
	public static void main(String[] args) {
		SpringApplication.run(ReactiveMongoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
//		_playWithRepositoryBlocking();
		
		_playWithRepositoryUnBlocking();
//		
//		_playWithTemplate();
		
	}
	
	private void _playWithRepositoryBlocking() {
		accountCrudRepository.deleteAll().block();

		// save a couple of customers
		accountCrudRepository.save(new Account(null, "Bill", 12.3)).block();
		accountCrudRepository.save(new Account(null, "Mary", 13.3)).block();
		
		accountCrudRepository.saveAll(Flux.just(new Account(null, "David", 13.3))).blockLast();

		System.out.println("Playng with Repository Blocking");
		System.out.println("----------------------");
		// fetch all accounts
		accountCrudRepository.findAll().subscribe(a -> {
			System.out.println("findAll():" + a);
			}
		);
			
		// fetch all byValue
		accountCrudRepository.findAllByValue(12.3).subscribe(a -> {
			System.out.println("findByValue():" + a);
		});
			
		// fetch an individual customer
		accountCrudRepository.findFirstByOwner(Mono.just("Bill")).subscribe(a -> {
			System.out.println("findFirstByOwner('Bill'):" + a);
		});

		accountCrudRepository.deleteAll();
		
	}

	private void _playWithRepositoryUnBlocking() {
		accountCrudRepository.deleteAll().block();

		// save a couple of customers
		accountCrudRepository.save(new Account(null, "Bill", 12.3)).subscribe();
		accountCrudRepository.save(new Account(null, "Mary", 13.3)).subscribe();
		
		accountCrudRepository.saveAll(Flux.just(new Account(null, "David", 13.3))).subscribe();

		System.out.println("Playing with Repository Unblocking");
		System.out.println("----------------------");
		// fetch all accounts
		accountCrudRepository.findAll().subscribe(a -> {
			System.out.println("findAll():" + a);
			}
		);
			
		// fetch all byValue
		accountCrudRepository.findAllByValue(12.3).subscribe(a -> {
			System.out.println("findByValue():" + a);
		});
			
		// fetch an individual customer
		accountCrudRepository.findFirstByOwner(Mono.just("Bill")).subscribe(a -> {
			System.out.println("findFirstByOwner('Bill'):" + a);
		});

		accountCrudRepository.deleteAll();
		
	}

	private void _playWithTemplate() {
		accountService.save(Mono.just(new Account(null, "Bill", 12.3))).block();
		accountService.save(Mono.just(new Account(null, "Mary", 13.3))).block();

		System.out.println("Playng with Template");
		System.out.println("----------------------");
		// fetch all accounts
		accountService.findAll().subscribe(a -> {
			System.out.println("findAll():" + a);
			}
		);
			
	

		
	}

}
