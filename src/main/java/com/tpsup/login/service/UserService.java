package com.tpsup.login.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tpsup.login.controller.UserNotFoundException;
import com.tpsup.login.entity.User;
import com.tpsup.login.repo.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository repo;

	public List<User> listAll() {
		return (List<User>) repo.findAll();
	}

	public void save(User user) {
		repo.save(user);
	}

	public User get(String id) throws UserNotFoundException {
		Optional<User> result = repo.findById(id);

		if (result.isPresent()) {
			return result.get();
		}

		throw new UserNotFoundException("Could not find any users with ID " + id);
	}

	public void delete(String id) throws UserNotFoundException {
		// we could have used repo.findById as in get() above. Here we try a new way
		// countById
		Long count = repo.countById(id);

		if (count == null || count == 0) {
			throw new UserNotFoundException("Could not find any users with ID " + id);
		}

		repo.deleteById(id);
	}
}
