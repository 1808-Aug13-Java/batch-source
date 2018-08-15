package com.revature.fourpillars;

public class Zoo {
	
	public static void add(int a, int b)
	{
		System.out.println(a + b);		//these add functions are also examples of Polymorphism in methods
	}									//because they are overloaded functions so that the parameters
										//are different, though the name is the same
	
	public static void add(String a, String b)
	{
		System.out.println(a + b);
	}
	
	public static void main(String[] args)
	{
		//testing overrided displayInfo function
		Cat catA = new Cat(4, "female", 20);
		System.out.print("CatA: ");
		catA.displayInfo();						//the methods for displayInfo() are polymorphic because they
		Dog dogA = new Dog(4, "male", 30);		//are implemented differently for each subclass
		System.out.print("DogA: ");
		dogA.displayInfo();
		Bird birdA = new Bird(2, "male", "toucan");
		System.out.print("BirdA: ");
		birdA.displayInfo();
		System.out.println();
		
		//testing overrided equals function
		Cat catB = new Cat(4, "female", 20);
		Cat catC = new Cat(4, "female", 24);
		System.out.println("Cat A equals Cat B?: "+catB.equals(catA));
		System.out.println("Cat C equals Cat A?: "+catC.equals(catA));
		System.out.println();
		
		//ttesting overloaded function add()
		System.out.print("Adding 3 + 4: ");		
		add(3, 4);
		System.out.print("Adding 'starry' and ' skies': ");
		add("starry", " skies");
		System.out.println();
		
		//testing checked exception
		Cat cat1 = new Cat(4, "female", 20);
		cat1.setTailLength(-3);
		cat1.displayInfo();
		System.out.println();
		
		//testing unchecked exception
		Cat cat2 = new Cat(3, "female", 20);
		int wrongtype = 5;
		System.out.println("cat equals integer?: "+ cat2.equals(wrongtype));
		
		System.out.println("seeing if it keeps running");
	}
	
}
