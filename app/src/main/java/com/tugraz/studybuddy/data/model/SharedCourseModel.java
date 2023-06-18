package com.tugraz.studybuddy.data.model;

import java.time.LocalDate;

public class SharedCourseModel extends BaseModel {

    private String code;
    private String courseId;


    public SharedCourseModel(String code,String courseId) {
        this.code = code;
        this.courseId = courseId;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCode() {
        return code;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public void setCode(String code) {
        this.code = code;
    }


}
