package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import static org.junit.jupiter.api.Assertions.*;

class GeoServiceImplTest {

    @Test
    void testByIpLocationCity() {
        GeoService geoServiceImpl = new GeoServiceImpl();
        String ip = GeoServiceImpl.MOSCOW_IP;
        Location expected = geoServiceImpl.byIp(ip);
        Country expectedCountry = expected.getCountry();
        Country actualCountry = Country.RUSSIA;
        assertEquals(expectedCountry, actualCountry);

    }
}