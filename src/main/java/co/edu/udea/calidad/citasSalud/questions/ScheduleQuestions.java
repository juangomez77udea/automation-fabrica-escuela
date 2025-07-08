package co.edu.udea.calidad.citasSalud.questions;

import co.edu.udea.calidad.citasSalud.userinterfaces.SchedulePage;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;

public class ScheduleQuestions {

    public static Question<String> successMessage() {
        return actor -> {
            actor.attemptsTo(
                    WaitUntil.the(SchedulePage.SUCCESS_MESSAGE_CONTAINER,
                            WebElementStateMatchers
                                    .isVisible())
                            .forNoMoreThan(10)
                            .seconds());
            return Text.of(
                    SchedulePage
                            .SUCCESS_MESSAGE_CONTAINER)
                    .answeredBy(actor);
        };
    }

    public static Question<Boolean> scheduleDataIsDisplayedInFirstRow(String expectedDias, String expectedHorario, String expectedRango) {
        return actor -> {
            // 1. Definimos el localizador para la primera fila de la tabla.
            Target firstRow = SchedulePage.SCHEDULE_TABLE_FIRST_ROW;

            // 2. ESPERA EXPLÍCITA: Esperamos a que la fila sea visible.
            try {
                actor.attemptsTo(WaitUntil.the(firstRow, WebElementStateMatchers.isVisible()).forNoMoreThan(15).seconds());
            } catch (Exception e) {
                System.out.println("Timeout: La primera fila de la tabla nunca se hizo visible después de agregar el horario.");
                return false;
            }

            // 3. OBTENCIÓN DE TEXTO: Leemos el texto de la fila.
            String rowText = Text.of(firstRow).answeredBy(actor);

            // 4. VERIFICACIÓN: Comprobamos si el texto contiene los datos esperados.
            boolean diasMatch = rowText.contains(expectedDias);
            boolean horarioMatch = rowText.contains(expectedHorario);
            boolean rangoMatch = rowText.contains(expectedRango);

            // Depuración final para ver qué se está leyendo
            System.out.println("--- Verificación Final de Fila ---");
            System.out.println("Texto leído de la fila: " + rowText);
            System.out.println("¿Contiene Días (" + expectedDias + ")? -> " + diasMatch);
            System.out.println("¿Contiene Horario (" + expectedHorario + ")? -> " + horarioMatch);
            System.out.println("¿Contiene Rango (" + expectedRango + ")? -> " + rangoMatch);
            System.out.println("---------------------------------");

            return diasMatch && horarioMatch && rangoMatch;
        };
    }
}