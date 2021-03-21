package by.a1qa.service;

import aquality.selenium.browser.AqualityServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;

public class Utils {


    public static ObjectNode getObjectNode(String json) {
        ObjectNode node = null;
        try {
            node = new ObjectMapper().readValue(json, ObjectNode.class);
        } catch (IOException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
        return node;
    }

    public static boolean isJson(String content) {
        AqualityServices.getLogger().info("Check if string is JSON");
        try {
            new ObjectMapper().readTree(content);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static String toJSONString(Object object) {
        AqualityServices.getLogger().info("Write " + object.getClass().getSimpleName() + " to JSON string");
        String result = "{}";
        try {
            result = new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
        return result;
    }

    public static <T> T readObjectFromJSON(String json, Class<T> objectClass) {
        AqualityServices.getLogger().info("Read " + objectClass.getSimpleName() + " from JSON string");
        T object = null;
        try {
            object = new ObjectMapper().readValue(json, objectClass);
        } catch (IOException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
        return object;
    }
}