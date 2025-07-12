package co.edu.udea.calidad.citasSalud.tasks;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class OpenThe implements Task {

    private final Class<? extends PageObject> pageClass;

    public OpenThe(Class<? extends PageObject> pageClass) {
        this.pageClass = pageClass;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Open.browserOn().the(pageClass)
        );
    }

    public static OpenThe page(Class<? extends PageObject> pageClass) {
        return instrumented(OpenThe.class, pageClass);
    }
}