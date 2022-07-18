package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.DataHelper.*;

public class FieldYearTest {

    private PaymentPage paymentPage;

    @BeforeEach
    void setup() {
        open("http://localhost:8080/");
        paymentPage = new PaymentPage();
    }

    @DisplayName("4.1. Payment for the tour by card, short year number")
    @Test
    void shouldNotSubmitApplicationWrongFormatShortYearNumber() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillYearField(DataHelper.getOneNumber());
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationWrongFormat();
    }

    @DisplayName("4.1. Payment for the tour by credit card, short year number")
    @Test
    void shouldNotSubmitApplicationCreditCardWrongFormatShortYearNumber() {
        paymentPage.openCreditCardPaymentPage();
        paymentPage.fillYearField(DataHelper.getOneNumber());
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationWrongFormat();
    }

    @DisplayName("4.2. Payment for the tour by card, empty year number input")
    @Test
    void shouldNotSubmitApplicationEmptyInput() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillYearField("");
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationWrongFormat();
    }

    @DisplayName("4.2. Payment for the tour by credit card, empty year number input")
    @Test
    void shouldNotSubmitApplicationCreditCardEmptyInput() {
        paymentPage.openCreditCardPaymentPage();
        paymentPage.fillYearField("");
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationWrongFormat();
    }

    @DisplayName("4.3. Payment for the tour by card, year number is all 0")
    @Test
    void shouldNotSubmitApplicationYearNumberAll0() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillYearField(monthAndYearNumbersIsAll0);
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationCardExpired();
    }

    @DisplayName("4.3. Payment for the tour by credit credit card, year number is all 0")
    @Test
    void shouldNotSubmitApplicationCreditCardYearNumberAll0() {
        paymentPage.openCreditCardPaymentPage();
        paymentPage.fillYearField(monthAndYearNumbersIsAll0);
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationCardExpired();
    }

    @DisplayName("4.4. Payment for the tour by card, last year")
    @Test
    void shouldNotSubmitApplicationLastYear() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillYearField(getYear(-1));
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationCardExpired();
    }

    @DisplayName("4.4. Payment for the tour by credit card, last year")
    @Test
    void shouldNotSubmitApplicationCreditCardLastYear() {
        paymentPage.openCreditCardPaymentPage();
        paymentPage.fillYearField(getYear(-1));
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationCardExpired();
    }

    @DisplayName("4.5. Payment for the tour by card, invalid year number")
    @Test
    void shouldNotSubmitApplicationInvalidYearNumber() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillYearField(yearNumberInvalid);
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationWrongFormat();
    }

    @DisplayName("4.5. Payment for the tour by credit card, invalid year number")
    @Test
    void shouldNotSubmitApplicationCreditCardInvalidYearNumber() {
        paymentPage.openCreditCardPaymentPage();
        paymentPage.fillYearField(yearNumberInvalid);
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationWrongFormat();
    }

    @DisplayName("4.6. Payment for the tour by card, full year number")
    @Test
    void shouldNotSubmitApplicationFullYearNumber() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillYearField(DataHelper.getFullYearNumber(0));
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationCardExpired();
    }

    @DisplayName("4.6. Payment for the tour by credit card, full year number")
    @Test
    void shouldNotSubmitApplicationCreditCardFullYearNumber() {
        paymentPage.openCreditCardPaymentPage();
        paymentPage.fillYearField(DataHelper.getFullYearNumber(0));
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationCardExpired();
    }

    @DisplayName("4.7. Payment for the tour by card, year number is 6 more")
    @Test
    void shouldNotSubmitApplicationYearNumberIs6More() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillYearField(DataHelper.getYear(6));
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationInvalidCard();
    }

    @DisplayName("4.7. Payment for the tour by credit card, year number is 6 more")
    @Test
    void shouldNotSubmitApplicationCreditCardYearNumberIs6More() {
        paymentPage.openCreditCardPaymentPage();
        paymentPage.fillYearField(DataHelper.getYear(6));
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationInvalidCard();
    }

    private void fillOtherFieldsByValidInfo() {
        paymentPage.fillCardNumberField(DataHelper.getCardNumberSign16());     //случайная карта
        paymentPage.fillMonthField(DataHelper.getCurrentMonth());       //текущий месяц
        paymentPage.fillOwnerField(DataHelper.getOwnerFullNameEn());
        paymentPage.fillCvcCvvField(DataHelper.getCVC_CVV());
        paymentPage.clickContinueButton();
    }
}
