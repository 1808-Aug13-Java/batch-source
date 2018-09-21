package com.revature.services;

import java.util.List;

import com.revature.models.Faerye;

public interface FaeryeService {
	public List<Faerye> findAllFaeryes();
	public Faerye findFaeryeById(Long id);
	public Faerye addFaerye(Faerye newFaerye);
	public Faerye updateFaerye(Faerye faerye);
	public Faerye deleteFaerye(Faerye faerye);
}
