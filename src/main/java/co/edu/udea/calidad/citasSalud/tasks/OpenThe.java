package co.edu.udea.calidad.citasSalud.tasks;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class OpenThe implements Task {

    private final PageObject page;

    public OpenThe(PageObject page) {
        this.page = page;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Open.browserOn(this.page)
        );
    }

    public static OpenThe browserOn(PageObject page) {
        return instrumented(OpenThe.class, page);
    }
}