package com.tugraz.studybuddy.data.model;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class CourseModel extends BaseModel {

    public static final List<String> MUTABLE_FIELDS = List.of("updatedAt", "name", "description", "examDate", "deleted");

    private String name;
    private String description;
    private String userId;
    private String shareCode;
    private long examDate;

    private boolean deleted;

    public CourseModel() {}

    public CourseModel(String id, String name, String description, LocalDate examDate) {
        this.setId(id);
        this.name = name;
        this.description = description;
        this.shareCode = UUID.randomUUID().toString().substring(0, 5);
        this.examDate = examDate.toEpochDay();
        this.deleted = false;
    }

    public CourseModel(String name, String description, LocalDate examDate) {
        this.name = name;
        this.description = description;
        this.shareCode = UUID.randomUUID().toString().substring(0, 5);
        this.examDate = examDate.toEpochDay();
        this.deleted = false;
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

    public String getShareCode() {
        return shareCode;
    }

    public void setShareCode(String shareCode) {
        this.shareCode = shareCode;
    }

    public LocalDate prettyExamDate() {
        return LocalDate.ofEpochDay(examDate);
    }

    public long getExamDate() {
        return examDate;
    }

    public void setExamDate(long examDate) {
        this.examDate = examDate;
    }

    public boolean getDeleted() { return deleted; }

    public void setDeleted(boolean deleted) { this.deleted = deleted; }
}
