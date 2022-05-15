package com.defusername.firebasedemo.auth;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.defusername.firebasedemo.R;
import com.firebase.ui.auth.AuthUI;

public class SignedInActivity extends AppCompatActivity {

	private TextView textViewUserName, textViewEmail;
	private Button buttonSignOut;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signed_in);

		initViews();
		String username = getIntent().getStringExtra("USER_NAME");
		String email = getIntent().getStringExtra("EMAIL");
		showData(username, email);
		handleButtonClicks();
	}

	private void handleButtonClicks() {
		buttonSignOut.setOnClickListener(view -> {
			AuthUI.getInstance()
					.signOut(this)
					.addOnCompleteListener(task -> {
						if (task.isSuccessful()) {
							startActivity(new Intent(SignedInActivity.this, FirebaseAuthentication.class));
							finish();
						} else
							Log.d("SIGNED_IN_ACTIVITY", "Can't log out");
					});
		});
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
		buttonSignOut = findViewById(R.id.Button_SignOut);
	}
}