package org.formation;



import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;
import reactor.core.publisher.Mono;

public class MainError {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Hooks.onOperatorDebug();
		
//		MainError.methode3WithFlux();
//		
//		MainError.methode2();
		
//		MainError.methode1();
		
		MainError.methode5WithRetry();
		
	}
	
	public static void methode1() {
	 
		Flux.range(1, 10)
		  .map(MainError::badUncheckedMethod)
		  .log()
		  .subscribe();
	}
	
	public static void methode2() {
		 
		Flux.range(1, 10)
		  .map(MainError::badUncheckedMethod)
		  .log()
		  .subscribe(System.out::println, e -> System.err.println(e) );
	}
	
	public static void methode3WithFlux() {
		 
		Flux.range(1, 10)
		  .log()
		  .map(MainError::badUncheckedMethod)
		  .onErrorResume(e -> Flux.range(5, 5))
		  .subscribe(i -> System.out.println(i) );
	}
	
	public static void methode4() {
		 
		Flux.range(1, 10)
		  .log()
		  .map(MainError::badUncheckedMethod)
		  .onErrorResume(MainError::goodMethod)
		  .subscribe(i -> System.out.println(i) );
	}
	
	public static void methode5WithRetry() {
		 
		Flux.range(1, 10)
		  .log()
		  .map(MainError::badUncheckedMethod)
		  .retry(1)
		  .onErrorResume(MainError::goodMethod)
		  .subscribe(i -> System.out.println(i) );
	}

	
	
	public static int badUncheckedMethod(int i) throws RuntimeException {
		if ( i == 5 )
			throw new RuntimeException("5");
		return i;
	}
	
	public static Flux<Integer> goodMethod(Throwable e) {
		return Flux.just(0);
	}
}
