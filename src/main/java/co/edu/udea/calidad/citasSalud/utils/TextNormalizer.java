package co.edu.udea.calidad.citasSalud.utils;

import java.text.Normalizer;

public class TextNormalizer {

    private TextNormalizer() {}

    public static String normalize(String input) {
        if (input == null) {
            return "";
        }

        String text = input.toLowerCase();
        // Quitar tildes y signos de puntuación
        text = Normalizer.normalize(text, Normalizer.Form.NFD);
        text = text.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        // Quitar signos de puntuación
        text = text.replaceAll("[^a-z0-9\\s]", "");
        return text.trim();
    }
}