package com.tugraz.studybuddy.presentation.viewmodel;

import com.tugraz.studybuddy.data.model.CardModel;
import com.tugraz.studybuddy.domain.service.CardService;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CardViewModelTest {
    @Mock
    private CardService cardServiceMock;
    private CardViewModel cardServiceViewModel;

    @Before
    public void setUp() {
        cardServiceMock = Mockito.mock(CardService.class);
        cardServiceViewModel = new CardViewModel(cardServiceMock);
    }

    @Test
    public void givenEmptyRepository_whenGetAllCards_thenReturnEmptyList() {
        Assert.assertNull(cardServiceViewModel.getAllCards());
    }

    @Test
    public void givenEmptyRepository_whenCreateInvalidCard_thenReturnFalse() {
        Assert.assertFalse(cardServiceViewModel.createCard("", ""));
    }

    @Test
    public void givenEmptyRepository_whenCreateValidCard_thenReturnTrue() {
        cardServiceViewModel.setCourseId("1");
        Mockito.when(cardServiceMock.createCard("1", "front", "back")).thenReturn(true);
        Assert.assertTrue(cardServiceViewModel.createCard("front", "back"));
    }

    @Test
    public void givenEmptyRepository_whenUpdateInvalidCard_thenReturnFalse() {
        Assert.assertFalse(cardServiceViewModel.updateCard("", "", ""));
    }

    @Test
    public void givenEmptyRepository_whenUpdateValidCard_thenReturnTrue() {
        cardServiceViewModel.setCourseId("1");
        Mockito.when(cardServiceMock.updateCard("1", "1", "front", "back")).thenReturn(true);
        Assert.assertTrue(cardServiceViewModel.updateCard("1", "front", "back"));
    }

    @Test
    public void givenEmptyRepository_whenDeleteCard_thenDeleteCard() {
        CardModel card = new CardModel("1", "front", "back");
        cardServiceViewModel.setCourseId("1");
        cardServiceViewModel.deleteCard(card);
        Mockito.verify(cardServiceMock, Mockito.times(1)).deleteCard("1", card);
    }
}
