package by.a1qa.forms;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import java.util.List;
import java.util.Objects;

public class ProjectsForm extends Form {
    private final MenuForm menuForm = new MenuForm();
    private final NewProjectForm newProjectForm = new NewProjectForm();

    private final ILabel lblBody = AqualityServices.getElementFactory().getLabel(By.xpath("//body"), "Body");
    private final ILabel lblVersion = AqualityServices.getElementFactory().getLabel(By.xpath("//p[@class='text-muted text-center footer-text']/span"), "Version");
    private final IButton btnAdd = AqualityServices.getElementFactory().getButton(By.xpath("//button[@class='btn btn-xs btn-primary pull-right']"),"Add button");

    private final By projectsLocator = By.xpath("//a[@class='list-group-item']");
    
    public ProjectsForm() {
        super(By.xpath("//div[@class='panel panel-default']"), "Projects page");
    }

    public boolean atPage() {
        return lblBody.state().isExist();
    }

    public String getVersion() {
        return lblVersion.getText();
    }

    public List<ILink> getProjects() {
        List<ILink> projects = AqualityServices.getElementFactory().findElements(projectsLocator, ElementType.LINK);
        return projects;
    }


    public String getIdOfProject(String project) {
        List<ILink> projects = getProjects();
        String id = null;
        for(ILink currProject: projects) {
            if(currProject.getText().equals(project)) {
                id = currProject.getHref();
            } else {
                AqualityServices.getLogger().warn("No such project");
            }
        }
        return id;
    }

    public void clickProjectButton(String projectName) {
        getProject(projectName).click();
    }

    private ILink getProject(String projectName) {
        return (ILink) AqualityServices.getElementFactory().findElements(projectsLocator, ElementType.LINK).stream()
                .filter(elem -> elem.getText().equals(projectName)).findAny().orElse(null);
    }

    public void clickAddButton() {
        btnAdd.click();
    }

    public MenuForm getMenuForm() {
        return menuForm;
    }

    public NewProjectForm getNewProjectForm() {
        return newProjectForm;
    }

    public boolean waitNewProjectFormForDisplayed() {
        return getNewProjectForm().state().waitForDisplayed();
    }

    public boolean waitNewProjectFormForNotDisplayed() {
        return newProjectForm.state().waitForNotDisplayed();
    }

    public boolean isProjectDisplayed(String projectName) {
        return Objects.nonNull(getProject(projectName));
    }


}
