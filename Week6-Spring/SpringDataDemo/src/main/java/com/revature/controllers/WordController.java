package com.revature.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Word;
import com.revature.repositories.WordRepository;
@RestController
@RequestMapping("/wor")
public class WordController {
  @Autowired
  WordRepository wordService;

  @GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
  public Iterable<Word> getWords() {
    return wordService.findAll();
  }

  @GetMapping(value="/{headword}", produces=MediaType.APPLICATION_JSON_VALUE)
  public Word getWord(@PathVariable("headword") String headword) {
    return wordService.findWordByHeadword(headword);
  }

//	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
//	//@ResponseBody
//	public Word addWord(@Valid @RequestBody Word w) {
//		return wordService.addWord(w);
//	}
//	
//	@PutMapping(value="/{headword}", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
//	//@ResponseBody
//	public Word updateWord(@Valid @RequestBody Word w, @PathVariable("headword") String headword) {
//		w.setHeadword(headword);
//		return wordService.updateWord(w);
//	}
//	
//	@DeleteMapping(value="/{headword}",consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
//	//@ResponseBody
//	public Word deleteWord(@RequestBody Word w, @PathVariable("headword") String headword) {
//		w.setHeadword(headword);
//		return wordService.deleteWord(w);
//	}
	
}

