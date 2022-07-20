package ru.netology.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SqlDataProvider;
import ru.netology.page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertNull;
import static ru.netology.data.DataHelper.*;
import static ru.netology.data.SqlDataProvider.clearTables;

public class FieldCvcCvvTest {

    private PaymentPage paymentPage;

    @BeforeEach
    void setup() {
        open("http://localhost:8080/");
        paymentPage = new PaymentPage();
    }

    @AfterEach
    public void cleanTables() {
        clearTables();
    }

    @DisplayName("1.6.1. Payment for the tour by debit card, one number for CVC/CVV")
    @Test
    void shouldNotSubmitApplicationWrongFormatOneNumberForCvcCvv() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillCvcCvvField(DataHelper.getOneNumber());
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationWrongFormat();
        assertNull(new SqlDataProvider().getPaymentStatus());
    }

    @DisplayName("2.6.1. Payment for the tour by credit card, one number for CVC/CVV")
    @Test
    void shouldNotSubmitApplicationCreditCardWrongFormatOneNumberForCvcCvv() {
        paymentPage.openCreditCardPaymentPage();
        paymentPage.fillCvcCvvField(DataHelper.getOneNumber());
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationWrongFormat();
        assertNull(new SqlDataProvider().getCreditRequestStatus());
    }

    @DisplayName("1.6.2. Payment for the tour by debit card, two numbers for CVC/CVV")
    @Test
    void shouldNotSubmitApplicationWrongFormatTwoNumberForCvcCvv() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillCvcCvvField(DataHelper.getTwoNumber());
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationWrongFormat();
        assertNull(new SqlDataProvider().getPaymentStatus());
    }

    @DisplayName("2.6.2. Payment for the tour by credit card, two numbers for CVC/CVV")
    @Test
    void shouldNotSubmitApplicationCreditCardWrongFormatTwoNumberForCvcCvv() {
        paymentPage.openCreditCardPaymentPage();
        paymentPage.fillCvcCvvField(DataHelper.getTwoNumber());
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationWrongFormat();
        assertNull(new SqlDataProvider().getCreditRequestStatus());
    }

    @DisplayName("1.6.3. Payment for the tour by debit card, empty CVC/CVV number input")
    @Test
    void shouldNotSubmitApplicationEmptyInput() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillCvcCvvField("");
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationWrongFormat();
        assertNull(new SqlDataProvider().getPaymentStatus());
    }

    @DisplayName("2.6.3. Payment for the tour by credit card, empty CVC/CVV number input")
    @Test
    void shouldNotSubmitApplicationCreditCardEmptyInput() {
        paymentPage.openCreditCardPaymentPage();
        paymentPage.fillCvcCvvField("");
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationWrongFormat();
        assertNull(new SqlDataProvider().getCreditRequestStatus());
    }

    @DisplayName("1.6.4. Payment for the tour by debit card, CVC/CVV number is all 0")
    @Test
    void shouldNotSubmitApplicationCvcCvvAll0() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillCvcCvvField(cvcCvvIsAll0);
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationWrongFormat();
        assertNull(new SqlDataProvider().getPaymentStatus());
    }

    @DisplayName("2.6.4. Payment for the tour by credit card, CVC/CVV number is all 0")
    @Test
    void shouldNotSubmitApplicationCreditCardCvcCvvAll0() {
        paymentPage.openCreditCardPaymentPage();
        paymentPage.fillCvcCvvField(cvcCvvIsAll0);
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationWrongFormat();
        assertNull(new SqlDataProvider().getCreditRequestStatus());
    }

    @DisplayName("1.6.5. Payment for the tour by debit card, invalid CVC/CVV number")
    @Test
    void shouldNotSubmitApplicationInvalidCvcCvvNumber() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillCvcCvvField(cvcCvvInvalid);
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationWrongFormat();
        assertNull(new SqlDataProvider().getPaymentStatus());
    }

    @DisplayName("2.6.5. Payment for the tour by credit card, invalid CVC/CVV number")
    @Test
    void shouldNotSubmitApplicationCreditCardInvalidCvcCvvNumber() {
        paymentPage.openCreditCardPaymentPage();
        paymentPage.fillCvcCvvField(cvcCvvInvalid);
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationWrongFormat();
        assertNull(new SqlDataProvider().getCreditRequestStatus());
    }

    @DisplayName("1.6.6. Payment for the tour by debit card, long CVC/CVV number")
    @Test
    void shouldNotSubmitApplicationLongCvcCvvNumber() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillCvcCvvField(longCvcCvvNumber);
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotification();
        assertNull(new SqlDataProvider().getPaymentStatus());
    }

    @DisplayName("2.6.6. Payment for the tour by credit card, long CVC/CVV number")
    @Test
    void shouldNotSubmitApplicationCreditCardLongCvcCvvNumber() {
        paymentPage.openCreditCardPaymentPage();
        paymentPage.fillCvcCvvField(longCvcCvvNumber);
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotification();
        assertNull(new SqlDataProvider().getCreditRequestStatus());
    }

    private void fillOtherFieldsByValidInfo() {
        paymentPage.fillCardNumberField(DataHelper.getCardNumberSign16());     //случайная карта
        paymentPage.fillMonthField(DataHelper.getCurrentMonth());       //текущий месяц
        paymentPage.fillYearField(DataHelper.getYear(5));        //год + 5
        paymentPage.fillOwnerField(DataHelper.getOwnerFullNameEn());
        paymentPage.clickContinueButton();
    }
}
