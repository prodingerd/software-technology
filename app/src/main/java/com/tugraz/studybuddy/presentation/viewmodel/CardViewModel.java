package com.tugraz.studybuddy.presentation.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tugraz.studybuddy.data.model.CardModel;
import com.tugraz.studybuddy.domain.service.CardService;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class CardViewModel extends ViewModel {

    private final CardService cardService;
    private String courseId;

    @Inject
    public CardViewModel(CardService cardService) {
        this.cardService = cardService;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public MutableLiveData<List<CardModel>> getAllCards() {
        return cardService.getAllCards(courseId);
    }

    public boolean createCard(String frontText, String backText) {
        return cardService.createCard(courseId, frontText, backText);
    }

    public boolean updateCard(String id, String frontText, String backText) {
        return cardService.updateCard(courseId, id, frontText, backText);
    }

    public void deleteCard(CardModel card) {
        cardService.deleteCard(courseId, card);
    }
}
