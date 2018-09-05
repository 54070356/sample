package sample.javacore.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;


public class MultiThreadSample {
	/**
	 * terminate thread pool after all tasks complete
	 * @throws InterruptedException
	 */
	@Test
	public void test_ExecutorService() throws InterruptedException {
		ExecutorService es = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		long start = System.currentTimeMillis();
		for (int i = 0; i < 100; i++) {
			final int c = i;
		    es.submit(() -> {
		         System.out.println("hello "+c);
		    });
		}
		
		System.out.println("before terminated");
		es.shutdown();
		es.awaitTermination(10, TimeUnit.SECONDS);
		long end = System.currentTimeMillis();
		long time = end - start;
		System.out.println("time escaped="+time);
		System.out.println("terminated");
	}
}
