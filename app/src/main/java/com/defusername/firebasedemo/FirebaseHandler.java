package com.defusername.firebasedemo;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseHandler {
    public static final FirebaseDatabase FIREBASE_DATABASE = FirebaseDatabase.getInstance();
    public static final FirebaseAuth FIREBASE_AUTH = FirebaseAuth.getInstance();
    public static final AuthUI AUTH_UI = AuthUI.getInstance();
}