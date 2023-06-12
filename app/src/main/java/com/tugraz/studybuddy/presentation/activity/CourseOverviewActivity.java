package com.tugraz.studybuddy.presentation.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tugraz.studybuddy.R;
import com.tugraz.studybuddy.data.model.CardModel;
import com.tugraz.studybuddy.data.model.CourseModel;
import com.tugraz.studybuddy.presentation.adapter.CardAdapter;
import com.tugraz.studybuddy.presentation.generic.IClickListener;
import com.tugraz.studybuddy.presentation.viewmodel.CardViewModel;
import com.tugraz.studybuddy.presentation.viewmodel.CourseViewModel;

import java.time.LocalDate;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CourseOverviewActivity extends AppCompatActivity implements IClickListener<CardModel> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_overview);

        CourseViewModel courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        CardViewModel cardViewModel = new ViewModelProvider(this).get(CardViewModel.class);

        RecyclerView cardRecycler = findViewById(R.id.recyclerViewCard);
        CardAdapter cardAdapter = new CardAdapter(cardViewModel.getAllCards(), this);

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

        @SuppressLint("UseSwitchCompatOrMaterialCode")
        Switch simpleSwitch = findViewById(R.id.switchToggleEditCourseDetails);

        simpleSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                editTextCourseName.setEnabled(true);
                editTextCourseDescription.setEnabled(true);
                editTextExamDate.setEnabled(true);
                findViewById(R.id.buttonSaveCourse).setEnabled(true);
            } else {
                editTextCourseName.setEnabled(false);
                editTextCourseDescription.setEnabled(false);
                editTextExamDate.setEnabled(false);
                findViewById(R.id.buttonSaveCourse).setEnabled(false);
            }
        });

        findViewById(R.id.buttonPlayCourse).setOnClickListener(v ->
                startActivity(new Intent(this, PlayCourseActivity.class).putExtra("course", course))
        );

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
        //TODO implement edit Card
    }

    @Override
    public boolean longOnItemClick(CardModel card) {
        CardViewModel cardViewModel = new ViewModelProvider(this).get(CardViewModel.class);
        new AlertDialog.Builder(this).setTitle(R.string.delete_title).setMessage(R.string.delete_message_card)
                .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                    cardViewModel.deleteCard(card);
                    Bundle extras = getIntent().getExtras();
                    Intent intent = new Intent(this, CourseOverviewActivity.class);
                    intent.putExtra("course", (CourseModel) extras.get("course"));
                    startActivity(intent);
                }).setNegativeButton(android.R.string.cancel, null).show();
        return true;
    }

    private boolean validInput(String examDate, String examDescription, String date) {
        if (examDate.isEmpty() || examDescription.isEmpty() || date.isEmpty()) {
            Toast.makeText(getApplicationContext(), getString(R.string.all_input_fields), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
