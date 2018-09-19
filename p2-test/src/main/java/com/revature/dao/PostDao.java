package com.revature.dao;

import java.util.List;

import com.revature.models.Post;

public interface PostDao {
	List<Post> getPosts();
	List<Post> getPostsByPage(int page);
	List<Post> getPostsByUserId(String id);
	int createPost(Post post);
	void updatePost(Post post);
	
	
}
