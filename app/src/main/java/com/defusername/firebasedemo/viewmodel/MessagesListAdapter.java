package com.defusername.firebasedemo.viewmodel;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.defusername.firebasedemo.R;
import com.defusername.firebasedemo.model.Message;

import java.util.List;

public class MessagesListAdapter extends
		RecyclerView.Adapter<MessagesListAdapter.MessagesListViewHolder> {

	private final List<Message> messageList;
	private final OnMessageListClickListener onMessageListClickListener;

	public MessagesListAdapter(
			List<Message> messageList, OnMessageListClickListener onMessageListClickListener) {
		this.messageList = messageList;
		this.onMessageListClickListener = onMessageListClickListener;
	}

	@NonNull
	@Override
	public MessagesListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
		View view = layoutInflater.inflate(R.layout.messages_recyclerview,
				parent, false);

		return new MessagesListViewHolder(view, onMessageListClickListener);
	}

	@Override
	public void onBindViewHolder(@NonNull MessagesListViewHolder holder, int position) {
		String username = messageList.get(position).getUsername();
		String timeMessageSent = messageList.get(position).getTime();
		String messageText = messageList.get(position).getMessage();
		Drawable profilePicture = messageList.get(position).getDrawableProfilePicture();

		holder.textViewUsername.setText(username);
		holder.textViewTimeMessageSent.setText(timeMessageSent);
		holder.textViewMessageText.setText(messageText);
		holder.imageViewProfilePicture.setImageDrawable(profilePicture);
	}

	@Override
	public int getItemCount() {
		return messageList.size();
	}

	public static class MessagesListViewHolder extends RecyclerView.ViewHolder
			implements View.OnClickListener {

		private final TextView textViewUsername, textViewTimeMessageSent, textViewMessageText;
		private final ImageView imageViewProfilePicture;
		private final OnMessageListClickListener onMessageListClickListener;

		public MessagesListViewHolder(
				@NonNull View itemView, OnMessageListClickListener onMessageListClickListener) {
			super(itemView);
			this.textViewUsername = itemView.findViewById(R.id.TextView_UsernameInMessage);
			this.textViewTimeMessageSent = itemView.findViewById(R.id.TextView_TimeMessageSent);
			this.textViewMessageText = itemView.findViewById(R.id.TextView_Message);
			this.imageViewProfilePicture = itemView.findViewById(R.id.ImageView_ProfilePictureMessage);
			this.onMessageListClickListener = onMessageListClickListener;
			itemView.setOnClickListener(this);
		}

		@Override
		public void onClick(View v) {
			onMessageListClickListener.gotoMessage(getAdapterPosition());
		}
	}

	public interface OnMessageListClickListener {
		void gotoMessage(int position);
	}
}