package com.tugraz.studybuddy.presentation.viewmodel;

import androidx.lifecycle.ViewModel;

import com.tugraz.studybuddy.data.model.CardModel;
import com.tugraz.studybuddy.domain.service.CardService;
import com.tugraz.studybuddy.domain.service.CourseService;

import java.util.ArrayDeque;
import java.util.Queue;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class PlayCourseViewModel extends ViewModel {

    private final CourseService courseService;
    private final CardService cardService;
    private Queue<CardModel> cards;

    @Inject
    public PlayCourseViewModel(CourseService courseService, CardService cardService) {
        this.courseService = courseService;
        this.cardService = cardService;
    }

    public void initialize(String courseId) {
        courseService.getCourseById(courseId);
        cards = new ArrayDeque<>(cardService.getAllCards());
    }

    public CardModel nextCard() {
        return cards.poll();
    }
}
