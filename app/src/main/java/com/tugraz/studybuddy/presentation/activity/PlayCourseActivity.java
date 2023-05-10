package com.tugraz.studybuddy.presentation.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.tugraz.studybuddy.R;
import com.tugraz.studybuddy.data.model.CardModel;

public class PlayCourseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_course);

        Bundle extras = getIntent().getExtras();
        CardModel cards = (CardModel) extras.get("cards");
    }
}