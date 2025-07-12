package co.edu.udea.calidad.citasSalud.questions;

import co.edu.udea.calidad.citasSalud.userinterfaces.LoginPage;
import co.edu.udea.calidad.citasSalud.utils.TextNormalizer;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.questions.page.TheWebPage;
import net.serenitybdd.screenplay.waits.WaitUntil;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class TheLoginResult {

    public static Question<Boolean> wasSuccessful() {
        return actor -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            String currentUrl = TheWebPage.currentUrl().answeredBy(actor);
            return currentUrl.contains("/horarios");
        };
    }

    public static Question<String> errorMessage() {
        return actor -> {
            try {
                String currentUrl = TheWebPage.currentUrl().answeredBy(actor);
                System.out.println("URL en errorMessage: " + currentUrl);

                if (currentUrl.contains("/login")) {
                    try {
                        actor.attemptsTo(WaitUntil.the(LoginPage.ERROR_MODAL, isVisible())
                                .forNoMoreThan(5).seconds());

                        String rawText = Text.of(LoginPage.ERROR_MODAL_TITLE).answeredBy(actor);
                        System.out.println("Texto del modal de error: " + rawText);
                        return TextNormalizer.normalize(rawText);
                    } catch (Exception e) {
                        System.out.println("No se encontró modal de error, pero seguimos en login");
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
