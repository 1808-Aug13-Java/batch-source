package com.revature.bean;

public class Celebrity
{
	private Integer id;

	private String name;

	private Integer age;
	
	private String occupation;
	public Celebrity() 
	{
		super();
	}
	
	public Celebrity(Integer id, String name, Integer age, String occupation) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.occupation = occupation;
	}
	
	public Integer getId()
	{
		return id;
	}
	
	public void setId(Integer id)
	{
		this.id = id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public Integer getAge() 
	{
		return age;
	}
	
	public void setAge(Integer age)
	{
		this.age = age;
	}
	public String getOccupation() {
		return occupation;
	}
	
	public void setOccupation(String occupation) 
	{
		this.occupation = occupation;
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((age == null) ? 0 : age.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((occupation == null) ? 0 : occupation.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Celebrity other = (Celebrity) obj;
		if (age == null) {
			if (other.age != null)
				return false;
		} else if (!age.equals(other.age))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (occupation == null) {
			if (other.occupation != null)
				return false;
		} else if (!occupation.equals(other.occupation))
			return false;
		return true;
	}
	
	@Override
	public String toString() 
	{
		return "Celebrity [id=" + id + ", name=" + name + ", age=" + age + ", occupation=" + occupation + "]";
	}


}
