package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        Button addStudentBtn = findViewById(R.id.submitStudentInfo);
        addStudentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText studentNameField = findViewById(R.id.studentNameInput);
                String studentName = studentNameField.getText().toString();

                EditText enrollYearField = findViewById(R.id.enrollYearInput);
                int enrollYear = Integer.parseInt(enrollYearField.getText().toString());

                Student student = new Student(studentName,enrollYear);
                Long newStudentId = AppDatabase.getInstance(getApplicationContext()).studentDao().insertStudent(student);

                System.out.println("******************** Student ID = " + newStudentId + " ************************");
            }
        });




    }
}