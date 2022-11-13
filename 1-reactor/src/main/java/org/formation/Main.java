package org.formation;

import java.util.ArrayList;
import java.util.List;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import reactor.core.publisher.Flux;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main.methode1();
		
		Main.methode2();
		
		Main.methode3();
	}

	public static void methode1() {

		List<Integer> elements = new ArrayList<>();

		Flux.range(1, 10).log().subscribe(elements::add, e -> System.err.println(e),() -> System.out.println(elements));
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
			public void onError(Throwable t) {	System.err.println(t); }

			@Override
			public void onComplete() {System.out.println(elements);	}
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
			public void onError(Throwable t) {	System.err.println(t); }

			@Override
			public void onComplete() {System.out.println(elements);	}
		});
	}
}
