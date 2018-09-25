package com.revature.beans;

public class Bear {
	protected int id;
	protected String name;
	protected Cave cave;
	public Bear() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Bear(int it, String name, Cave cave) {
		super();
		this.id = it;
		this.name = name;
		this.cave = cave;
	}
	public int getId() {
		return id;
	}
	public void setId(int it) {
		this.id = it;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Cave getCave() {
		return cave;
	}
	public void setCave(Cave cave) {
		this.cave = cave;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cave == null) ? 0 : cave.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Bear other = (Bear) obj;
		if (cave == null) {
			if (other.cave != null)
				return false;
		} else if (!cave.equals(other.cave))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Bear [id=" + id + ", name=" + name + ", cave=" + cave + "]";
	}
	public  void methodInBear() {
		
	}

}
