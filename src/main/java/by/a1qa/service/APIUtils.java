package by.a1qa.service;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import by.a1qa.entity.TestsResponse;
import org.apache.http.client.methods.HttpPost;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class  APIUtils {

    private static ISettingsFile environment = new JsonSettingsFile("settings.json");
    private static final String urlMethod = environment.getValue("/urlMethod").toString();
    private static final String headerName = "content-type";
    private static final String headerValue = "application/json";
    private static final String token_get = "token/get";
    private static final String tests_get = "test/get/json";


    public static final Map<String, String> commonParams = new HashMap<String, String>() {

    };

    public static Map<String, String> getCommonParams() {
        return new HashMap<>(commonParams);
    }

    public static String tokenPost(String variant) throws IOException {
        Map<String, String> params = getCommonParams();
        params.put("variant", variant);
        return execute(token_get, params);
    }

    public static String testsPost(String projectNumber) throws IOException {
        Map<String, String> params = getCommonParams();
        params.put("projectId", projectNumber);
        String body = execute(tests_get, params);
        return body ;
    }

    private static String execute(HttpPost httpPost) throws IOException {
        String body;
        CloseableHttpClient client = HttpClients.createDefault();
        body = EntityUtils.toString(client.execute(httpPost).getEntity());
        return body;
    }

    protected static String execute(String method, Map<String, String> params) throws IOException {
        String parametersString = StringUtils.getParametersString(params);
        HttpEntity entity = EntityBuilder.create()
                .setContentType(ContentType.APPLICATION_FORM_URLENCODED)
                .setText(parametersString)
                .build();
        HttpPost httpPost = new HttpPost(urlMethod + method);
        httpPost.setEntity(entity);
        return execute(httpPost);
    }

    public static HttpRequest getTestsRequest(String path, String projectNumber)  {
        Map<String, String> params = getCommonParams();
        params.put("projectId", projectNumber);
        String parametersString = StringUtils.getParametersString(params);
        AqualityServices.getLogger().info("Send request: POST " + path);
        return HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(Utils.toJSONString(parametersString)))
                .uri(URI.create(urlMethod + path))
                .build();
    }

    public static HttpRequest getGetRequest(String path, String projectNumber) {
        Map<String, String> params = getCommonParams();
        params.put("projectId", projectNumber);
        String parametersString = StringUtils.getParametersString(params);
        AqualityServices.getLogger().info("Build request: GET " + path);
        return HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(parametersString))
                .uri(URI.create(urlMethod + path + "?" + parametersString))
                .build();
    }


    public static HttpResponse<String> getResponse(HttpRequest request) {
        AqualityServices.getLogger().info("Send request: " + request.toString());
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
        return response;
    }

    public static TestsResponse postTests(String projectNumber) {
        return new TestsResponse(getResponse(getGetRequest(tests_get, projectNumber)));
    }



}
