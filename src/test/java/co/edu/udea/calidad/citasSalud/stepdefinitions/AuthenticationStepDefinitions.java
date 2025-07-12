package co.edu.udea.calidad.citasSalud.stepdefinitions;

import co.edu.udea.calidad.citasSalud.questions.TheLoginResult;
import co.edu.udea.calidad.citasSalud.tasks.Authenticate;
import co.edu.udea.calidad.citasSalud.tasks.OpenThe;
import co.edu.udea.calidad.citasSalud.userinterfaces.LoginPage;
import co.edu.udea.calidad.citasSalud.utils.TextNormalizer;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.questions.WebElementQuestion.the;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.is;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class AuthenticationStepDefinitions {

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("the user is on the login page")
    public void theUserIsOnTheLoginPage() {
        OnStage.theActorCalled("User").wasAbleTo(OpenThe.page(LoginPage.class));
    }

    // Este método maneja las credenciales válidas del Scenario
    @When("he enters the valid credentials {string} and {string}")
    public void heEntersTheValidCredentialsAnd(String user, String password) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                Authenticate.with(user, password)
        );
    }

    @Then("he should be redirected to the schedules page")
    public void heShouldBeRedirectedToTheSchedulesPage() {
        OnStage.theActorInTheSpotlight().should(seeThat(TheLoginResult.wasSuccessful(), is(true)));
    }

    // Este método maneja las credenciales inválidas del Scenario Outline
    @When("he enters the invalid credentials {string} and {string}")
    public void heEntersTheInvalidCredentialsAnd(String user, String password) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                Authenticate.with(user, password)
        );
    }

    @Then("he should see the error message {string}")
    public void heShouldSeeTheErrorMessage(String expectedMessage) {
        String expectedNormalizedMessage = TextNormalizer.normalize(expectedMessage);
        OnStage.theActorInTheSpotlight().should(
                seeThat(TheLoginResult.errorMessage(), containsString(expectedNormalizedMessage))
        );
    }

    @Then("he should remain on the login page")
    public void heShouldRemainOnTheLoginPage() {
        OnStage.theActorInTheSpotlight().should(seeThat(the(LoginPage.LOGIN_BUTTON), isVisible()));
    }
}