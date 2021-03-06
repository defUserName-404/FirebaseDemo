package com.defusername.firebasedemo.auth;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.MainThread;
import androidx.appcompat.app.AppCompatActivity;

import com.defusername.firebasedemo.R;
import com.defusername.firebasedemo.SignedInActivity;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.Objects;

public class FirebaseAuthenticationActivity extends AppCompatActivity {

	private Button buttonSignIn;
	private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (user != null) {
			startActivity(createIntentForHomePage());

			finish();

			return;
		}

		setContentView(R.layout.activity_firebase_authentication);

		initViews();
		handleButtonClicks();
	}

	private void initViews() {
		buttonSignIn = findViewById(R.id.Button_SignIn);
	}

	private void handleButtonClicks() {
		buttonSignIn.setOnClickListener((view -> createSignInIntent()));
	}

	private final ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(
			new FirebaseAuthUIActivityResultContract(),
			this::onSignInResult
	);

	@MainThread
	private void onSignInResult(FirebaseAuthUIAuthenticationResult result) {
		IdpResponse response = result.getIdpResponse();
		Toast toast;

		if (user == null)
			user = FirebaseAuth.getInstance().getCurrentUser();

		if (result.getResultCode() == RESULT_OK) {
			startActivity(createIntentForHomePage());

			finish();

			return;
		} else {
			if (response == null) {
				toast = Toast.makeText(this, "Sign in was cancelled!", Toast.LENGTH_LONG);
				toast.show();
				return;
			}
			if (Objects.requireNonNull(response.getError()).getErrorCode() == ErrorCodes.NO_NETWORK) {
				toast = Toast.makeText(this, "You have no internet connection", Toast.LENGTH_LONG);
				toast.show();
			}
		}

		toast = Toast.makeText(this, "Unknown Error!", Toast.LENGTH_LONG);
		toast.show();
	}

	private Intent createIntentForHomePage() {
		Intent signInIntent = new Intent(this, SignedInActivity.class);

		assert user != null;
		signInIntent.putExtra("USERNAME", user.getDisplayName());
		signInIntent.putExtra("EMAIL", user.getEmail());

		return signInIntent;
	}

	private void createSignInIntent() {
		Intent signInIntent = AuthUI.getInstance()
				.createSignInIntentBuilder()
				.setAvailableProviders(Arrays.asList(
						new AuthUI.IdpConfig.EmailBuilder().build(),
						new AuthUI.IdpConfig.GoogleBuilder().build())
				)
				.setIsSmartLockEnabled(true)
				.build();

		signInLauncher.launch(signInIntent);
	}
}