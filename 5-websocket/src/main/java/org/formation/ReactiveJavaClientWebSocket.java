package org.formation;

import java.net.URI;
import java.time.Duration;

import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ReactiveJavaClientWebSocket {

	public static void main(String[] args) throws InterruptedException {
		  
		Flux<Long> longFlux = Flux.interval(Duration.ofSeconds(1));
		
        WebSocketClient client = new ReactorNettyWebSocketClient();
        client.execute(
          URI.create("ws://localhost:8080/uppercase"), 
          session -> session.send(
            longFlux.map(i -> session.textMessage("msg" + i)).take(10))
            .and(session.receive()
              .map(WebSocketMessage::getPayloadAsText)
              .log())
            .then())
            .block(Duration.ofSeconds(15L));
    }
}
