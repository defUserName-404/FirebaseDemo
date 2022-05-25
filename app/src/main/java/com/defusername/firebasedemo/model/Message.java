package com.defusername.firebasedemo.model;

import android.graphics.drawable.Drawable;

public class Message {

	private final Drawable drawableProfilePicture;
	private final String username, time, message;

	public Message(Drawable drawableProfilePicture, String username, String time, String message) {
		this.drawableProfilePicture = drawableProfilePicture;
		this.username = username;
		this.time = time;
		this.message = message;
	}

	public Drawable getDrawableProfilePicture() {
		return drawableProfilePicture;
	}

	public String getUsername() {
		return username;
	}

	public String getTime() {
		return time;
	}

	public String getMessage() {
		return message;
	}
}