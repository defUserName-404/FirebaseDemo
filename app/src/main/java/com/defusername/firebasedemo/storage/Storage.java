package com.defusername.firebasedemo.storage;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;

/*
	Singleton class for handling storage operations
 */
public class Storage {

	private static Storage instance;
	private final StorageReference profilePictureRef;
	private Bitmap bitmapImage;
	private static final String TAG = "STORAGE_OPERATION";

	private Storage() {
		profilePictureRef = FirebaseStorage.getInstance()
				.getReference()
				.child("profile_pictures");
	}

	public static Storage getInstance() {
		if (instance == null)
			instance = new Storage();

		return instance;
	}

	public void uploadPicture(Uri imageUri, String fileName) {
		StorageReference imageRef = profilePictureRef.child(fileName);

		imageRef.putFile(imageUri)
				.addOnFailureListener(exception ->
						Log.e(TAG, "Upload failed"))
				.addOnSuccessListener(taskSnapshot -> {
					Log.i(TAG, "Upload successful");
					Log.i(TAG, taskSnapshot.getMetadata().getName());
				});
	}

	public void downloadPicture(Context context, String fileName) {
		StorageReference imageRef = profilePictureRef.child(fileName);

		final File STORAGE_PATH = new File(context.
				getExternalFilesDir(Environment.DIRECTORY_PICTURES), "Profile Pictures");

		if (!STORAGE_PATH.exists())
			STORAGE_PATH.mkdirs();

		final File IMAGE = new File(STORAGE_PATH, fileName);
		Log.i(TAG, IMAGE.getAbsolutePath());

		imageRef.getFile(IMAGE)
				.addOnSuccessListener(taskSnapshot -> {
					Log.d(TAG, "Download successful");
				})
				.addOnFailureListener(exception -> {
				});
	}

	public boolean doesDownloadLocationExist() {
		String filePath = "/storage/emulated/0/Android/data/com.defusername.firebasedemo/files/Pictures/Profile Pictures/Nafi Rahman";

		return (new File(filePath).exists());
	}
}