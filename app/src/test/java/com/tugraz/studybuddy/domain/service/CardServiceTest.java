package com.tugraz.studybuddy.domain.service;

import androidx.lifecycle.MutableLiveData;

import com.tugraz.studybuddy.data.model.CardModel;
import com.tugraz.studybuddy.data.repository.CourseRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CardServiceTest {
    @Mock
    private CardService cardService;
    private CourseRepository courseRepositoryMock;

    @Before
    public void setUp() {
        courseRepositoryMock = Mockito.mock(CourseRepository.class);
        cardService = new CardService(courseRepositoryMock);
    }

    @Test
    public void givenEmptyRepository_whenGetAll_thenReturnEmptyList() {
        Mockito.when(courseRepositoryMock.getAllCards("1")).thenReturn(new MutableLiveData<List<CardModel>>());
        Assert.assertEquals(cardService.getAllCards("1").getValue(), null);
    }

    @Test
    public void givenEmptyRepository_whenCreateInvalidCard_thenReturnFalse() {
        Assert.assertEquals(cardService.createCard("1", "", ""), false);
    }

    @Test
    public void givenEmptyRepository_whenCreateValidCard_thenReturnTrue() {
        Assert.assertEquals(cardService.createCard("1", "front", "back"), true);
    }

    @Test
    public void givenEmptyRepository_whenUpdateInvalidCard_thenReturnFalse() {
        Assert.assertEquals(cardService.updateCard("1", "", "", ""), false);
    }

    @Test
    public void givenEmptyRepository_whenUpdateValidCard_thenReturnTrue() {
        Assert.assertEquals(cardService.updateCard("1", "1", "front", "back"), true);
    }

    @Test
    public void givenEmptyRepository_whenDeleteCard_thenDeleteCard() {
        CardModel card = new CardModel("1", "front", "back");
        cardService.deleteCard("1", card);
        Mockito.verify(courseRepositoryMock, Mockito.times(1)).deleteCard("1", card);
    }
}
