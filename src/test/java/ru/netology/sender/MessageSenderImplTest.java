package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import org.mockito.Mockito;
import ru.netology.i18n.LocalizationService;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MessageSenderImplTest {

    @org.junit.jupiter.api.Test
    void testMessageSederRus() {
        String ip = "172.000.000.000";
        String message = "Добро пожаловать";
        Location location;

        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(ip))
                .thenReturn(location = new Location("Moscow", Country.RUSSIA, null, 0));

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(location.getCountry())).thenReturn(message);

        MessageSender messageSenderImpl = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);
        String messageSender = messageSenderImpl.send(headers);

        String expected = localizationService.locale(Country.RUSSIA);

        Assertions.assertEquals(expected, messageSender);
    }

    @Test
    void testMessageSenderNotRus() {
        String ip = "96.000.000.000";
        String message = "Welcome";
        Location location;

        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(ip))
                .thenReturn(location = new Location("New York", Country.USA, null, 0));

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(location.getCountry())).thenReturn(message);

        MessageSender messageSenderImpl = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);
        String messageSender = messageSenderImpl.send(headers);

        String expected = localizationService.locale(Country.USA);

        Assertions.assertEquals(expected, messageSender);
    }
}