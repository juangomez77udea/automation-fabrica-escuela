package co.edu.udea.calidad.citasSalud.utils;

import java.text.Normalizer;

public class TextNormalizer {

    private TextNormalizer() {} // Clase de utilidad, no instanciable

    public static String normalize(String input) {
        if (input == null) {
            return "";
        }
        // 1. Convertir a minúsculas
        String text = input.toLowerCase();
        // 2. Quitar tildes y diacríticos
        text = Normalizer.normalize(text, Normalizer.Form.NFD);
        text = text.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        // 3. Opcional: quitar signos de puntuación
        text = text.replaceAll("[^a-z0-9\\s]", "");
        return text.trim();
    }
}