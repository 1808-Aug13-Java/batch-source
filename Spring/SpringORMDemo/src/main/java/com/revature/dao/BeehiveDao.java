package com.revature.dao;

import java.util.List;

import com.revature.models.Beehive;

public interface BeehiveDao {

	public List<Beehive> getBeehives();
	public Beehive getBeehiveById(int id);
	public int createBeehive(Beehive b);
	public void updateBeehive(Beehive b);
	public void deleteBeehiveById(int id);
}
