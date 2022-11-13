package org.formation;

import reactor.core.publisher.Flux;

public class MainError {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainError.methode1();
	}


	
	public static void methode1() {

		Flux.range(1, 10).concatWith(Flux.error(new RuntimeException()))
		  .log()
		  .subscribe();
	}
}
