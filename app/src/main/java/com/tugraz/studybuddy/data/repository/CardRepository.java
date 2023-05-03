package com.tugraz.studybuddy.data.repository;

import com.tugraz.studybuddy.data.model.CardModel;

import java.util.ArrayList;
import java.util.List;

public class CardRepository implements ICardRepository<CardModel> {
    private static final String CARD_COLLECTION = "cards";
    private static final String TAG = "CardRepository";

    private static final List<CardModel> cards = new ArrayList<>();

    public CardRepository() {
        if (cards.isEmpty()) {
            cards.addAll(List.of(
                    new CardModel("What is 1+1?", "2"),
                    new CardModel("What is 1+2?", "3"),
                    new CardModel("What is 1+3?", "4"),
                    new CardModel("What is 1+4?", "5")
            ));
        }
    }

    @Override
    public List<CardModel> getAll() {
        return cards;
    }

    @Override
    public CardModel getById(String id) {
        return cards.stream()
                .filter(x -> x.getId().equals(id))
                .findFirst().orElseGet(null);
    }

    @Override
    public void add(CardModel entity) {
        cards.add(entity);
    }

    @Override
    public void update(CardModel entity) {
    }

    @Override
    public void delete(CardModel entity) {
    }
}
