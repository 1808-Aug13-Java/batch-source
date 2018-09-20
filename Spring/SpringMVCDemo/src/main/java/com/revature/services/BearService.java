package com.revature.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.beans.Bear;

@Service
public class BearService {

	List<Bear> bears = new ArrayList<>();
	
	public BearService() {
		bears.add(new Bear(1, "Griz", Date.valueOf("2014-04-20")));
		bears.add(new Bear(2, "Stepehn Colbear", Date.valueOf("1974-08-02")));
		bears.add(new Bear(3, "Ice Bear", Date.valueOf("2014-10-09")));
		bears.add(new Bear(4, "Yogi", Date.valueOf("1952-04-12")));
		bears.add(new Bear(5, "Bearie White", Date.valueOf("1944-09-12")));
	}
	
	public List<Bear> getBears(){
		return bears;
	}
	
	public Bear getBearById(int id) {
		for (Bear b: bears) {
			if(id == b.getId()) {
				return b;
			}
		}
		return null;
	}
}
