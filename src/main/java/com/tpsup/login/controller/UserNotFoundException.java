package com.tpsup.login.controller;

public class UserNotFoundException extends Throwable {
	public UserNotFoundException(String message) {
		super(message);
	}
}
