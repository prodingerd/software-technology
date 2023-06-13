package com.tugraz.studybuddy.data.model;

import java.util.List;

public class CardModel extends BaseModel {

    public static final List<String> MUTABLE_FIELDS = List.of("updatedAt", "frontText", "backText");

    private String frontText;
    private String backText;

    public CardModel() {}

    public CardModel(String id, String frontText, String backText) {
        this.setId(id);
        this.frontText = frontText;
        this.backText = backText;
    }

    public CardModel(String frontText, String backText) {
        this.frontText = frontText;
        this.backText = backText;
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
