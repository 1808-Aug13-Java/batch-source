package com.revature.dao;

import java.util.List;

import com.revature.models.Post;

public interface PostDao {
	List<Post> getPosts();
	Post getPostsByUserId(String id);
	int createPost(Post post);
}
