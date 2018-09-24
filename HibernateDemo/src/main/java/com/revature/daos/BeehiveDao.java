package com.revature.daos;

import java.util.List;

import com.revature.models.Beehive;

public interface BeehiveDao {
	
	
	public List<Beehive> getBeehives();
	public Beehive getBeehiveById(int id);
	public int createBeehive(Beehive bh);
	public void deleteBeehive(Beehive bh);

}