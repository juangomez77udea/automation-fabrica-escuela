package co.edu.udea.calidad.citasSalud.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class HorariosPage {
    private HorariosPage() {}

    public static final Target SCHEDULE_TABLE = Target.the("tabla de horarios")
            .located(By.tagName("table"));

    public static final Target SCHEDULE_TABLE_BODY = Target.the("cuerpo de la tabla de horarios")
            .located(By.cssSelector("table tbody"));

    // Localizador para cualquier fila de la tabla
    public static final Target DOCTOR_ROWS = Target.the("filas de doctores")
            .located(By.cssSelector("tbody tr"));

    public static final Target ACTION_BUTTONS_CELL = Target.the("celda de acciones de la primera fila")
            .located(By.cssSelector("tbody tr:first-child td:last-child"));

    public static final Target ADD_SUCCESS_MESSAGE = Target.the("mensaje de éxito agregar franja")
            .located(By.xpath("//div[contains(text(), 'Franja horaria agregada exitosamente') or contains(text(), 'exitosamente')]"));


    public static Target DELETE_SCHEDULE_BUTTON_FOR_DOCTOR(String doctorName) {
        return Target.the("botón eliminar horario para " + doctorName)
                .located(By.xpath(String.format(
                        "//tr[td[contains(text(),'%s')]]//button[@title='Eliminar horario']",
                        doctorName
                )));
    }

    public static Target UPDATE_SCHEDULE_BUTTON_FOR_DOCTOR(String doctorName) {
        return Target.the("botón actualizar horario para " + doctorName)
                .located(By.xpath(String.format(
                        "//tr[td[contains(text(),'%s')]]//button[@title='Actualizar horario']",
                        doctorName
                )));
    }

    // Localizador alternativo usando íconos FontAwesome
    public static Target ADD_BUTTON_BY_ICON_FOR_DOCTOR(String doctorName) {
        return Target.the("botón agregar por ícono para " + doctorName)
                .located(By.xpath(String.format(
                        "//tr[td[contains(text(),'%s')]]//button[.//svg[@data-icon='plus']]",
                        doctorName
                )));
    }

    public static Target SCHEDULE_TABLE_ROW(String doctorName) {
        return Target.the("fila de la tabla para " + doctorName)
                .located(By.xpath(String.format("//tr[td[contains(text(),'%s')]]", doctorName)));
    }

    // Localizador para verificar que estamos en la página correcta
    public static final Target PAGE_TITLE = Target.the("título de la página")
            .located(By.xpath("//h1[contains(text(), 'Lista de Turnos') or contains(text(), 'Horarios')]"));

    // Localizador para el rol del usuario
    public static final Target USER_ROLE_INFO = Target.the("información del rol del usuario")
            .located(By.xpath("//span[contains(text(), 'Médico') or contains(text(), 'Coordinador') or contains(text(), 'Administrador')]"));


    public static final Target ACTIONS_COLUMN_HEADER = Target.the("encabezado de la columna Selección")
            .located(By.xpath("//th[normalize-space()='Selección']"));

    public static Target ADD_SCHEDULE_BUTTON_FOR_DOCTOR(String doctorName) {
        String xpath = String.format(
                "//tr[td[normalize-space(.)='%s']]//button[@title='Agregar horario']",
                doctorName
        );
        return Target.the("botón agregar horario para " + doctorName)
                .located(By.xpath(xpath));
    }

    public static final Target SUCCESS_NOTIFICATION = Target.the("notificación de éxito")
            .located(By.xpath("//div[contains(@class, 'bg-green-100')]"));

    /**
     * Localiza la celda de 'Días' para un doctor específico.
     */

    public static Target DAYS_CELL_FOR_DOCTOR(String doctorName) {
        return Target.the("celda de Días para " + doctorName)
                .located(By.xpath(String.format("//tr[td[normalize-space()='%s']]//td[5]", doctorName)));
    }

    /**
     * Localiza la celda de 'Horario' para un doctor específico.
     */
    public static Target SCHEDULE_CELL_FOR_DOCTOR(String doctorName) {
        return Target.the("celda de Horario para " + doctorName)
                .located(By.xpath(String.format("//tr[td[normalize-space()='%s']]//td[6]", doctorName)));
    }

    /**
     * Localiza la celda de 'Rango Fechas' para un doctor específico.
     */
    public static Target DATE_RANGE_CELL_FOR_DOCTOR(String doctorName) {
        return Target.the("celda de Rango de Fechas para " + doctorName)
                .located(By.xpath(String.format("//tr[td[normalize-space()='%s']]//td[7]", doctorName)));
    }
}
