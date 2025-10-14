package com.example.nba_quizapp;

import java.util.List;

public class Pregunta {

    // Enum para definir los tipos de pregunta que manejaremos
    public enum TipoPregunta {
        TEXTO_RADIO_BUTTON,
        TEXTO_SPINNER,
        IMAGEN_SELECCION
    }

    private String textoPregunta;
    private List<String> opcionesTexto; // Para RadioButton y Spinner
    private List<Integer> opcionesImagen; // Para respuestas con Imágenes (IDs de drawable)
    private int respuestaCorrecta; // Sigue siendo el índice de la respuesta
    private TipoPregunta tipo; // El tipo de pregunta

    // Constructor para preguntas con opciones de TEXTO
    public Pregunta(String textoPregunta, List<String> opcionesTexto, int respuestaCorrecta, TipoPregunta tipo) {
        this.textoPregunta = textoPregunta;
        this.opcionesTexto = opcionesTexto;
        this.respuestaCorrecta = respuestaCorrecta;
        this.tipo = tipo;
    }

    // Constructor para preguntas con opciones de IMAGEN
    public Pregunta(String textoPregunta, List<Integer> opcionesImagen, int respuestaCorrecta) {
        this.textoPregunta = textoPregunta;
        this.opcionesImagen = opcionesImagen;
        this.respuestaCorrecta = respuestaCorrecta;
        this.tipo = TipoPregunta.IMAGEN_SELECCION;
    }

    // Getters
    public String getTextoPregunta() { return textoPregunta; }
    public List<String> getOpcionesTexto() { return opcionesTexto; }
    public List<Integer> getOpcionesImagen() { return opcionesImagen; }
    public int getRespuestaCorrecta() { return respuestaCorrecta; }
    public TipoPregunta getTipo() { return tipo; }
}