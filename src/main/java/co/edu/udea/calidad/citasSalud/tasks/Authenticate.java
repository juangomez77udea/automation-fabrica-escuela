package co.edu.udea.calidad.citasSalud.tasks;

import co.edu.udea.calidad.citasSalud.userinterfaces.LoginPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class Authenticate implements Task {
    private final String user;
    private final String password;

    public Authenticate(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public static Performable with(String user, String password) {
        return instrumented(Authenticate.class, user, password);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        System.out.println("🔐 Intentando autenticar a: " + (user.isEmpty() ? "[usuario vacío]" : user));

        // La tarea solo se encarga de realizar las acciones.
        // No debe contener lógica de verificación, esperas largas o try-catch.
        actor.attemptsTo(
                Enter.theValue(user).into(LoginPage.USERNAME_FIELD),
                Enter.theValue(password).into(LoginPage.PASSWORD_FIELD),
                Click.on(LoginPage.LOGIN_BUTTON)
        );
    }
}