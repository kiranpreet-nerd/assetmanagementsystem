package com.nerdapplabs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "user")
public class User {

	@Id
	@Column(name = "email", unique = true)
	@Email
	@NotEmpty(message = "email required")
	private String email;
	
	@NotEmpty(message ="*")
	@Column(name = "firstname")
	private String firstName;
	
	@NotEmpty(message = "*")
	@Column(name = "lastname")
	private String lastName;
	
	@NotEmpty(message = "*")
	@Column(name = "designation")
	private String designation;
	
	//@Column(name = "status")
	//private String status;
	
	@NotEmpty(message = "password required")
	@Column(name = "password")
	private String password;
	
	@NotEmpty(message = "*")
	@Column(name = "confirm")
	private String confirm;

	
	public User() {

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

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	
	

}
