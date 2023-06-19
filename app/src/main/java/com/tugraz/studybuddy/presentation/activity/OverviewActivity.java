package com.tugraz.studybuddy.presentation.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tugraz.studybuddy.R;
import com.tugraz.studybuddy.data.model.CourseModel;
import com.tugraz.studybuddy.presentation.adapter.CourseAdapter;
import com.tugraz.studybuddy.presentation.contract.IClickListener;
import com.tugraz.studybuddy.presentation.viewmodel.CourseViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class OverviewActivity extends AppCompatActivity implements IClickListener<CourseModel> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        CourseViewModel courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        RecyclerView courseRecycler = findViewById(R.id.recyclerViewMain);

        courseViewModel.getAllCourses().observe(this, courses -> {
            courseRecycler.setAdapter(new CourseAdapter(courses, this));
            courseRecycler.setLayoutManager(new LinearLayoutManager(this));
        });

        FloatingActionButton mFab, mAddFab, mShareFab;
        TextView addActionText, shareActionText;

        mFab = findViewById(R.id.add_fab);

        mAddFab = findViewById(R.id.add_course_fab);
        mShareFab = findViewById(R.id.share_fab);

        addActionText = findViewById(R.id.add_course_action_text);
        shareActionText = findViewById(R.id.share_action_text);

        mAddFab.setVisibility(View.GONE);
        mShareFab.setVisibility(View.GONE);

        addActionText.setVisibility(View.GONE);
        shareActionText.setVisibility(View.GONE);

        mFab.setOnClickListener(view -> {
            if (mAddFab.getVisibility() == View.GONE) {
                mAddFab.show();
                mShareFab.show();
                addActionText.setVisibility(View.VISIBLE);
                shareActionText.setVisibility(View.VISIBLE);

            } else {
                mAddFab.setVisibility(View.GONE);
                mShareFab.setVisibility(View.GONE);
                addActionText.setVisibility(View.GONE);
                shareActionText.setVisibility(View.GONE);
            }
        });

        mShareFab.setOnClickListener(v -> {
            courseViewModel.cloneCourse("46e97");
        });

        mAddFab.setOnClickListener(v -> startActivity(new Intent(this, AddCourseActivity.class)));
    }

    @Override
    public void onItemClick(CourseModel course) {
        Intent intent = new Intent(this, CourseOverviewActivity.class);
        intent.putExtra("course", course);
        startActivity(intent);
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
