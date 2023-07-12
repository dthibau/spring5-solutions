package org.formation;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@SpringBootTest
@Slf4j
public class ReactiveControllerApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void testSSE() throws InterruptedException {
		WebClient client = WebClient.create("http://localhost:8080");
		
		ParameterizedTypeReference<ServerSentEvent<String>> type
	     = new ParameterizedTypeReference<ServerSentEvent<String>>() {};
	     
	     Flux<ServerSentEvent<String>> eventStream = client.get()
	    	      .uri("/stream-sse")
	    	      .retrieve()
	    	      .bodyToFlux(type);
	     
	     eventStream.take(10).subscribe(
	    		 content -> log.info("Time: {} - event: name[{}], id [{}], content[{}] ",
	    			        LocalTime.now(), content.event(), content.id(), content.data()),
	    			      error -> log.error("Error receiving SSE: {}", error),
	    			      () -> log.info("Completed!!!"));

	     Thread.sleep(15000);
	}
}
