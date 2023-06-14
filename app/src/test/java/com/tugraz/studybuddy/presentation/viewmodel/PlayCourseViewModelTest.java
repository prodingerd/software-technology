package com.tugraz.studybuddy.presentation.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.tugraz.studybuddy.data.model.CourseModel;
import com.tugraz.studybuddy.domain.service.CardService;
import com.tugraz.studybuddy.domain.service.CourseService;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class PlayCourseViewModelTest {

    private PlayCourseViewModel playCourseViewModel;

    @Mock
    private CardService cardServiceMock;
    private CourseService courseServiceMock;

    @Before
    public void setUp() {
        cardServiceMock = Mockito.mock(CardService.class);
        courseServiceMock = Mockito.mock(CourseService.class);
        playCourseViewModel = new PlayCourseViewModel(courseServiceMock, cardServiceMock);
    }

    @Test
    public void dummyTest() {
        assert true;
    }
}
