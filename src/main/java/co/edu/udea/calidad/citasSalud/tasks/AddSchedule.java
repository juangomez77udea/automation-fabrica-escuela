package co.edu.udea.calidad.citasSalud.tasks;

import co.edu.udea.calidad.citasSalud.interactions.FillDate;
import co.edu.udea.calidad.citasSalud.userinterfaces.SchedulePage;
import io.cucumber.datatable.DataTable;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.util.Map;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class AddSchedule implements Task {

    private final DataTable scheduleData;

    public AddSchedule(DataTable scheduleData) {
        this.scheduleData = scheduleData;
    }

    public static Performable withTheData(DataTable scheduleData) {
        return instrumented(AddSchedule.class, scheduleData);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        Map<String, String> data = scheduleData.asMap(String.class, String.class);

        String startDate = data.get("Start Date");
        String endDate = data.get("End Date");
        String startTime = data.get("Start Time");
        String endTime = data.get("End Time");

        String startHour = startTime.split(":")[0];
        String startMinute = startTime.split(":")[1];
        String endHour = endTime.split(":")[0];
        String endMinute = endTime.split(":")[1];

        actor.attemptsTo(
                WaitUntil.the(SchedulePage.START_DATE_INPUT, isVisible()).forNoMoreThan(15).seconds(),

                FillDate.with(startDate, SchedulePage.START_DATE_INPUT),
                FillDate.with(endDate, SchedulePage.END_DATE_INPUT),

                SelectFromOptions.byValue(startHour).from(SchedulePage.START_TIME_HOUR_SELECT),
                SelectFromOptions.byValue(startMinute).from(SchedulePage.START_TIME_MINUTE_SELECT),
                SelectFromOptions.byValue(endHour).from(SchedulePage.END_TIME_HOUR_SELECT),
                SelectFromOptions.byValue(endMinute).from(SchedulePage.END_TIME_MINUTE_SELECT),
                Click.on(SchedulePage.ADD_BUTTON_IN_MODAL)
        );
    }
}