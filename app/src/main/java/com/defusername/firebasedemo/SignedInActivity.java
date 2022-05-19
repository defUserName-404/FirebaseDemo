package com.defusername.firebasedemo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.defusername.firebasedemo.auth.FirebaseAuthenticationActivity;
import com.defusername.firebasedemo.auth.User;
import com.defusername.firebasedemo.realtime_database.RealtimeDatabase;
import com.defusername.firebasedemo.storage.Storage;
import com.firebase.ui.auth.AuthUI;

public class SignedInActivity extends AppCompatActivity {

	private TextView textViewUserName, textViewEmail;
	private Button buttonSignOut, buttonWriteToDatabase, buttonSetProfilePicture;
	private ImageView imageViewProfilePicture;
	private String username, email;
	private RealtimeDatabase firebaseRealtimeDatabase;
	private static final int SELECT_IMAGE_CODE = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signed_in);

		initViews();
		showData();
		handleButtonClicks();
	}

	private void handleButtonClicks() {
		buttonSignOut.setOnClickListener(view -> AuthUI.getInstance()
				.signOut(this)
				.addOnCompleteListener(task -> {
					if (task.isSuccessful()) {
						startActivity(new Intent(SignedInActivity.this,
								FirebaseAuthenticationActivity.class));
						finish();
					} else
						Log.d("SIGNED_IN_ACTIVITY", "Can't log out");
				}));

		buttonWriteToDatabase.setOnClickListener(view -> {
					User user = new User(username, email);
					if (firebaseRealtimeDatabase.writeToDatabase(user))
						Toast.makeText(this, "Write to database successful",
								Toast.LENGTH_SHORT).
								show();
				}
		);

		buttonSetProfilePicture.setOnClickListener(view -> {
			Intent intent = new Intent();
			intent.setType("image/*");
			intent.setAction(Intent.ACTION_GET_CONTENT);
			startActivityForResult(Intent.createChooser(intent, "Title"), SELECT_IMAGE_CODE);
		});
	}

	@SuppressLint("SetTextI18n")
	private void showData() {
		if (username != null)
			textViewUserName.setText("Username: " + username);
		if (email != null)
			textViewEmail.setText("Email: " + email);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == SELECT_IMAGE_CODE) {
			assert data != null;
			Storage.getInstance()
					.uploadPicture(data.getData(), username);
		}
	}

	private void initViews() {
		textViewUserName = findViewById(R.id.TextView_UserName);
		textViewEmail = findViewById(R.id.TextView_Email);
		buttonSignOut = findViewById(R.id.Button_SignOut);
		buttonWriteToDatabase = findViewById(R.id.Button_WriteToDatabase);
		buttonSetProfilePicture = findViewById(R.id.Button_SetProfilePicture);
		imageViewProfilePicture = findViewById(R.id.ImageView_ProfilePicture);

		username = getIntent().getStringExtra("USERNAME");
		email = getIntent().getStringExtra("EMAIL");
		firebaseRealtimeDatabase = RealtimeDatabase.getInstance();
	}
}