package com.revature.beans;


public class Intern {

		private Long internId;
		
		private String firstname;
		
		private String lastname;
		
		private String email;

		private String username;
		
		private String password;
		
		private Long lifeExp;
		
		
		public Intern() {
			super();
			// TODO Auto-generated constructor stub
		}


		public Intern(Long userId, String firstname, String lastname, String email, String username, String password, Long lifeExp) {
			super();
			this.internId = userId;
			this.firstname = firstname;
			this.lastname = lastname;
			this.email = email;
			this.username = username;
			this.password = password;
			this.lifeExp = lifeExp;
		}


		public Long getInternId() {
			return internId;
		}


		public void setInternId(Long internId) {
			this.internId = internId;
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


		public String getEmail() {
			return email;
		}


		public void setEmail(String email) {
			this.email = email;
		}


		public String getUsername() {
			return username;
		}


		public void setUsername(String username) {
			this.username = username;
		}


		public String getPassword() {
			return password;
		}


		public void setPassword(String password) {
			this.password = password;
		}


		public Long getLifeExp() {
			return lifeExp;
		}


		public void setLifeExp(Long lifeExp) {
			this.lifeExp = lifeExp;
		}


	

}
