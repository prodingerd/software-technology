package com.tugraz.studybuddy;

import java.util.ArrayList;

public class Course {
    private String name;
    private String description;

    public Course(String name, String description) {
        this.name = name;
        this.description = description;
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

    public static ArrayList<Course> createDummyCourses() {
        ArrayList<Course> dummy_courses = new ArrayList<Course>();
        for (int i = 0; i <= 5; i++) {
            dummy_courses.add(new Course("Course " + i, "Placeholder description"));
        }
        return dummy_courses;
    }
}
