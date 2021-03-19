package by.a1qa.service;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.utilities.ISettingsFile;
import org.apache.hc.core5.net.URIBuilder;

import java.net.URISyntaxException;
import java.util.List;

public class AuthorizationUtils {


    public static String authorization(String url, String username, String password) {
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            uriBuilder.setUserInfo(username, password);
            url = uriBuilder.toString();
        } catch (URISyntaxException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
        return url;
    }
}
