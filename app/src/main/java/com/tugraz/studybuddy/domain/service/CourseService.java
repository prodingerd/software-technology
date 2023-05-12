package com.tugraz.studybuddy.domain.service;

import com.tugraz.studybuddy.data.model.CourseModel;
import com.tugraz.studybuddy.data.repository.CourseRepository;

import java.time.LocalDate;
import java.util.List;

import javax.inject.Inject;

public class CourseService {

    private final CourseRepository courseRepository;

    @Inject
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<CourseModel> getAllCourses() {
        return courseRepository.getAll();
    }

    public CourseModel getCourseById(String id) {
        return courseRepository.getById(id);
    }

    public boolean createCourse(String name, String description, String examDate) {
        if (name.isEmpty() || description.isEmpty() || examDate.isEmpty()) {
            return false;
        }

        LocalDate parsedExamDate = LocalDate.parse(examDate);
        courseRepository.add(new CourseModel(name, description, parsedExamDate));

        return true;
    }

    public boolean updateCourse(String id, String name, String description, String examDate) {
        if (id.isEmpty() || name.isEmpty() || description.isEmpty() || examDate.isEmpty()) {
            return false;
        }

        LocalDate parsedExamDate = LocalDate.parse(examDate);
        CourseModel toUpdate = courseRepository.getById(id);
        toUpdate.setName(name);
        toUpdate.setDescription(description);
        toUpdate.setExamDate(parsedExamDate);
        courseRepository.update(toUpdate);

        return true;
    }

    public boolean deleteCourse(CourseModel course) {
        courseRepository.delete(course);
        return true;
    }
}
