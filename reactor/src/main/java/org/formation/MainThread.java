package org.formation;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class MainThread {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		MainThread.methode4();
		
		MainThread.methode5();

	}

	public static void methode4() {

//		Mono<Integer> result = Flux.range(1, 10).map(i -> 3*i).filter(i -> i%2 == 0).flatMap(i -> Flux.just(i,-i)).log().reduce((x,y) -> x + y);
//		result.log().subscribeOn(Schedulers.newParallel("Par",2)).subscribe();
//		
		Flux.range(1, 10).map(i -> 3*i).filter(i -> i%2 == 0).flatMap(i -> Flux.just(i,-i)).subscribeOn(Schedulers.newParallel("Par",2)).log().subscribe();
		
		
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void methode5() {

		Mono<Integer> result = Flux.range(1, 10).map(i -> 3*i).publishOn(Schedulers.parallel()).filter(i -> i%2 == 0).flatMap(i -> Flux.just(i,-i)).log().reduce((x,y) -> x + y);
	
		result.log().subscribeOn(Schedulers.parallel()).subscribe();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
