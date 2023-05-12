package com.tugraz.studybuddy.data.model;

import java.time.LocalDate;
import java.util.UUID;

public class CourseModel extends BaseModel {

    private String name;
    private String description;
    private String userId;
    private LocalDate examDate;

    public CourseModel() {}

    public CourseModel(String name, String description, LocalDate examDate) {
        this.name = name;
        this.description = description;
        this.examDate = examDate;

        setId(UUID.randomUUID().toString());
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getExamDate() {
        return examDate;
    }

    public void setExamDate(LocalDate examDate) {
        this.examDate = examDate;
    }
}
