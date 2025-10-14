package com.example.nba_quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);

        TextView textViewFinalScore = findViewById(R.id.textViewFinalScore);
        Button buttonPlayAgain = findViewById(R.id.buttonPlayAgain);

        int score = getIntent().getIntExtra("EXTRA_SCORE", 0);
        textViewFinalScore.setText("Puntuación Final: " + score);

        buttonPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, QuizActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}