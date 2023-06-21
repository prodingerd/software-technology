package com.tugraz.studybuddy.data.repository;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

public interface IGenericRepository<T> {

    MutableLiveData<List<T>> getAll(boolean includeDeleted);

    void add(T entity);

    void update(T entity);

    void delete(T entity);
}
