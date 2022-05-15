package com.defusername.firebasedemo;

import static com.defusername.firebasedemo.FirebaseHandler.FIREBASE_DATABASE;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

	private Button buttonWriteData;
	private TextView textViewDisplayData;
	private final DatabaseReference mRef = FIREBASE_DATABASE.getReference("message");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initViews();
		handleButtonClicks();
		handleDatabaseRefChanges();
	}

	private void initViews() {
		buttonWriteData = findViewById(R.id.Button_WriteData);
		textViewDisplayData = findViewById(R.id.TextView_DisplayData);
	}

	private void handleButtonClicks() {
		buttonWriteData.setOnClickListener((view) -> {
			mRef.setValue("Hello darkness my old friend.");
		});
	}

	private void handleDatabaseRefChanges() {
		mRef.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot snapshot) {
				String value = snapshot.getValue(String.class);
				textViewDisplayData.setText(value);
			}

			@SuppressLint("SetTextI18n")
			@Override
			public void onCancelled(@NonNull DatabaseError error) {
				textViewDisplayData.setText("Error " + error);
			}
		});
	}
}