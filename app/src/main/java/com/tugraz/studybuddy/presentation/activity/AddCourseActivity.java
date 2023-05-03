package com.tugraz.studybuddy.presentation.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.tugraz.studybuddy.R;

import com.tugraz.studybuddy.presentation.viewmodel.CourseViewModel;

import java.util.Calendar;

public class AddCourseActivity extends AppCompatActivity {

    //private CourseViewModel courseViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        CourseViewModel courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);

        EditText editTextCourseName = findViewById(R.id.editTextCourseName);
        EditText editTextCourseDescription = findViewById(R.id.editTextCourseDescription);
        EditText editTextExamDate = findViewById(R.id.editTextExamDate);

        findViewById(R.id.editTextExamDate).setOnClickListener(view -> {
            final Calendar cldr = Calendar.getInstance();
            int day = cldr.get(Calendar.DAY_OF_MONTH);
            int month = cldr.get(Calendar.MONTH);
            int year = cldr.get(Calendar.YEAR);


            DatePickerDialog picker = new DatePickerDialog(AddCourseActivity.this,
                    (view1, year1, monthOfYear, dayOfMonth) -> editTextExamDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year1),
                    year, month, day);
            picker.show();
        });
        //if button got back?
        findViewById(R.id.buttonGoBack).setOnClickListener(v -> {
            startActivity(new Intent(this, OverviewActivity.class));
        });

        //if button add?
        findViewById(R.id.buttonAddCourse).setOnClickListener(v -> {
            String courseName = editTextCourseName.getText().toString();
            String courseDescription = editTextCourseDescription.getText().toString();
            String examDate = editTextExamDate.getText().toString();

            if(!validinput(courseName, courseDescription, examDate)){
                return;
            }
            courseViewModel.createCourse(courseName,courseDescription,examDate);

        });
    }

    private boolean validinput(String examDate, String examDescription, String date ) {
        if (examDate.isEmpty() || examDescription.isEmpty() || date.isEmpty()) {
            Toast.makeText(getApplicationContext(), getString(R.string.all_input_fields), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}