package com.tugraz.studybuddy.data.model;

import java.time.LocalDate;

public class CourseModel extends BaseModel {

    private String name;
    private String description;
    private String userId;
    private long examDate;

    public CourseModel() {}

    public CourseModel(String name, String description, LocalDate examDate) {
        this.name = name;
        this.description = description;
        this.examDate = examDate.toEpochDay();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDate abc() {
        return LocalDate.ofEpochDay(examDate);
    }

    public long getExamDate() {
        return examDate;
    }

    public void setExamDate(long examDate) {
        this.examDate = examDate;
    }
}
