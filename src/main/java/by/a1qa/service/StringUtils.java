package by.a1qa.service;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class StringUtils {
    private static final String versionStr = "Version: ";

    public static String getParametersString(Map<String, String> params) {
        Set<String> values = params.entrySet().stream().map(o -> o.getKey() + "=" + o.getValue()).collect(Collectors.toSet());
        return String.join("?", values);
    }

    public static String getNumberVersion(String version) {
        String number = null;
        number = version.replace(versionStr, "");
        return number;
    }

    public static String getProjectId(String projectHref) {
        String id = null;
        id = projectHref.replace(projectHref.substring(0, projectHref.indexOf("=") + 1), "");
        return id;
    }
}
