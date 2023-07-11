package org.formation;

import java.time.Duration;
import java.time.LocalDateTime;
import static java.util.UUID.randomUUID;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ReactiveWebSocketHandler implements WebSocketHandler {
     

	Flux<Event> eventFlux = Flux.range(1,5)
			.map(i-> new Event(i.toString(),LocalDateTime.now().toString()));
	

 
	private Flux<String> intervalFlux = Flux.interval(Duration.ofMillis(1000L))
		      .zipWith(eventFlux, (time, event) -> event.toString());
	
    @Override
    public Mono<Void> handle(WebSocketSession webSocketSession) {
        return webSocketSession.send(intervalFlux
          .map(webSocketSession::textMessage))
          .and(webSocketSession.receive()
            .map(WebSocketMessage::getPayloadAsText)
            .log());
    }
}
