package com.tugraz.studybuddy.presentation.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.tugraz.studybuddy.R;
import com.tugraz.studybuddy.data.model.CourseModel;
import com.tugraz.studybuddy.presentation.viewmodel.CourseViewModel;

import java.util.Date;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CourseOverviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_overview);

        CourseViewModel courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);

        Bundle extras = getIntent().getExtras();
        CourseModel course = (CourseModel) extras.get("course");

        EditText editTextCourseName = findViewById(R.id.editTextCourseName);
        EditText editTextCourseDescription = findViewById(R.id.editTextCourseDescription);
        EditText editTextExamDate = findViewById(R.id.editTextExamDate);

        editTextCourseName.setText(course.getName());
        editTextCourseDescription.setText(course.getDescription());

        Date date = course.getExamDate();

        editTextExamDate.setText((date.getDay() + "/" + date.getMonth() + "/" + date.getYear()));

        findViewById(R.id.buttonGoBack).setOnClickListener(v -> {
            startActivity(new Intent(this, OverviewActivity.class));
        });

        findViewById(R.id.buttonSaveCourse).setOnClickListener(v -> {
            String courseName = editTextCourseName.getText().toString();
            String courseDescription = editTextCourseDescription.getText().toString();
            String examDate = editTextExamDate.getText().toString();

            if (!validInput(courseName, courseDescription, examDate)) {
                return;
            }
            if (courseViewModel.updateCourse(course.getId(),courseName, courseDescription, examDate)) {
                startActivity(new Intent(this, OverviewActivity.class));
            } else {
                Toast.makeText(getApplicationContext(), "Course creation failed!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validInput(String examDate, String examDescription, String date) {
        if (examDate.isEmpty() || examDescription.isEmpty() || date.isEmpty()) {
            Toast.makeText(getApplicationContext(), getString(R.string.all_input_fields), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}