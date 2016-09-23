package com.eudemon.taurus.app.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User extends BaseEntity implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 6065339087039687447L;

	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String password;
	
	@Column
	private String passwordEncrypt;
	
	@Column
	private String salt;
	
	@Column
	private String roles;
	
	@Column
	private String permissions;
	
	@Column
	private Timestamp registerDate;
	
	@Column
	private String photo;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordEncrypt() {
		return passwordEncrypt;
	}

	public void setPasswordEncrypt(String passwordEncrypt) {
		this.passwordEncrypt = passwordEncrypt;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}

	public Timestamp getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Timestamp registerDate) {
		this.registerDate = registerDate;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
}