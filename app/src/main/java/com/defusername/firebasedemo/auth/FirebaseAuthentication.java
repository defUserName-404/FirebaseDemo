package com.defusername.firebasedemo.auth;

import static com.defusername.firebasedemo.FirebaseHandler.AUTH_UI;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.MainThread;
import androidx.appcompat.app.AppCompatActivity;

import com.defusername.firebasedemo.MainActivity;
import com.defusername.firebasedemo.R;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class FirebaseAuthentication extends AppCompatActivity {

    private static final int RC_SIGN_IN = 100;
    private Button buttonSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_authentication);

        initViews();
        handleButtonClicks();
    }

    private void initViews() {
        buttonSignIn = findViewById(R.id.Button_SignIn);
    }

    private void handleButtonClicks() {
        buttonSignIn.setOnClickListener((view -> {
            startActivityForResult(AUTH_UI
                            .createSignInIntentBuilder()
                            .build(),
                    RC_SIGN_IN);
        }));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            handleSignInResponse(resultCode, data);
        }
    }

    @MainThread
    private void handleSignInResponse(int resultCode, Intent data) {
        IdpResponse response = IdpResponse.fromResultIntent(data);
        Toast toast;

        if (resultCode == RESULT_OK) {
            startActivity(createIntent(this));
            finish();
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

    @SuppressLint("RestrictedApi")
    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, SignInActivity.class);
        intent.putExtra("USER_NAME", AUTH_UI.getAuth().getCurrentUser().getDisplayName());
        intent.putExtra("EMAIL", AUTH_UI.getAuth().getCurrentUser().getEmail());

        return intent;
    }
}