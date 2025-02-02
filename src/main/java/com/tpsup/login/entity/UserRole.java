package com.tpsup.login.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;


// these two annotations link class to database. These are from java hibernate
@Entity
@IdClass(UserRoleId.class) // this is needed because this table has composite key (Vs single primary key)
@Table(name = "login_users_roles")

public class UserRole {
	@Id
	@Column(name="role_id")
	private Integer roleId;
	
	@Id
	@Column(name="user_id")
	private String userId;

}
