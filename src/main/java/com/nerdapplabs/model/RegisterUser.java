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

	}

