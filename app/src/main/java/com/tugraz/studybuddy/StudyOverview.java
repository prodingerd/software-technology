package com.tugraz.studybuddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tugraz.studybuddy.adapter.CustomCourseAdapter;

import java.util.ArrayList;

public class StudyOverview extends AppCompatActivity {

    ArrayList<Course> courses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_overview);

        RecyclerView course_list = findViewById(R.id.recyclerViewMain);

        CustomCourseAdapter adapter = new CustomCourseAdapter(Course.createDummyCourses());
        course_list.setAdapter(adapter);
        course_list.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton add_course = findViewById(R.id.button_add_course);
        add_course.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "Clicked add course", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}