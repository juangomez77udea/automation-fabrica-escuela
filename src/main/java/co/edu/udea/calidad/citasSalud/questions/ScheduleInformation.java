package co.edu.udea.calidad.citasSalud.questions;

import co.edu.udea.calidad.citasSalud.userinterfaces.HorariosPage;
import co.edu.udea.calidad.citasSalud.utils.TextNormalizer;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class ScheduleInformation {

    public static Question<String> aSuccessNotification() {
        return actor -> {
            actor.attemptsTo(WaitUntil.the(HorariosPage.SUCCESS_NOTIFICATION, isVisible()).forNoMoreThan(10).seconds());
            String rawText = Text.of(HorariosPage.SUCCESS_NOTIFICATION).answeredBy(actor);
            return TextNormalizer.normalize(rawText);
        };
    }

    public static Question<String> forDoctor(String doctorName) {
        Target doctorRow = HorariosPage.SCHEDULE_TABLE_ROW(doctorName);
        return actor -> Text.of(doctorRow).answeredBy(actor);
    }
}