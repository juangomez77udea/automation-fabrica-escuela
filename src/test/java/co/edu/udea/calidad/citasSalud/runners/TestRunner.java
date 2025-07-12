package co.edu.udea.calidad.citasSalud.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

/**
 * Esta clase es el punto de entrada principal para ejecutar todas las pruebas de Cucumber.
 * Utiliza JUnit para iniciar el proceso, pero delega la ejecución a Serenity y Cucumber.
 */
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        // Le decimos a Cucumber que busque TODOS los archivos .feature en esta carpeta.
        features = "src/test/resources/features",

        // Le indicamos el paquete donde están TODAS nuestras clases de StepDefinitions.
        glue = "co.edu.udea.calidad.citasSalud.stepdefinitions",

        // Formato para los snippets de código sugeridos.
        snippets = CucumberOptions.SnippetType.CAMELCASE,

        // Los tags permiten filtrar qué escenarios ejecutar.
        // "not @ignore" es una configuración común para poder marcar escenarios
        // que no queremos que se ejecuten temporalmente.
        tags = "not @ignore"
)
public class TestRunner {
    // La clase runner siempre está vacía.
    // Su único propósito es servir como un contenedor para las anotaciones de configuración.
}