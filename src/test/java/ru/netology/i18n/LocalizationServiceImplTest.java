package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

import static org.junit.jupiter.api.Assertions.*;

class LocalizationServiceImplTest {

    @Test
    void testLocaleRussia() {
        LocalizationService localizationServiceImpl = new LocalizationServiceImpl();
        String message = "Добро пожаловать";
        Country country = Country.RUSSIA;
        String expected = localizationServiceImpl.locale(country);
        Assertions.assertEquals(expected, message);
    }

    @Test
    void testLocaleNotRussia() {

        LocalizationService localizationServiceImpl = new LocalizationServiceImpl();
        Country country = Country.USA;
        String message = "Welcome";

        String expected = localizationServiceImpl.locale(country);

        Assertions.assertEquals(expected, message);
    }
}