package com.revature.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	public User findUserByUsername(String username);
	public User findUserByEmail(String email);
	public User findUserByUsernameAndPassword(String username, String password);
}
