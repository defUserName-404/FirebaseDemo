package com.defusername.firebasedemo.model;

public class User {
	private String username, email;

	// No argument constructor required for firebase
	public User() {
	}

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