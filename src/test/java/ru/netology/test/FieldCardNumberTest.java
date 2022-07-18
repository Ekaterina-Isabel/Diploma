package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.DataHelper.cardNumberAll0;
import static ru.netology.data.DataHelper.cardNumberInvalid;

public class FieldCardNumberTest {

    private PaymentPage paymentPage;

    @BeforeEach
    void setup() {
        open("http://localhost:8080/");
        paymentPage = new PaymentPage();
    }

    @DisplayName("2.1. Payment for the tour by card, short card number")
    @Test
    void shouldNotSubmitApplicationWrongFormatShortCardNumber() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillCardNumberField(DataHelper.getCardNumberSign15());
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationWrongFormat();
    }

    @DisplayName("2.1. Payment for the tour by credit card, short card number")
    @Test
    void shouldNotSubmitApplicationWrongFormatShortCreditCardNumber() {
        paymentPage.openCreditCardPaymentPage();
        paymentPage.fillCardNumberField(DataHelper.getCardNumberSign15());
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationWrongFormat();
    }

    @DisplayName("2.2. Payment for the tour by card, empty card number input")
    @Test
    void shouldNotSubmitApplicationEmptyInput() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillCardNumberField("");
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationWrongFormat();
    }

    @DisplayName("2.2. Payment for the tour by credit card, empty card number input")
    @Test
    void shouldNotSubmitApplicationCreditCardEmptyInput() {
        paymentPage.openCreditCardPaymentPage();
        paymentPage.fillCardNumberField("");
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationWrongFormat();
    }

    @DisplayName("2.3. Payment for the tour by card, long card number")
    @Test
    void shouldNotSubmitApplicationLongCardNumber() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillCardNumberField(DataHelper.getCardNumberSign17());
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotification();
    }

    @DisplayName("2.3. Payment for the tour by credit card, long card number")
    @Test
    void shouldNotSubmitApplicationLongCreditCardNumber() {
        paymentPage.openCreditCardPaymentPage();
        paymentPage.fillCardNumberField(DataHelper.getCardNumberSign17());
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotification();
    }

    @DisplayName("2.4. Payment for the tour by card, card number is all 0")
    @Test
    void shouldNotSubmitApplicationCardAll0() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillCardNumberField(cardNumberAll0);
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationWrongFormat();
    }

    @DisplayName("2.4. Payment for the tour by credit card, card number is all 0")
    @Test
    void shouldNotSubmitApplicationCreditCardAll0() {
        paymentPage.openCreditCardPaymentPage();
        paymentPage.fillCardNumberField(cardNumberAll0);
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationWrongFormat();
    }

    @DisplayName("2.5. Payment for the tour by card, invalid card number")
    @Test
    void shouldNotSubmitApplicationInvalidCardNumber() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillCardNumberField(cardNumberInvalid);
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationWrongFormat();
    }

    @DisplayName("2.5. Payment for the tour by credit card, invalid card number")
    @Test
    void shouldNotSubmitApplicationInvalidCreditCardNumber() {
        paymentPage.openCreditCardPaymentPage();
        paymentPage.fillCardNumberField(cardNumberInvalid);
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationWrongFormat();
    }

    private void fillOtherFieldsByValidInfo() {
        paymentPage.fillMonthField(DataHelper.getCurrentMonth());       //текущий месяц
        paymentPage.fillYearField(DataHelper.getYear(0));        //текущий год
        paymentPage.fillOwnerField(DataHelper.getOwnerFullNameEn());
        paymentPage.fillCvcCvvField(DataHelper.getCVC_CVV());
        paymentPage.clickContinueButton();
    }
}
