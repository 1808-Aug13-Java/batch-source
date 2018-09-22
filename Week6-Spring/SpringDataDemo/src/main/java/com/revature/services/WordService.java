package com.revature.services;

import java.util.List;

import com.revature.models.Word;

public interface WordService {

	public List<Word> getWords();
	public Word getWord(String headword);
	public Word addWord(Word newWord);
	public Word updateWord(Word word);
	public Word deleteWord(Word word);
	
	
}
