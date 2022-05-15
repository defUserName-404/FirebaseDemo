package com.defusername.firebasedemo.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.defusername.firebasedemo.R;

public class SignInActivity extends AppCompatActivity {

	private TextView textViewUserName, textViewEmail;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_in);

		initViews();
		String username = getIntent().getStringExtra("USER_NAME");
		String email = getIntent().getStringExtra("EMAIL");
		showData(username, email);
	}

	@SuppressLint("SetTextI18n")
	private void showData(String username, String email) {
		if (username != null)
			textViewUserName.setText("Hello " + username);
		if (email != null)
			textViewEmail.setText("Your email is " + email);
	}

	private void initViews() {
		textViewUserName = findViewById(R.id.TextView_UserName);
		textViewEmail = findViewById(R.id.TextView_Email);
	}
}