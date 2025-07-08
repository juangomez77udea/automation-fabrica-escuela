package co.edu.udea.calidad.citasSalud.stepdefinitions;

import co.edu.udea.calidad.citasSalud.questions.ErrorMessage;
import co.edu.udea.calidad.citasSalud.questions.TheHomePage;
import co.edu.udea.calidad.citasSalud.tasks.Login;
import co.edu.udea.calidad.citasSalud.tasks.OpenThe;
import co.edu.udea.calidad.citasSalud.userinterfaces.LoginPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
// ---
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.By;

import java.util.List;
import java.util.Map;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.is;

public class LoginStepDefinitions {

    private LoginPage loginPage;

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("that Juan wants to log in to the system")
    public void thatJuanWantsToLogInToTheSystem() {
        OnStage.theActorCalled("Juan");
    }

    @Given("that Juan is on the login page")
    public void thatJuanIsOnTheLoginPage() {
        OnStage.theActorInTheSpotlight().wasAbleTo(OpenThe.browserOn(loginPage));
    }

    @When("he enters the valid credentials")
    public void heEntersTheValidCredentials(DataTable dataTable) {
        performLogin(dataTable);
    }

    @Then("he should see the main page of the application")
    public void heShouldSeeTheMainPageOfTheApplication() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(WaitUntil.the(By.xpath("//button[contains(text(),'Cerrar sesión')]"), isVisible()).forNoMoreThan(15).seconds());
        actor.should(seeThat(TheHomePage.isDisplayed(), is(true)));
    }

    @When("he enters an incorrect password for a registered user")
    public void heEntersAnIncorrectPasswordForARegisteredUser(DataTable dataTable) {
        performLogin(dataTable);
    }

    @Then("he should see an error message that says {string}")
    public void heShouldSeeAnErrorMessageThatSays(String expectedMessage) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.should(seeThat(ErrorMessage.displayed(), containsString(expectedMessage)));
    }

    @And("he should remain on the login page")
    public void heShouldRemainOnTheLoginPage() {
        OnStage.theActorInTheSpotlight().should(seeThat(net.serenitybdd.screenplay.questions.Visibility.of(LoginPage.LOGIN_BUTTON), is(true)));
    }

    @When("he enters credentials for an unregistered user")
    public void heEntersCredentialsForAnUnregisteredUser(DataTable dataTable) {
        performLogin(dataTable);
    }

    private void performLogin(DataTable credentials) {
        List<Map<String, String>> data = credentials.asMaps(String.class, String.class);
        String username = data.get(0).get("user");
        String password = data.get(0).get("password");
        OnStage.theActorInTheSpotlight().attemptsTo(Login.withCredentials(username, password));
    }
}