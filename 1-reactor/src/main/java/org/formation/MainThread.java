package org.formation;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

public class MainThread {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Scheduler daemons = Schedulers.newParallel("Par",2,true);

		for ( int i= 0; i< 5; i++) {
			MainThread.methode1().subscribeOn(daemons).log().subscribe();
		}
		
		 ThreadMXBean threadMxBean = ManagementFactory.getThreadMXBean();
		CountDownLatch latch = new CountDownLatch(1);
		Scheduler scheduler = Schedulers.newParallel("ParZip",2,false);
		MainThread.methode5().subscribeOn(scheduler).log().doFinally(sig -> {
			for ( long id : threadMxBean.getAllThreadIds())
				System.out.println(threadMxBean.getThreadInfo(id)) ;
            scheduler.dispose();
            System.out.println("Shut down all Scheduler worker threads");
            latch.countDown();
        }).subscribe();
		
		latch.await();
		for ( long id : threadMxBean.getAllThreadIds())
			System.out.println(threadMxBean.getThreadInfo(id)) ;
		
	}
	
	public static Flux<Integer> methode1() {

		return Flux.range(1, 10);
	}
	
	public static Flux<String>  methode5() {
		return Flux.just(1, 2, 3, 4).map(i -> i * 2).zipWith(Flux.range(0, Integer.MAX_VALUE),
				(one, two) -> String.format("First Flux: %d, Second Flux: %d", one, two));
	}

}
