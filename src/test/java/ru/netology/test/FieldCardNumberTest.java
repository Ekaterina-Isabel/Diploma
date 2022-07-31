package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SqlDataProvider;
import ru.netology.page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertNull;
import static ru.netology.data.DataHelper.cardNumberAll0;
import static ru.netology.data.DataHelper.cardNumberInvalid;
import static ru.netology.data.SqlDataProvider.clearTables;

public class FieldCardNumberTest {

    private PaymentPage paymentPage;

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setup() {
        open("http://localhost:8080/");
        paymentPage = new PaymentPage();
    }

    @AfterEach
    public void cleanTables() {
        clearTables();
    }

    @DisplayName("1.2.1. Payment for the tour by debit card, short card number")
    @Test
    void shouldNotSubmitApplicationWrongFormatShortCardNumber() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillCardNumberField(DataHelper.getCardNumberSign15());
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationWrongFormat();
        assertNull(new SqlDataProvider().getPaymentStatus());
    }

    @DisplayName("2.2.1. Payment for the tour by credit card, short card number")
    @Test
    void shouldNotSubmitApplicationWrongFormatShortCreditCardNumber() {
        paymentPage.openCreditCardPaymentPage();
        paymentPage.fillCardNumberField(DataHelper.getCardNumberSign15());
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationWrongFormat();
        assertNull(new SqlDataProvider().getCreditRequestStatus());
    }

    @DisplayName("1.2.2. Payment for the tour by debit card, empty card number input")
    @Test
    void shouldNotSubmitApplicationEmptyInput() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillCardNumberField("");
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationRequiredField();
        assertNull(new SqlDataProvider().getPaymentStatus());
    }

    @DisplayName("2.2.2. Payment for the tour by credit card, empty card number input")
    @Test
    void shouldNotSubmitApplicationCreditCardEmptyInput() {
        paymentPage.openCreditCardPaymentPage();
        paymentPage.fillCardNumberField("");
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationRequiredField();
        assertNull(new SqlDataProvider().getCreditRequestStatus());
    }

    @DisplayName("1.2.3. Payment for the tour by debit card, long card number")
    @Test
    void shouldNotSubmitApplicationLongCardNumber() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillCardNumberField(DataHelper.getCardNumberSign17());
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotification();
        assertNull(new SqlDataProvider().getPaymentStatus());
    }

    @DisplayName("2.2.3. Payment for the tour by credit card, long card number")
    @Test
    void shouldNotSubmitApplicationLongCreditCardNumber() {
        paymentPage.openCreditCardPaymentPage();
        paymentPage.fillCardNumberField(DataHelper.getCardNumberSign17());
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotification();
        assertNull(new SqlDataProvider().getCreditRequestStatus());
    }

    @DisplayName("1.2.4. Payment for the tour by debit card, card number is all 0")
    @Test
    void shouldNotSubmitApplicationCardAll0() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillCardNumberField(cardNumberAll0);
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationWrongFormat();
        assertNull(new SqlDataProvider().getPaymentStatus());
    }

    @DisplayName("2.2.4. Payment for the tour by credit card, card number is all 0")
    @Test
    void shouldNotSubmitApplicationCreditCardAll0() {
        paymentPage.openCreditCardPaymentPage();
        paymentPage.fillCardNumberField(cardNumberAll0);
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationWrongFormat();
        assertNull(new SqlDataProvider().getCreditRequestStatus());
    }

    @DisplayName("1.2.5. Payment for the tour by debit card, invalid card number")
    @Test
    void shouldNotSubmitApplicationInvalidCardNumber() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillCardNumberField(cardNumberInvalid);
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationWrongFormat();
        assertNull(new SqlDataProvider().getPaymentStatus());
    }

    @DisplayName("2.2.5. Payment for the tour by credit card, invalid card number")
    @Test
    void shouldNotSubmitApplicationInvalidCreditCardNumber() {
        paymentPage.openCreditCardPaymentPage();
        paymentPage.fillCardNumberField(cardNumberInvalid);
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationWrongFormat();
        assertNull(new SqlDataProvider().getCreditRequestStatus());
    }

    private void fillOtherFieldsByValidInfo() {
        paymentPage.fillMonthField(DataHelper.getCurrentMonth());       //текущий месяц
        paymentPage.fillYearField(DataHelper.getYear(0));        //текущий год
        paymentPage.fillOwnerField(DataHelper.getOwnerFullNameEn());
        paymentPage.fillCvcCvvField(DataHelper.getCVC_CVV());
        paymentPage.clickContinueButton();
    }
}
