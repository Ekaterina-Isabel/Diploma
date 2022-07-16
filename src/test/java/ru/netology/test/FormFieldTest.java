package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.PaymentPage;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.DataHelper.cardNumberAll0;
import static ru.netology.data.DataHelper.cardNumberInvalid;

public class FormFieldTest {

    private PaymentPage paymentPage;

    @BeforeEach
    void setup() {
        open("http://localhost:8080/");
        paymentPage = new PaymentPage();
    }

    @Test
    void shouldDeclinedBuyingTourCardAll0() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillCardNumberField(cardNumberAll0);

        var expected = $(byText("Неверный формат"));

//        assertEquals(expected, actual);
    }

    @Test
    void shouldNotSubmitApplicationEmptyInput() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillCardNumberField("");

        var expected = $(byText("Неверный формат"));

//        assertEquals(expected, actual);
    }

    @Test
    void shouldNotSubmitApplicationWrongFormat() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillCardNumberField(DataHelper.getCardNumberSign15());

        var expected = $(byText("Неверный формат"));

//        assertEquals(expected, actual);
    }

    @Test
    void shouldNotSubmitApplicationException() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillCardNumberField(DataHelper.getCardNumberSign17());

        //        var expected = $(byText(""));

//        assertEquals(expected, actual);
    }

    @Test
    void shouldNotSubmitApplicationExceptionSymbols() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillCardNumberField(cardNumberInvalid);

//        var expected = $(byText(""));

//        assertEquals(expected, actual);
    }

}
