package com.tugraz.studybuddy.presentation.viewmodel;

import androidx.lifecycle.ViewModel;

import com.tugraz.studybuddy.data.repository.CourseRepository;
import com.tugraz.studybuddy.domain.service.CourseService;

public class CourseViewModel extends ViewModel {

    private final CourseService courseService;

    public CourseViewModel() {
        this.courseService = new CourseService(new CourseRepository());
    }

    public CourseViewModel(CourseService courseService) {
        this.courseService = courseService;
    }

    public void createCourse(String courseName, String courseDescription, String examDate){
        var course = courseService.createCourse(courseName,courseDescription,examDate);
    }
}
