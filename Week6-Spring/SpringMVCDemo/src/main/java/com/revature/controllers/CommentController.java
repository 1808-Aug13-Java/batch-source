package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Comment;
import com.revature.services.CommentService;

@CrossOrigin
@RestController
public class CommentController {
	
	
	@Autowired
	CommentService commentService;
	
	@GetMapping("api/groups/{name}/posts/{id}/comments")
	public List<Comment> getCommentsAssociatedWithPost(@PathVariable("name") String name,
			@PathVariable("id") Integer id) {
		return commentService.listAllCommentsWithPost(id);
	}
	
	@PostMapping("api/groups/{name}/posts/{id}/comments")
	public Comment addCommentToPost(@RequestBody Comment comment, @PathVariable("name") String name,
			@PathVariable("id") Integer id) {
		commentService.createComment(comment);
		return comment;
	}
	
	@PutMapping("api/groups/{name}/posts/{id}/comments")
	public Comment updateCommentToPost(@RequestBody Comment comment, @PathVariable("name") String name,
			@PathVariable("id") Integer id) {
		commentService.updateComment(comment);
		return comment;
	}
	
	@RequestMapping(value="/api/posts/{name}", method={RequestMethod.HEAD, RequestMethod.OPTIONS, 
			RequestMethod.PATCH, RequestMethod.TRACE})
	public String notSupported() {
		return "not supported";
	}
	
}
