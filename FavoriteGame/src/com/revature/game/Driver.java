package com.revature.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Driver {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		
		ArrayList<User> names = new ArrayList<User>();
		User crandon = new User("Crandon");
		User jeremiah = new User("Jeremiah");
		User jerry = new User("Jerry");
		User julie = new User("Julie");
		names.add(crandon);
		names.add(jeremiah);
		names.add(jerry);
		names.add(julie);
		
		ArrayList<Question> questions = new ArrayList<Question>();
		Question q1 = new Question("During his embarrassing\n Dundie award presentation,"
				+ " Michael covers a number\n of popular songs. To whom is Michael "
				+ "presenting a Dundie award\n when he sings along to \"You Sexy Thing\""
				+ " by '70s\n British funk band Hot Chocolate?");
		Question q2 = new Question("This character became "
				+ "Jim's \nlove interest after\n he m"
				+ "oved to \nthe Stamford branch in season three "
				+ "and joined \nthe Scranton office during the merger. Who left the "
				+ "office to run her own"
				+ "\n branch when she discovered that Jim\n was still in love with Pam?");
		
		Question q3 = new Question("What city in Pennsylvania is Michael's branch located?");
		
		Question q4 = new Question("What substance does Jim "
				+ "put office supplies owned by Dwight and Andy into?");
		
		questions.add(q1);
		questions.add(q2);
		questions.add(q3);
		questions.add(q4);
		
		
		Game game = new Game(names, questions);
		
		game.start();
		
	}
	
}
