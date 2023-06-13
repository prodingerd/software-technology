package com.tugraz.studybuddy.domain.service;

import androidx.lifecycle.MutableLiveData;

import com.tugraz.studybuddy.data.model.CardModel;
import com.tugraz.studybuddy.data.repository.CourseRepository;

import java.util.List;

import javax.inject.Inject;

public class CardService {

    private final CourseRepository repository;

    @Inject
    public CardService(CourseRepository repository) {
        this.repository = repository;
    }

    public MutableLiveData<List<CardModel>> getAllCards(String courseId) {
        return repository.getAllCards(courseId);
    }

    public boolean createCard(String courseId, String frontText, String backText) {
        if (frontText.isEmpty() || backText.isEmpty()) {
            return false;
        }

        repository.addCard(courseId, new CardModel(frontText, backText));
        return true;
    }

    public boolean updateCard(String courseId, String id, String frontText, String backText) {
        if (courseId.isEmpty() || id.isEmpty() || frontText.isEmpty() || backText.isEmpty()) {
            return false;
        }

        repository.updateCard(courseId, new CardModel(id, frontText, backText));
        return true;
    }

    public void deleteCard(String courseId, CardModel card) {
        repository.deleteCard(courseId, card);
    }
}
