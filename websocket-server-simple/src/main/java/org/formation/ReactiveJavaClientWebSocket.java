package org.formation;

import java.net.URI;
import java.time.Duration;

import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;

import reactor.core.publisher.Mono;

public class ReactiveJavaClientWebSocket {

	public static void main(String[] args) throws InterruptedException {
		  
        WebSocketClient client = new ReactorNettyWebSocketClient();
              
//       client.execute(URI.create("ws://localhost:8080/event-emitter"), 
//    		   session ->
//       			session.send(Mono.just(session.textMessage("Coucou"))).
//       			thenMany(session.receive().doOnNext(m -> System.out.println(m.getPayloadAsText()))).
//       			then()).block(Duration.ofSeconds(20L));
       
       client.execute(URI.create("ws://localhost:8080/event-emitter"), 
    		   session ->
       			session.receive().doOnNext(m -> System.out.println(m.getPayloadAsText())).
       			then()).block(Duration.ofSeconds(20L));

    }
}
