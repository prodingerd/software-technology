package com.tugraz.studybuddy.data.repository;

import com.tugraz.studybuddy.data.model.CourseModel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import javax.inject.Inject;

public class CourseRepository implements ICourseRepository<CourseModel> {
    private static final String TAG = "CourseRepository";
    private static final String COURSE_COLLECTION = "courses";

    private static final List<CourseModel> courses = new ArrayList<>();

    @Inject
    public CourseRepository() {
        if (courses.isEmpty()) {
            courses.addAll(List.of(
                    new CourseModel("Software Technology", "Summer 2023", LocalDate.now()),
                    new CourseModel("Android Development", "Summer 2023", LocalDate.now()),
                    new CourseModel("Analysis T 10000000", "Summer 2023", LocalDate.now())
            ));
        }
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

        int pos = IntStream.range(0, courses.size())
                .filter(x -> courses.get(x).getId().equals(entity.getId()))
                .findFirst().getAsInt();

        if (pos != -1) {
            courses.set(pos, entity);
        }
    }

    @Override
    public void delete(CourseModel entity) {
    }
}