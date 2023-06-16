package com.tugraz.studybuddy.presentation.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tugraz.studybuddy.data.model.CardModel;
import com.tugraz.studybuddy.domain.service.CardService;
import java.util.List;
import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class PlayCourseViewModel extends ViewModel {

    private final CardService cardService;

    @Inject
    public PlayCourseViewModel(CardService cardService) {
        this.cardService = cardService;
    }

    public MutableLiveData<List<CardModel>> initialize(String courseId) {
        return cardService.getAllCards(courseId);
    }
}
