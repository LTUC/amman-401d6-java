package com.example.weatherme.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.example.weatherme.R;


public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = SignUpActivity.class.getSimpleName();
    public static final String EMAIL = "email";
    private ProgressBar loadingProgressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_up);

        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button signUpButton = findViewById(R.id.sign_up);
        loadingProgressBar = findViewById(R.id.loading);
        
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_SEND) {
                    signUpButton.setEnabled(true);
                }
                return false;
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);

                signUp(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        });
    }

    private void signUp(String email, String password) {

        // add as many attributes as you wish
        AuthSignUpOptions options = AuthSignUpOptions.builder()
                .userAttribute(AuthUserAttributeKey.email(), email)
                .userAttribute(AuthUserAttributeKey.nickname(), "John Doe")
                .build();

        Amplify.Auth.signUp(email, password, options,
                result -> {
                    Log.i(TAG, "Result: " + result.toString());
                    loadingProgressBar.setVisibility(View.INVISIBLE);

                    Intent intent = new Intent(SignUpActivity.this, VerificationActivity.class);
                    intent.putExtra(EMAIL, email);
                    startActivity(intent);

                    finish();
                },
                error -> {
                    Log.e(TAG, "Sign up failed", error);
                    // show a dialog of the error below
                    // error.getMessage()
                }
        );

    }
}