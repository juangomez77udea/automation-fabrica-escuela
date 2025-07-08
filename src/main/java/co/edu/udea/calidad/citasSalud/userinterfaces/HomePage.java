package co.edu.udea.calidad.citasSalud.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class HomePage {
    private HomePage() {
    }

    public static final Target LIST_BUTTON = Target.the(
            "Botón Lista en la página principal")
            .located(
                    By.xpath("//button[contains(text(),'Lista')]")
            );
}