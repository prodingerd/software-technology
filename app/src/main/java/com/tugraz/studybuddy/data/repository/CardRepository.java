package com.tugraz.studybuddy.data.repository;

import com.tugraz.studybuddy.data.model.CardModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import javax.inject.Inject;

public class CardRepository implements ICardRepository<CardModel> {

    private static final List<CardModel> cards = new ArrayList<>();

    @Inject
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
                .findFirst().orElse(null);
    }

    @Override
    public void add(CardModel entity) {
        cards.add(entity);
    }

    @Override
    public void update(CardModel entity) {
        IntStream.range(0, cards.size())
                .filter(x -> cards.get(x).getId().equals(entity.getId()))
                .findFirst().ifPresent(i -> cards.set(i, entity));
    }

    @Override
    public void delete(CardModel entity) {
        cards.remove(entity);
    }
}
