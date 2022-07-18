package com.tpsup.login;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;

import com.tpsup.login.entity.User;
import com.tpsup.login.repo.UserRepository;
import com.ulisesbocchio.jasyptspringboot.configuration.EnableEncryptablePropertiesConfiguration;


@DataJpaTest

//this means we use real database, not in memory fake database
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE )

//if rollback set to true, we can repeat test.
//if rollback set to false, we can use test to populate database
@Rollback(true) 

// because we used jasypt to encrypt database password, we need the following
@Import(EnableEncryptablePropertiesConfiguration.class)
public class UserRepositoryTests {
	@Autowired private UserRepository repo;
	
	@Test
	public void testAddNew() {
		User user = new User();
		user.setEmail("alex.testern@gmail.com");
		user.setPassword("ravi123456");
		user.setFirstName("alex");
		user.setLastName("stevenson");
		
		User savedUser = repo.save(user);
		
		Assertions.assertThat(savedUser).isNotNull();
		Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
	}

	@Test
	public void testListAll() {
		Iterable<User> users = repo.findAll();
		Assertions.assertThat(users).hasSizeGreaterThan(0);
		for (User user : users) {
			System.out.println(user);
		}
	}
	
	@Test
	public void testUpdate() {
		Integer userId = 1;
		Optional<User> optionalUser = repo.findById(userId);
		User user = optionalUser.get();
		user.setPassword("hello2000");
		repo.save(user);
		
		User updateUser = repo.findById(userId).get();
		Assertions.assertThat(updateUser.getPassword()).isEqualTo("hello2000");		
	}
	
	@Test
	public void testGet() {
		Integer userId = 2;
		Optional<User> optionalUser = repo.findById(userId);
		Assertions.assertThat(optionalUser).isPresent();
		System.out.println(optionalUser.get());
	}

	@Test
	public void testDelete() {
		Integer userId = 2;
		repo.deleteById(userId);
		Optional<User> optionalUser = repo.findById(userId);
		Assertions.assertThat(optionalUser).isNotPresent();
	}
}
