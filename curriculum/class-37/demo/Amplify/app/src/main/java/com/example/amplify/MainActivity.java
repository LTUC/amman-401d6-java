package com.example.amplify;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Note;
import com.amplifyframework.datastore.generated.model.Task;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    public static final String TASK_ID = "taskId";
    public static final String DATA = "data";
    public static final int REQUEST_CODE = 123;

    private final Handler handler = new Handler(Looper.getMainLooper(), msg -> {
        String data = msg.getData().getString(DATA);
        String taskId = msg.getData().getString(TASK_ID);
//            Toast.makeText(this, "The Toast Works => " + data, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, QueryActivity.class);
        intent.putExtra(TASK_ID, taskId);

        startActivity(intent);
        return true;
    });

    private Button buttonUpload;
    private Button buttonPressMe;
    private Button buttonDownload;

    private EditText titleEditText;
    private EditText noteEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getAuthSession("onCreate");
        findViews();
        setUpListeners();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.logout:
                logout();
                break;
            case R.id.reset:
                // TODO: 5/25/22 Implement reset password
                break;
            default:
        }
        return true;
    }

    private void dataStoreSync() {
        // Datastore and API sync
        Amplify.DataStore.observe(Task.class,
                started -> {
                    Log.i(TAG, "Observation began.");
                },
                change -> {
                    Log.i(TAG, change.item().toString());

                    Bundle bundle = new Bundle();
                    bundle.putString(DATA, change.item().toString());

                    Message message = new Message();
                    message.setData(bundle);

//                    handler.sendMessage(message);
                },
                failure -> Log.e(TAG, "Observation failed.", failure),
                () -> Log.i(TAG, "Observation complete.")
        );
    }

    private void getAuthSession(String method) {
        Amplify.Auth.fetchAuthSession(
                result -> Log.i(TAG, "Auth Session => " + method + result.toString()),
                error -> Log.e(TAG, error.toString())
        );
    }

    private void logout() {
        Amplify.Auth.signOut(
                () -> {
                    Log.i(TAG, "Signed out successfully");
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    getAuthSession("logout");
                    finish();
                },
                error -> Log.e(TAG, error.toString())
        );
    }

    private void findViews() {
        buttonPressMe = findViewById(R.id.button_press_me);
        buttonUpload = findViewById(R.id.button_upload);
        buttonDownload = findViewById(R.id.button_download);
        titleEditText = findViewById(R.id.edittext_title);
        noteEditText = findViewById(R.id.edittext_note);
    }

    private void setUpListeners() {
        buttonPressMe.setOnClickListener(view -> {
            String title = titleEditText.getText().toString();
            String note = noteEditText.getText().toString();

            Task task = Task.builder()
                    .title(title)
                    .description("It's anybody's guess")
                    .build();

            // Data store save
            Amplify.DataStore.save(task,
                    successTask -> {
                        Log.i(TAG, "Saved task: " + successTask.item().getTitle());

                        // add the note to the task
                        Note taskNote = Note.builder()
                                .content(note)
                                .taskNotesId(successTask.item().getId())
                                .build();

                        Amplify.DataStore.save(taskNote,
                                noteSuccess -> {
                                    Log.i(TAG, "Saved note: " + noteSuccess.item().getId());

                                    Bundle bundle = new Bundle();
                                    bundle.putString(TASK_ID, noteSuccess.item().getTaskNotesId());

                                    Message message = new Message();
                                    message.setData(bundle);

                                    handler.sendMessage(message);
                                },
                                error -> Log.e(TAG, "Could not save note to DataStore", error)
                        );

                    },
                    error -> Log.e(TAG, "Could not save task to DataStore", error)
            );

            Amplify.API.mutate(ModelMutation.create(task),
                    successTask -> {
                        Log.i(TAG, "Saved task: " + successTask.getData().getTitle());

                        // add the note to the task
                        Note taskNote = Note.builder()
                                .content(note)
                                .taskNotesId(successTask.getData().getId())
                                .build();

                        Amplify.API.mutate(ModelMutation.create(taskNote),
                                noteSuccess -> {
                                    Log.i(TAG, "Saved note: " + noteSuccess.getData().getId());

                                    Bundle bundle = new Bundle();
                                    bundle.putString(TASK_ID, noteSuccess.getData().getTaskNotesId());

                                    Message message = new Message();
                                    message.setData(bundle);

                                    handler.sendMessage(message);
                                },
                                error -> Log.e(TAG, "Could not save note to DataStore", error)
                        );
                    },
                    error -> Log.e(TAG, "Could not save task to DataStore", error)
            );
        });
        buttonUpload.setOnClickListener(view -> pictureUpload());
        buttonDownload.setOnClickListener(view -> pictureDownload());
    }

    private void fileUpload() {
        File exampleFile = new File(getApplicationContext().getFilesDir(), "ExampleKey");

        // creates a file on the device
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(exampleFile));
            writer.append("Example file contents");
            writer.close();
        } catch (Exception exception) {
            Log.e(TAG, "Upload failed", exception);
        }

        // uploads the file
        Amplify.Storage.uploadFile(
                "ExampleKey",
                exampleFile,
                result -> Log.i(TAG, "Successfully uploaded: " + result.getKey()),
                storageFailure -> Log.e(TAG, "Upload failed", storageFailure)
        );
    }

    private void pictureUpload() {
        // Launches photo picker in single-select mode.
        // This means that the user can select one photo or video.
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");

        startActivityForResult(intent, REQUEST_CODE);
    }

    private void pictureDownload() {
        Amplify.Storage.downloadFile(
                "image.jpg",
                new File(getApplicationContext().getFilesDir() + "/download.jpg"),
                result -> {
                    Log.i(TAG, "The root path is: " + getApplicationContext().getFilesDir());
                    Log.i(TAG, "Successfully downloaded: " + result.getFile().getName());
                },
                error -> Log.e(TAG,  "Download Failure", error)
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != Activity.RESULT_OK) {
            // Handle error
            Log.e(TAG, "onActivityResult: Error getting image from device");
            return;
        }

        switch(requestCode) {
            case REQUEST_CODE:
                // Get photo picker response for single select.
                Uri currentUri = data.getData();

                // Do stuff with the photo/video URI.
                Log.i(TAG, "onActivityResult: the uri is => " + currentUri);

                try {
                    Bitmap bitmap = getBitmapFromUri(currentUri);

                    File file = new File(getApplicationContext().getFilesDir(), "image.jpg");
                    OutputStream os = new BufferedOutputStream(new FileOutputStream(file));
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
                    os.close();

                    // upload to s3
                    // uploads the file
                    Amplify.Storage.uploadFile(
                            "image.jpg",
                            file,
                            result -> Log.i(TAG, "Successfully uploaded: " + result.getKey()),
                            storageFailure -> Log.e(TAG, "Upload failed", storageFailure)
                    );
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
        }
    }

    /*
        https://stackoverflow.com/questions/2169649/get-pick-an-image-from-androids-built-in-gallery-app-programmatically
         */
    private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor =
                getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();

        return image;
    }
}