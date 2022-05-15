package com.defusername.firebasedemo;

<<<<<<< HEAD
=======
import static com.defusername.firebasedemo.FirebaseHandler.FIREBASE_DATABASE;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
>>>>>>> testing
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

<<<<<<< HEAD
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
=======
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
>>>>>>> testing
}