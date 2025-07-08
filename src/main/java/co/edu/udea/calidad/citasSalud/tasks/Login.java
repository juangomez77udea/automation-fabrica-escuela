package co.edu.udea.calidad.citasSalud.tasks;

import co.edu.udea.calidad.citasSalud.interactions.FillLoginForm; // <-- Importamos nuestra nueva Interaction
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class Login implements Task {

    private final String username;
    private final String password;

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static Performable withCredentials(String username, String password) {
        return instrumented(Login.class, username, password);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                FillLoginForm.withCredentials(this.username, this.password)
        );
    }
}