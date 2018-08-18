package com.revature.thegame;

public class TheGame {

	private String associate;
	private String question;
	public TheGame(String associate, String question) {
		super();
		this.associate = associate;
		this.question = question;
	}
	public TheGame() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getAssociate() {
		return associate;
	}
	public void setAssociate(String associate) {
		this.associate = associate;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((associate == null) ? 0 : associate.hashCode());
		result = prime * result + ((question == null) ? 0 : question.hashCode());
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
		TheGame other = (TheGame) obj;
		if (associate == null) {
			if (other.associate != null)
				return false;
		} else if (!associate.equals(other.associate))
			return false;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "TheGame [associate=" + associate + ", question=" + question + "]";
	}
	
	
	

}
