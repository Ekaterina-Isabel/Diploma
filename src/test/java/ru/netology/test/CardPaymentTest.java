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

    @DisplayName("Payment for the tour by card, card with the status \"APPROVED\"")
    @Test
    void shouldApproveBuyingTourCardApproved() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillCardNumberField(cardNumberApproved);
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveSuccessNotification();
    }

    @DisplayName("Payment for the tour by card, card with the status \"DECLINED\"")
    @Test
    void shouldDeclinedBuyingTourCardDeclined() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillCardNumberField(cardNumberDeclined);
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotification();
    }

    @DisplayName("Payment for the tour by card, valid card number")
    @Test
    void shouldDeclinedBuyingTourValidCardNumber() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillCardNumberField(DataHelper.getCardNumberSign16());
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotification();
    }

    private void fillOtherFieldsByValidInfo() {
        paymentPage.fillMonthField(DataHelper.getCurrentMonth());
        paymentPage.fillYear(DataHelper.getYear(0));
        paymentPage.fillOwner(DataHelper.getOwner());
        paymentPage.fillCvc_cvvField(DataHelper.getCVC_CVV());
        paymentPage.clickContinueButton();
    }
}
