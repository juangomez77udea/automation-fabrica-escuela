package co.edu.udea.calidad.citasSalud.stepdefinitions;

import co.edu.udea.calidad.citasSalud.questions.ScheduleQuestions;
import co.edu.udea.calidad.citasSalud.tasks.AddSchedule;
import co.edu.udea.calidad.citasSalud.tasks.ClickOn;
import co.edu.udea.calidad.citasSalud.tasks.Login;
import co.edu.udea.calidad.citasSalud.userinterfaces.LoginPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import java.util.Map;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

public class ScheduleAvailabilityStepDefinitions {

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("the doctor {string} with password {string} is on the schedules page")
    public void theDoctorIsOnTheSchedulesPage(String email, String password) {
        theActorCalled("Doctor").wasAbleTo(
                Open.browserOn().the(LoginPage.class),
                Login.withCredentials(email, password)
        );
        String doctorName = "Carlos Santana";
        theActorInTheSpotlight().remember("doctorName", doctorName);
    }

    @When("he clicks on the add schedule button for {string}")
    public void heClicksOnTheAddScheduleButtonFor(String doctorName) {
        theActorInTheSpotlight().attemptsTo(
                ClickOn.addScheduleButtonFor(doctorName)
        );
    }

    @And("he adds a new availability with the following data")
    public void heAddsANewAvailabilityWithTheFollowingData(DataTable dataTable) {
        theActorInTheSpotlight().attemptsTo(
                AddSchedule.withTheData(dataTable)
        );
    }

    @Then("he should see a success message that says {string}")
    public void heShouldSeeASuccessMessageThatSays(String successMessage) {
        theActorInTheSpotlight().should(
                seeThat(ScheduleQuestions.successMessage(), containsString(successMessage))
        );
    }

    @Then("his schedule in the table should show the new availability")
    public void hisScheduleInTheTableShouldShowTheNewAvailability(DataTable dataTable) {
        Map<String, String> verificationData = dataTable.asMaps().get(0);
        String expectedHorario = verificationData.get("Schedule");
        String expectedRango = verificationData.get("Date Range");
        String expectedDias = verificationData.get("Days");

        theActorInTheSpotlight().should(
                seeThat(ScheduleQuestions.scheduleDataIsDisplayedInFirstRow(expectedDias, expectedHorario, expectedRango), is(true))
        );
    }
}