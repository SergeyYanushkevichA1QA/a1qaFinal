package test;

import aquality.selenium.browser.AlertActions;
import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import by.a1qa.database.ConnectionManager;
import by.a1qa.database.DataManager;
import by.a1qa.entity.TestsResponse;
import by.a1qa.forms.AuthorizationForm;
import by.a1qa.forms.ProjectForm;
import by.a1qa.forms.ProjectsForm;
import by.a1qa.service.*;
import org.apache.commons.httpclient.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class FinalTest {
    private static final AuthorizationForm authForm = new AuthorizationForm();
    private static final ProjectsForm projectsForm = new ProjectsForm();
    private static final ProjectForm projectForm = new ProjectForm();
    private final Browser browser = AqualityServices.getBrowser();
    private static ISettingsFile environment = new JsonSettingsFile("settings.json");
    private static final String url = environment.getValue("/url").toString();

    @BeforeClass
    public void setup() {
        browser.maximize();
        browser.goTo(AuthorizationUtils.authorization(url, TestData.getWebUsername(), TestData.getWebPassword()));
        browser.waitForPageToLoad();
        Assert.assertTrue(projectsForm.atPage(), "Projects page didn't open");
    }


    @Test
    public void FinalTest() throws IOException {
        Integer variant = 1;
        String token = APIUtils.tokenPost(variant.toString());
        Assert.assertNotNull(token, "Checking response token");
        CookieUtils.addCookie(TestData.getCookie(token));
        browser.refresh();
        Assert.assertEquals(variant.toString(), StringUtils.getNumberVersion(projectsForm.getVersion()), "Checking number of version");
        String project = "Nexage";
        String id = StringUtils.getProjectId(projectsForm.getIdOfProject(project));
        projectsForm.clickProjectButton(project);
        System.out.println(APIUtils.testsPost(id));
       // TestsResponse testsResponse = APIUtils.postTests(id);
        AqualityServices.getLogger().info("Checking status code");
     //   Assert.assertEquals(testsResponse.getStatusCode(), HttpStatus.SC_OK);
     //   AqualityServices.getLogger().info("Status code is " + testsResponse.getStatusCode());
        AqualityServices.getLogger().info("Checking json");
      //  Assert.assertTrue(testsResponse.isJSON());
      //  List<by.a1qa.models.Test> dbTests;

      //  Assert.assertThat(pageTests, is(sortedDescendingByDate()));
      //  Assert.assertEquals(dbTests, pageTests, "Tests received from the page isn't equal to tests received from a database");*/
        List<by.a1qa.models.Test> pageTests = projectForm.getTests();
        projectForm.getMenuForm().clickHomeButton();
        Assert.assertTrue(projectsForm.state().waitForDisplayed(), "Failed to return the projects page");
        projectsForm.clickAddButton();
        String newProject = "32245";
        Assert.assertTrue(projectsForm.waitNewProjectFormForDisplayed(), "Add project form isn't displayed");
        projectsForm.getNewProjectForm().setProjectName(newProject);
        projectsForm.getNewProjectForm().clickSaveProject();
        Assert.assertEquals(projectsForm.getNewProjectForm().getAlertText(), "Project " + newProject + " saved");
        browser.executeScript("closePopUp()");
        Assert.assertTrue(projectsForm.waitNewProjectFormForNotDisplayed(), "Add project form is displayed");
        browser.refresh();
        Assert.assertTrue(projectsForm.isProjectDisplayed(newProject), "Saved project isn't diplsayed in projects list");
        projectsForm.clickProjectButton(newProject);
        String testName = "newTest";  String testMethod = "Test"; String testEnv = "localhost";
     //   DataManager.setTestProject(newProject, testName, testMethod, testEnv);
      //  ConnectionManager.closeConnection();
    }

    @AfterClass
    public void tearDown() {
        browser.quit();
    }
}
