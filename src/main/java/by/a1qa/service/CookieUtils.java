package by.a1qa.service;

import aquality.selenium.browser.AqualityServices;
import org.openqa.selenium.Cookie;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CookieUtils {


    public static void addCookie(Cookie cookie) {
        AqualityServices.getLogger().info("Adding cookie " + cookie.getName() + ":" + cookie.getValue());
        AqualityServices.getBrowser().getDriver().manage().addCookie(cookie);
    }
}
