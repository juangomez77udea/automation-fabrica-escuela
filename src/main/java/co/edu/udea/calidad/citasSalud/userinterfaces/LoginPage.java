package co.edu.udea.calidad.citasSalud.userinterfaces;

import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

@DefaultUrl("http://localhost:3000/login")
public class LoginPage extends PageObject {

    public static final Target USERNAME_FIELD = Target.the("Campo de Correo Electrónico")
            .located(By.cssSelector("[data-testid='email-input']"));

    public static final Target PASSWORD_FIELD = Target.the("Campo de Contraseña")
            .located(By.cssSelector("[data-testid='password-input']"));

    public static final Target LOGIN_BUTTON = Target.the("Botón Aceptar")
            .located(By.cssSelector("[data-testid='login-button']"));

    public static final Target ERROR_MESSAGE_LOGIN = Target.the("Modal de error de login")
            .located(By.xpath("//h2[contains(text(),'¡LOGIN NO VÁLIDO!')]"));
}