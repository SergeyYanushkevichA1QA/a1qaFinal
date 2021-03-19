package by.a1qa.database;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import by.a1qa.service.TestData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static ISettingsFile environment = new JsonSettingsFile("settings.json");


    private static final String url = environment.getValue("/urlDB").toString();

    private static Connection instance;

    public static void createConnection() {
        try {
            instance = DriverManager.getConnection(url, TestData.getDBUsername(), TestData.getDBPassword());
        } catch (SQLException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
    }

    public static Connection getConnection() {
        if (instance == null) {
            createConnection();
        }
        return instance;
    }

    public static void closeConnection() {
        try {
            getConnection().close();
        } catch (SQLException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
    }
}
