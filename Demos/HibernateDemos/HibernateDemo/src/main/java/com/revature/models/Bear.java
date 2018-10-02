package com.revature.models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table
public class Bear {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="bearSequence")
	@SequenceGenerator(name="bearSequence", allocationSize=1, sequenceName="SQ_BEAR_PK")
	@Column(name="BEAR_ID")
	private int id;
	
	@Column(name="BEAR_NAME")
	private String name;
	
	@Column
	private Date birthday;
	
	// @Transient
	@ManyToOne
	@JoinColumn(name="CAVE_ID")
	private Cave cave;
	
	// @Transient
	@ManyToMany
	@JoinTable(
			name="BEAR_BEEHIVE",
			joinColumns = {@JoinColumn(name="BEAR_ID")},
			inverseJoinColumns = {@JoinColumn(name="BEEHIVE_ID")}
			)
	private List<Beehive> beehives = new ArrayList<>();
	public Bear() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Bear(int id, String name, Date birthday, Cave cave, List<Beehive> beehives) {
		super();
		this.id = id;
		this.name = name;
		this.birthday = birthday;
		this.cave = cave;
		this.beehives = beehives;
	}
	
	public Bear(String name, Date birthday, Cave cave) {
		super();
		this.name = name;
		this.birthday = birthday;
		this.cave = cave;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Cave getCave() {
		return cave;
	}
	public void setCave(Cave cave) {
		this.cave = cave;
	}
	public List<Beehive> getBeehives() {
		return beehives;
	}
	public void setBeehives(List<Beehive> beehives) {
		this.beehives = beehives;
	}
	public void addBeehive(Beehive bh) {
		this.beehives.add(bh);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((beehives == null) ? 0 : beehives.hashCode());
		result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
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
		if (beehives == null) {
			if (other.beehives != null)
				return false;
		} else if (!beehives.equals(other.beehives))
			return false;
		if (birthday == null) {
			if (other.birthday != null)
				return false;
		} else if (!birthday.equals(other.birthday))
			return false;
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
		return "Bear [id=" + id + ", name=" + name + ", birthday=" + birthday + ", cave=" + cave + ", beehives="
				+ beehives + "]";
	}
	
	
}
