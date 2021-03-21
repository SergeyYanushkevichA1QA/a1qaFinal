package by.a1qa.models;

import by.a1qa.entity.LocalDateTimeDeserializator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.LocalDateTime;
import java.util.Objects;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Test {

    private int id;
    private String name;
    private String status;
    private String method;
    private Integer projectId;
    private Integer sessionId;

    @JsonDeserialize(using = LocalDateTimeDeserializator.class)
    private LocalDateTime startTime;

    @JsonDeserialize(using = LocalDateTimeDeserializator.class)
    private LocalDateTime endTime;

    private String env;
    private Integer authorId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Test test = (Test) o;
        return name.equals(test.name) &&
                method.equals(test.method) &&
                status.equals(test.status) &&
                Objects.equals(startTime, test.startTime) &&
                Objects.equals(endTime, test.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, method, status, startTime, endTime);
    }
}
