package com.revature.models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table
public class Post {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="postSequence")
	@SequenceGenerator(name="postSequence", allocationSize=1, sequenceName="SQ_POST_PK")
	@Column(name="POST_ID")
	@JsonProperty
	private int id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="USER_ID")
	@JsonProperty("user")
	private User user;
	
	@Column
	@JsonProperty
	private String title;
	
	@Column
	@JsonProperty
	private String content;
	
	
	public Post() {
		super();
	}

	public Post(int id, User user, String title, String content) {
		super();
		this.id = id;
		this.user = user;
		this.title = title;
		this.content = content;
	}
	
	public Post(User user, String title, String content) {
		super();
		this.id = id;
		this.user = user;
		this.title = title;
		this.content = content;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + id;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Post other = (Post) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (id != other.id)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
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
		return "Post [id=" + id + ", user=" + user + ", title=" + title + ", content=" + content + "]";
	}

}
