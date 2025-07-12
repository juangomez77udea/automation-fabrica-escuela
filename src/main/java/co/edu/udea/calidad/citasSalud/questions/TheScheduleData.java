package co.edu.udea.calidad.citasSalud.questions;

import co.edu.udea.calidad.citasSalud.userinterfaces.HorariosPage;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class TheScheduleData {

    public static Question<String> dateRangeFor(String doctorName) {
        return actor -> {
            actor.attemptsTo(WaitUntil.the(HorariosPage.DATE_RANGE_CELL_FOR_DOCTOR(doctorName), isVisible()).forNoMoreThan(10).seconds());
            return Text.of(HorariosPage.DATE_RANGE_CELL_FOR_DOCTOR(doctorName)).answeredBy(actor).trim();
        };
    }

    public static Question<String> timeFor(String doctorName) {
        return actor -> {
            actor.attemptsTo(WaitUntil.the(HorariosPage.SCHEDULE_CELL_FOR_DOCTOR(doctorName), isVisible()).forNoMoreThan(10).seconds());
            return Text.of(HorariosPage.SCHEDULE_CELL_FOR_DOCTOR(doctorName)).answeredBy(actor).trim();
        };
    }
}