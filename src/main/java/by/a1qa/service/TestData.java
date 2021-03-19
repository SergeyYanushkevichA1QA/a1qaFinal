package by.a1qa.service;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import org.openqa.selenium.Cookie;

import java.util.*;

public class TestData {
    private static ISettingsFile testdata = new JsonSettingsFile("testdata.json");

    public static Object getValue(String value) {
        AqualityServices.getLogger().info("Read value '" + value + "' from test data");
        return testdata.getValue(value);
    }

    public static String getWebUsername() {
        return getValue("/web/username").toString();
    }

    public static String getWebPassword() {
        return getValue("/web/password").toString();
    }

    public static String getDBUsername() {
        return getValue("/db/username").toString();
    }

    public static String getDBPassword() {
        return getValue("/db/password").toString();
    }

    public static Cookie getCookie(String token) {
        Map<String, String> newCookies = APIUtils.getCommonParams();
        newCookies.put("token", token);
        List<Cookie> cookies = new ArrayList<>();
        for (Map.Entry<String, String> newCookie : newCookies.entrySet()) {
            cookies.add(new Cookie(newCookie.getKey(), newCookie.getValue()));
        }
        return cookies.get(0);
    }
}
