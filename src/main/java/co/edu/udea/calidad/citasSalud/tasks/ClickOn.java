package co.edu.udea.calidad.citasSalud.tasks;

import co.edu.udea.calidad.citasSalud.userinterfaces.SchedulePage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;

public class ClickOn implements Task {

    private final String doctorName;

    public ClickOn(String doctorName) {
        this.doctorName = doctorName;
    }

    public static Performable addScheduleButtonFor(String doctorName) {
        return instrumented(ClickOn.class, doctorName);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        // 1. Definimos el localizador dinámico para el botón específico del doctor.
        Target addButtonForDoctor = SchedulePage.ADD_SCHEDULE_BUTTON_FOR_DOCTOR.of(doctorName);

        actor.attemptsTo(
                // 2. AÑADIMOS LA ESPERA EXPLÍCITA:
                WaitUntil.the(addButtonForDoctor, isClickable()).forNoMoreThan(15).seconds(),

                // 3. ACCIÓN DE CLIC:
                Click.on(addButtonForDoctor)
        );
    }
}