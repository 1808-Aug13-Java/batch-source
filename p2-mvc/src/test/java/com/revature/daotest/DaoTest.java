package com.revature.daotest;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.*;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.revature.dao.*;
import com.revature.models.*;

public class DaoTest {
	// static final Logger logger = Logger.getLogger(DaoTest.class);
	
	// ---------------------------------------------------
	// COMMENT DAO METHODS
	// ---------------------------------------------------
	
	@Test 
	public void canRetrieveAllComments() {
		CommentDaoImpl cdi = new CommentDaoImpl();
		List<Comment> comments = cdi.getComments();
		
		User a = new User();
		User b = new User();

        assertThat(comments, hasItems(
        		new Comment()
                // new Comment("COMMENT 1 GOES HERE", new Post(a, "TITLE 1")),
                // new Comment("COMMENT 2 GOES HERE", new Post(b, "TITLE 2"))
        ));
	}
	
	@Test
	public void canRetrieveCommentById() {
		CommentDaoImpl cdi = new CommentDaoImpl();
		Comment com = cdi.getCommentById(1); // PUT AN ACTUAL ID NUMBER IN HERE
		assertEquals("SOMETHING GOES HERE", com.getUser().getNickname());
	}
	
	@Test 
	public void canRetrieveCommentsByUserId() {
		CommentDaoImpl cdi = new CommentDaoImpl();
		List<Comment> comments = cdi.getCommentsByUserId("Chandrika"); // PUT AN ACTUAL USER ID IN HERE
		assertEquals("ACTUAL COMMENT CONTENT GOES HERE", comments.get(0).getContent());
	}
	
	@Test
	public void canCreateComment() {
		CommentDaoImpl cdi = new CommentDaoImpl();
		Comment comment = new Comment();
		// use set methods
		cdi.createComment(comment);
		assertEquals(comment, cdi.getCommentById(comment.getId()));
	}
	
	
	// ---------------------------------------------------
	// GROUP DAO METHODS
	// ---------------------------------------------------
	
	// ---------------------------------------------------
	// POST DAO METHODS
	// ---------------------------------------------------
	
	// ---------------------------------------------------
	// USER DAO METHODS
	// ---------------------------------------------------
	
	// ---------------------------------------------------
	// VOTE DAO METHODS
	// ---------------------------------------------------
}