package com.tugraz.studybuddy.presentation.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tugraz.studybuddy.R;
import com.tugraz.studybuddy.data.model.CourseModel;
import com.tugraz.studybuddy.presentation.adapter.CourseAdapter;
import com.tugraz.studybuddy.presentation.viewmodel.CourseViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class OverviewActivity extends AppCompatActivity implements CourseAdapter.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        CourseViewModel courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);

        RecyclerView courseRecycler = findViewById(R.id.recyclerViewMain);
        CourseAdapter adapter = new CourseAdapter(courseViewModel.getAllCourses(), this);

        courseRecycler.setAdapter(adapter);
        courseRecycler.setLayoutManager(new LinearLayoutManager(this));

        findViewById(R.id.button_add_course).setOnClickListener(v -> startActivity(new Intent(this, AddCourseActivity.class)));
    }

    @Override
    public void onItemClick(CourseModel course) {
        Intent intent = new Intent(this, CourseOverviewActivity.class);
        intent.putExtra("course", course);
        startActivity(intent);
    }

    public boolean longOnItemClick(CourseModel course) {
        CourseViewModel courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        courseViewModel.deleteCourse(course);
        startActivity(new Intent(this, OverviewActivity.class));
        return true;
    }
}
