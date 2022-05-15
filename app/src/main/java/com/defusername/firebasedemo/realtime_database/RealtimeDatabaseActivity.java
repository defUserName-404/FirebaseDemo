package com.defusername.firebasedemo.realtime_database;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.defusername.firebasedemo.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RealtimeDatabaseActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_realtime_database);

		writeToDatabase();
	}

	private void writeToDatabase() {
		FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

		final DatabaseReference databaseReference = firebaseDatabase.getReference("message");
		databaseReference.setValue("Hello, World!");
	}
}