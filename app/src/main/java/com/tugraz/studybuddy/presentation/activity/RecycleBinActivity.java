package com.tugraz.studybuddy.presentation.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import com.tugraz.studybuddy.R;
import com.tugraz.studybuddy.data.model.CourseModel;
import com.tugraz.studybuddy.presentation.adapter.CourseAdapter;
import com.tugraz.studybuddy.presentation.contract.IClickListener;
import com.tugraz.studybuddy.presentation.viewmodel.CourseViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class RecycleBinActivity extends AppCompatActivity implements IClickListener<CourseModel> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_bin);

        CourseViewModel courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        RecyclerView courseRecycler = findViewById(R.id.recyclerViewMain);

        courseViewModel.getAllCourses(true).observe(this, courses -> {
            courseRecycler.setAdapter(new CourseAdapter(courses, this));
            courseRecycler.setLayoutManager(new LinearLayoutManager(this));
        });
    }
    @Override
    public void onItemClick(CourseModel course) {
        CourseViewModel courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        new AlertDialog.Builder(this)
                .setTitle(R.string.recover_title)
                .setMessage(R.string.recover_message_course)
                .setPositiveButton(android.R.string.ok, (dialog, which) -> courseViewModel.setCourseToDeleted(course, false))
                .setNegativeButton(android.R.string.cancel, null)
                .show();
    }

    @Override
    public boolean longOnItemClick(CourseModel course) {
        CourseViewModel courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        new AlertDialog.Builder(this)
                .setTitle(R.string.delete_title)
                .setMessage(R.string.delete_message_course)
                .setPositiveButton(android.R.string.ok, (dialog, which) -> courseViewModel.deleteCourse(course))
                .setNegativeButton(android.R.string.cancel, null)
                .show();
        return true;
    }

}