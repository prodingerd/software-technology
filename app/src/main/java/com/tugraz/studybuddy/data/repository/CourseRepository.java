package com.tugraz.studybuddy.data.repository;

import com.tugraz.studybuddy.data.model.CourseModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CourseRepository implements ICourseRepository<CourseModel> {
    private static final String COURSE_COLLECTION = "courses";
    private static final String TAG = "CourseRepository";

    private final List<CourseModel> courses;

    public CourseRepository() {
        courses = new ArrayList<>(List.of(
                new CourseModel("Software Technology", "Summer 2023", new Date()),
                new CourseModel("Android Development", "Summer 2023", new Date()),
                new CourseModel("Analysis T 10000000", "Summer 2023", new Date())
        ));
    }

    @Override
    public List<CourseModel> getAll() {
        return courses;
    }

    @Override
    public CourseModel getById(String id) {
        return courses.stream()
                .filter(x -> x.getId().equals(id))
                .findFirst().orElseGet(null);
    }

    @Override
    public void add(CourseModel entity) {
        courses.add(entity);
    }

    @Override
    public void update(CourseModel entity) {
    }

    @Override
    public void delete(CourseModel entity) {
    }
}