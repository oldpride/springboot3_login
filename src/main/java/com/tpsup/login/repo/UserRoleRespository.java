package com.tpsup.login.repo;

import org.springframework.data.repository.CrudRepository;

import com.tpsup.login.entity.UserRole;
import com.tpsup.login.entity.UserRoleId;

public interface UserRoleRespository extends CrudRepository<UserRole, UserRoleId> {

}
