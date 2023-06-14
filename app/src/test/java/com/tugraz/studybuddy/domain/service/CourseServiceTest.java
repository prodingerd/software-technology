package com.tugraz.studybuddy.domain.service;

import androidx.lifecycle.MutableLiveData;

import com.tugraz.studybuddy.data.model.CourseModel;
import com.tugraz.studybuddy.data.repository.CourseRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CourseServiceTest {
    @Mock
    private CourseService courseService;
    private CourseRepository courseRepositoryMock;

    @Before
    public void setUp() {
        courseRepositoryMock = Mockito.mock(CourseRepository.class);
        courseService = new CourseService(courseRepositoryMock);
    }

    @Test
    public void givenEmptyRepository_whenGetAll_thenReturnEmptyList() {
        Mockito.when(courseRepositoryMock.getAll()).thenReturn(new MutableLiveData<List<CourseModel>>());
        Assert.assertEquals(courseService.getAllCourses().getValue(), null);
    }

    @Test
    public void givenEmptyRepository_whenCreateInvalidCourse_thenReturnFalse() {
        Assert.assertEquals(courseService.createCourse("", "", ""), false);
    }

    @Test
    public void givenEmptyRepository_whenCreateValidCourse_thenReturnTrue() {
        Assert.assertEquals(courseService.createCourse("name", "description", "2023-01-01"), true);
    }

    @Test
    public void givenEmptyRepository_whenUpdateInvalidCourse_thenReturnFalse() {
        Assert.assertEquals(courseService.updateCourse("", "", "", ""), false);
    }

    @Test
    public void givenEmptyRepository_whenUpdateValidCourse_thenReturnTrue() {
        Assert.assertEquals(courseService.updateCourse("1", "name", "description", "2023-01-01"), true);
    }

    @Test
    public void givenEmptyRepository_whenDeleteCourse_thenDeleteCourse() {
        CourseModel course = new CourseModel("1", "name", "description", LocalDate.parse("2023-01-01"));
        courseService.deleteCourse(course);
        Mockito.verify(courseRepositoryMock, Mockito.times(1)).delete(course);
    }

}