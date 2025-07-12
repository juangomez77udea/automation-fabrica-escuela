package co.edu.udea.calidad.citasSalud.userinterfaces;

import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

@DefaultUrl("http://localhost:3000/login")
public class LoginPage extends PageObject {

    public static final Target USERNAME_FIELD = Target.the("campo de correo electrónico")
            .located(By.xpath("//input[@placeholder='Correo electrónico']"));

    public static final Target PASSWORD_FIELD = Target.the("campo de contraseña")
            .located(By.xpath("//input[@placeholder='Contraseña']"));

    public static final Target LOGIN_BUTTON = Target.the("botón Aceptar")
            .located(By.xpath("//button[text()='Aceptar']"));

    // Modal de error basado en tu componente ModalLoginError.tsx
    public static final Target ERROR_MODAL = Target.the("modal de error de login")
            .located(By.xpath("//div[contains(@class, 'bg-[white]') and contains(@class, 'border-[red]')]"));

    public static final Target ERROR_MODAL_TITLE = Target.the("título del modal de error")
            .located(By.xpath("//h2[text()='¡LOGIN NO VÁLIDO!']"));

    public static final Target ERROR_MODAL_MESSAGE = Target.the("mensaje del modal de error")
            .located(By.xpath("//p[contains(text(), 'La información suministrada no corresponde')]"));
}
