package org.formation.test;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.stream.Stream;

import org.formation.RouterConfig;
import org.formation.controller.AccountHandler;
import org.formation.model.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@SpringBootTest()
public class RouterFunctionTest {

	@MockBean
	AccountHandler handler;
	
	@Autowired
	WebTestClient webTestClient;
	
	@Autowired
	ApplicationContext context;	
	
	@Configuration
	@Import(RouterConfig.class)
    static class Config {
		@Autowired
		RouterFunction<ServerResponse> routes;
		
		
		@Bean
		WebTestClient client() {
			return WebTestClient.bindToRouterFunction(routes).build();
		}
	}
	

	
	@Test
	public void contextLoads() {
		Stream<String> stream = Arrays.stream(context.getBeanDefinitionNames());
        stream.forEach(x -> System.out.println(x));
        
	}
	
	@Test
	public void testRoutes() {
		webTestClient.get().uri("/hello").exchange().expectStatus().isNotFound();
		
		
		Mono<Account> account = Mono.just(new Account(null,"David",45.9));
		ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(account,Account.class);

		when(handler.createAccount(null)).thenReturn(ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(account,Account.class));
		webTestClient.post().uri("/accounts").body(account,Account.class).exchange().expectStatus().isOk();
		
	}
}
