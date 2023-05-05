package com.tugraz.studybuddy.domain.service;

import android.util.Log;

import com.tugraz.studybuddy.data.model.CourseModel;
import com.tugraz.studybuddy.data.repository.CourseRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

public class CourseService {
    private static final String TAG = "CourseService";

    private final CourseRepository courseRepository;

    @Inject
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<CourseModel> getAllCourses() {
        return courseRepository.getAll();
    }

    public boolean createCourse(String name, String description, String examDate) {
        try {
            // TODO Validation.

            // TODO Fix the simple date format.
            Date parsedExamDate = new SimpleDateFormat("dd/MM/yyyy").parse(examDate);
            courseRepository.add(new CourseModel(name, description, parsedExamDate));
        } catch (ParseException exception) {
            Log.e(TAG, "Parsing date failed");
            return false;
        }

        return true;
    }

    public boolean updateCourse(String id, String name, String description, String examDate) {
        try {
            // TODO Validation.

            // TODO Fix the simple date format.
            Date parsedExamDate = new SimpleDateFormat("dd/MM/yyyy").parse(examDate);
            CourseModel toUpdate = courseRepository.getById(id);
            toUpdate.setName(name);
            toUpdate.setDescription(description);
            toUpdate.setExamDate(parsedExamDate);
            courseRepository.update(toUpdate);
        } catch (ParseException exception) {
            Log.e(TAG, "Parsing date failed");
            return false;
        }

        return true;
    }
}
