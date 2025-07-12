package co.edu.udea.calidad.citasSalud.utils;

import java.text.Normalizer;

public class TextNormalizer {

    private TextNormalizer() {} // Clase de utilidad, no instanciable

    public static String normalize(String input) {
        if (input == null) {
            return "";
        }

        String text = input.toLowerCase();
        // Quitar tildes y diacríticos
        text = Normalizer.normalize(text, Normalizer.Form.NFD);
        text = text.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        // Quitar signos de puntuación
        text = text.replaceAll("[^a-z0-9\\s]", "");
        return text.trim();
    }
}