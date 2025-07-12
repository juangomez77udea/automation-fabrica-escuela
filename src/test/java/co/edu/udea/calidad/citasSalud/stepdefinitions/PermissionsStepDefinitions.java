package co.edu.udea.calidad.citasSalud.stepdefinitions;

import co.edu.udea.calidad.citasSalud.questions.TheLoginResult; // Importamos TheLoginResult
import co.edu.udea.calidad.citasSalud.tasks.Authenticate;
import co.edu.udea.calidad.citasSalud.tasks.OpenThe;
import co.edu.udea.calidad.citasSalud.userinterfaces.HorariosPage;
import co.edu.udea.calidad.citasSalud.userinterfaces.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.questions.Visibility;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.is;

public class PermissionsStepDefinitions {

    @Given("the user {string} with password {string} logs in to the system")
    public void theUserWithPasswordLogsInToTheSystem(String user, String password) {
        OnStage.theActorCalled("User with role").wasAbleTo(
                OpenThe.page(LoginPage.class),
                Authenticate.with(user, password)
        );
        // Añadimos una pequeña espera para la redirección.
        try { Thread.sleep(2000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    // Este paso ya no es necesario, lo eliminamos. La lógica se mueve al Then.
    // @When("he is on the schedules page")
    // public void heIsOnTheSchedulesPage() { ... }

    // ESTE ES EL NUEVO Y ÚNICO PASO 'THEN'
    @Then("^he (should|should not) see the modification options in the schedule table$")
    public void heShouldSeeTheModificationOptionsInTheScheduleTable(String visibility) {
        boolean shouldBeVisible = "should".equals(visibility);

        if (shouldBeVisible) {
            // Para los usuarios autorizados, verificamos DOS COSAS:
            // 1. Que llegaron a la página de horarios.
            // 2. Que ven la columna de acciones.
            OnStage.theActorInTheSpotlight().should(
                    seeThat(TheLoginResult.wasSuccessful(), is(true)),
                    seeThat(Visibility.of(HorariosPage.ACTIONS_COLUMN_HEADER), is(true))
            );
        } else {
            // Para el usuario NO autorizado, verificamos DOS COSAS:
            // 1. Que NO llegaron a la página de horarios.
            // 2. Que, por lo tanto, NO ven la columna de acciones.
            OnStage.theActorInTheSpotlight().should(
                    seeThat(TheLoginResult.wasSuccessful(), is(false)),
                    seeThat(Visibility.of(HorariosPage.ACTIONS_COLUMN_HEADER), is(false))
            );
        }
    }
}