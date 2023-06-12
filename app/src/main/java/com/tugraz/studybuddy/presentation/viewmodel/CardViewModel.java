package com.tugraz.studybuddy.presentation.viewmodel;

import androidx.lifecycle.ViewModel;

import com.tugraz.studybuddy.data.model.CardModel;
import com.tugraz.studybuddy.domain.service.CardService;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class CardViewModel extends ViewModel {

    private final CardService cardService;

    @Inject
    public CardViewModel(CardService cardService) {
        this.cardService = cardService;
    }

    public List<CardModel> getAllCards() {
        return cardService.getAllCards();
    }

    public void deleteCard(CardModel card) {
        cardService.deleteCard(card);
    }

    public boolean createCard(String frontText, String backText) {
        return cardService.createCard(frontText, backText);
    }
}
