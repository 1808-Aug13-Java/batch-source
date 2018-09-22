package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Word;
import com.revature.repositories.WordRepository;

@Service
public class WordServiceImpl implements WordService {

	@Autowired
	WordRepository wordRepo;
	
	@Override
	public List<Word> getWords() {
		return wordRepo.findAll();
	}

	@Override
	public Word getWord(String headword) {
		return wordRepo.getOne(headword);
	}

	@Override
	public Word addWord(Word newWord) {
		return wordRepo.save(newWord);
	}

	@Override
	public Word updateWord(Word word) {
		return wordRepo.save(word);
	}

	@Override
	public Word deleteWord(Word word) {
		wordRepo.delete(word);
		return word;
		
	}

}
