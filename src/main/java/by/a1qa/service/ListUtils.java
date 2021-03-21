package by.a1qa.service;

import by.a1qa.models.Test;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ListUtils {

    private static List<Test> sortByLocalDateTime(List<Test> tests) {
        return tests.stream()
                .sorted(Comparator.comparing(Test :: getStartTime).reversed())
                .collect(Collectors.toList());
    }

    public static List<Test> getSortedProjectList(List<Test> tests, int number) {
        return sortByLocalDateTime(tests).stream().limit(number).collect(Collectors.toList());
    }

}
