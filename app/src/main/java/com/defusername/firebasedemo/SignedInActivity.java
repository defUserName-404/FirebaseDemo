package com.defusername.firebasedemo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.defusername.firebasedemo.auth.FirebaseAuthenticationActivity;
import com.defusername.firebasedemo.realtime_database.RealtimeDatabaseActivity;
import com.firebase.ui.auth.AuthUI;

public class SignedInActivity extends AppCompatActivity {

	private TextView textViewUserName, textViewEmail;
	private Button buttonSignOut, buttonWriteToDatabase;
	private String username, email;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signed_in);

		initViews();
		showData();
		handleButtonClicks();
	}

	private void handleButtonClicks() {
		buttonSignOut.setOnClickListener(view -> {
			AuthUI.getInstance()
					.signOut(this)
					.addOnCompleteListener(task -> {
						if (task.isSuccessful()) {
							startActivity(new Intent(SignedInActivity.this, FirebaseAuthenticationActivity.class));
							finish();
						} else
							Log.d("SIGNED_IN_ACTIVITY", "Can't log out");
					});
		});

		buttonWriteToDatabase.setOnClickListener(view -> {
					Toast.makeText(this, "Write to database successful", Toast.LENGTH_SHORT).show();
					
					Intent databaseIntent = new Intent(this, RealtimeDatabaseActivity.class);

					assert (username != null && email != null);
					databaseIntent.putExtra("USERNAME", username);
					databaseIntent.putExtra("EMAIL", email);

					startActivity(databaseIntent);
				}
		);
	}

	@SuppressLint("SetTextI18n")
	private void showData() {
		if (username != null)
			textViewUserName.setText("Hello " + username);
		if (email != null)
			textViewEmail.setText("Your email is " + email);
	}

	private void initViews() {
		textViewUserName = findViewById(R.id.TextView_UserName);
		textViewEmail = findViewById(R.id.TextView_Email);
		buttonSignOut = findViewById(R.id.Button_SignOut);
		buttonWriteToDatabase = findViewById(R.id.Button_WriteToDatabase);

		username = getIntent().getStringExtra("USERNAME");
		email = getIntent().getStringExtra("EMAIL");
	}
}