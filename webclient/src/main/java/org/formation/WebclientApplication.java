package org.formation;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class WebclientApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(WebclientApplication.class);
		// prevent SpringBoot from starting a web server
		app.setWebApplicationType(WebApplicationType.NONE);
		app.run(args);

	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("RUN");

		WebClient client = WebClient.create("http://localhost:8080");

		Account account = new Account(null, "David", 12.3);
		Mono<Account> accountMono = Mono.just(account);

		// Start with a post
		Flux<Account> accountFlux = client.post().uri("/accounts").contentType(MediaType.APPLICATION_JSON)
				.body(accountMono, Account.class).retrieve().bodyToFlux(Account.class);

		account = accountFlux.blockFirst();
		accountMono = client.get().uri("/accounts", account.getId()).accept(MediaType.APPLICATION_JSON).retrieve()
				.bodyToMono(Account.class);

		accountFlux.subscribe(ac -> {
			client.get().uri("/accounts", ac.getId()).accept(MediaType.APPLICATION_JSON).retrieve()
			.bodyToMono(Account.class);
		});


		System.out.println("account  retereived "+account);
		
		// Get all
		Flux<Account> accounts = client.get().uri("/accounts").accept(MediaType.APPLICATION_JSON).exchange()
				.flatMapMany(r -> r.bodyToFlux(Account.class));

		accounts = client.get().uri("/accounts").accept(MediaType.APPLICATION_JSON).retrieve().bodyToFlux(Account.class);
		
		accounts.subscribe(a -> System.out.println(a));

		System.out.println("First account  retereived "+account);
		
		Thread.sleep(2000);

	}
}
