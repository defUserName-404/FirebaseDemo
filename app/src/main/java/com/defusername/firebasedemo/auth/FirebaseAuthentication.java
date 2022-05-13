package com.defusername.firebasedemo.auth;

import static com.defusername.firebasedemo.FirebaseHandler.AUTH_UI;
import static com.defusername.firebasedemo.FirebaseHandler.FIREBASE_AUTH;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.defusername.firebasedemo.R;

public class FirebaseAuthentication extends AppCompatActivity {

    private static final int RC_SIGN_IN = 100;
    private Button buttonSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_authentication);

        buttonSignIn = findViewById(R.id.Button_SignIn);
        if (FIREBASE_AUTH.getCurrentUser() != null) {
            finish();
        }

        buttonSignIn.setOnClickListener((view -> {
            startActivityForResult(AUTH_UI
                            .createSignInIntentBuilder()
                            .build(),
                    RC_SIGN_IN);
        }));
    }
}