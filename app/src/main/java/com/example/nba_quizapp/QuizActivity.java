package com.example.nba_quizapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private List<Pregunta> questionList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz);

        TextView questionTextView = findViewById(R.id.question1TextView);
        RadioGroup optionsRadioGroup = findViewById(R.id.optionsRadioGroup);
        RadioButton option1RadioButton = findViewById(R.id.option1RadioButton);
        RadioButton option2RadioButton = findViewById(R.id.option2RadioButton);
        RadioButton option3RadioButton = findViewById(R.id.option3RadioButton);
        RadioButton option4RadioButton = findViewById(R.id.option4RadioButton);
        Button confirmButton = findViewById(R.id.confirmButton);


    }
}