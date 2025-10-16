package com.example.nba_quizapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ImageView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nba_quizapp.model.ImageQuestion;
import com.example.nba_quizapp.model.Question;
import com.example.nba_quizapp.R;
import com.example.nba_quizapp.model.TextQuestion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private ImageView imageViewQuestion;
    private TextView textViewQuestion;
    private ListView listViewOptions;
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
        listViewOptions = findViewById(R.id.listViewOptions);
        imageViewQuestion = findViewById(R.id.imageViewQuestion);
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
                RadioButton rbSelected = findViewById(radioGroupOptions.getCheckedRadioButtonId());
                int answerIndex = radioGroupOptions.indexOfChild(rbSelected);
                checkAnswer(answerIndex);
            }
        });

        listViewOptions.setOnItemClickListener((parent, view, position, id) -> {
            checkAnswer(position);
        });
    }

    private void fillQuestionList() {
        questionList = new ArrayList<>();
        // Q1
        questionList.add(new TextQuestion(
                "¿Qué jugador ha ganado más campeonatos de la NBA?",
                Arrays.asList("Michael Jordan", "Bill Russell", "Kareem Abdul-Jabbar", "LeBron James"),
                1
        ));

        // Q2
        questionList.add(new ImageQuestion(
                "¿A qué equipo pertenece este logo?",
                R.drawable.golden_state,
                Arrays.asList("Boston Celtics", "Golden State Warriors", "Miami Heat", "Cleveland Cavaliers"),
                1
        ));

        // Q3
        questionList.add(new TextQuestion(
                "¿Quién es el máximo anotador de la historia de la NBA?",
                Arrays.asList("Michael Jordan", "Kareem Abdul-Jabbar", "Kobe Bryant", "LeBron James"),
                3
        ));

        // Q4
        questionList.add(new TextQuestion(
                "¿En qué año se fundó la NBA (originalmente como BAA)?",
                Arrays.asList("1946", "1950", "1962", "1971"),
                0
        ));

        // Q5
        questionList.add(new TextQuestion(
                "¿Qué equipo tiene el récord de más victorias en una sola temporada regular?",
                Arrays.asList("Chicago Bulls", "Los Angeles Lakers", "Boston Celtics", "Golden State Warriors"),
                3
        ));
    }

    private void showNextQuestion() {
        radioGroupOptions.clearCheck();

        if (questionCounter < questionCountTotal) {
            currentQuestion = questionList.get(questionCounter);


            if (currentQuestion instanceof ImageQuestion) {
                ImageQuestion imageQuestion = (ImageQuestion) currentQuestion;
                imageViewQuestion.setVisibility(View.VISIBLE);
                textViewQuestion.setVisibility(View.VISIBLE);

                imageViewQuestion.setImageResource(imageQuestion.getImageResourceId());
                textViewQuestion.setText(imageQuestion.getQuestionText());

            } else if (currentQuestion instanceof TextQuestion) {
                TextQuestion textQuestion = (TextQuestion) currentQuestion;
                imageViewQuestion.setVisibility(View.GONE);
                textViewQuestion.setVisibility(View.VISIBLE);

                textViewQuestion.setText(textQuestion.getQuestionText());
            }

            if(questionCounter == 1 || questionCounter == 3){
                radioGroupOptions.setVisibility(View.GONE);
                listViewOptions.setVisibility(View.VISIBLE);
                buttonConfirm.setVisibility(View.VISIBLE);

                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, currentQuestion.getOptions());
                listViewOptions.setAdapter(adapter);
            } else {
                radioGroupOptions.setVisibility(View.VISIBLE);
                listViewOptions.setVisibility(View.GONE);
                buttonConfirm.setVisibility(View.VISIBLE);

                rb1.setText(currentQuestion.getOptions().get(0));
                rb2.setText(currentQuestion.getOptions().get(1));
                rb3.setText(currentQuestion.getOptions().get(2));
                rb4.setText(currentQuestion.getOptions().get(3));
            }

            questionCounter++;
        } else {
            finishQuiz();
        }
    }

    private void checkAnswer(int selectedAnswerIndex) {
        if (selectedAnswerIndex == currentQuestion.getCorrectAnswerIndex()) {
            score += 3;
            Toast.makeText(QuizActivity.this, "Respuesta correcta", Toast.LENGTH_SHORT).show();
            updateScore();
            showNextQuestion();
        }else{
            score =-2;
            updateScore();
            showFailureDialog();
        }
    }
    private void showFailureDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Respuesta incorrecta")
                .setMessage("Que quieres hacer?")
                .setPositiveButton("Continuar", (dialog, which) -> {
                    showNextQuestion();
                })
                .setNegativeButton("Reiniciar Juego", (dialog, which) -> {
                    Intent intent = new Intent(QuizActivity.this, QuizActivity.class);
                    startActivity(intent);
                    finish();
                })
                .setCancelable(false)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
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




