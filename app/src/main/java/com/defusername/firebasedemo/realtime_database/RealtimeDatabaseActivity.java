package com.defusername.firebasedemo.realtime_database;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.defusername.firebasedemo.R;
import com.defusername.firebasedemo.auth.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RealtimeDatabaseActivity extends AppCompatActivity {

	private TextView textViewDatabaseData;
	private final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("User");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_realtime_database);

		initViews();
		writeToDatabase();
		onDataChange();
	}

	private void initViews() {
		textViewDatabaseData = findViewById(R.id.TextView_DatabaseData);
	}

	private void writeToDatabase() {
		String username = getIntent().getStringExtra("USERNAME");
		String email = getIntent().getStringExtra("EMAIL");
		User user = new User(username, email);

		databaseReference.setValue(user);
	}

	private void onDataChange() {
		databaseReference.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot snapshot) {
//				String value = snapshot.getValue(String.class);
//				textViewDatabaseData.setText(value);
			}

			@Override
			public void onCancelled(@NonNull DatabaseError error) {
				Log.e("REALTIME_DATABASE", "Failed to read value", error.toException());
			}
		});
	}
}