package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.visible;
public class MainPage {
    private SelenideElement heading = $(byText("Путешествие дня"));
    private SelenideElement buyButton = $(byText("Купить"));
    private SelenideElement creditButton = $(byText("Купить в кредит"));

    public MainPage() {
        heading.shouldBe(visible);
    }

    public PurchasePage purchasePage() {
        buyButton.click();
        return new PurchasePage();
    }

    public CreditPage creditPage() {
        creditButton.click();
        return new CreditPage();
    }
}
