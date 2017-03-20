package com.nerdapplabs.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "registeruser")
public class RegisterUser {
	
	
		@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
		private long id;
		private String email;
		private String password;
		private String firstname;
        private String lastname;
        private String designation;
	    
		public RegisterUser() {
	    	
	    }
	    
	    public RegisterUser (long id) {
	    	   this.id = id;
	    }
	    
	    public long getId() {
	    	return id;
	    }
	    
	    public void setId(long id) {
	    	this.id = id;
	    }
	    
		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
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

			public String getDesignation() {
				return designation;
			}

			public void setDesignation(String designation) {
				this.designation = designation;
			}


	}

