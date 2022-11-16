package org.formation.test;

import java.util.Arrays;
import java.util.stream.Stream;

import org.formation.controller.ReactiveController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest()
public class UniqueControllerTest {

	@Autowired
	WebTestClient webTestClient;
	
	@Configuration
    static class Config {
		
		@Bean
		public String hello() {
			return new String("Hello");
		}
		
		@Bean
		WebTestClient client() {
			return WebTestClient.bindToController(new ReactiveController()).build();
		}
	}
	
	@Autowired
	ApplicationContext context;
	
	@Test
	public void contextLoads() {
		Stream<String> stream = Arrays.stream(context.getBeanDefinitionNames());
        stream.forEach(x -> System.out.println(x));
        
	}
	
	@Test
	public void testController() {
		
		webTestClient.get().uri("/pause/10")
		.exchange()
        .expectStatus().isOk();
        
	}
}
