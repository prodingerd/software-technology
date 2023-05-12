package com.tugraz.studybuddy.domain.service;

import com.tugraz.studybuddy.data.model.CardModel;
import com.tugraz.studybuddy.data.repository.CardRepository;

import java.util.List;

import javax.inject.Inject;

public class CardService {

    private final CardRepository cardRepository;

    @Inject
    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public List<CardModel> getAllCards() {
        return cardRepository.getAll();
    }
}
