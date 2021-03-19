package by.a1qa.database;

import aquality.selenium.browser.AqualityServices;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataManager {

    public static void setTestProject(String projectName, String testName, String testMethod, String testEnv) {

        try {
            Statement statement = ConnectionManager.getConnection().createStatement();
            statement.execute(Query.addTest(projectName, testName, testMethod, testEnv));
        } catch (SQLException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
    }




}
