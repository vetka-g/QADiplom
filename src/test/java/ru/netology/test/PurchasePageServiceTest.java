package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SqlHelper;
import ru.netology.page.MainPage;
import ru.netology.page.PurchasePage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PurchasePageServiceTest {
    private static String host = System.getProperty("db.host");
    MainPage mainPage = open(host, MainPage.class);

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @AfterEach
    public void cleanBase() {
        SqlHelper.clearDB();
    }

    @Test
    void paymentWithApprovedCardAndValidData() {
        PurchasePage purchasePage = mainPage.purchasePage();
        var cardInfo = DataHelper.getApprovedCard();
        purchasePage.insertCardData(cardInfo);
        purchasePage.waitNotificationApproved();
        assertEquals("APPROVED", SqlHelper.getPaymentStatus());
    }

    @Test
    void paymentWithDeclinedCardAndValidData() {
        PurchasePage purchasePage = mainPage.purchasePage();
        var cardInfo = DataHelper.getDeclinedCard();
        purchasePage.insertCardData(cardInfo);
        purchasePage.waitNotificationFailure();
        assertEquals("DECLINED", SqlHelper.getPaymentStatus());
    }

    @Test
    void paymentWithEmptyFormFields() {
        PurchasePage purchasePage = mainPage.purchasePage();
        var cardInfo = DataHelper.getEmptyCard();
        purchasePage.insertCardData(cardInfo);
        purchasePage.waitNotificationWrongFormatError();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void paymentWithCardNotFromDatabaseAndValidData() {
        PurchasePage purchasePage = mainPage.purchasePage();
        var cardInfo = DataHelper.getCardNotInDatabase();
        purchasePage.insertCardData(cardInfo);
        purchasePage.waitNotificationFailure();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void paymentWithZeroCardNumberAndValidData() {
        PurchasePage purchasePage = mainPage.purchasePage();
        var cardInfo = DataHelper.getZeroNumberCard();
        purchasePage.insertCardData(cardInfo);
        purchasePage.waitNotificationWrongFormat();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void paymentWithInvalidCardNumberAndValidData() {
        PurchasePage purchasePage = mainPage.purchasePage();
        var cardInfo = DataHelper.getInvalidNumberCard();
        purchasePage.insertCardData(cardInfo);
        purchasePage.waitNotificationWrongFormat();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void paymentWithoutMonthAndValidData() {
        PurchasePage purchasePage = mainPage.purchasePage();
        var cardInfo = DataHelper.getCardWithoutMonth();
        purchasePage.insertCardData(cardInfo);
        purchasePage.waitNotificationRequiredField();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void paymentWithPreviousMonthAndValidData() {
        PurchasePage purchasePage = mainPage.purchasePage();
        var cardInfo = DataHelper.getCardWithPreviousMonth();
        purchasePage.insertCardData(cardInfo);
        purchasePage.waitNotificationCardExpired();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void paymentWithInvalidMonthAndValidData() {
        PurchasePage purchasePage = mainPage.purchasePage();
        var cardInfo = DataHelper.getCardInvalidMonth();
        purchasePage.insertCardData(cardInfo);
        purchasePage.waitNotificationInvalidCardExpirationDate();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void paymentWithoutYearAndValidData() {
        PurchasePage purchasePage = mainPage.purchasePage();
        var cardInfo = DataHelper.getCardWithoutYear();
        purchasePage.insertCardData(cardInfo);
        purchasePage.waitNotificationRequiredField();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void paymentWithExpiredYearAndValidData() {
        PurchasePage purchasePage = mainPage.purchasePage();
        var cardInfo = DataHelper.getCardWithPreviousYear();
        purchasePage.insertCardData(cardInfo);
        purchasePage.waitNotificationCardExpired();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void paymentWithInvalidYearAndValidData() {
        PurchasePage purchasePage = mainPage.purchasePage();
        var cardInfo = DataHelper.getCardWithInvalidYear();
        purchasePage.insertCardData(cardInfo);
        purchasePage.waitNotificationInvalidCardExpirationDate();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void paymentWithoutOwnerAndValidData() {
        PurchasePage purchasePage = mainPage.purchasePage();
        var cardInfo = DataHelper.getCardWithoutOwner();
        purchasePage.insertCardData(cardInfo);
        purchasePage.waitNotificationRequiredField();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void paymentWithOwnerInCyrillicSymbolsAndValidData() {
        PurchasePage purchasePage = mainPage.purchasePage();
        var cardInfo = DataHelper.getCardOwnerInCyrillicSymbols();
        purchasePage.insertCardData(cardInfo);
        purchasePage.waitNotificationWrongFormat();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void paymentOwnerWithNumbersAndValidData() {
        PurchasePage purchasePage = mainPage.purchasePage();
        var cardInfo = DataHelper.getCardOwnerWithNumbers();
        purchasePage.insertCardData(cardInfo);
        purchasePage.waitNotificationWrongFormat();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void paymentOwnerInLowerCaseNameAndValidData() {
        PurchasePage purchasePage = mainPage.purchasePage();
        var cardInfo = DataHelper.getCardOwnerInLowerCaseName();
        purchasePage.insertCardData(cardInfo);
        purchasePage.waitNotificationWrongFormat();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void paymentWithoutCvcAndValidData() {
        PurchasePage purchasePage = mainPage.purchasePage();
        var cardInfo = DataHelper.getCardWithoutCvc();
        purchasePage.insertCardData(cardInfo);
        purchasePage.waitNotificationRequiredField();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void paymentWithZeroCvcAndValidData() {
        PurchasePage purchasePage = mainPage.purchasePage();
        var cardInfo = DataHelper.getCardWithZeroCvc();
        purchasePage.insertCardData(cardInfo);
        purchasePage.waitNotificationWrongFormat();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void paymentWithInvalidCvcAndValidData() {
        PurchasePage purchasePage = mainPage.purchasePage();
        var cardInfo = DataHelper.getCardWithInvalidCvc();
        purchasePage.insertCardData(cardInfo);
        purchasePage.waitNotificationWrongFormat();
        assertEquals("0", SqlHelper.getOrderCount());
    }
}
