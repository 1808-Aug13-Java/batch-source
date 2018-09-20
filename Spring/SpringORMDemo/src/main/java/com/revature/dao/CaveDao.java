package com.revature.dao;

import java.util.List;

import com.revature.models.Cave;

public interface CaveDao {
	public List<Cave> getCaves();
	public Cave getCaveById(int id);
	public int createCave(Cave c);
	public void updateCave(Cave c);
	public void deleteCaveById(int id);
}
