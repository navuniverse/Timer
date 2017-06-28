/**
 * 
 */
package com.rockingengineering.timer.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

import com.rockingengineering.timer.Timer;

/**
 * @author naveen
 *
 * @date 28-Jun-2017
 */
public class TimerTest {

	@Test
	public void testTimer() throws InterruptedException {

		ExecutorService executorService = Executors.newFixedThreadPool(10);

		for (int i = 1; i <= 10; i++) {
			executorService.submit(new Runnable() {

				@Override
				public void run() {
					Thread.currentThread().setName("Measure - " + System.nanoTime());
					System.out.println("Running Thread: " + Thread.currentThread().getName());
					measureTime();
				}

			});
		}
		Thread.sleep(1000);
	}

	private void measureTime() {
		Timer.timeIt();
		System.out.println("This is a test string to pass some time");

		System.out.println(Thread.currentThread().getName() + ": Thread running time: " + Timer.timeTaken());
	}
}