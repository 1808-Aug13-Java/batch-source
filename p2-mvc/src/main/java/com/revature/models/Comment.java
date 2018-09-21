package com.revature.models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Comment {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="commentSequence")
	@SequenceGenerator(name="commentSequence", allocationSize=1, sequenceName="SQ_COMMENT_PK")
	@Column(name="COMMENT_ID")
	@JsonProperty
	private int id;
	
	@Column
	@JsonProperty
	private String content;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@Column(name="POST_ID")
	@JsonProperty
	private Post post;
	
	@Column(name="USER_ID")
	@JsonProperty
	private User user;
	
	public Comment() {
		super();
	}

	public Comment(String content, Post post, User user) {
		super();
		this.content = content;
		this.post = post;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + id;
		result = prime * result + ((post == null) ? 0 : post.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (id != other.id)
			return false;
		if (post == null) {
			if (other.post != null)
				return false;
		} else if (!post.equals(other.post))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", content=" + content + ", post=" + post + ", user=" + user + "]";
	}
	
}
