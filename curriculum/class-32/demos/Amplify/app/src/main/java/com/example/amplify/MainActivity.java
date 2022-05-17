package com.example.amplify;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.Task;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureAmplify();

        Task item = Task.builder()
                .title("Build Amazing Mobile Apps")
                .description("Build it using Java, Spring and Amplify")
                .build();

        // Data store save
        Amplify.DataStore.save(item,
                success -> Log.i(TAG, "Saved item: " + success.item().getTitle()),
                error -> Log.e(TAG, "Could not save item to DataStore", error)
        );

        // Data store query
//        Amplify.DataStore.query(Task.class,
//                tasks -> {
//                    while (tasks.hasNext()) {
//                        Task task = tasks.next();
//
//                        Log.i(TAG, "==== Task ====");
//                        Log.i(TAG, "Name: " + task.getTitle());
//                    }
//                },
//                failure -> Log.e(TAG, "Could not query DataStore", failure)
//        );

        // API save to backend
//        Amplify.API.mutate(
//                ModelMutation.create(item),
//                success -> Log.i(TAG, "Saved item: " + success.getData().getTitle()),
//                error -> Log.e(TAG, "Could not save item to API", error)
//        );

        // API query
//        Amplify.API.query(
//                ModelQuery.list(Task.class, Task.TITLE.contains("Build")),
//                response -> {
//                    for (Task task : response.getData()) {
//                        Log.i(TAG, "------------------> " + task.getTitle());
//                    }
//                },
//                error -> Log.e(TAG, "Query failure", error)
//        );

        // Datastore and API sync
        Amplify.DataStore.observe(Task.class,
                started -> {
                    Log.i(TAG, "Observation began.");
                    // TODO: 5/17/22 Update the UI thread with in this call method
                    // Manipulate your views

                    // call handler
                },
                change -> Log.i(TAG, change.item().toString()),
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