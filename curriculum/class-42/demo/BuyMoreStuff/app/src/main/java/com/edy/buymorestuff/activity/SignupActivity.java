package com.edy.buymorestuff.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.edy.buymorestuff.R;

import java.util.Locale;

public class SignupActivity extends AppCompatActivity
{
  public static final String TAG = "SignupActivity";
  public static final String SIGNUP_EMAIL_TAG = "Signup_Email_Tag";

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_signup);

    Button signupSubmitButton = (Button) findViewById(R.id.signupSubmitButton);
    signupSubmitButton.setOnClickListener(v ->
    {
      String username = ((EditText) findViewById(R.id.signupUsernameEditText)).getText().toString();
      String password = ((EditText) findViewById(R.id.signupPasswordEditText)).getText().toString();

      Amplify.Auth.signUp(username,
                        password,
                        AuthSignUpOptions.builder()
                          .userAttribute(AuthUserAttributeKey.email(), username)
                          .userAttribute(AuthUserAttributeKey.nickname(), "Ed")
                          .build(),
                        good ->
                        {
                          Log.i(TAG, "Signup succeeded: " + good.toString());
                          Intent goToLogInIntent = new Intent(SignupActivity.this, VerifyAccountActivity.class);
                          goToLogInIntent.putExtra(SIGNUP_EMAIL_TAG, username);
                          startActivity(goToLogInIntent);
                        },
                        bad ->
                        {
                          Log.i(TAG, "Signup failed with username: " + "ed@codefellows.com" + " with this message: " + bad.toString());
                          runOnUiThread(() ->
                            {
                              Toast.makeText(SignupActivity.this, "Signup failed!", Toast.LENGTH_SHORT).show();
                            }
                          );
                        }
      );
    });
  }
}
