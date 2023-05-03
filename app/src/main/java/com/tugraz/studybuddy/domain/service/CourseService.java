package com.tugraz.studybuddy.domain.service;

import com.tugraz.studybuddy.data.repository.CourseRepository;

public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public boolean createCourse(String courseName, String courseDescription, String courseExamDate){
        //validate
        return true;
    }
}
