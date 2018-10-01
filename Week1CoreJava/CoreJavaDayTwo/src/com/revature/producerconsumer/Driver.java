package com.revature.producerconsumer;

public class Driver {
	public static void main(String[] args) {
		
		System.out.println(Thread.currentThread().getName());
		
		Basket basket = new Basket();
		
		Runnable producerJob = new Producer(basket);
		
		Runnable consumerJob = new Consumer(basket);
		
		
		Thread producerWorker = new Thread(producerJob, "PRODUCER");
		Thread consumerWorker = new Thread(consumerJob, "CONSUMER");
		
		producerWorker.start();
		consumerWorker.start();
		
	}
}
