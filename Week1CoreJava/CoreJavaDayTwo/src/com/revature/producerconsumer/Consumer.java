package com.revature.producerconsumer;

public class Consumer implements Runnable {
	
	Basket basket;
	
	public Consumer() {
		super();
	}
	
	public Consumer(Basket basket) {
		super();
		this.basket = basket;
	}
	
	public void run() {
		
		int value = 0;
		for (int i = 0; i < 10; i++) {
			value = basket.get();
			System.out.println(Thread.currentThread().getName() + " got " + value);
			try {
				Thread.sleep((int) Math.random() * 10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public Basket getBasket() {
		return basket;
	}

	public void setBasket(Basket basket) {
		this.basket = basket;
	}

}
