package co.edu.udea.calidad.citasSalud.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

public class TheHomePage implements Question<Boolean> {

    @Override
    public Boolean answeredBy(Actor actor) {

        String currentUrl = BrowseTheWeb.as(actor).getDriver().getCurrentUrl();
        return currentUrl.contains("/horarios");
    }

    public static TheHomePage isDisplayed() {
        return new TheHomePage();
    }
}