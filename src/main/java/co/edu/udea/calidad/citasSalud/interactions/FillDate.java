package co.edu.udea.calidad.citasSalud.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.targets.Target;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class FillDate implements Interaction {

    private final String date;
    private final Target dateField;

    public FillDate(String date, Target dateField) {
        this.date = date;
        this.dateField = dateField;
    }

    public static FillDate with(String date, Target dateField) {
        return instrumented(FillDate.class, date, dateField);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        String dateForSendKeys = date.substring(8, 10) + date.substring(5, 7) + date.substring(0, 4);

        actor.attemptsTo(
                Enter.theValue(dateForSendKeys).into(dateField)
        );
    }
}