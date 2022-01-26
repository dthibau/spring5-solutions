package org.formation;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MainApi {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

////		MainApi.methode1();
//
////		MainApi.methode2();
//////		
//		MainApi.methode3();
//		
//		MainApi.methode4();
//		
		MainApi.methode5();
//		
//		MainApi.methode6();

	}

	public static void methode1() {

		List<Integer> elements = new ArrayList<>();

		Flux.range(1, 10).log().subscribe(elements::add);
	}

	public static void methode2() {
		List<Integer> elements = new ArrayList<>();

		Flux.range(1, 10).log().subscribe(new Subscriber<Integer>() {
			@Override
			public void onSubscribe(Subscription s) {
				s.request(Long.MAX_VALUE);
			}

			@Override
			public void onNext(Integer integer) {
				elements.add(integer);
			}

			@Override
			public void onError(Throwable t) {	}

			@Override
			public void onComplete() {	}
		});
	}

	public static void methode3() {
		List<Integer> elements = new ArrayList<>();

		Flux.range(1, 10).log().subscribe(new Subscriber<Integer>() {
			private Subscription s;
			int index = 0;

			@Override
			public void onSubscribe(Subscription s) {
				this.s = s;
				this.s.request(2);
			}

			@Override
			public void onNext(Integer integer) {
				elements.add(integer);
				index++;
				if (index % 2 == 0) {
					s.request(2);
				}
			}

			@Override
			public void onError(Throwable t) {
			}

			@Override
			public void onComplete() {
			}
		});
	}

	public static void methode4() {

		Mono<Integer> result = Flux.range(1, 10).map(i -> 3 * i)
												.filter(i -> i % 2 == 0)
												.flatMap(i -> Flux.just(i, -i))
												.log()											
												.reduce(0,(x, y) -> x + y);

		result.subscribe(System.out::println);
		
	}

	public static void methode5() {
		Flux.just(1, 2, 3, 4).map(i -> i * 2).zipWith(Flux.range(0, Integer.MAX_VALUE),
				(one, two) -> String.format("First Flux: %d, Second Flux: %d", one, two)).log().subscribe();
	}

	public static void methode6() {
//		Flux.interval(Duration.ofSeconds(1)).log().subscribe();
		Flux.range(1,3).delayElements(Duration.ofSeconds(1)).log().subscribe();
		try {
			Thread.sleep(5500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
