package org.formation;

import java.time.Duration;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/react")
public class ReactController {

	@GetMapping("/{pause}")
	Mono<String> react(@PathVariable long pause) {
		return Mono.delay(Duration.ofMillis(pause)).map((i) ->  "Hello");
	}
}
