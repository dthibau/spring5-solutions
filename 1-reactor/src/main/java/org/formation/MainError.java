package org.formation;

import reactor.core.publisher.Flux;

public class MainError {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		MainError.methode1();
//		
//		MainError.methode2();
		
		MainError.methode3();
	}

	public static void methode1() {

		Flux.range(1, 10).concatWith(Flux.error(new RuntimeException())).log().subscribe();
	}

	public static void methode2() {

		Flux.range(1, 10)
		.concatWith(Flux.error(new RuntimeException()))
		.log().subscribe(
				System.out::println,
				e -> System.err.println(e)
		);
	}
	
	public static void methode3() {
		 
		Flux.range(1, 10)
		  .concatWith(Flux.error(new RuntimeException()))
		  .onErrorResume(e -> Flux.range(11, 5))
		  .subscribe(i -> System.out.println(i) );
	}
}
