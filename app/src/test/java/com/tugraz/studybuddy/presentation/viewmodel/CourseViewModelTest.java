package com.tugraz.studybuddy.presentation.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.tugraz.studybuddy.data.model.CourseModel;
import com.tugraz.studybuddy.domain.service.CourseService;

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
public class CourseViewModelTest {

    private CourseViewModel courseViewModel;

    @Mock
    private CourseService courseServiceMock;

    @Before
    public void setUp() {
        Mockito.when(courseServiceMock.getAllCourses()).thenReturn(new MutableLiveData<List<CourseModel>>());
        courseViewModel = new CourseViewModel(courseServiceMock);
    }

    @Test
    public void givenEmptyRepository_whenGetAllCourses_thenReturnEmptyList() {
        Assert.assertNull(courseViewModel.getAllCourses().getValue());
    }

    @Test
    public void givenEmptyRepository_whenCreateInvalidCourse_thenReturnFalse() {
        Assert.assertFalse(courseViewModel.createCourse("", "", "2023-01-01"));
    }

    @Test
    public void givenEmptyRepository_whenCreateValidCourse_thenReturnTrue() {
        Mockito.when(courseServiceMock.createCourse("1", "1", "2023-01-01")).thenReturn(true);
        Assert.assertTrue(courseViewModel.createCourse("1", "1", "2023-01-01"));
    }

    @Test
    public void givenEmptyRepository_whenUpdateInvalidCourse_thenReturnFalse() {
        Assert.assertFalse(courseViewModel.updateCourse("", "", "", "2023-01-01"));
    }

    @Test
    public void givenEmptyRepository_whenUpdateValidCourse_thenReturnTrue() {
        Mockito.when(courseServiceMock.updateCourse("1", "1", "1", "2023-01-01")).thenReturn(true);
        Assert.assertTrue(courseViewModel.updateCourse("1", "1", "1", "2023-01-01"));
    }

    @Test
    public void givenEmptyRepository_whenDeleteCourse_thenDeleteCourse() {
        CourseModel course = new CourseModel("1", "1", LocalDate.parse("2023-01-01"));
        courseViewModel.deleteCourse(course);
        Mockito.verify(courseServiceMock, Mockito.times(1)).deleteCourse(course);
    }
}
