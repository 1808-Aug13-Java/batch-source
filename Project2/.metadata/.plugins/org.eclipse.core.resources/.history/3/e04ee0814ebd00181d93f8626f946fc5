package com.revature.salutem.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class Account {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="accountSequence")
	@SequenceGenerator(name="accountSequence",allocationSize=1,sequenceName="SQ_ACC_PK")
	@Column(name="ACCOUNT_ID")
	private int accountId;
	@Column(name="USERNAME")
	private String username;
	@Column(name="ACC_KEY")
	private String key;
	@ManyToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinTable(
			name="ACC_SYMPTOMS",
			joinColumns= {@JoinColumn(name="ACCOUNT_ID")},
			inverseJoinColumns={@JoinColumn(name="SYMPTOM_ID")}
			)
	private List<Symptom> pastSymptoms;
	public Account() {
		super();
	}
	public Account(int accountId, String username, String key, List<Symptom> pastSymptoms) {
		super();
		this.accountId = accountId;
		this.username = username;
		this.key = key;
		this.pastSymptoms = pastSymptoms;
	}
	public Account(String username, String key) {
		super();
		this.accountId = 0;
		this.username = username;
		this.key = key;
		this.pastSymptoms = new ArrayList<>();
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public List<Symptom> getPastSymptoms() {
		return pastSymptoms;
	}
	public void setPastSymptoms(List<Symptom> pastSymptoms) {
		this.pastSymptoms = pastSymptoms;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountId;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((pastSymptoms == null) ? 0 : pastSymptoms.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		Account other = (Account) obj;
		if (accountId != other.accountId)
			return false;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (pastSymptoms == null) {
			if (other.pastSymptoms != null)
				return false;
		} else if (!pastSymptoms.equals(other.pastSymptoms))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", username=" + username + ", key=" + key + ", pastSymptoms="
				+ pastSymptoms + "]";
	}
	
	
}
