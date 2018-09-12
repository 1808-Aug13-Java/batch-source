package com.revature.test;

import com.revature.util.StringHasher;

public class TestMain2 {

	public static void main(String[] args) {
		byte b1 = -127;
		
		int i1 = (0xFF & b1);
		
		System.out.println(i1);
		
		System.out.println(StringHasher.sha256Hash("man"));
		System.out.println(StringHasher.sha256Hash("emp"));
		System.out.println(StringHasher.sha256Hash("qwer"));
		System.out.println(StringHasher.sha256Hash("qwer"));
		System.out.println(StringHasher.sha256Hash("asdf"));
		System.out.println(StringHasher.sha256Hash("asdf").length());
		System.out.println(StringHasher.sha256Hash("qwer").length());
	}

}
