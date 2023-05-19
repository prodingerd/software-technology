package com.tugraz.studybuddy.presentation.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.tugraz.studybuddy.R;
import com.tugraz.studybuddy.data.model.CardModel;
import com.tugraz.studybuddy.data.model.CourseModel;
import com.tugraz.studybuddy.presentation.viewmodel.PlayCourseViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class PlayCourseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_course);

        PlayCourseViewModel playCourseViewModel = new ViewModelProvider(this).get(PlayCourseViewModel.class);

        Bundle extras = getIntent().getExtras();
        CourseModel course = (CourseModel) extras.get("course");

        playCourseViewModel.initialize(course.getId());

        TextView frontCardText = findViewById(R.id.textViewFront);
        TextView backCardText = findViewById(R.id.textViewBack);
        CardModel currentCard = playCourseViewModel.nextCard();
        frontCardText.setText(currentCard.getFrontText());
        backCardText.setText(currentCard.getBackText());
        backCardText.setVisibility(View.INVISIBLE);

        frontCardText.setOnClickListener(v -> {
            if (backCardText.getVisibility() == View.INVISIBLE) {
                backCardText.setVisibility(View.VISIBLE);
            } else {
                backCardText.setVisibility(View.INVISIBLE);
            }
        });

        findViewById(R.id.buttonNextCard).setOnClickListener(v -> {
            CardModel nextCard = playCourseViewModel.nextCard();
            frontCardText.setText(nextCard.getFrontText());
            backCardText.setText(nextCard.getBackText());
            backCardText.setVisibility(View.INVISIBLE);
        });
    }
}
