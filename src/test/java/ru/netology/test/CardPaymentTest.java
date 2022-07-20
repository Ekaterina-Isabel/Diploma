package ru.netology.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SqlDataProvider;
import ru.netology.page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static ru.netology.data.DataHelper.cardNumberApproved;
import static ru.netology.data.DataHelper.cardNumberDeclined;
import static ru.netology.data.SqlDataProvider.clearTables;

public class CardPaymentTest {

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

    @DisplayName("1.1.1. Payment for the tour by debit card, card with the status \"APPROVED\"")
    @Test
    void shouldApproveBuyingTourCardApproved() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillCardNumberField(cardNumberApproved);
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveSuccessNotification();
        assertEquals("APPROVED", new SqlDataProvider().getPaymentStatus());
    }

    @DisplayName("2.1.1. Payment for the tour by credit card, card with the status \"APPROVED\"")
    @Test
    void shouldApproveBuyingTourCreditCardApproved() {
        paymentPage.openCreditCardPaymentPage();
        paymentPage.fillCardNumberField(cardNumberApproved);
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveSuccessNotification();
        assertEquals("APPROVED", new SqlDataProvider().getCreditRequestStatus());
    }

    @DisplayName("1.1.2. Payment for the tour by debit card, card with the status \"DECLINED\"")
    @Test
    void shouldDeclinedBuyingTourCardDeclined() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillCardNumberField(cardNumberDeclined);
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotification();
        assertEquals("DECLINED", new SqlDataProvider().getPaymentStatus());
    }

    @DisplayName("2.1.2. Payment for the tour by credit card, card with the status \"DECLINED\"")
    @Test
    void shouldDeclinedBuyingTourCreditCardDeclined() {
        paymentPage.openCreditCardPaymentPage();
        paymentPage.fillCardNumberField(cardNumberDeclined);
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotification();
        assertEquals("DECLINED", new SqlDataProvider().getCreditRequestStatus());
    }

    @DisplayName("1.1.3. Payment for the tour by debit card, valid card number, boundary values of input fields \"Month\" and \"Year\"")
    @Test
    void shouldDeclinedBuyingTourValidCardNumber() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillCardNumberField(DataHelper.getCardNumberSign16());
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotification();
        assertNull(new SqlDataProvider().getPaymentStatus());
    }

    @DisplayName("2.1.3. Payment for the tour by credit card, valid card number, boundary values of input fields \"Month\" and \"Year\"")
    @Test
    void shouldDeclinedBuyingTourValidCreditCardNumber() {
        paymentPage.openCreditCardPaymentPage();
        paymentPage.fillCardNumberField(DataHelper.getCardNumberSign16());
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotification();
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
