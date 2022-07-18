package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.DataHelper.cardNumberApproved;
import static ru.netology.data.DataHelper.cardNumberDeclined;

public class CardPaymentTest {

    private PaymentPage paymentPage;

    @BeforeEach
    void setup() {
        open("http://localhost:8080/");
        paymentPage = new PaymentPage();
    }

    @DisplayName("1.1. Payment for the tour by card, card with the status \"APPROVED\"")
    @Test
    void shouldApproveBuyingTourCardApproved() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillCardNumberField(cardNumberApproved);
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveSuccessNotification();
    }

    @DisplayName("1.1. Payment for the tour by credit card, card with the status \"APPROVED\"")
    @Test
    void shouldApproveBuyingTourCreditCardApproved() {
        paymentPage.openCreditCardPaymentPage();
        paymentPage.fillCardNumberField(cardNumberApproved);
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveSuccessNotification();
    }

    @DisplayName("1.2. Payment for the tour by card, card with the status \"DECLINED\"")
    @Test
    void shouldDeclinedBuyingTourCardDeclined() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillCardNumberField(cardNumberDeclined);
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotification();
    }

    @DisplayName("1.2. Payment for the tour by credit card, card with the status \"DECLINED\"")
    @Test
    void shouldDeclinedBuyingTourCreditCardDeclined() {
        paymentPage.openCreditCardPaymentPage();
        paymentPage.fillCardNumberField(cardNumberDeclined);
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotification();
    }

    @DisplayName("1.3. Payment for the tour by card, valid card number, boundary values of input fields \"Month\" and \"Year\"")
    @Test
    void shouldDeclinedBuyingTourValidCardNumber() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillCardNumberField(DataHelper.getCardNumberSign16());
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotification();
    }

    @DisplayName("1.3. Payment for the tour by credit card, valid card number, boundary values of input fields \"Month\" and \"Year\"")
    @Test
    void shouldDeclinedBuyingTourValidCreditCardNumber() {
        paymentPage.openCreditCardPaymentPage();
        paymentPage.fillCardNumberField(DataHelper.getCardNumberSign16());
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotification();
    }

    private void fillOtherFieldsByValidInfo() {
        paymentPage.fillMonthField(DataHelper.getCurrentMonth());       //текущий месяц
        paymentPage.fillYearField(DataHelper.getYear(0));        //текущий год
        paymentPage.fillOwnerField(DataHelper.getOwnerFullNameEn());
        paymentPage.fillCvcCvvField(DataHelper.getCVC_CVV());
        paymentPage.clickContinueButton();
    }
}
