package com.revature.dao;

import java.util.List;

import com.revature.models.Profile;

public interface ProfileDao {
	public Profile getProfileById(int id);
	public List<Profile> getProfiles();
	public int updateProfile(Profile profile);
}
