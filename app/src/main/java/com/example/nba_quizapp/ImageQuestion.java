package com.example.nba_quizapp;

import java.util.List;

public class ImageQuestion extends Question {
    private int imageResourceId;
    private String questionText;

    public ImageQuestion(String questionText, int imageResourceId, List<String> options, int correctAnswerIndex) {
        super(options, correctAnswerIndex);
        this.questionText = questionText;
        this.imageResourceId = imageResourceId;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public String getQuestionText() {
        return questionText;
    }
}