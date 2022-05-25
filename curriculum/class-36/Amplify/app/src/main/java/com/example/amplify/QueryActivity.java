package com.example.amplify;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Note;
import com.amplifyframework.datastore.generated.model.Task;

public class QueryActivity extends AppCompatActivity {

    private static final String TAG = QueryActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);

        Intent intent = getIntent();
        String taskId = intent.getStringExtra(MainActivity.TASK_ID);

        Toast.makeText(this, "The id is => " + taskId, Toast.LENGTH_SHORT).show();

        Amplify.API.query(
                ModelQuery.list(Note.class, Note.TASK_NOTES_ID.eq(taskId)),
                notes -> {
                    for (Note note : notes.getData()) {
                        Log.i(TAG, "<==================================>");
                        Log.i(TAG, "The note is => " + note.getContent());
                    }

                    Amplify.API.query(
                            ModelQuery.get(Task.class, taskId),
                            task -> {
                                Log.i(TAG, "<@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@>");
                                Log.i(TAG, "The note is => " + task.getData().getDescription());
                            },
                            error -> Log.e(TAG, error.toString())
                    );
                },
                error -> Log.e(TAG, error.toString())
        );
    }
}