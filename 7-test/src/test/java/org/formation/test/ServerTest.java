package org.formation.test;

import java.util.Arrays;
import java.util.stream.Stream;

import org.formation.model.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Mono;

@SpringBootTest()
public class ServerTest {

	@Autowired
	ApplicationContext context;
	
    private WebTestClient webTestClient;
    
	@BeforeEach
    public void setUp() {
		webTestClient = WebTestClient.bindToApplicationContext(context).build(); 
    }
	
	@Test
	public void contextLoads() {
		Stream<String> stream = Arrays.stream(context.getBeanDefinitionNames());
        stream.forEach(x -> System.out.println(x));
        
	}
	
	@Test
	@WithMockUser
	public void testCreate() {

		webTestClient.get().uri("/accounts").exchange().expectStatus().isOk();
		
		
		Mono<Account> account = Mono.just(new Account(null,"David",45.9));

		webTestClient.post().uri("/accounts").body(account,Account.class).exchange().expectStatus().isOk();
		
	}
}
