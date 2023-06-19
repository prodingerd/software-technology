package com.tugraz.studybuddy.data.repository;

import androidx.lifecycle.MutableLiveData;

import com.tugraz.studybuddy.data.model.CardModel;
import com.tugraz.studybuddy.data.model.CourseModel;

import java.util.List;

public interface ICourseRepository extends IGenericRepository<CourseModel> {

    void cloneByShareCode(String shareCode);

    MutableLiveData<List<CardModel>> getAllCards(String courseId);

    void addCard(String courseId, CardModel entity);

    void updateCard(String courseId, CardModel entity);

    void deleteCard(String courseId, CardModel entity);
}
