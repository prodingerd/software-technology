package com.tugraz.studybuddy.presentation.generic;

public interface IClickListener<T> {
    void onItemClick(T entity);
    boolean longOnItemClick(T entity);
}
