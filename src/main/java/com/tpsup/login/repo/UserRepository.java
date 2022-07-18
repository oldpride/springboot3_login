package com.tpsup.login.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.tpsup.login.entity.User;

public interface UserRepository extends CrudRepository<User, String> {
	//Integer is the key (id)
	
	// CrudRepository implemented: save(), saveAll() FindByID(), existsById, findAll() .... 
	
	// this creates the table defined in User class immediately
	
	public Long countById(String id); // used by delete() in UserService.java

	public Optional<User> findById(String id);

}
