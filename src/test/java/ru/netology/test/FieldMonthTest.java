package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SqlDataProvider;
import ru.netology.page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertNull;
import static ru.netology.data.DataHelper.*;
import static ru.netology.data.SqlDataProvider.clearTables;

public class FieldMonthTest {

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

    @DisplayName("1.3.1. Payment for the tour by debit card, short month number")
    @Test
    void shouldNotSubmitApplicationWrongFormatShortMonthNumber() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillMonthField(DataHelper.getOneNumber());
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationWrongFormat();
        assertNull(new SqlDataProvider().getPaymentStatus());
    }

    @DisplayName("2.3.1. Payment for the tour by credit card, short month number")
    @Test
    void shouldNotSubmitApplicationWrongFormatCreditCardShortMonthNumber() {
        paymentPage.openCreditCardPaymentPage();
        paymentPage.fillMonthField(DataHelper.getOneNumber());
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationWrongFormat();
        assertNull(new SqlDataProvider().getCreditRequestStatus());
    }

    @DisplayName("1.3.2. Payment for the tour by debit card, empty month number input")
    @Test
    void shouldNotSubmitApplicationEmptyInput() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillMonthField("");
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationWrongFormat();
        assertNull(new SqlDataProvider().getPaymentStatus());
    }

    @DisplayName("2.3.2. Payment for the tour by credit card, empty month number input")
    @Test
    void shouldNotSubmitApplicationCreditCardEmptyInput() {
        paymentPage.openCreditCardPaymentPage();
        paymentPage.fillMonthField("");
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationWrongFormat();
        assertNull(new SqlDataProvider().getCreditRequestStatus());
    }

    @DisplayName("1.3.3. Payment for the tour by debit card, month number is all 0")
    @Test
    void shouldNotSubmitApplicationMonthNumberAll0() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillMonthField(monthAndYearNumbersIsAll0);
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationInvalidCard();
        assertNull(new SqlDataProvider().getPaymentStatus());
    }

    @DisplayName("2.3.3. Payment for the tour by credit card, month number is all 0")
    @Test
    void shouldNotSubmitApplicationCreditCardMonthNumberAll0() {
        paymentPage.openCreditCardPaymentPage();
        paymentPage.fillMonthField(monthAndYearNumbersIsAll0);
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationInvalidCard();
        assertNull(new SqlDataProvider().getCreditRequestStatus());
    }

    @DisplayName("1.3.4. Payment for the tour by debit card, non-existent month number")
    @Test
    void shouldNotSubmitApplicationNonExistentMonthNumber() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillMonthField(nonExistentMonthNumber);
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationInvalidCard();
        assertNull(new SqlDataProvider().getPaymentStatus());
    }

    @DisplayName("2.3.4. Payment for the tour by credit card, non-existent month number")
    @Test
    void shouldNotSubmitApplicationCreditCardNonExistentMonthNumber() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillMonthField(nonExistentMonthNumber);
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationInvalidCard();
        assertNull(new SqlDataProvider().getCreditRequestStatus());
    }

    @DisplayName("1.3.5. Payment for the tour by debit card, invalid month number")
    @Test
    void shouldNotSubmitApplicationInvalidMonthNumber() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillMonthField(monthNumberInvalid);
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationWrongFormat();
        assertNull(new SqlDataProvider().getPaymentStatus());
    }

    @DisplayName("2.3.5. Payment for the tour by credit card, invalid month number")
    @Test
    void shouldNotSubmitApplicationCreditCardInvalidMonthNumber() {
        paymentPage.openCreditCardPaymentPage();
        paymentPage.fillMonthField(monthNumberInvalid);
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationWrongFormat();
        assertNull(new SqlDataProvider().getCreditRequestStatus());
    }

    @DisplayName("1.3.6. Payment for the tour by debit card, long month number")
    @Test
    void shouldNotSubmitApplicationLongMonthNumber() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillMonthField(longMonthNumber);
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotification();
        assertNull(new SqlDataProvider().getPaymentStatus());
    }

    @DisplayName("2.3.6. Payment for the tour by credit card, long month number")
    @Test
    void shouldNotSubmitApplicationCreditCardLongMonthNumber() {
        paymentPage.openCreditCardPaymentPage();
        paymentPage.fillMonthField(longMonthNumber);
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotification();
        assertNull(new SqlDataProvider().getCreditRequestStatus());
    }

    private void fillOtherFieldsByValidInfo() {
        paymentPage.fillCardNumberField(DataHelper.getCardNumberSign16());     //случайная карта
        paymentPage.fillYearField(DataHelper.getYear(2));        //год + 2
        paymentPage.fillOwnerField(DataHelper.getOwnerFullNameEn());
        paymentPage.fillCvcCvvField(DataHelper.getCVC_CVV());
        paymentPage.clickContinueButton();
    }
}
