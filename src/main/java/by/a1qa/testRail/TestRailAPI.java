package by.a1qa.testRail;

import by.a1qa.testRail.gurock.APIClient;
import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

import by.a1qa.testRail.gurock.APIException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestRailAPI {

    private static APIClient client;
    private static ISettingsFile environment = new JsonSettingsFile("settings.json");

    private static APIClient getClient() {
        if (client == null) {
            AqualityServices.getLogger().info("Logging TestRail");
            client = new APIClient(environment.getValue("/TRurl").toString());
            client.setUser(environment.getValue("/TRuser").toString());
            client.setPassword(environment.getValue("/TRpassword").toString());
        }
        return client;
    }

    private static int getId(JSONObject object) {
        return Integer.parseInt(object.get("id").toString());
    }

    public static int getProjectId() {
        return (int) environment.getValue("/TRprojectId");
    }

    public static int createSuite(int projectId, String name) {
        int id = 0;
        Map<String, Object> data = new HashMap<>();
        data.put("name", name);
        try {
            JSONObject suite = (JSONObject) getClient().sendPost("add_suite/" + projectId, data);
            id = getId(suite);
        } catch (IOException | APIException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
        return id;
    }

    public static int createSection(int projectId, int suiteId, String name) {
        int id = 0;
        Map<String, Object> data = new HashMap<>();
        data.put("suite_id", suiteId);
        data.put("name", name);
        try {
            JSONObject section = (JSONObject) getClient().sendPost("add_section/" + projectId, data);
            id = getId(section);
        } catch (IOException | APIException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
        return id;
    }

    public static int createTestCase(int sectionId, String name) {
        int id = 0;
        Map<String, Object> data = new HashMap<>();
        data.put("title", name);
        try {
            JSONObject testCase = (JSONObject) getClient().sendPost("add_case/" + sectionId, data);
            id = getId(testCase);
        } catch (IOException | APIException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
        return id;
    }

    public static int createRun(int projectId, int suiteId, String name) {
        int id = 0;
        Map<String, Object> data = new HashMap<>();
        data.put("suite_id", suiteId);
        data.put("name", name);
        try {
            JSONObject run = (JSONObject) getClient().sendPost("add_run/" + projectId, data);
            id = getId(run);
        } catch (IOException | APIException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
        return id;
    }

    public static void addAttachmentToResult(int resultId, String path) {
        try {
            getClient().sendPost("add_attachment_to_result/" + resultId, path);
        } catch (IOException | APIException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
    }

    public static int addResult(int runId, int caseId, int statusId) {
        int id = 0;
        TestRailStatus testRailStatus = TestRailStatus.getTestRailStatus(statusId);
        Map<String, Object> data = new HashMap<>();
        data.put("status_id", testRailStatus.getId());
        data.put("comment", testRailStatus.getName());
        try {
            JSONObject result = (JSONObject) getClient().sendPost("add_result_for_case/" + runId + "/" + caseId, data);
            id = getId(result);
        } catch (IOException | APIException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
        return id;
    }

    public static void deleteRun(int runId) {
        Map<String, Object> data = new HashMap<>();
        try {
            getClient().sendPost("delete_run/" + runId, data);
        } catch (IOException | APIException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
    }

    public static void deleteSuite(int suiteId) {
        Map<String, Object> data = new HashMap<>();
        try {
            getClient().sendPost("delete_suite/" + suiteId, data);
        } catch (IOException | APIException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
    }

    public static void deleteRunSuite(int runId, int suiteId) {
        deleteRun(runId);
        deleteSuite(suiteId);
    }
}
