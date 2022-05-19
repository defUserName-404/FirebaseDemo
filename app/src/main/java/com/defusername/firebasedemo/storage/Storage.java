package com.defusername.firebasedemo.storage;

import android.net.Uri;
import android.util.Log;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


/*
	Singleton class for handling storage operations
 */
public class Storage {

	private static Storage instance;
	private final StorageReference storageRef = FirebaseStorage.getInstance().getReference();

	private Storage() {
	}

	public static Storage getInstance() {
		if (instance == null)
			instance = new Storage();

		return instance;
	}

	public void uploadPicture(Uri imageUri, String fileName) {
		StorageReference imageRef = storageRef.child("images/" + fileName + ".jpg");

		imageRef.putFile(imageUri)
				.addOnFailureListener(exception -> {
					// Handle unsuccessful uploads)
					// Register observers to listen for when the download is done or if it fails
					Log.e("Message", "Upload failed");
				})
				.addOnSuccessListener(taskSnapshot -> {
					// taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
					// ...
					Log.i("Message", "Upload successful");
					Log.i("Metadata", taskSnapshot.getMetadata().toString());
				});
	}
}