package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {


    public static String getShiftedMonth() {
        int shift = (int) (Math.random() * 10);
        return LocalDate.now().plusMonths(shift).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getShiftedYear(int yearCount) {
        return LocalDate.now().plusYears(yearCount).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static CardInfo getApprovedCard() {
        Faker faker = new Faker();
        String owner = faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase();
        String month = getShiftedMonth();
        String year = getShiftedYear(1);
        String cvc = faker.number().digits(3);
        return new CardInfo("4444444444444441", month, year, owner, cvc);
    }

    public static CardInfo getDeclinedCard() {
        Faker faker = new Faker();
        String owner = faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase();
        String month = getShiftedMonth();
        String year = getShiftedYear(1);
        String cvc = faker.number().digits(3);
        return new CardInfo("4444444444444442", month, year, owner, cvc);
    }

    public static CardInfo getEmptyCard() {

        return new CardInfo("", "", "", "", "");
    }

    public static CardInfo getCardNotInDatabase() {
        Faker faker = new Faker();
        String owner = faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase();
        String month = getShiftedMonth();
        String year = getShiftedYear(1);
        String cvc = faker.number().digits(3);
        return new CardInfo("4444444444444444", month, year, owner, cvc);
    }

    public static CardInfo getZeroNumberCard() {
        Faker faker = new Faker();
        String owner = faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase();
        String month = getShiftedMonth();
        String year = getShiftedYear(1);
        String cvc = faker.number().digits(3);
        return new CardInfo("0000000000000000", month, year, owner, cvc);
    }

    public static CardInfo getInvalidNumberCard() {
        Faker faker = new Faker();
        String owner = faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase();
        String month = getShiftedMonth();
        String year = getShiftedYear(1);
        String cvc = faker.number().digits(3);
        String number = faker.number().digits(10);
        return new CardInfo(number, month, year, owner, cvc);
    }

    public static CardInfo getCardWithoutMonth() {
        Faker faker = new Faker();
        String owner = faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase();
        String year = getShiftedYear(1);
        String cvc = faker.number().digits(3);
        return new CardInfo("4444444444444441", "", year, owner, cvc);
    }

    public static CardInfo getCardWithPreviousMonth() {
        Faker faker = new Faker();
        String owner = faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase();
        String month = LocalDate.now().minusMonths(1).format(DateTimeFormatter.ofPattern("MM"));
        String year = getShiftedYear(0);
        String cvc = faker.number().digits(3);
        return new CardInfo("4444444444444441", month, year, owner, cvc);
    }

    public static CardInfo getCardInvalidMonth() {
        Faker faker = new Faker();
        String owner = faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase();
        String year = getShiftedYear(1);
        String cvc = faker.number().digits(3);
        return new CardInfo("4444444444444441", "20", year, owner, cvc);
    }

    public static CardInfo getCardWithoutYear() {
        Faker faker = new Faker();
        String owner = faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase();
        String month = getShiftedMonth();
        String cvc = faker.number().digits(3);
        return new CardInfo("4444444444444441", month, "", owner, cvc);
    }

    public static CardInfo getCardWithPreviousYear() {
        Faker faker = new Faker();
        String owner = faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase();
        String month = getShiftedMonth();
        String year = getShiftedYear(-1);
        String cvc = faker.number().digits(3);
        return new CardInfo("4444444444444441", month, year, owner, cvc);
    }

    public static CardInfo getCardWithInvalidYear() {
        Faker faker = new Faker();
        String owner = faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase();
        String month = getShiftedMonth();
        String year = getShiftedYear(27);
        String cvc = faker.number().digits(3);
        return new CardInfo("4444444444444441", month, year, owner, cvc);
    }

    public static CardInfo getCardWithoutOwner() {
        Faker faker = new Faker(new Locale("ru"));
        String month = getShiftedMonth();
        String year = getShiftedYear(1);
        String cvc = faker.number().digits(3);
        return new CardInfo("4444444444444441", month, year, "", cvc);
    }

    public static CardInfo getCardOwnerInCyrillicSymbols() {
        Faker faker = new Faker(new Locale("ru"));
        String owner = faker.name().firstName() + " " + faker.name().lastName();
        String month = getShiftedMonth();
        String year = getShiftedYear(1);
        String cvc = faker.number().digits(3);
        return new CardInfo("4444444444444441", month, year, owner, cvc);
    }

    public static CardInfo getCardOwnerWithNumbers() {
        Faker faker = new Faker();
        String owner = faker.name().firstName().toUpperCase() + " " + faker.number().digit();
        String month = getShiftedMonth();
        String year = getShiftedYear(1);
        String cvc = faker.number().digits(3);
        return new CardInfo("4444444444444441", month, year, owner, cvc);
    }

    public static CardInfo getCardOwnerInLowerCaseName() {
        Faker faker = new Faker();
        String owner = faker.name().firstName().toLowerCase() + " " + faker.name().lastName().toLowerCase();
        String month = getShiftedMonth();
        String year = getShiftedYear(1);
        String cvc = faker.number().digits(3);
        return new CardInfo("4444444444444441", month, year, owner, cvc);
    }

    public static CardInfo getCardWithoutCvc() {
        Faker faker = new Faker();
        String owner = faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase();
        String month = getShiftedMonth();
        String year = getShiftedYear(1);
        return new CardInfo("4444444444444441", month, year, owner, "");
    }

    public static CardInfo getCardWithZeroCvc() {
        Faker faker = new Faker();
        String holder = faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase();
        String month = getShiftedMonth();
        String year = getShiftedYear(1);
        return new CardInfo("4444444444444441", month, year, holder, "000");
    }

    public static CardInfo getCardWithInvalidCvc() {
        Faker faker = new Faker();
        String owner = faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase();
        String month = getShiftedMonth();
        String year = getShiftedYear(1);
        String cvc = faker.number().digits(2);
        return new CardInfo("4444444444444441", month, year, owner, cvc);
    }

    @Value
    public static class CardInfo {
        String cardNumber;
        String month;
        String year;
        String cardOwner;
        String cvc;
    }
}

