package com.tugraz.studybuddy.data.model;

import java.util.UUID;

public class CardModel extends BaseModel {

    private String frontText;
    private String backText;

    public CardModel() {}

    public CardModel(String frontText, String backText) {
        this.frontText = frontText;
        this.backText = backText;

        setId(UUID.randomUUID().toString());
    }

    public String getFrontText() {
        return frontText;
    }

    public void setFrontText(String frontText) {
        this.frontText = frontText;
    }

    public String getBackText() {
        return backText;
    }

    public void setBackText(String backText) {
        this.backText = backText;
    }
}
