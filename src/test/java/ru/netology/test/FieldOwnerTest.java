package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.DataHelper.ownerInvalid;

public class FieldOwnerTest {

    private PaymentPage paymentPage;

    @BeforeEach
    void setup() {
        open("http://localhost:8080/");
        paymentPage = new PaymentPage();
    }

    @DisplayName("5.1. Payment for the tour by card, only last name in Latin letters")
    @Test
    void shouldNotSubmitApplicationWrongFormatLastNameInLatinLetters() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillOwnerField(DataHelper.getOwnerLastNameEn());
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationWrongFormat();
    }

    @DisplayName("5.1. Payment for the tour by credit card, only last name in Latin letters")
    @Test
    void shouldNotSubmitApplicationCreditCardWrongFormatLastNameInLatinLetters() {
        paymentPage.openCreditCardPaymentPage();
        paymentPage.fillOwnerField(DataHelper.getOwnerLastNameEn());
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationWrongFormat();
    }

    @DisplayName("5.2. Payment for the tour by card, only first name in Latin letters")
    @Test
    void shouldNotSubmitApplicationWrongFormatFirstNameInLatinLetters() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillOwnerField(DataHelper.getOwnerFirstNameEn());
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationWrongFormat();
    }

    @DisplayName("5.2. Payment for the tour by credit card, only first name in Latin letters")
    @Test
    void shouldNotSubmitApplicationCreditCardWrongFormatFirstNameInLatinLetters() {
        paymentPage.openCreditCardPaymentPage();
        paymentPage.fillOwnerField(DataHelper.getOwnerFirstNameEn());
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationWrongFormat();
    }

    @DisplayName("5.3. Payment for the tour by card, only last name in Cyrillic")
    @Test
    void shouldNotSubmitApplicationWrongFormatLastNameInCyrillic() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillOwnerField(DataHelper.getOwnerLastNameRu());
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationWrongFormat();
    }

    @DisplayName("5.3. Payment for the tour by credit card, only last name in Cyrillic")
    @Test
    void shouldNotSubmitApplicationCreditCardWrongFormatLastNameInCyrillic() {
        paymentPage.openCreditCardPaymentPage();
        paymentPage.fillOwnerField(DataHelper.getOwnerLastNameRu());
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationWrongFormat();
    }

    @DisplayName("5.4. Payment for the tour by card, only first name in Cyrillic")
    @Test
    void shouldNotSubmitApplicationWrongFormatFirstNameCyrillic() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillOwnerField(DataHelper.getOwnerFirstNameRu());
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationWrongFormat();
    }

    @DisplayName("5.4. Payment for the tour by credit card, only first name in Cyrillic")
    @Test
    void shouldNotSubmitApplicationCreditCardWrongFormatFirstNameCyrillic() {
        paymentPage.openCreditCardPaymentPage();
        paymentPage.fillOwnerField(DataHelper.getOwnerFirstNameRu());
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationWrongFormat();
    }

    @DisplayName("5.5. Payment for the tour by card, empty owner input")
    @Test
    void shouldNotSubmitApplicationEmptyInput() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillOwnerField("");
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationRequiredField();
    }

    @DisplayName("5.5. Payment for the tour by credit card, empty owner input")
    @Test
    void shouldNotSubmitApplicationCreditCardEmptyInput() {
        paymentPage.openCreditCardPaymentPage();
        paymentPage.fillOwnerField("");
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationRequiredField();
    }

    @DisplayName("5.6. Payment for the tour by card, invalid owner")
    @Test
    void shouldNotSubmitApplicationInvalidOwner() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillOwnerField(ownerInvalid);
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationWrongFormat();
    }

    @DisplayName("5.6. Payment for the tour by credit card, invalid owner")
    @Test
    void shouldNotSubmitApplicationCreditCardInvalidCardOwner() {
        paymentPage.openCreditCardPaymentPage();
        paymentPage.fillOwnerField(ownerInvalid);
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationWrongFormat();
    }

    private void fillOtherFieldsByValidInfo() {
        paymentPage.fillCardNumberField(DataHelper.getCardNumberSign16());     //случайная карта
        paymentPage.fillMonthField(DataHelper.getCurrentMonth());       //текущий месяц
        paymentPage.fillYearField(DataHelper.getYear(0));       //текущий месяц
        paymentPage.fillCvcCvvField(DataHelper.getCVC_CVV());
        paymentPage.clickContinueButton();
    }
}
