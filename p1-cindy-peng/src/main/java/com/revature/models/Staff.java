package com.revature.models;

public class Staff {
	private int staffId;		//at most 3 digit
	private String staffName;  //100 char
	private String userN;		//30 char
	private String passW;		//50 char
	private int manId;			//at most 3 digit
	private String staffRole;  //'EMPLOYEE', 'BOTH', 'MANAGER' (choices on html)
	private String phone;       //20 char 
	
	public Staff() {
		super();
	}

	public Staff(int id, String name, String userN, String passW, int manId, String staffRole,
			String phone) {
		super();
		this.staffId = id;
		this.staffName = name;
		this.userN = userN;
		this.passW = passW;
		this.manId = manId;
		this.staffRole = staffRole;
		this.phone = phone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + manId;
		result = prime * result + ((passW == null) ? 0 : passW.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + staffId;
		result = prime * result + ((staffName == null) ? 0 : staffName.hashCode());
		result = prime * result + ((staffRole == null) ? 0 : staffRole.hashCode());
		result = prime * result + ((userN == null) ? 0 : userN.hashCode());
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
		Staff other = (Staff) obj;
		if (manId != other.manId)
			return false;
		if (passW == null) {
			if (other.passW != null)
				return false;
		} else if (!passW.equals(other.passW))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (staffId != other.staffId)
			return false;
		if (staffName == null) {
			if (other.staffName != null)
				return false;
		} else if (!staffName.equals(other.staffName))
			return false;
		if (staffRole == null) {
			if (other.staffRole != null)
				return false;
		} else if (!staffRole.equals(other.staffRole))
			return false;
		if (userN == null) {
			if (other.userN != null)
				return false;
		} else if (!userN.equals(other.userN))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Staff [staffId=" + staffId + ", staffName=" + staffName + ", userN=" + userN + ", passW="
				+ passW + ", manId=" + manId + ", staffRole=" + staffRole + ", phone=" + phone + "]";
	}

	public int getStaffId() {
		return staffId;
	}

	public String getStaffName() {
		return staffName;
	}

	public String getUserN() {
		return userN;
	}

	public String getPassW() {
		return passW;
	}

	public int getManId() {
		return manId;
	}

	public String getStaffRole() {
		return staffRole;
	}

	public String getPhone() {
		return phone;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public void setUserN(String userN) {
		this.userN = userN;
	}

	public void setPassW(String passW) {
		this.passW = passW;
	}

	public void setManId(int manId) {
		this.manId = manId;
	}

	public void setStaffRole(String staffRole) {
		this.staffRole = staffRole;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	
	
	
}
