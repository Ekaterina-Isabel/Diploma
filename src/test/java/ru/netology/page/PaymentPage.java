package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class PaymentPage {
    private final SelenideElement buyButton = $(byText("Купить"));
    private final SelenideElement creditByButton = $(byText("Купить в кредит"));
    private final SelenideElement cardNumberField = $(".input [placeholder='0000 0000 0000 0000']");
    private final SelenideElement monthField = $(".input [placeholder='08']");
    private final SelenideElement yearField = $(".input [placeholder='22']");
    private final SelenideElement ownerField = $(byText("Владелец")).parent().$(".input__control");
    private final SelenideElement cvc_cvvField = $(".input [placeholder='999']");
    private final SelenideElement header = $(".heading.heading_size_m.heading_theme_alfa-on-white");
    private final SelenideElement continueButton = $(byText("Продолжить"));

    private final SelenideElement notification = $("div.notification_visible  div.notification__content");
    private final SelenideElement wrongFormat = $(byText("Неверный формат"));
    private final SelenideElement invalidCard = $(byText("Неверно указан срок действия карты"));
    private final SelenideElement cardExpired = $(byText("Истёк срок действия карты"));
    private final SelenideElement requiredField = $(byText("Поле обязательно для заполнения"));

    public void openCardPaymentPage() {
        buyButton.click();
    }

    public void openCreditCardPaymentPage() {
        creditByButton.click();
    }

    public void fillCardNumberField(String cardNumber) {
        cardNumberField.setValue(cardNumber);
    }

    public void fillMonthField(String month) {
        monthField.setValue(month);
    }

    public void fillYearField(String year) {
        yearField.setValue(year);
    }

    public void fillOwnerField(String owner) {
        ownerField.setValue(owner);
    }

    public void fillCvcCvvField(String cvc_cvv) {
        cvc_cvvField.setValue(cvc_cvv);
    }

    public void clickContinueButton() {
        continueButton.click();
    }

    public void shouldHaveSuccessNotification() {
        notification.shouldHave(Condition.text("Операция одобрена Банком."), Duration.ofSeconds(15));
    }

    public void shouldHaveErrorNotification() {
        notification.shouldHave(Condition.text("Ошибка! Банк отказал в проведении операции."), Duration.ofSeconds(15));
    }

    public void shouldHaveErrorNotificationWrongFormat() {
        wrongFormat.shouldHave(Condition.text("Неверный формат"));
    }

    public void shouldHaveErrorNotificationInvalidCard() {
        invalidCard.shouldHave(Condition.text("Неверно указан срок действия карты"));
    }

    public void shouldHaveErrorNotificationCardExpired() {
        cardExpired.shouldHave(Condition.text("Истёк срок действия карты"));
    }

    public void shouldHaveErrorNotificationRequiredField() {
        requiredField.shouldHave(Condition.text("Поле обязательно для заполнения"));
    }
}
