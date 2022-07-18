package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.DataHelper.*;

public class FieldMonthTest {

    private PaymentPage paymentPage;

    @BeforeEach
    void setup() {
        open("http://localhost:8080/");
        paymentPage = new PaymentPage();
    }

    @DisplayName("3.1. Payment for the tour by card, short month number")
    @Test
    void shouldNotSubmitApplicationWrongFormatShortMonthNumber() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillMonthField(DataHelper.getOneNumber());
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationWrongFormat();
    }

    @DisplayName("3.1. Payment for the tour by credit card, short month number")
    @Test
    void shouldNotSubmitApplicationWrongFormatCreditCardShortMonthNumber() {
        paymentPage.openCreditCardPaymentPage();
        paymentPage.fillMonthField(DataHelper.getOneNumber());
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationWrongFormat();
    }

    @DisplayName("3.2. Payment for the tour by card, empty month number input")
    @Test
    void shouldNotSubmitApplicationEmptyInput() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillMonthField("");
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationWrongFormat();
    }

    @DisplayName("3.2. Payment for the tour by credit card, empty month number input")
    @Test
    void shouldNotSubmitApplicationCreditCardEmptyInput() {
        paymentPage.openCreditCardPaymentPage();
        paymentPage.fillMonthField("");
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationWrongFormat();
    }

    @DisplayName("3.3. Payment for the tour by card, month number is all 0")
    @Test
    void shouldNotSubmitApplicationMonthNumberAll0() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillMonthField(monthAndYearNumbersIsAll0);
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationInvalidCard();
    }

    @DisplayName("3.3. Payment for the tour by credit card, month number is all 0")
    @Test
    void shouldNotSubmitApplicationCreditCardMonthNumberAll0() {
        paymentPage.openCreditCardPaymentPage();
        paymentPage.fillMonthField(monthAndYearNumbersIsAll0);
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationInvalidCard();
    }

    @DisplayName("3.4. Payment for the tour by card, non-existent month number")
    @Test
    void shouldNotSubmitApplicationNonExistentMonthNumber() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillMonthField(nonExistentMonthNumber);
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationInvalidCard();
    }

    @DisplayName("3.4. Payment for the tour by credit card, non-existent month number")
    @Test
    void shouldNotSubmitApplicationCreditCardNonExistentMonthNumber() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillMonthField(nonExistentMonthNumber);
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationInvalidCard();
    }

    @DisplayName("3.5. Payment for the tour by card, invalid month number")
    @Test
    void shouldNotSubmitApplicationInvalidMonthNumber() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillMonthField(monthNumberInvalid);
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationWrongFormat();
    }

    @DisplayName("3.5. Payment for the tour by credit card, invalid month number")
    @Test
    void shouldNotSubmitApplicationCreditCardInvalidMonthNumber() {
        paymentPage.openCreditCardPaymentPage();
        paymentPage.fillMonthField(monthNumberInvalid);
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationWrongFormat();
    }

    @DisplayName("3.6. Payment for the tour by card, long month number")
    @Test
    void shouldNotSubmitApplicationLongMonthNumber() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillMonthField(longMonthNumber);
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotification();
    }

    @DisplayName("3.6. Payment for the tour by credit card, long month number")
    @Test
    void shouldNotSubmitApplicationCreditCardLongMonthNumber() {
        paymentPage.openCreditCardPaymentPage();
        paymentPage.fillMonthField(longMonthNumber);
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotification();
    }

    private void fillOtherFieldsByValidInfo() {
        paymentPage.fillCardNumberField(DataHelper.getCardNumberSign16());     //случайная карта
        paymentPage.fillYearField(DataHelper.getYear(2));        //год + 2
        paymentPage.fillOwnerField(DataHelper.getOwnerFullNameEn());
        paymentPage.fillCvcCvvField(DataHelper.getCVC_CVV());
        paymentPage.clickContinueButton();
    }
}
