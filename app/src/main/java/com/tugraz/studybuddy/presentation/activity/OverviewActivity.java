package com.tugraz.studybuddy.presentation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tugraz.studybuddy.R;
import com.tugraz.studybuddy.data.model.CourseModel;
import com.tugraz.studybuddy.data.repository.CourseRepository;
import com.tugraz.studybuddy.data.repository.ICourseRepository;
import com.tugraz.studybuddy.presentation.adapter.CourseAdapter;

public class OverviewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        ICourseRepository<CourseModel> repository = new CourseRepository();
        RecyclerView courseRecycler = findViewById(R.id.recyclerViewMain);
        CourseAdapter adapter = new CourseAdapter(repository.getAll());

        courseRecycler.setAdapter(adapter);
        courseRecycler.setLayoutManager(new LinearLayoutManager(this));

        findViewById(R.id.button_add_course).setOnClickListener(v -> {
            Toast toast = Toast.makeText(getApplicationContext(), "Clicked add course", Toast.LENGTH_SHORT);
            toast.show();

            startActivity(new Intent(this, AddCourseActivity.class));
        });
    }
}
