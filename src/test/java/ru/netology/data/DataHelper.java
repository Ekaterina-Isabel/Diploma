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
    //    public static final String correctMonth = "08";
    public static final String correctYear = "22";
    public static final String correctOwner = "Петров";
    public static final String correctCVC_CVV = "999";
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

    public static String getYear(int shiftYears) {
        String year = LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));

        if (shiftYears < 0) {
            LocalDate.now().minusYears(shiftYears).format(DateTimeFormatter.ofPattern("yy"));
        } else {
            LocalDate.now().plusYears(shiftYears).format(DateTimeFormatter.ofPattern("yy"));
        }
        return year;
    }

    public static String getOwner() {
        Faker faker = new Faker(new Locale("en"));
        return faker.name().fullName();
    }

    public static String getCVC_CVV() {
        Faker faker = new Faker();
        return faker.number().digits(3);
    }


//    public static String getDate(String locale) {
//        Faker faker = new Faker(new Locale(locale));
//        return faker.date().;
//    }
}
