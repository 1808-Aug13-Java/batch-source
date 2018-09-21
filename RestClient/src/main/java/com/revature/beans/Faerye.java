package com.revature.beans;

public class Faerye {

	private Long faeryeId;
	
	private String firstname;
	
	private String lastname;

	private String power;
	
	private Boolean babyStealer;

	public Long getFaeryeId() {
		return faeryeId;
	}

	public void setFaeryeId(Long faeryeId) {
		this.faeryeId = faeryeId;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}

	public Boolean getBabyStealer() {
		return babyStealer;
	}

	public void setBabyStealer(Boolean babyStealer) {
		this.babyStealer = babyStealer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((babyStealer == null) ? 0 : babyStealer.hashCode());
		result = prime * result + ((faeryeId == null) ? 0 : faeryeId.hashCode());
		result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
		result = prime * result + ((power == null) ? 0 : power.hashCode());
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
		Faerye other = (Faerye) obj;
		if (babyStealer == null) {
			if (other.babyStealer != null)
				return false;
		} else if (!babyStealer.equals(other.babyStealer))
			return false;
		if (faeryeId == null) {
			if (other.faeryeId != null)
				return false;
		} else if (!faeryeId.equals(other.faeryeId))
			return false;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		if (power == null) {
			if (other.power != null)
				return false;
		} else if (!power.equals(other.power))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Faerye [faeryeId=" + faeryeId + ", firstname=" + firstname + ", lastname=" + lastname + ", power="
				+ power + ", babyStealer=" + babyStealer + "]";
	}

	public Faerye(Long faeryeId, String firstname, String lastname, String power, Boolean babyStealer) {
		super();
		this.faeryeId = faeryeId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.power = power;
		this.babyStealer = babyStealer;
	}

	public Faerye() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
