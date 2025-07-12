package co.edu.udea.calidad.citasSalud.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(

        features = "src/test/resources/features",
        glue = "co.edu.udea.calidad.citasSalud.stepdefinitions",
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        tags = "not @ignore"
)
public class TestRunner {
    // La clase runner siempre está vacía.
}