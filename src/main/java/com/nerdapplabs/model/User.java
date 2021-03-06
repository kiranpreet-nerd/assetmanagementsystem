package com.nerdapplabs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "user")
public class User {

	@Id
	@Column(name = "email", unique = true)
	@NotEmpty
	@Size(min = 6, max = 40, message = "email name must be between 6 and 40")
	@Email
	private String email;

	
	@Column(name = "firstname")
	private String firstname;

	@Column(name = "lastname")
	private String lastname;
	
	public User() {
		
	} 
	
	public User (String firstname,String email, String designation) {
		this.firstname = firstname;
		this.email = email;
		this.designation = designation;
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

	@Column(name = "designation")
	private String designation;
    
	@Column(name = "role")
	private String role;

	@Column(name = "password")
	private String password;

	@Column(name = "confirm")
	private String confirm;
	
	@Column(name = "status")
	private Integer status;
	
	@Column(name = "is_approved")
	private Boolean is_approved;
	
	public Boolean getIs_approved() {
		return is_approved;
	}

	public void setIs_approved(Boolean is_approved) {
		this.is_approved = is_approved;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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
