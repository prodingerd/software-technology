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

import java.util.ArrayDeque;
import java.util.Queue;

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

        TextView frontCardText = findViewById(R.id.textViewFront);
        TextView backCardText = findViewById(R.id.textViewBack);

        Queue<CardModel> cardQueue = new ArrayDeque<>();

        playCourseViewModel.initialize(course.getId()).observe(this, collection -> {
            cardQueue.addAll(collection);
            displayNextCard(cardQueue);
        });

        frontCardText.setOnClickListener(v -> {
            if (backCardText.getVisibility() == View.INVISIBLE) {
                backCardText.setVisibility(View.VISIBLE);
            } else {
                backCardText.setVisibility(View.INVISIBLE);
            }
        });

        findViewById(R.id.buttonNextCard).setOnClickListener(v -> displayNextCard(cardQueue));
    }

    private void displayNextCard(Queue<CardModel> cards){
        TextView frontCardText = findViewById(R.id.textViewFront);
        TextView backCardText = findViewById(R.id.textViewBack);

        CardModel nextCard = cards.poll();
        if(nextCard != null) {
            frontCardText.setText(nextCard.getFrontText());
            backCardText.setText(nextCard.getBackText());
            backCardText.setVisibility(View.INVISIBLE);
        }
    }
}
