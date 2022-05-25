package com.defusername.firebasedemo.realtime_database;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.annotation.NonNull;

import com.defusername.firebasedemo.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/*
	Singleton class for handling database operations
 */
public class RealtimeDatabase {

	@SuppressLint("StaticFieldLeak")
	private static RealtimeDatabase instance;
	private final DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("Users");
	private boolean isDatabaseOperationSuccessful;
	private static final String TAG = "DATABASE_OPERATION";

	private RealtimeDatabase() {
	}

	public static RealtimeDatabase getInstance() {
		if (instance == null)
			instance = new RealtimeDatabase();

		return instance;
	}

	/*
		Database CREATE operation
		returns true if operation is successful or false otherwise
	 */
	public boolean writeToDatabase(User user) {
		databaseRef.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot snapshot) {
				for (DataSnapshot userSnapshot : snapshot.getChildren()) {
					User tempUser = userSnapshot.getValue(User.class);

					assert tempUser != null;
					if (tempUser.getUsername().equals(user.getUsername())
							&& tempUser.getEmail().equals(user.getEmail())) {
						Log.e(TAG, "User already exists");

						isDatabaseOperationSuccessful = true;

						return;
					}
				}

				databaseRef
						.push()
						.setValue(user);

				isDatabaseOperationSuccessful = true;
			}

			@Override
			public void onCancelled(@NonNull DatabaseError error) {
				Log.e(TAG, "Database error", error.toException());

				isDatabaseOperationSuccessful = false;
			}
		});

		return isDatabaseOperationSuccessful;
	}
}