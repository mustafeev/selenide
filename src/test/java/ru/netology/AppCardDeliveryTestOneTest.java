package ru.netology;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.locks.Condition;

import static con.codeborne.selenide.Selenide.$;
import static con.codeborne.selenide.Selenide.open;


public class AppCardDeliveryTestOneTest {
    private String generateDate(int assDays, String patter) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    public void shouldBeSuccessfullyCompleted() {
        open(" http://localhost:9999");
        $("[date-test-id="city"] input").setValue("Ульяновск");
        String currentDate = generateDate(7, "dd.mm.yyyy");
        $("[date-test-id="date"] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[date-test-id="date"] input").sendKeys(currentDate);
        $("[date-test-id="name"] input").setValue("Иванов Иван Иванович");
        $("[date-test-id="phone"] input").setValue("+79278586347");
        $("[date-test-id="agreement"] *)input").click();
        $("button.button").click();
        $(".notification__content")
                .shouldBe(Condition.Visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactTest("Встреча успешно забронирована на " + CurrentDate));
    }
}
