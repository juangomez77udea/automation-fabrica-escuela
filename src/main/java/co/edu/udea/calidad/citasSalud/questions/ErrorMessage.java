package co.edu.udea.calidad.citasSalud.questions;

import co.edu.udea.calidad.citasSalud.userinterfaces.LoginPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class ErrorMessage implements Question<String> {

    @Override
    public String answeredBy(Actor actor) {
        actor.attemptsTo(
                WaitUntil.the(LoginPage.ERROR_MESSAGE_LOGIN, isVisible()).forNoMoreThan(5).seconds()
        );
        return Text.of(LoginPage.ERROR_MESSAGE_LOGIN).answeredBy(actor);
    }

    public static Question<String> displayed() {
        return new ErrorMessage();
    }
}