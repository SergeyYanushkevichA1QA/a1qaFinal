package by.a1qa.entity;

import by.a1qa.service.Utils;

import java.net.http.HttpResponse;

public abstract class Response<T> {

    private final int statusCode;
    private final String body;
    private T instance;

    public Response(HttpResponse<String> response) {
        statusCode = response.statusCode();
        body = response.body();
    }

    public T getObject() {
        if (instance == null) {
            instance = getInstance();
        }
        return instance;
    }

    protected abstract T getInstance();

    public boolean isJSON() {
        return Utils.isJson(getBody());
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getBody() {
        return body;
    }

}