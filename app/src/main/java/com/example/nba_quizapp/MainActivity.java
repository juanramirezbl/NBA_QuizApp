package com.example.nba_quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Encontrar el botón en el layout por su ID
        Button startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(v -> {
            // 3. Crear un Intent.
            // getApplicationContext() es el contexto actual.
            // SecondActivity.class es la clase de la Activity de destino.
            Intent intent = new Intent(getApplicationContext(), QuizActivity.class);

            // 4. Iniciar la nueva Activity.
            startActivity(intent);
        });
    }
}