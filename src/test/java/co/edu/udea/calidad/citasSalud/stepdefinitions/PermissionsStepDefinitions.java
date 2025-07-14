package co.edu.udea.calidad.citasSalud.stepdefinitions;

import co.edu.udea.calidad.citasSalud.questions.TheLoginResult;
import co.edu.udea.calidad.citasSalud.tasks.Authenticate;
import co.edu.udea.calidad.citasSalud.tasks.OpenThe;
import co.edu.udea.calidad.citasSalud.userinterfaces.HorariosPage;
import co.edu.udea.calidad.citasSalud.userinterfaces.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.questions.Visibility;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static net.serenitybdd.screenplay.questions.WebElementQuestion.the;
import static org.hamcrest.Matchers.is;

public class PermissionsStepDefinitions {

    @Given("the user {string} with password {string} logs in to the system")
    public void theUserWithPasswordLogsInToTheSystem(String user, String password) {
        OnStage.theActorCalled("User with role").wasAbleTo(
                OpenThe.page(LoginPage.class),
                Authenticate.with(user, password)
        );
        try { Thread.sleep(2000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    @Then("^he (should|should not) see the modification options in the schedule table$")
    public void heShouldSeeTheModificationOptionsInTheScheduleTable(String visibility) {
        boolean shouldBeVisible = "should".equals(visibility);

        if (shouldBeVisible) {
            OnStage.theActorInTheSpotlight().should(
                    seeThat(TheLoginResult.wasSuccessful(), is(true)),
                    seeThat(Visibility.of(HorariosPage.ACTIONS_COLUMN_HEADER), is(true))
            );
        } else {
            OnStage.theActorInTheSpotlight().should(
                    seeThat(TheLoginResult.wasSuccessful(), is(false)),
                    seeThat(the(LoginPage.LOGIN_BUTTON), isVisible())
            );
        }
    }
}