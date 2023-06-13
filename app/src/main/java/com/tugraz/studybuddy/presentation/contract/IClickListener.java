package com.tugraz.studybuddy.presentation.contract;

public interface IClickListener<T> {

    void onItemClick(T entity);
    boolean longOnItemClick(T entity);
}
