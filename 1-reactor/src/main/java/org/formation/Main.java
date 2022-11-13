package org.formation;

import java.util.ArrayList;
import java.util.List;

import reactor.core.publisher.Flux;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main.methode1();
	}

	public static void methode1() {

		List<Integer> elements = new ArrayList<>();

		Flux.range(1, 10).log().subscribe(elements::add, e -> System.err.println(e),() -> System.out.println(elements));
	}
}
