package org.springframework.mytests.custom;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestThread {


	public static void main(String[] args) {
		ExecutorService threadPool = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 5; i++) {
			threadPool.submit(() -> {
				System.out.println("current thread name" + Thread.currentThread().getName());
				Object object = null;
				System.out.print("result## "+object.toString());
			});
		}

	}

}
