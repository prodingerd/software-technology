package com.tugraz.studybuddy.presentation.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tugraz.studybuddy.R;
import com.tugraz.studybuddy.data.model.CardModel;
import com.tugraz.studybuddy.data.model.CourseModel;
import com.tugraz.studybuddy.presentation.adapter.CardAdapter;
import com.tugraz.studybuddy.presentation.contract.IClickListener;
import com.tugraz.studybuddy.presentation.viewmodel.CardViewModel;
import com.tugraz.studybuddy.presentation.viewmodel.CourseViewModel;

import java.time.LocalDate;
import java.util.UUID;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CourseOverviewActivity extends AppCompatActivity implements IClickListener<CardModel> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_overview);

        Bundle extras = getIntent().getExtras();
        CourseModel course = (CourseModel) extras.get("course");

        EditText editTextCourseName = findViewById(R.id.editTextCourseName);
        EditText editTextCourseDescription = findViewById(R.id.editTextCourseDescription);
        EditText editTextExamDate = findViewById(R.id.editTextExamDate);

        editTextCourseName.setText(course.getName());
        editTextCourseDescription.setText(course.getDescription());
        editTextExamDate.setText(course.prettyExamDate().toString());

        CourseViewModel courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        CardViewModel cardViewModel = new ViewModelProvider(this).get(CardViewModel.class);
        RecyclerView cardRecycler = findViewById(R.id.recyclerViewCard);

        cardViewModel.setCourseId(course.getId());

        cardViewModel.getAllCards().observe(this, cards -> {
            cardRecycler.setAdapter(new CardAdapter(cards, this));
            cardRecycler.setLayoutManager(new LinearLayoutManager(this));
        });

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

        findViewById(R.id.buttonSaveCourse).setOnClickListener(v -> {
            String courseName = editTextCourseName.getText().toString();
            String courseDescription = editTextCourseDescription.getText().toString();
            String examDate = editTextExamDate.getText().toString();

            if (courseViewModel.updateCourse(course.getId(), courseName, courseDescription, examDate)) {
                startActivity(new Intent(this, OverviewActivity.class));
            } else {
                Toast.makeText(getApplicationContext(), "Updating course failed!", Toast.LENGTH_SHORT).show();
            }
        });

        FloatingActionButton mFab, mAddFab, mShareFab, mPlayFab;

        TextView addActionText, shareActionText, playActionText;

        mFab = findViewById(R.id.add_fab);

        mAddFab = findViewById(R.id.add_card_fab);
        mShareFab = findViewById(R.id.share_fab);
        mPlayFab = findViewById(R.id.play_fab);

        addActionText = findViewById(R.id.add_card_action_text);
        shareActionText = findViewById(R.id.share_action_text);
        playActionText = findViewById(R.id.play_action_text);

        mAddFab.setVisibility(View.GONE);
        mShareFab.setVisibility(View.GONE);
        mPlayFab.setVisibility(View.GONE);
        addActionText.setVisibility(View.GONE);
        shareActionText.setVisibility(View.GONE);
        playActionText.setVisibility(View.GONE);

        mFab.setOnClickListener(view -> {
            if (mAddFab.getVisibility() == View.GONE) {
                mAddFab.show();
                mShareFab.show();
                mPlayFab.setVisibility(View.VISIBLE);
                addActionText.setVisibility(View.VISIBLE);
                shareActionText.setVisibility(View.VISIBLE);
                playActionText.setVisibility(View.VISIBLE);

            } else {
                mAddFab.setVisibility(View.GONE);
                mShareFab.setVisibility(View.GONE);
                mPlayFab.setVisibility(View.GONE);
                addActionText.setVisibility(View.GONE);
                shareActionText.setVisibility(View.GONE);
                playActionText.setVisibility(View.GONE);

            }
        });

        mPlayFab.setOnClickListener(v ->
                startActivity(new Intent(this, PlayCourseActivity.class).putExtra("course", course))
        );

        mShareFab.setOnClickListener(v -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Share course");
                    builder.setMessage("Do you want to share this course?");
                    builder.setPositiveButton(android.R.string.ok, (dialog, which) -> {
                        String shareCode = random();
                        Toast.makeText(getApplicationContext(), "Your share Code is: "+ shareCode, Toast.LENGTH_SHORT).show();
                    }).setNegativeButton(android.R.string.cancel, null);
                    builder.create().show();
        });

        mAddFab.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Create new card");

            final View customLayout = getLayoutInflater().inflate(R.layout.add_card_alert, null);
            builder.setView(customLayout);
            EditText editTextFrontText = customLayout.findViewById(R.id.editTextFrontText);
            EditText editTextBackText = customLayout.findViewById(R.id.editTextBackText);
            builder.setPositiveButton(android.R.string.ok, (dialog, which) -> {
                String frontText = editTextFrontText.getText().toString();
                String backText = editTextBackText.getText().toString();

                if (!cardViewModel.createCard(frontText, backText)) {
                    Toast.makeText(getApplicationContext(), "Course creation failed!", Toast.LENGTH_SHORT).show();
                }
            }).setNegativeButton(android.R.string.cancel, null);

            builder.create().show();
        });
    }

    @Override
    public void onItemClick(CardModel card) {
        CardViewModel cardViewModel = new ViewModelProvider(this).get(CardViewModel.class);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit card");

        final View customLayout = getLayoutInflater().inflate(R.layout.add_card_alert, null);
        builder.setView(customLayout);
        EditText editTextFrontText = customLayout.findViewById(R.id.editTextFrontText);
        EditText editTextBackText = customLayout.findViewById(R.id.editTextBackText);
        editTextFrontText.setText(card.getFrontText());
        editTextBackText.setText(card.getBackText());
        builder.setPositiveButton(android.R.string.ok, (dialog, which) -> {
            String frontText = editTextFrontText.getText().toString();
            String backText = editTextBackText.getText().toString();

            if (!cardViewModel.updateCard(card.getId(),frontText, backText)) {
                Toast.makeText(getApplicationContext(), "Failed to update card!", Toast.LENGTH_SHORT).show();
            }
        }).setNegativeButton(android.R.string.cancel, null);

        builder.create().show();
    }

    @Override
    public boolean longOnItemClick(CardModel card) {
        CardViewModel cardViewModel = new ViewModelProvider(this).get(CardViewModel.class);
        new AlertDialog.Builder(this)
                .setTitle(R.string.delete_title)
                .setMessage(R.string.delete_message_card)
                .setPositiveButton(android.R.string.ok, (dialog, which) -> cardViewModel.deleteCard(card))
                .setNegativeButton(android.R.string.cancel, null)
                .show();
        return true;
    }

    public static String random() {
        return UUID.randomUUID().toString().substring(0,5);
    }
}
