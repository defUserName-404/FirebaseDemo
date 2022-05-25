package com.defusername.firebasedemo;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.defusername.firebasedemo.model.Message;
import com.defusername.firebasedemo.viewmodel.MessagesListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class SendMessageActivity extends AppCompatActivity
		implements MessagesListAdapter.OnMessageListClickListener {

	private List<Message> messageList;
	private FloatingActionButton floatingActionButtonCreateNewMessage;
	private RecyclerView recyclerViewMessagesList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_send_message);

		initViews();
		setupRecyclerView();
		handleButtonClicks();
	}

	private void initViews() {
		recyclerViewMessagesList = findViewById(R.id.RecyclerView_MessagesList);
		floatingActionButtonCreateNewMessage = findViewById(R.id.FloatingActionButton_CreateNewMessage);
	}

	private void handleButtonClicks() {
		floatingActionButtonCreateNewMessage.setOnClickListener(view -> {

		});
	}

	private void setupRecyclerView() {
		messageList = new ArrayList<>();

		Message firstMessage = new Message(
				AppCompatResources.getDrawable(this, R.drawable.ic_launcher_background),
				"Nafi Rahman",
				"May 12, 2022 8:21 AM",
				"Hello World"
		);

		Message secondMessage = new Message(
				AppCompatResources.getDrawable(this, R.drawable.ic_launcher_background),
				"John Doe",
				"May 12, 2022 8:21 AM",
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
		);

		Message thirdMessage = new Message(
				AppCompatResources.getDrawable(this, R.drawable.ic_launcher_background),
				"Abc",
				"May 25 2022 2:20 PM",
				"Hi"
		);

		messageList.add(firstMessage);
		messageList.add(secondMessage);
		messageList.add(thirdMessage);

		MessagesListAdapter messagesListAdapter = new MessagesListAdapter(
				messageList, this);
		recyclerViewMessagesList.setAdapter(messagesListAdapter);
		recyclerViewMessagesList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
	}

	@Override
	public void gotoMessage(int position) {
		Toast.makeText(this, "Item Clicked", Toast.LENGTH_SHORT)
				.show();
	}
}