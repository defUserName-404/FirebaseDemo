package com.defusername.firebasedemo.realtime_database;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.defusername.firebasedemo.R;
import com.defusername.firebasedemo.auth.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RealtimeDatabaseActivity extends AppCompatActivity {

	private TextView textViewDatabaseData;
	private final DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("Users");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_realtime_database);

		initViews();
		writeToDatabase();
	}

	private void initViews() {
		textViewDatabaseData = findViewById(R.id.TextView_DatabaseData);
	}

	private void writeToDatabase() {
		String username = getIntent().getStringExtra("USERNAME");
		String email = getIntent().getStringExtra("EMAIL");
		User user = new User(username, email);

		databaseRef.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot snapshot) {
				for (DataSnapshot userSnapshot : snapshot.getChildren()) {
					User tempUser = userSnapshot.getValue(User.class);

					assert tempUser != null;
					if (tempUser.getUsername().equals(user.getUsername())
							&& tempUser.getEmail().equals(user.getEmail())) {
						Toast.makeText(RealtimeDatabaseActivity.this,
								"User already added", Toast.LENGTH_SHORT)
								.show();
						return;
					}
				}

				databaseRef
						.push()
						.setValue(user);
			}

			@Override
			public void onCancelled(@NonNull DatabaseError error) {
				Log.e("DATABASE_ACTIVITY", "Database error", error.toException());
			}
		});
	}
}