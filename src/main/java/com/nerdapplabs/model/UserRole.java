package com.nerdapplabs.model;

//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "userrole")
public class UserRole {

	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	//private RegisterUser registeruser;
    @Column(name = "email")
	private String email;
    @Column(name = "role")
	private String role;
    @Column(name = "permission")
    private String permission;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	/*@OneToOne(cascade = CascadeType.ALL , targetEntity = RegisterUser.class)
	@JoinColumn(name = "email", referencedColumnName = "email")
	public RegisterUser getRegisterUser() {
		return registeruser;
	}

	public void setRegisterUser(RegisterUser registeruser) {
		this.registeruser = registeruser;
	}*/

}
