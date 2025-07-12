package co.edu.udea.calidad.citasSalud.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class AddScheduleModal {
    private AddScheduleModal() {}

    public static final Target MODAL_CONTAINER = Target.the("contenedor del modal de agregar horario")
            .located(By.xpath("//div[contains(@class, 'fixed')]//label[text()='Fecha:']"));

    public static final Target START_DATE_INPUT = Target.the("campo fecha de inicio")
            .located(By.xpath("(//input[@type='date'])[1]"));

    public static final Target END_DATE_INPUT = Target.the("campo fecha de fin")
            .located(By.xpath("(//input[@type='date'])[2]"));

    public static final Target START_TIME_HOUR_SELECT = Target.the("selector de hora de inicio")
            .located(By.xpath("(//select)[1]"));

    public static final Target START_TIME_MINUTE_SELECT = Target.the("selector de minuto de inicio")
            .located(By.xpath("(//select)[2]"));

    public static final Target END_TIME_HOUR_SELECT = Target.the("selector de hora de fin")
            .located(By.xpath("(//select)[3]"));

    public static final Target END_TIME_MINUTE_SELECT = Target.the("selector de minuto de fin")
            .located(By.xpath("(//select)[4]"));

    public static final Target ADD_BUTTON_IN_MODAL = Target.the("botón Agregar del modal")
            .located(By.xpath("//button[text()='Agregar']"));
}