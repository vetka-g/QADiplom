package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SqlHelper;
import ru.netology.page.CreditPage;
import ru.netology.page.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditPageServiceTest {

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
    void creditWithApprovedCardAndValidData() {
        CreditPage creditPage = mainPage.creditPage();
        var cardInfo = DataHelper.getApprovedCard();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationApproved();
        assertEquals("APPROVED", SqlHelper.getCreditStatus());
    }

    @Test
    void creditWithDeclinedCardAndValidData() {
        CreditPage creditPage = mainPage.creditPage();
        var cardInfo = DataHelper.getDeclinedCard();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationFailure();
        assertEquals("DECLINED", SqlHelper.getCreditStatus());
    }

    @Test
    void creditWithEmptyFormFields() {
        CreditPage creditPage = mainPage.creditPage();
        var cardInfo = DataHelper.getEmptyCard();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationWrongFormatError();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void creditWithCardNotFromDatabaseAndValidData() {
        CreditPage creditPage = mainPage.creditPage();
        var cardInfo = DataHelper.getCardNotInDatabase();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationFailure();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void creditWithZeroCardNumberAndValidData() {
        CreditPage creditPage = mainPage.creditPage();
        var cardInfo = DataHelper.getZeroNumberCard();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationWrongFormat();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void creditWithInvalidCardNumberAndValidData() {
        CreditPage creditPage = mainPage.creditPage();
        var cardInfo = DataHelper.getInvalidNumberCard();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationWrongFormat();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void creditWithoutMonthAndValidData() {
        CreditPage creditPage = mainPage.creditPage();
        var cardInfo = DataHelper.getCardWithoutMonth();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationRequiredField();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void creditWithPreviousMonthAndValidData() {
        CreditPage creditPage = mainPage.creditPage();
        var cardInfo = DataHelper.getCardWithPreviousMonth();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationCardExpired();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void creditWithInvalidMonthAndValidData() {
        CreditPage creditPage = mainPage.creditPage();
        var cardInfo = DataHelper.getCardInvalidMonth();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationInvalidCardExpirationDate();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void creditWithoutYearAndValidData() {
        CreditPage creditPage = mainPage.creditPage();
        var cardInfo = DataHelper.getCardWithoutYear();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationRequiredField();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void creditWithExpiredYearAndValidData() {
        CreditPage creditPage = mainPage.creditPage();
        var cardInfo = DataHelper.getCardWithPreviousYear();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationCardExpired();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void creditWithInvalidYearAndValidData() {
        CreditPage creditPage = mainPage.creditPage();
        var cardInfo = DataHelper.getCardWithInvalidYear();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationInvalidCardExpirationDate();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void creditWithoutOwnerAndValidData() {
        CreditPage creditPage = mainPage.creditPage();
        var cardInfo = DataHelper.getCardWithoutOwner();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationRequiredField();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void creditWithOwnerInCyrillicSymbolsAndValidData() {
        CreditPage creditPage = mainPage.creditPage();
        var cardInfo = DataHelper.getCardOwnerInCyrillicSymbols();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationWrongFormat();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void creditOwnerWithNumbersAndValidData() {
        CreditPage creditPage = mainPage.creditPage();
        var cardInfo = DataHelper.getCardOwnerWithNumbers();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationWrongFormat();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void creditOwnerInLowerCaseNameAndValidData() {
        CreditPage creditPage = mainPage.creditPage();
        var cardInfo = DataHelper.getCardOwnerInLowerCaseName();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationWrongFormat();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void creditWithoutCvcAndValidData() {
        CreditPage creditPage = mainPage.creditPage();
        var cardInfo = DataHelper.getCardWithoutCvc();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationRequiredField();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void creditWithZeroCvcAndValidData() {
        CreditPage creditPage = mainPage.creditPage();
        var cardInfo = DataHelper.getCardWithZeroCvc();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationWrongFormat();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void creditWithInvalidCvcAndValidData() {
        CreditPage creditPage = mainPage.creditPage();
        var cardInfo = DataHelper.getCardWithInvalidCvc();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationWrongFormat();
        assertEquals("0", SqlHelper.getOrderCount());
    }
}
