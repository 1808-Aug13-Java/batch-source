package com.revature.util;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.PostDao;
import com.revature.dao.PostDaoImpl;
import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImpl;
import com.revature.models.Post;


public class Driver {

	public Driver() {
		super();
	}
	
	public static void main(String[] args) {
		// create some users
		UserDao ud = new UserDaoImpl();
		PostDao pd = new PostDaoImpl();
		
//		ud.createUser(new User("ggl-123", "bob"));
//		ud.createUser(new User("ggl-124", "bob1"));
//		ud.createUser(new User("ggl-125", "bob2"));
//		ud.createUser(new User("ggl-126", "bob3"));
//		ud.createUser(new User("ggl-127", "bob4"));
//		ud.createUser(new User("ggl-128", "bob5"));
//		User u1 = ud.getUserById("ggl-123");
//		User u2 = ud.getUserById("ggl-124");
//		System.out.println(u1);
//		System.out.println(u2);
//		pd.createPost(new Post(u1, "Big Bob", "LOREM asdf asdfsadfdsasdfSAFSDasdfasdfFASDFASDasdfF asdfasdf ASD"));
//		pd.createPost(new Post(u1, "Title 2", "LOREM IPSdfaUMDF SAFSDFASDFASDF ASDFASDFASDF ASD"));
//		pd.createPost(new Post(u1, "Big Bob", "LOREM IPSUMDasdffFfdsafsd SAFSDFASDFASDF ASfsdfasdfDFASDFASDF fsadfasdfASD"));
//		pd.createPost(new Post(u1, "Big Bob", "LOREM IPSasUdfF SAFSDFASDFASDF ASDFAasdfasdfSDFASDF ASD"));
//		pd.createPost(new Post(u1, "Title", "LOREM asdfasdfIPasdfSUMDF SAasDFASDF ASDFASDasdfasdfFASDF ASD"));
//		pd.createPost(new Post(u1, "Big Bob", "LOREM IPSUMDF SasdfasdfAFSasdfsadfDFASDFASDF ASDFASDFASDF ASD"));
//		pd.createPost(new Post(u1, "Big Bob", "LOREM IPSUMDFasdf SAFSDFASDFASDasdfasdfF ASDFASDFASDF ASD"));
//		List<Post> posts = pd.getPosts();
//		System.out.println(posts);
//		ObjectMapper om = new ObjectMapper();
	}

}
