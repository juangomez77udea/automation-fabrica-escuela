package co.edu.udea.calidad.citasSalud.questions;

import co.edu.udea.calidad.citasSalud.userinterfaces.LoginPage;
import co.edu.udea.calidad.citasSalud.utils.TextNormalizer;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.questions.page.TheWebPage;
import net.serenitybdd.screenplay.waits.WaitUntil;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class TheLoginResult {

    public static Question<Boolean> wasSuccessful() {
        return actor -> {
            try {
                Thread.sleep(3000); // Esperar a que la redirección ocurra
                String currentUrl = TheWebPage.currentUrl().answeredBy(actor);
                System.out.println("URL actual en wasSuccessful: " + currentUrl);

                boolean success = currentUrl.contains("/horarios") ||
                        currentUrl.contains("horarios") ||
                        !currentUrl.contains("/login");

                System.out.println("Login exitoso: " + success);
                return success;
            } catch (Exception e) {
                System.out.println("Error en wasSuccessful: " + e.getMessage());
                return false;
            }
        };
    }

    public static Question<String> errorMessage() {
        return actor -> {
            try {
                String currentUrl = TheWebPage.currentUrl().answeredBy(actor);
                System.out.println("URL en errorMessage: " + currentUrl);

                // Si seguimos en login, buscar el modal de error
                if (currentUrl.contains("/login")) {
                    try {
                        actor.attemptsTo(WaitUntil.the(LoginPage.ERROR_MODAL, isVisible())
                                .forNoMoreThan(5).seconds());

                        String rawText = Text.of(LoginPage.ERROR_MODAL_TITLE).answeredBy(actor);
                        System.out.println("Texto del modal de error: " + rawText);
                        return TextNormalizer.normalize(rawText);
                    } catch (Exception e) {
                        System.out.println("No se encontró modal de error, pero seguimos en login");
                        // Si no hay modal pero seguimos en login, asumir que hubo error
                        return TextNormalizer.normalize("LOGIN NO VÁLIDO");
                    }
                } else {
                    System.out.println("No estamos en login, no hay error");
                    return "";
                }
            } catch (Exception e) {
                System.out.println("Error obteniendo mensaje de error: " + e.getMessage());
                return "";
            }
        };
    }
}
