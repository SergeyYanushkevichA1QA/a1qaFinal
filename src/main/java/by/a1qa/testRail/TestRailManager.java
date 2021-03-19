package by.a1qa.testRail;

import by.a1qa.testRail.utils.ImageUtils;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import java.util.HashSet;
import java.util.Set;

import static by.a1qa.testRail.TestRailAPI.*;


public class TestRailManager {

    private final int suiteId;
    private final int sectionId;
    private final int runId;
    private final int caseId;

    public TestRailManager(String testName) {
        suiteId = createSuite(getProjectId(), testName);
        sectionId = createSection(getProjectId(), suiteId, testName);
        runId = createRun(getProjectId(), suiteId, testName);
        caseId = createTestCase(sectionId, testName);
    }

    public void addResult(ITestResult result) {
        int resultId = TestRailAPI.addResult(runId, caseId, result.getStatus());
        addAttachmentToResult(resultId, ImageUtils.getScreenshotPath(result.getMethod().getMethodName()));
    }

    public void addResults(ITestContext context) {
        Set<ITestResult> allResults = new HashSet<>();
        allResults.addAll(context.getPassedTests().getAllResults());
        allResults.addAll(context.getFailedTests().getAllResults());
        allResults.addAll(context.getSkippedTests().getAllResults());
        for (ITestResult result : allResults) {
            addResult(result);
        }
    }

    public void deleteRunSuite() {
        TestRailAPI.deleteRunSuite(runId, suiteId);
    }

    public void deleteScreenshots(ITestContext context) {
        for (ITestNGMethod method : context.getAllTestMethods()) {
            ImageUtils.deleteScreenshot(method.getMethodName());
        }
    }

    public void deleteAll(ITestContext context) {
        deleteScreenshots(context);
    }
}
