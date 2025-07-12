package co.edu.udea.calidad.citasSalud.stepdefinitions;

import co.edu.udea.calidad.citasSalud.tasks.Authenticate;
import co.edu.udea.calidad.citasSalud.tasks.DefineANew;
import co.edu.udea.calidad.citasSalud.tasks.OpenThe;
import co.edu.udea.calidad.citasSalud.userinterfaces.HorariosPage;
import co.edu.udea.calidad.citasSalud.userinterfaces.LoginPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.waits.WaitUntil;
import java.util.List;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static org.hamcrest.CoreMatchers.is;


public class ScheduleStepDefinitions {

    @Given("the doctor {string} with password {string} is on the schedules page")
    public void theDoctorWithPasswordIsOnTheSchedulesPage(String email, String password) {
        OnStage.theActorCalled("Doctor").wasAbleTo(
                OpenThe.page(LoginPage.class),
                Authenticate.with(email, password)
        );
        OnStage.theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(HorariosPage.SCHEDULE_TABLE, isVisible()).forNoMoreThan(15).seconds()
        );
    }

    @When("he clicks on the add schedule button for {string}")
    public void heClicksOnTheAddScheduleButtonFor(String doctorName) {
        OnStage.theActorInTheSpotlight().remember("doctorName", doctorName);
        OnStage.theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(HorariosPage.ADD_SCHEDULE_BUTTON_FOR_DOCTOR(doctorName), isClickable()).forNoMoreThan(15).seconds(),
                Click.on(HorariosPage.ADD_SCHEDULE_BUTTON_FOR_DOCTOR(doctorName))
        );
    }

    @When("he adds a new availability with the following data:")
    public void heAddsANewAvailabilityWithTheFollowingData(DataTable dataTable) {
        String doctorName = OnStage.theActorInTheSpotlight().recall("doctorName");

        List<String> dataRow = dataTable.row(1);

        String startDate = dataRow.get(0);
        String endDate = dataRow.get(1);
        String startTime = dataRow.get(2);
        String endTime = dataRow.get(3);

        // Recordamos los datos para poder usarlos en el 'Then'
        OnStage.theActorInTheSpotlight().remember("startTime", startTime);
        OnStage.theActorInTheSpotlight().remember("endTime", endTime);
        OnStage.theActorInTheSpotlight().remember("startDate", startDate);

        OnStage.theActorInTheSpotlight().attemptsTo(
                DefineANew.scheduleFor(doctorName, startDate, endDate, startTime, endTime)
        );
    }

    @Then("the new schedule should be visible in the table for {string}")
    public void theNewScheduleShouldBeVisibleInTheTableFor(String doctorName) {
        // Obtenemos los datos que guardamos en el paso anterior usando 'remember'
        String expectedStartTime = OnStage.theActorInTheSpotlight().recall("startTime");
        String expectedEndTime = OnStage.theActorInTheSpotlight().recall("endTime");
        String expectedDate = OnStage.theActorInTheSpotlight().recall("startDate");

        String expectedSchedule = expectedStartTime + " - " + expectedEndTime;
        String expectedDateRange = expectedDate + " - " + expectedDate; // Asumiendo que fecha de fin es la misma

        OnStage.theActorInTheSpotlight().should(
                seeThat("el horario en la tabla",
                        Text.of(HorariosPage.SCHEDULE_CELL_FOR_DOCTOR(doctorName)), is(expectedSchedule)),
                seeThat("el rango de fechas en la tabla",
                        Text.of(HorariosPage.DATE_RANGE_CELL_FOR_DOCTOR(doctorName)), is(expectedDateRange))
        );
    }
}