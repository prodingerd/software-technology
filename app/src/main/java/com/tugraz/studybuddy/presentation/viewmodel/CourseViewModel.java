package com.tugraz.studybuddy.presentation.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tugraz.studybuddy.data.model.CourseModel;
import com.tugraz.studybuddy.domain.service.CourseService;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class CourseViewModel extends ViewModel {

    private final CourseService courseService;

    @Inject
    public CourseViewModel(CourseService courseService) {
        this.courseService = courseService;
    }

    public MutableLiveData<List<CourseModel>> getAllCourses(boolean includeDeleted) {
        return courseService.getAllCourses(includeDeleted);
    }

    public boolean createCourse(String name, String description, String examDate) {
        return courseService.createCourse(name, description, examDate);
    }

    public boolean updateCourse(String id, String name, String description, String examDate) {
        return courseService.updateCourse(id, name, description, examDate);
    }

    public void deleteCourse(CourseModel course) {
        courseService.deleteCourse(course);
    }

    public void setCourseToDeleted(CourseModel course, boolean deleted) {
        courseService.setCourseToDeleted(course, deleted);
    }

    public void cloneCourse(String shareCode) {
        courseService.cloneCourse(shareCode);
    }

    public void incrementPlayCount(CourseModel course) {
        courseService.incrementPlayCount(course);
    }
}
