package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.Word;

@Repository
public interface WordRepository extends JpaRepository<Word, String> {
	
	public Word findWordByHeadword(String headword);

}
