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


		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((email == null) ? 0 : email.hashCode());
			result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
			result = prime * result + ((internId == null) ? 0 : internId.hashCode());
			result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
			result = prime * result + ((lifeExp == null) ? 0 : lifeExp.hashCode());
			result = prime * result + ((password == null) ? 0 : password.hashCode());
			result = prime * result + ((username == null) ? 0 : username.hashCode());
			return result;
		}


		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof Intern)) {
				return false;
			}
			Intern other = (Intern) obj;
			if (email == null) {
				if (other.email != null) {
					return false;
				}
			} else if (!email.equals(other.email)) {
				return false;
			}
			if (firstname == null) {
				if (other.firstname != null) {
					return false;
				}
			} else if (!firstname.equals(other.firstname)) {
				return false;
			}
			if (internId == null) {
				if (other.internId != null) {
					return false;
				}
			} else if (!internId.equals(other.internId)) {
				return false;
			}
			if (lastname == null) {
				if (other.lastname != null) {
					return false;
				}
			} else if (!lastname.equals(other.lastname)) {
				return false;
			}
			if (lifeExp == null) {
				if (other.lifeExp != null) {
					return false;
				}
			} else if (!lifeExp.equals(other.lifeExp)) {
				return false;
			}
			if (password == null) {
				if (other.password != null) {
					return false;
				}
			} else if (!password.equals(other.password)) {
				return false;
			}
			if (username == null) {
				if (other.username != null) {
					return false;
				}
			} else if (!username.equals(other.username)) {
				return false;
			}
			return true;
		}


		@Override
		public String toString() {
			return "Intern [" + (internId != null ? "internId=" + internId + ", " : "")
					+ (firstname != null ? "firstname=" + firstname + ", " : "")
					+ (lastname != null ? "lastname=" + lastname + ", " : "")
					+ (email != null ? "email=" + email + ", " : "")
					+ (username != null ? "username=" + username + ", " : "")
					+ (password != null ? "password=" + password + ", " : "")
					+ (lifeExp != null ? "lifeExp=" + lifeExp : "") + "]";
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