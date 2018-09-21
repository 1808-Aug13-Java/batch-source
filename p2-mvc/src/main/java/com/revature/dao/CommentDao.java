package com.revature.dao;

import java.util.List;

import com.revature.models.Comment;
import com.revature.models.Group;
import com.revature.models.Post;

public interface CommentDao {
	List<Comment> getComments();
	List<Comment> getCommentsByUserId(String id);
	Comment getCommentById(int id);
	int createComment(Comment comment);
}
