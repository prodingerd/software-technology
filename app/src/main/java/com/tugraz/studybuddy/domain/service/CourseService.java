package com.tugraz.studybuddy.domain.service;

import androidx.lifecycle.MutableLiveData;

import com.tugraz.studybuddy.data.model.CourseModel;
import com.tugraz.studybuddy.data.repository.CourseRepository;

import java.time.LocalDate;
import java.util.List;

import javax.inject.Inject;

public class CourseService {

    private final CourseRepository repository;

    @Inject
    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    public MutableLiveData<List<CourseModel>> getAllCourses(boolean includeDeleted) {
        return repository.getAll(includeDeleted);
    }

    public boolean createCourse(String name, String description, String examDate) {
        if (name.isEmpty() || description.isEmpty() || examDate.isEmpty()) {
            return false;
        }

        LocalDate parsedExamDate = LocalDate.parse(examDate);
        repository.add(new CourseModel(name, description, parsedExamDate));

        return true;
    }

    public boolean updateCourse(String id, String name, String description, String examDate) {
        if (id.isEmpty() || name.isEmpty() || description.isEmpty() || examDate.isEmpty()) {
            return false;
        }

        LocalDate parsedExamDate = LocalDate.parse(examDate);
        repository.update(new CourseModel(id, name, description, parsedExamDate));
        return true;
    }

    public void deleteCourse(CourseModel course) {
        repository.delete(course);
    }

    public void setCourseToDeleted(CourseModel course, boolean deleted) {
        course.setDeleted(deleted);
        repository.update(course);
    }

    public void cloneCourse(String shareCode) {
        repository.cloneByShareCode(shareCode);
    }

    public void incrementPlayCount(CourseModel course){
        course.setPlayedCount(course.getPlayedCount() + 1);
        repository.update(course);
    }
}
