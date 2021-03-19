package by.a1qa.testRail;

import org.testng.ITestResult;

public enum TestRailStatus {

    PASSED(1), SKIPPED(4), RETEST(4), FAILED(5);

    private final int id;

    TestRailStatus(int id) {
        this.id = id;
    }
    
    static TestRailStatus getTestRailStatus(int statusId) {
        switch (statusId) {
            case ITestResult.SUCCESS:
                return PASSED;
            case ITestResult.FAILURE:
                return FAILED;
            case ITestResult.SKIP:
                return SKIPPED;
            default:
                return RETEST;
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return this.toString();
    }
}
