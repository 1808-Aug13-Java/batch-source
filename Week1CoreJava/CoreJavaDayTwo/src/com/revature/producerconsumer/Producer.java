package com.revature.producerconsumer;

public class Producer implements Runnable {

	private Basket basket;
	
	public Producer() {
		super();
	}
	
	public Producer(Basket basket) {
		this.setBasket(basket);
	}
	
	
	@Override
	public void run() {
		
		for(int i = 0; i < 10; i++) {
			basket.put(i);
			System.out.println(Thread.currentThread().getName() + " put: " + i);
			
			try {
				Thread.sleep((int) Math.random() * 10000);
			} catch (InterruptedException e) {
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
