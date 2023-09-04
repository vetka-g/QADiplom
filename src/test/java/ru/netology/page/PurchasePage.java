package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PurchasePage {
    private SelenideElement header = $(byText("Оплата по карте"));
    private SelenideElement cardNumber = $(byText("Номер карты")).parent().$(".input__control");
    private SelenideElement month = $(byText("Месяц")).parent().$(".input__control");
    private SelenideElement year = $(byText("Год")).parent().$(".input__control");
    private SelenideElement cardOwner = $(byText("Владелец")).parent().$(".input__control");
    private SelenideElement cvc = $(byText("CVC/CVV")).parent().$(".input__control");
    private SelenideElement buttonContinue = $(byText("Продолжить"));
    private SelenideElement notificationSuccess = $(".notification_status_ok");
    private SelenideElement notificationError = $(".notification_status_error");
    private SelenideElement wrongFormatMessage = $(byText("Неверный формат"));
    private ElementsCollection wrongFormatError = $$(byText("Неверный формат"));
    private SelenideElement invalidCardExpirationDate = $(byText("Неверно указан срок действия карты"));
    private SelenideElement cardExpiredMessage = $(byText("Истёк срок действия карты"));
    private SelenideElement requiredFieldMessage = $(byText("Поле обязательно для заполнения"));

    public PurchasePage() {
        header.shouldBe(visible);
    }

    public void insertCardData(DataHelper.CardInfo cardInfo) {
        cardNumber.setValue(cardInfo.getCardNumber());
        month.setValue(cardInfo.getMonth());
        year.setValue(cardInfo.getYear());
        cardOwner.setValue(cardInfo.getCardOwner());
        cvc.setValue(cardInfo.getCvc());
        buttonContinue.click();
    }

    public void waitNotificationApproved() {
        notificationSuccess.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void waitNotificationFailure() {
        notificationError.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void waitNotificationWrongFormat() {
        wrongFormatMessage.shouldBe(visible);
    }

    public void waitNotificationInvalidCardExpirationDate() {
        invalidCardExpirationDate.shouldBe(visible);
    }

    public void waitNotificationCardExpired() {
        cardExpiredMessage.shouldBe(visible);
    }

    public void waitNotificationRequiredField() {
        requiredFieldMessage.shouldBe(visible);
    }

    public void waitNotificationWrongFormatError() {
        wrongFormatError.shouldHave(size(4));
        requiredFieldMessage.shouldBe(visible);
    }
}
