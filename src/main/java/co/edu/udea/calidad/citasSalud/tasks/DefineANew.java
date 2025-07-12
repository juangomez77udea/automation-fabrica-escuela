package co.edu.udea.calidad.citasSalud.tasks;

import co.edu.udea.calidad.citasSalud.userinterfaces.AddScheduleModal;
import co.edu.udea.calidad.citasSalud.userinterfaces.HorariosPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.Keys;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class DefineANew implements Task {
    private final String doctorName;
    private final String startDate;
    private final String endDate;
    private final String startTime;
    private final String endTime;

    public DefineANew(String doctorName, String startDate, String endDate, String startTime, String endTime) {
        this.doctorName = doctorName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static Performable scheduleFor(String doctorName, String startDate, String endDate, String startTime, String endTime) {
        return instrumented(DefineANew.class, doctorName, startDate, endDate, startTime, endTime);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        String[] startTimeParts = this.startTime.split(":");
        String[] endTimeParts = this.endTime.split(":");

        actor.attemptsTo(
                // Esto ya estaba funcionando bien
                WaitUntil.the(HorariosPage.ADD_SCHEDULE_BUTTON_FOR_DOCTOR(doctorName), isClickable()).forNoMoreThan(15).seconds(),
                Click.on(HorariosPage.ADD_SCHEDULE_BUTTON_FOR_DOCTOR(doctorName)),
                WaitUntil.the(AddScheduleModal.MODAL_CONTAINER, isVisible()).forNoMoreThan(10).seconds(),

                // Ahora interactuamos con los localizadores correctos
                Enter.theValue(this.startDate).into(AddScheduleModal.START_DATE_INPUT).thenHit(Keys.TAB),
                Enter.theValue(this.endDate).into(AddScheduleModal.END_DATE_INPUT).thenHit(Keys.TAB),
                SelectFromOptions.byValue(startTimeParts[0]).from(AddScheduleModal.START_TIME_HOUR_SELECT),
                SelectFromOptions.byValue(startTimeParts[1]).from(AddScheduleModal.START_TIME_MINUTE_SELECT),
                SelectFromOptions.byValue(endTimeParts[0]).from(AddScheduleModal.END_TIME_HOUR_SELECT),
                SelectFromOptions.byValue(endTimeParts[1]).from(AddScheduleModal.END_TIME_MINUTE_SELECT),
                Click.on(AddScheduleModal.ADD_BUTTON_IN_MODAL)
        );
    }
}