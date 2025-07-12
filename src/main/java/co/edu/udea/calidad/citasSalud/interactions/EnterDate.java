package co.edu.udea.calidad.citasSalud.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.targets.Target;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Esta Interaction se especializa en ingresar una fecha en un campo de tipo 'date'.
 * Abstrae la complejidad de formatear la fecha (ej. "DDMMAAAA")
 * para que la Task no necesite conocer este detalle técnico.
 */
public class EnterDate implements Interaction {

    private final String date;
    private final Target dateField;

    public EnterDate(String date, Target dateField) {
        this.date = date;
        this.dateField = dateField;
    }

    // Método de fábrica para una sintaxis legible: EnterDate.of("2025-08-01").into(TARGET)
    public static EnterDate of(String date) {
        // Devuelve una instancia "parcialmente construida" que espera el target
        return new EnterDate(date, null);
    }

    // Este método permite encadenar la llamada: ...of(date).into(target)
    public EnterDate into(Target dateField) {
        return instrumented(EnterDate.class, this.date, dateField);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        // La lógica para formatear la fecha se encapsula aquí.
        // Formato esperado por el input: DDMMYYYY
        String dateForSendKeys = date.substring(8, 10) + date.substring(5, 7) + date.substring(0, 4);

        // Reutilizamos la Interaction 'Enter' de Serenity
        actor.attemptsTo(
                Enter.theValue(dateForSendKeys).into(this.dateField)
        );
    }
}