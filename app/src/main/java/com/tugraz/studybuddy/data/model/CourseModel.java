package com.tugraz.studybuddy.data.model;

import java.util.Date;
import java.util.UUID;

public class CourseModel extends BaseModel {
    private String name;
    private String description;
    private String userId;
    private Date examDate;

    public CourseModel() {}

    public CourseModel(String name, String description, Date examDate) {
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

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }
}
