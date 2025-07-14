package co.edu.udea.calidad.citasSalud.stepdefinitions;

import co.edu.udea.calidad.citasSalud.questions.TheLoginResult;
import co.edu.udea.calidad.citasSalud.tasks.Authenticate;
import co.edu.udea.calidad.citasSalud.tasks.OpenThe;
import co.edu.udea.calidad.citasSalud.userinterfaces.LoginPage;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.questions.WebElementQuestion.the;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.text.IsEmptyString.emptyString;

public class AuthenticationStepDefinitions {

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("the user is on the login page")
    public void theUserIsOnTheLoginPage() {
        OnStage.theActorCalled("User").wasAbleTo(OpenThe.page(LoginPage.class));
    }

    @When("he enters the {word} credentials {string} and {string}")
    public void heEntersTheCredentialsAnd(String typeOfCredentials, String user, String password) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                Authenticate.with(user, password)
        );
    }

    @Then("he should be redirected to the schedules page")
    public void heShouldBeRedirectedToTheSchedulesPage() {
        OnStage.theActorInTheSpotlight().should(seeThat(TheLoginResult.wasSuccessful(), is(true)));
    }

    @Then("the system should show an invalid login error message")
    public void theSystemShouldShowAnInvalidLoginErrorMessage() {
        OnStage.theActorInTheSpotlight().should(
                seeThat(TheLoginResult.errorMessage(), not(emptyString()))
        );
    }

    @Then("he should remain on the login page")
    public void heShouldRemainOnTheLoginPage() {
        OnStage.theActorInTheSpotlight().should(seeThat(the(LoginPage.LOGIN_BUTTON), isVisible()));
    }
}