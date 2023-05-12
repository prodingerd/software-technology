package com.tugraz.studybuddy.data.repository;

import java.util.List;

public interface IGenericRepository<T> {

    public List<T> getAll();

    public T getById(String id);

    public void add(T entity);

    public void update(T entity);

    public void delete(T entity);
}
