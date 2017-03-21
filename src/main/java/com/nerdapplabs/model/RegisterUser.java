package com.nerdapplabs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "registeruser")
public class RegisterUser {
	

  
        @Id
	    @Column(name = "email" ,unique = true )
		private String email;
        @Column(name = "firstname")
	    private String firstName;
        @Column(name = "lastname")
		private String lastName;
        @Column(name = "designation")
		private String designation;
        @Column(name = "status")
		private String status;
        @Column(name = "password")
	    private String password;
        @Column(name = "confirmpassword")
        private String confirmPassword;
	    
        public RegisterUser() {
	    	
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
				return firstName;
			}

			public void setFirstname(String firstname) {
				this.firstName = firstname;
			}

			public String getLastname() {
				return lastName;
			}

			public void setLastname(String lastname) {
				this.lastName = lastname;
			}

			public String getDesignation() {
				return designation;
			}

			public void setDesignation(String designation) {
				this.designation = designation;
			}
			
			public String getConfirmPassword() {
				return confirmPassword;
			}

			public void setConfirmPassword(String confirmPassword) {
				this.confirmPassword = confirmPassword;
			}
			
			public String getStatus() {
				  return status;
			}
			
			public void setStatus(String status) {
				this.status = status;
			}


	}

