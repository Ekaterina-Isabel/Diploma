package ru.netology.data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {
    public static final String cardNumberApproved = "4444 4444 4444 4441";
    public static final String cardNumberDeclined = "4444 4444 4444 4442";
    public static final String cardNumberAll0 = "0000 0000 0000 0000";
    public static final String cardNumberInvalid = "card!#$%&‘*+—/=?";
    public static final String monthAndYearNumbersIsAll0 = "00";
    public static final String nonExistentMonthNumber = "13";
    public static final String monthNumberInvalid = "y%";
    public static final String longMonthNumber = "011";
    public static final String yearNumberInvalid = "(U";
    public static final String ownerInvalid = "00()№%__9";
    public static final String cvcCvvIsAll0 = "000";
    public static final String cvcCvvInvalid = "y%_";
    public static final String longCvcCvvNumber = "1234";
    public static final String dbUrl = "jdbc:mysql://localhost:3306/app";
    public static final String user = "app";
    public static final String password = "pass";

    public static String getCardNumberSign15() {
        Faker faker = new Faker();
        return faker.number().digits(15);
    }

    public static String getCardNumberSign16() {
        Faker faker = new Faker();
        return faker.number().digits(16);
    }

    public static String getCardNumberSign17() {
        Faker faker = new Faker();
        return faker.number().digits(17);
    }

    public static String getCurrentMonth() {
        String month = LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
        return month;
    }

    public static String getOneNumber() {
        Faker faker = new Faker();
        return faker.number().digits(1);
    }

    public static String getTwoNumber() {
        Faker faker = new Faker();
        return faker.number().digits(2);
    }

    public static String getYear(int shiftYears) {
        return LocalDate.now().plusYears(shiftYears).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getFullYearNumber(int shiftYears) {
        return LocalDate.now().plusYears(shiftYears).format(DateTimeFormatter.ofPattern("yyyy"));
    }

    public static String getOwnerFullNameEn() {
        Faker faker = new Faker(new Locale("en"));
        return faker.name().fullName();
    }

    public static String getOwnerLastNameEn() {
        Faker faker = new Faker(new Locale("en"));
        return faker.name().lastName();
    }

    public static String getOwnerFirstNameEn() {
        Faker faker = new Faker(new Locale("en"));
        return faker.name().firstName();
    }

    public static String getOwnerLastNameRu() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.name().lastName();
    }

    public static String getOwnerFirstNameRu() {
        Faker faker = new Faker(new Locale("en"));
        return faker.name().firstName();
    }

    public static String getCVC_CVV() {
        Faker faker = new Faker();
        return faker.number().digits(3);
    }
}
