package com.tugraz.studybuddy.presentation.viewmodel;

import androidx.lifecycle.ViewModel;

import com.tugraz.studybuddy.data.model.CardModel;
import com.tugraz.studybuddy.domain.service.CardService;

import java.util.ArrayDeque;
import java.util.Queue;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class PlayCourseViewModel extends ViewModel {

    private final CardService cardService;
    private Queue<CardModel> cards;

    @Inject
    public PlayCourseViewModel(CardService cardService) {
        this.cardService = cardService;
    }

    public void initialize(String courseId) {
        this.cards = new ArrayDeque<>(cardService.getAllCards());
    }

    public CardModel nextCard() {
        return cards.poll();
    }
}
