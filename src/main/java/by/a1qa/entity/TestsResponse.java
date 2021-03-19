package by.a1qa.entity;

import by.a1qa.models.Test;
import by.a1qa.service.Utils;

import java.net.http.HttpResponse;

import java.util.Arrays;
import java.util.List;

public class TestsResponse extends Response<List<Test>> {

    public TestsResponse(HttpResponse<String> response) {
        super(response);
    }

    @Override
    protected List<Test> getInstance() {
        return Arrays.asList(Utils.readObjectFromJSON(getBody(), Test[].class));
    }
}
