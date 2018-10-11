package com.revature.salutem.models;
import javax.persistence.*;

//@Entity
//@Table
public class IssueInfo {
//	
//	@Id
//	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="issueInfoGenerator")
//	@SequenceGenerator(name="issueInfoGenerator",allocationSize=1,sequenceName="SQ_ISS_INFO_PK")
//	@Column(name="INFO_ID")
	private int issueid;
//	@Column
	private String description;
//	@Column(name="DESC_SHORT")
	private String descriptionshort;
//	@Column(name="CONDITION")
	private String medicalCondition;
//	@Column
	private String name;
//	@Column(name="SYMPTOMS")
	private String possibleSymptoms;
//	@Column(name="PROFESSIONAL")
	private String profName;
//	private String synonyms;
//	@Column(name="TREATMENT")
	private String treatmentDescription;
	
	public IssueInfo(int issueid, String description, String descriptionshort, String medicalCondition, String name,
			String possibleSymptoms, String profName, String treatmentDescription) {
		super();
		this.issueid = issueid;
		this.description = description;
		this.descriptionshort = descriptionshort;
		this.medicalCondition = medicalCondition;
		this.name = name;
		this.possibleSymptoms = possibleSymptoms;
		this.profName = profName;
		this.treatmentDescription = treatmentDescription;
	}

	public IssueInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getIssueid() {
		return issueid;
	}

	public void setIssueid(int issueid) {
		this.issueid = issueid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescriptionshort() {
		return descriptionshort;
	}

	public void setDescriptionshort(String descriptionshort) {
		this.descriptionshort = descriptionshort;
	}

	public String getMedicalCondition() {
		return medicalCondition;
	}

	public void setMedicalCondition(String medicalCondition) {
		this.medicalCondition = medicalCondition;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPossibleSymptoms() {
		return possibleSymptoms;
	}

	public void setPossibleSymptoms(String possibleSymptoms) {
		this.possibleSymptoms = possibleSymptoms;
	}

	public String getProfName() {
		return profName;
	}

	public void setProfName(String profName) {
		this.profName = profName;
	}

	public String getTreatmentDescription() {
		return treatmentDescription;
	}

	public void setTreatmentDescription(String treatmentDescription) {
		this.treatmentDescription = treatmentDescription;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((descriptionshort == null) ? 0 : descriptionshort.hashCode());
		result = prime * result + issueid;
		result = prime * result + ((medicalCondition == null) ? 0 : medicalCondition.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((possibleSymptoms == null) ? 0 : possibleSymptoms.hashCode());
		result = prime * result + ((profName == null) ? 0 : profName.hashCode());
		result = prime * result + ((treatmentDescription == null) ? 0 : treatmentDescription.hashCode());
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
		IssueInfo other = (IssueInfo) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (descriptionshort == null) {
			if (other.descriptionshort != null)
				return false;
		} else if (!descriptionshort.equals(other.descriptionshort))
			return false;
		if (issueid != other.issueid)
			return false;
		if (medicalCondition == null) {
			if (other.medicalCondition != null)
				return false;
		} else if (!medicalCondition.equals(other.medicalCondition))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (possibleSymptoms == null) {
			if (other.possibleSymptoms != null)
				return false;
		} else if (!possibleSymptoms.equals(other.possibleSymptoms))
			return false;
		if (profName == null) {
			if (other.profName != null)
				return false;
		} else if (!profName.equals(other.profName))
			return false;
		if (treatmentDescription == null) {
			if (other.treatmentDescription != null)
				return false;
		} else if (!treatmentDescription.equals(other.treatmentDescription))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "IssueInfo [issueid=" + issueid + ", description=" + description + ", descriptionshort="
				+ descriptionshort + ", medicalCondition=" + medicalCondition + ", name=" + name + ", possibleSymptoms="
				+ possibleSymptoms + ", profName=" + profName + ", treatmentDescription=" + treatmentDescription + "]";
	}
	
}
