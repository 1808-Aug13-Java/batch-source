package com.revature.beans;

public class Word {
	private String headword;
	private String significance;
	private String kind;
	public Word() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Word(String headword, String significance, String kind) {
		super();
		this.headword = headword;
		this.significance = significance;
		this.kind = kind;
	}
	public String getHeadWord() {
		return headword;
	}
	public void setHeadWord(String headword) {
		this.headword = headword;
	}
	public String getSignificance() {
		return significance;
	}
	public void setSignificance(String significance) {
		this.significance = significance;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((headword == null) ? 0 : headword.hashCode());
		result = prime * result + ((kind == null) ? 0 : kind.hashCode());
		result = prime * result + ((significance == null) ? 0 : significance.hashCode());
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
		Word other = (Word) obj;
		if (headword == null) {
			if (other.headword != null)
				return false;
		} else if (!headword.equals(other.headword))
			return false;
		if (kind == null) {
			if (other.kind != null)
				return false;
		} else if (!kind.equals(other.kind))
			return false;
		if (significance == null) {
			if (other.significance != null)
				return false;
		} else if (!significance.equals(other.significance))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Word [headword=" + headword + ", significance=" + significance + ", kind=" + kind + "]";
	}
	
	
	
}
