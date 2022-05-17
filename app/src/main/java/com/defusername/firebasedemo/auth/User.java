package com.defusername.firebasedemo.auth;

public class User {
	private final String username, email;

	public User(String username, String email) {
		this.username = username;
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}
}