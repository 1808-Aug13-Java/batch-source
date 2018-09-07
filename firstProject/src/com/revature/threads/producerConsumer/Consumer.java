package com.revature.threads.producerConsumer;

public class Consumer implements Runnable{

	Basket basket;
	
	public Consumer() {
		super();
	}
	
	public Consumer(Basket b) {
		this.basket = b;
	}
	
	@Override
	public void run() {
		int value = 0;
		
		for(int i=0; i<10;i++) {
			value = basket.get();
			System.out.println(Thread.currentThread().getName() + " got: " + value);
			
			try {
				Thread.sleep((int) (Math.random()*1500));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	

}
