package com.tugraz.studybuddy.presentation.viewmodel;

import com.tugraz.studybuddy.domain.service.CardService;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PlayCourseViewModelTest {

    private PlayCourseViewModel playCourseViewModel;

    @Mock
    private CardService cardServiceMock;

    @Before
    public void setUp() {
        cardServiceMock = Mockito.mock(CardService.class);
        playCourseViewModel = new PlayCourseViewModel(cardServiceMock);
    }

    @Test
    public void dummyTest() {
        Assert.assertTrue(true);
    }
}
