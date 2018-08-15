package com.revature.fourPillars;

public class CoreDriver {

	public static void main(String[] args) {
		System.out.println("Creating default Dog1");
		Dog dog1 = new Dog();
		
		System.out.println("Creating Dog2 with 3 legs, named Lucky");
		Dog dog2 = new Dog("Lucky", 3);
		
		System.out.println("Printing Dog1, Dog2");
		System.out.println(dog1);
		System.out.println(dog2);
		
		System.out.println("Lucky, come.");
		dog2.come();
		System.out.println("Doug, sit, stay.");
		dog1.sit();
		dog1.stay();
		
		System.out.println("Dogs 1 and 2 mate, making pup.");
		Dog pup = (Dog) dog1.mate();
		pup.name("pup");
		System.out.println(pup);
		
		System.out.println("pup.name(\"Jerry\")");
		pup.name("Jerry");
		System.out.println(pup);
		
		
		System.out.println("Making a few cats.");
		Cat cat1 = new Cat();
		Cat cat2 = new Cat("Tom", 4);
		Cat cat3 = new Cat("Old Bob", 2);
		
		System.out.println(cat1);
		System.out.println(cat2);
		System.out.println(cat3);
		
		
		
		System.out.println("Making geckos");
		Gecko geck1 = new Gecko();
		Gecko geck2 = new Gecko("Geck", true, false);
		
		System.out.println(geck1);
		System.out.println(geck2);
		
		
		System.out.println("One Liopleurodon");
		Liopleurodon lio = new Liopleurodon();
		System.out.println(lio);
		System.out.println("lio.name(\"Lio\")");
		lio.name("Lio");
		System.out.println(lio);
		
		
		//list mammals list reptiles list animals
		System.out.println("Make an array of Reptiles, one of Mammals, one of Animals.");
		Reptile[] reptileArray = {geck1, geck2, lio};
		Mammal[] mammalArray = {dog1, dog2, pup, cat1, cat2, cat3};
		Animal[] animalArray = new Animal[9];
		int i = 0;
		
		System.out.println("ForEach reptile");
		for(Reptile r: reptileArray)
		{
			System.out.println(r.name());
			r.speak();
			r.eat();
			System.out.println(r.genus());
			System.out.println(r);
			animalArray[i++] = r;
		}
		
		for(Mammal m: mammalArray)
		{	
			System.out.println(m.name());
			m.speak();
			m.eat();
			m.shed();
			System.out.println(m.genus());
			System.out.println(m);
			animalArray[i++] = m;
		}
		
		System.out.println("Cats and Geckos are special.");
		System.out.println("Old Bob should be put down... (OldBob.kill())");
		try{
			cat3.kill();
		} catch (GuiltOfConscienceException e) {
			System.out.println("Exception encountered: " + e.getMessage());
		}
		
		System.out.println("Geckos have expendible legs.");
		System.out.println("While (true) pull leg;");
		while (true) {
			try {
				geck1.loseLimb();
			}catch (NotEnoughLegsException e) {
				System.out.println(e.getMessage());
				break;
			}
		}
		
		System.out.println("All animals .come(), if they can. then print them.");
		for(Animal a: animalArray)
		{
			try {
				a.come();
			}catch(InabilityToPerformActionException e) {
				System.out.println("Exception encountered: " + e.getMessage());
			}finally {
				System.out.println(a);
			}
		}
	}

}
