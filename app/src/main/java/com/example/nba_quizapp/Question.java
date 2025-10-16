package com.example.nba_quizapp;

import java.util.List;


public abstract class Question {
    private List<String> options;
    private int correctAnswerIndex;

    public Question(List<String> options, int correctAnswerIndex) {
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
}