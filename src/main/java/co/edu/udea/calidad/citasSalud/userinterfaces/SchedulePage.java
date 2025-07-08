package co.edu.udea.calidad.citasSalud.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class SchedulePage {

    public static final Target ADD_SCHEDULE_BUTTON_FOR_DOCTOR = Target
            .the("Boton Agregar Horario para el doctor {0}")
            .locatedBy("//tr[contains(., '{0}')]//button[@title='Agregar horario']");

    private static final String MODAL_CONTAINER_XPATH = "//div[contains(@class, 'fixed')]//div[contains(@class, 'bg-[#5E7FD3]')]";

    public static final Target START_DATE_INPUT = Target.the("Campo Fecha Inicio")
            .locatedBy("(" + MODAL_CONTAINER_XPATH + "//input[@type='date'])[1]");

    public static final Target END_DATE_INPUT = Target.the("Campo Fecha Fin")
            .locatedBy("(" + MODAL_CONTAINER_XPATH + "//input[@type='date'])[2]");

    public static final Target START_TIME_HOUR_SELECT = Target.the("Selector de Hora Inicio")
            .locatedBy("(" + MODAL_CONTAINER_XPATH + "//select)[1]");

    public static final Target START_TIME_MINUTE_SELECT = Target.the("Selector de Minuto Inicio")
            .locatedBy("(" + MODAL_CONTAINER_XPATH + "//select)[2]");

    public static final Target END_TIME_HOUR_SELECT = Target.the("Selector de Hora Fin")
            .locatedBy("(" + MODAL_CONTAINER_XPATH + "//select)[3]");

    public static final Target END_TIME_MINUTE_SELECT = Target.the("Selector de Minuto Fin")
            .locatedBy("(" + MODAL_CONTAINER_XPATH + "//select)[4]");

    public static final Target ADD_BUTTON_IN_MODAL = Target.the("Boton Agregar del modal")
            .locatedBy(MODAL_CONTAINER_XPATH + "//button[text()='Agregar']");

    public static final Target SUCCESS_MESSAGE_CONTAINER = Target.the("Contenedor del Mensaje de exito")
            .located(By.xpath("//div[@role='alert']"));

    public static final Target SCHEDULE_TABLE_FIRST_ROW = Target
            .the("Primera fila de datos de la tabla de horarios")
            .locatedBy("(//tbody/tr)[1]");
}