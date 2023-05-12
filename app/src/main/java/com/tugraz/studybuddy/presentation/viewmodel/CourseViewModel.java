package com.tugraz.studybuddy.presentation.viewmodel;

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

    public List<CourseModel> getAllCourses() {
        return courseService.getAllCourses();
    }

    public boolean createCourse(String courseName, String courseDescription, String examDate) {
        return courseService.createCourse(courseName, courseDescription, examDate);
    }

    public boolean updateCourse(String id, String courseName, String courseDescription, String examDate) {
        return courseService.updateCourse(id, courseName, courseDescription, examDate);
    }

    public boolean deleteCourse(CourseModel course) {
        return courseService.deleteCourse(course);
    }
}
