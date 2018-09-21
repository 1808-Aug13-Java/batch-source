package com.revature.services;

import java.util.List;

import com.revature.models.Dragon;

public interface DragonService {

	public List<Dragon> getDragons();
	public Dragon getDragonById(Long id);
	public Dragon createDragon(Dragon dragon);
	public Dragon updateDragon(Dragon dragon);
	public Dragon deleteDragon(Dragon dragon);
}
