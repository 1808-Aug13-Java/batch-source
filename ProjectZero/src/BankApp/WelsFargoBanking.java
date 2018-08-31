package BankApp;

import java.util.Scanner;
import org.apache.log4j.Logger;

import ProjectDao.UserBankingDaoImpl;
import ProjectModel.UserBanking;


public class WelsFargoBanking {

	public static void main(String[] args) {
		UserBanking user1 = new UserBanking();
		UserBankingDaoImpl udao = new UserBankingDaoImpl();		
		user1.setUserName("JON DOE");
		Scanner sc = new Scanner(System.in);
		//String str = sc.nextLine();
		//System.out.println(str);
		//user1.setUserBalance(150.50);
		//udao.addUserBanking(user1);
		Account.viewer(user1);
		

	}
}
