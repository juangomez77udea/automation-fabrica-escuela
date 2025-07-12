package co.edu.udea.calidad.citasSalud.stepdefinitions;

import co.edu.udea.calidad.citasSalud.questions.TheScheduleData; // Crearemos esta clase
import co.edu.udea.calidad.citasSalud.tasks.Authenticate;
import co.edu.udea.calidad.citasSalud.tasks.DefineANew;
import co.edu.udea.calidad.citasSalud.tasks.OpenThe;
import co.edu.udea.calidad.citasSalud.userinterfaces.LoginPage;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;

import java.util.Map;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class ScheduleStepDefinitions {

    // Esta clase nos permite convertir la tabla del feature en un objeto útil.
    public static class ScheduleData {
        private final String startDate;
        private final String endDate;
        private final String startTime;
        private final String endTime;

        public ScheduleData(String startDate, String endDate, String startTime, String endTime) {
            this.startDate = startDate;
            this.endDate = endDate;
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }

    @DataTableType
    public ScheduleData scheduleDataEntry(Map<String, String> entry) {
        return new ScheduleData(
                entry.get("StartDate"),
                entry.get("EndDate"),
                entry.get("StartTime"),
                entry.get("EndTime")
        );
    }

    @Given("the doctor {string} with password {string} is on the schedules page")
    public void theDoctorIsOnTheSchedulesPage(String user, String password) {
        OnStage.theActorCalled("Doctor").wasAbleTo(
                OpenThe.page(LoginPage.class),
                Authenticate.with(user, password)
        );
        // Pequeña pausa explícita para asegurar que la redirección se complete.
        // En un proyecto real, se reemplazaría con un WaitUntil.the(HorariosPage.PAGE_TITLE, isVisible())
        try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
    }

    @When("he adds a new availability for {string} with the following data:")
    public void heAddsANewAvailabilityForWithTheFollowingData(String doctorName, ScheduleData scheduleData) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                DefineANew.scheduleFor(
                        doctorName,
                        scheduleData.startDate,
                        scheduleData.endDate,
                        scheduleData.startTime,
                        scheduleData.endTime
                )
        );
    }

    // Este Then es más específico y robusto
    @Then("the new schedule from {string} to {string} on {string} should be visible for {string}")
    public void theNewScheduleShouldBeVisibleInTheTableFor(String startTime, String endTime, String date, String doctorName) {
        String expectedTimeRange = startTime + " - " + endTime;
        String expectedDateRange = date + " - " + date;

        OnStage.theActorInTheSpotlight().should(
                seeThat(TheScheduleData.timeFor(doctorName), equalTo(expectedTimeRange)),
                seeThat(TheScheduleData.dateRangeFor(doctorName), equalTo(expectedDateRange))
        );
    }
}