package com.example.nba_quizapp.model;

import java.util.List;

public class TextQuestion extends Question {
    private String questionText;

    public TextQuestion(String questionText, List<String> options, int correctAnswerIndex) {
        super(options, correctAnswerIndex);
        this.questionText = questionText;
    }

    public String getQuestionText() {
        return questionText;
    }
}