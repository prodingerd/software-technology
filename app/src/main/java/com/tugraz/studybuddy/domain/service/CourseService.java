package com.tugraz.studybuddy.domain.service;

import androidx.lifecycle.MutableLiveData;

import com.tugraz.studybuddy.data.model.CourseModel;
import com.tugraz.studybuddy.data.model.SharedCourseModel;
import com.tugraz.studybuddy.data.repository.CourseRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

public class CourseService {

    private final CourseRepository repository;

    @Inject
    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    public MutableLiveData<List<CourseModel>> getAllCourses() {
        return repository.getAll();
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

    public String shareCourse(String courseId){
        List<SharedCourseModel> sharedCourses =  repository.getAllSharedCourses();
        for (SharedCourseModel sharedCourse : sharedCourses) {
            if(sharedCourse.getCourseId().equals(courseId)){
                return sharedCourse.getCode();
            }
        }
        if(!repository.checkIfCourseId(courseId)){

            return null;
        }
        String code = random();
        repository.addSharedCourse(new SharedCourseModel(courseId, code));
        return code;
    }

    public void deleteCourse(CourseModel course) {
        repository.delete(course);
    }

    public static String random() {
        return UUID.randomUUID().toString().substring(0,5);
    }
}
