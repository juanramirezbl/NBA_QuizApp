package com.example.nba_quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    private TextView textViewQuestion;

    private TextView textViewScore;
    private RadioGroup radioGroupOptions;
    private RadioButton rb1, rb2, rb3, rb4;
    private Button buttonConfirm;


    private List<Question> questionList;
    private int questionCounter;
    private int questionCountTotal;

    private Question currentQuestion;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz);

        // Initialize UI elements
        textViewScore = findViewById(R.id.textViewScore);
        textViewQuestion = findViewById(R.id.textViewQuestion);
        radioGroupOptions = findViewById(R.id.radioGroupOptions);
        rb1 = findViewById(R.id.radioButtonOption1);
        rb2 = findViewById(R.id.radioButtonOption2);
        rb3 = findViewById(R.id.radioButtonOption3);
        rb4 = findViewById(R.id.radioButtonOption4);
        buttonConfirm = findViewById(R.id.buttonConfirm);

        fillQuestionList();
        score = 0;
        updateScore();
        questionCountTotal = questionList.size();
        showNextQuestion();

        buttonConfirm.setOnClickListener(v -> {
            if (radioGroupOptions.getCheckedRadioButtonId() == -1) {
                Toast.makeText(QuizActivity.this, "Por favor, selecciona una respuesta", Toast.LENGTH_SHORT).show();
            } else {
                checkAnswer();
            }
        });
    }

    private void fillQuestionList() {
        questionList = new ArrayList<>();
        // Pregunta 1
        questionList.add(new Question(
                "¿Qué jugador ha ganado más campeonatos de la NBA?",
                Arrays.asList("Michael Jordan", "Bill Russell", "Kareem Abdul-Jabbar", "LeBron James"),
                1
        ));

        // Pregunta 2
        questionList.add(new Question(
                "¿Qué equipo seleccionó a Kobe Bryant en el Draft de 1996?",
                Arrays.asList("Los Angeles Lakers", "Boston Celtics", "Charlotte Hornets", "New Jersey Nets"),
                2
        ));

        // Pregunta 3
        questionList.add(new Question(
                "¿Quién es el máximo anotador de la historia de la NBA?",
                Arrays.asList("Michael Jordan", "Kareem Abdul-Jabbar", "Kobe Bryant", "LeBron James"),
                3
        ));

        // Pregunta 4
        questionList.add(new Question(
                "¿En qué año se fundó la NBA (originalmente como BAA)?",
                Arrays.asList("1946", "1950", "1962", "1971"),
                0
        ));

        // Pregunta 5
        questionList.add(new Question(
                "¿Qué equipo tiene el récord de más victorias en una sola temporada regular?",
                Arrays.asList("Chicago Bulls", "Los Angeles Lakers", "Boston Celtics", "Golden State Warriors"),
                3
        ));
    }

    private void showNextQuestion() {
        radioGroupOptions.clearCheck();

        if (questionCounter < questionCountTotal) {
            currentQuestion = questionList.get(questionCounter);
            textViewQuestion.setText(currentQuestion.getQuestionText());
            rb1.setText(currentQuestion.getOptions().get(0));
            rb2.setText(currentQuestion.getOptions().get(1));
            rb3.setText(currentQuestion.getOptions().get(2));
            rb4.setText(currentQuestion.getOptions().get(3));
            questionCounter++;
        } else {
            finishQuiz();
        }
    }

    private void checkAnswer() {
        RadioButton rbSelected = findViewById(radioGroupOptions.getCheckedRadioButtonId());
        int answerIndex = radioGroupOptions.indexOfChild(rbSelected);

        if (answerIndex == currentQuestion.getCorrectAnswerIndex()) {
            score += 3;
            Toast.makeText(QuizActivity.this, "Respuesta correcta", Toast.LENGTH_SHORT).show();
        } else {
            score -= 2;
            Toast.makeText(QuizActivity.this, "Respuesta incorrecta", Toast.LENGTH_SHORT).show();
        }
        updateScore();
        showNextQuestion();
    }

    private void finishQuiz() {
        Intent resultIntent = new Intent(QuizActivity.this, ResultActivity.class);
        resultIntent.putExtra("EXTRA_SCORE", score);
        startActivity(resultIntent);
        finish();
    }

    private void updateScore() {
        textViewScore.setText("Puntuación: " + score);
    }



}