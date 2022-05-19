package com.example.amplify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.Note;
import com.amplifyframework.datastore.generated.model.Task;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    public static final String TASK_ID = "taskId";
    public static final String DATA = "data";

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureAmplify();
//        dataStoreSync();

        Button button = findViewById(R.id.button_press_me);
        EditText titleEditText = findViewById(R.id.edittext_title);
        EditText noteEditText = findViewById(R.id.edittext_note);

        button.setOnClickListener(view -> {
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

        handler = new Handler(Looper.getMainLooper(), msg -> {
            String data = msg.getData().getString(DATA);
            String taskId = msg.getData().getString(TASK_ID);
//            Toast.makeText(this, "The Toast Works => " + data, Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, QueryActivity.class);
            intent.putExtra(TASK_ID, taskId);

            startActivity(intent);
            return true;
        });
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

    private void configureAmplify() {
        try {
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.configure(getApplicationContext());

            Log.i(TAG, "Initialized Amplify");
        } catch (AmplifyException e) {
            Log.e(TAG, "Could not initialize Amplify", e);
        }
    }
}