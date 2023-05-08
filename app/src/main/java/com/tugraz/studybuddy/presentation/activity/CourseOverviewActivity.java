package com.tugraz.studybuddy.presentation.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tugraz.studybuddy.R;
import com.tugraz.studybuddy.data.model.CardModel;
import com.tugraz.studybuddy.data.model.CourseModel;
import com.tugraz.studybuddy.presentation.adapter.CardAdapter;
import com.tugraz.studybuddy.presentation.viewmodel.CardViewModel;
import com.tugraz.studybuddy.presentation.viewmodel.CourseViewModel;

import java.time.LocalDate;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CourseOverviewActivity extends AppCompatActivity implements CardAdapter.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_overview);

        CourseViewModel courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        CardViewModel cardViewModel = new ViewModelProvider(this).get(CardViewModel.class);

        RecyclerView cardRecycler = findViewById(R.id.recyclerViewCard);
        CardAdapter cardAdapter = new CardAdapter(cardViewModel.getAllCourses(), this);

        cardRecycler.setAdapter(cardAdapter);
        cardRecycler.setLayoutManager(new LinearLayoutManager(this));

        Bundle extras = getIntent().getExtras();
        CourseModel course = (CourseModel) extras.get("course");

        EditText editTextCourseName = findViewById(R.id.editTextCourseName);
        EditText editTextCourseDescription = findViewById(R.id.editTextCourseDescription);
        EditText editTextExamDate = findViewById(R.id.editTextExamDate);

        editTextCourseName.setText(course.getName());
        editTextCourseDescription.setText(course.getDescription());
        editTextExamDate.setText(course.getExamDate().toString());

        findViewById(R.id.editTextExamDate).setOnClickListener(v -> {
            LocalDate date = LocalDate.now();

            DatePickerDialog picker = new DatePickerDialog(CourseOverviewActivity.this,
                    (dialog, year, month, day) -> {
                        LocalDate examDate = LocalDate.of(year, month + 1, day);
                        editTextExamDate.setText(examDate.toString());
                    },
                    date.getYear(), date.getMonthValue() - 1, date.getDayOfMonth());
            picker.show();
        });

        findViewById(R.id.buttonGoBack).setOnClickListener(v -> startActivity(new Intent(this, OverviewActivity.class)));

        findViewById(R.id.buttonSaveCourse).setOnClickListener(v -> {
            String courseName = editTextCourseName.getText().toString();
            String courseDescription = editTextCourseDescription.getText().toString();
            String examDate = editTextExamDate.getText().toString();

            if (!validInput(courseName, courseDescription, examDate)) {
                return;
            }
            if (courseViewModel.updateCourse(course.getId(), courseName, courseDescription, examDate)) {
                startActivity(new Intent(this, OverviewActivity.class));
            } else {
                Toast.makeText(getApplicationContext(), "Updating course failed!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onItemClick(CardModel card) {
        throw new NotImplementedError();
    }

    private boolean validInput(String examDate, String examDescription, String date) {
        if (examDate.isEmpty() || examDescription.isEmpty() || date.isEmpty()) {
            Toast.makeText(getApplicationContext(), getString(R.string.all_input_fields), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
