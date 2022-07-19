package com.tpsup.login.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

// these two annotations link class to database. These are from java hibernate
@Entity
@Table(name = "login_users")
public class User {
	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id", unique = true, length = 10)
	private String id;
	
	@Column(nullable = false, unique = true, length = 45) // validation: not null, must be unique, max len = 45
	private String email;
	
	@Column(length = 15, nullable = false)
	private String password;
	
	@Column(length = 45, nullable = false, name="first_name")
	private String firstName;
	
	@Column(length = 45, nullable = false, name="last_name")
	private String lastName;

	private boolean enabled;
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinTable(
			name = "login_users_roles",
			joinColumns = {@JoinColumn(name="user_id")},
			inverseJoinColumns = {@JoinColumn(name="role_id")}
	)
	private Set<Role> roles = new HashSet<Role>();
	
	
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString () {
		return "User {" +
	           "id=" + id +
	           ", email='" + email + '\'' +
	           ", password='" + password + '\'' +
	           ", firstName='" + firstName + '\'' +
	           ", lastName='" + lastName + '\'' +
	           '}';
	}
}
