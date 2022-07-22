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

public class FieldYearTest {

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

    @DisplayName("1.4.1. Payment for the tour by debit card, short year number")
    @Test
    void shouldNotSubmitApplicationWrongFormatShortYearNumber() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillYearField(DataHelper.getOneNumber());
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationWrongFormat();
        assertNull(new SqlDataProvider().getPaymentStatus());
    }

    @DisplayName("2.4.1. Payment for the tour by credit card, short year number")
    @Test
    void shouldNotSubmitApplicationCreditCardWrongFormatShortYearNumber() {
        paymentPage.openCreditCardPaymentPage();
        paymentPage.fillYearField(DataHelper.getOneNumber());
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationWrongFormat();
        assertNull(new SqlDataProvider().getCreditRequestStatus());
    }

    @DisplayName("1.4.2. Payment for the tour by debit card, empty year number input")
    @Test
    void shouldNotSubmitApplicationEmptyInput() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillYearField("");
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationWrongFormat();
        assertNull(new SqlDataProvider().getPaymentStatus());
    }

    @DisplayName("2.4.2. Payment for the tour by credit card, empty year number input")
    @Test
    void shouldNotSubmitApplicationCreditCardEmptyInput() {
        paymentPage.openCreditCardPaymentPage();
        paymentPage.fillYearField("");
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationWrongFormat();
        assertNull(new SqlDataProvider().getCreditRequestStatus());
    }

    @DisplayName("1.4.3. Payment for the tour by debit card, year number is all 0")
    @Test
    void shouldNotSubmitApplicationYearNumberAll0() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillYearField(monthAndYearNumbersIsAll0);
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationCardExpired();
        assertNull(new SqlDataProvider().getPaymentStatus());
    }

    @DisplayName("2.4.3. Payment for the tour by credit credit card, year number is all 0")
    @Test
    void shouldNotSubmitApplicationCreditCardYearNumberAll0() {
        paymentPage.openCreditCardPaymentPage();
        paymentPage.fillYearField(monthAndYearNumbersIsAll0);
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationCardExpired();
        assertNull(new SqlDataProvider().getCreditRequestStatus());
    }

    @DisplayName("1.4.4. Payment for the tour by debit card, last year")
    @Test
    void shouldNotSubmitApplicationLastYear() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillYearField(getYear(-1));
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationCardExpired();
        assertNull(new SqlDataProvider().getPaymentStatus());
    }

    @DisplayName("2.4.4. Payment for the tour by credit card, last year")
    @Test
    void shouldNotSubmitApplicationCreditCardLastYear() {
        paymentPage.openCreditCardPaymentPage();
        paymentPage.fillYearField(getYear(-1));
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationCardExpired();
        assertNull(new SqlDataProvider().getCreditRequestStatus());
    }

    @DisplayName("1.4.5. Payment for the tour by debit card, invalid year number")
    @Test
    void shouldNotSubmitApplicationInvalidYearNumber() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillYearField(yearNumberInvalid);
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationWrongFormat();
        assertNull(new SqlDataProvider().getPaymentStatus());
    }

    @DisplayName("2.4.5. Payment for the tour by credit card, invalid year number")
    @Test
    void shouldNotSubmitApplicationCreditCardInvalidYearNumber() {
        paymentPage.openCreditCardPaymentPage();
        paymentPage.fillYearField(yearNumberInvalid);
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationWrongFormat();
        assertNull(new SqlDataProvider().getCreditRequestStatus());
    }

    @DisplayName("1.4.6. Payment for the tour by debit card, full year number")
    @Test
    void shouldNotSubmitApplicationFullYearNumber() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillYearField(DataHelper.getFullYearNumber(0));
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationCardExpired();
        assertNull(new SqlDataProvider().getPaymentStatus());
    }

    @DisplayName("2.4.6. Payment for the tour by credit card, full year number")
    @Test
    void shouldNotSubmitApplicationCreditCardFullYearNumber() {
        paymentPage.openCreditCardPaymentPage();
        paymentPage.fillYearField(DataHelper.getFullYearNumber(0));
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationCardExpired();
        assertNull(new SqlDataProvider().getCreditRequestStatus());
    }

    @DisplayName("1.4.7. Payment for the tour by debit card, year number is 6 more")
    @Test
    void shouldNotSubmitApplicationYearNumberIs6More() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillYearField(DataHelper.getYear(6));
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationInvalidCard();
        assertNull(new SqlDataProvider().getPaymentStatus());
    }

    @DisplayName("2.4.7. Payment for the tour by credit card, year number is 6 more")
    @Test
    void shouldNotSubmitApplicationCreditCardYearNumberIs6More() {
        paymentPage.openCreditCardPaymentPage();
        paymentPage.fillYearField(DataHelper.getYear(6));
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationInvalidCard();
        assertNull(new SqlDataProvider().getCreditRequestStatus());
    }

    private void fillOtherFieldsByValidInfo() {
        paymentPage.fillCardNumberField(DataHelper.getCardNumberSign16());     //случайная карта
        paymentPage.fillMonthField(DataHelper.getCurrentMonth());       //текущий месяц
        paymentPage.fillOwnerField(DataHelper.getOwnerFullNameEn());
        paymentPage.fillCvcCvvField(DataHelper.getCVC_CVV());
        paymentPage.clickContinueButton();
    }
}
