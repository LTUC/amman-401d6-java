package com.example.helper.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.helper.R;

public class SettingsActivity extends AppCompatActivity {

    private static final String TAG = SettingsActivity.class.getSimpleName();
    public static final String ADDRESS = "address";
    private EditText mAddressEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mAddressEditText = findViewById(R.id.edit_text_address_setting);
        Button btnSubmit = findViewById(R.id.btn_submit);
        
        btnSubmit.setOnClickListener(view -> {
            Log.i(TAG, "Submit button clicked");

            // make sure the address is greater than 10 characters
            if (mAddressEditText.getText().toString().length() >= 10) {
                saveAddress();
            } else {
                Toast.makeText(this, "Add a longer address", Toast.LENGTH_SHORT).show();
            }

            // Check if no view has focus: https://stackoverflow.com/questions/1109022/how-to-close-hide-the-android-soft-keyboard-programmatically
            View view2 = this.getCurrentFocus();
            if (view2 != null) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view2.getWindowToken(), 0);
            }
        });

        mAddressEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.i(TAG, "onTextChanged: the text is : " + charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.i(TAG, "afterTextChanged: the final text is : " + editable.toString());
                if (!btnSubmit.isEnabled()) {
                    btnSubmit.setEnabled(true);
                }

                if (editable.toString().length() == 0){
                    btnSubmit.setEnabled(false);
                }

            }
        });
    }

    private void saveAddress() {
        // get the text from the edit text
        String address = mAddressEditText.getText().toString();
        
        // create shared preference object and set up an editor
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor preferenceEditor = sharedPreferences.edit();

        // save the text to shared preferences
        preferenceEditor.putString(ADDRESS, address);
        preferenceEditor.apply();

        Toast.makeText(this, "Address Saved", Toast.LENGTH_SHORT).show();
    }
}