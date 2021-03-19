package by.a1qa.forms;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

public class NewProjectForm extends Form {
    private final ITextBox txbProjectName = AqualityServices.getElementFactory().getTextBox(By.id("projectName"), "Project name");
    private final IButton btnSaveProject = AqualityServices.getElementFactory().getButton(By.xpath("//button[@class='btn btn-primary'][@type='submit']"), "Save Project");
    private final ITextBox txbSaved = AqualityServices.getElementFactory().getTextBox(By.xpath("//div[@class='alert alert-success']"), "Project succesfully saved alert");
    private final String frameId = "addProjectFrame";

    public NewProjectForm() {
        super(By.xpath("//div[@class='modal-content']"), "NewProject form");
    }

    public void setProjectName(String text) {
        switchToFrame();
        txbProjectName.clearAndType(text);
        leaveFrame();
    }

    public void clickSaveProject() {
        switchToFrame();
        btnSaveProject.click();
        leaveFrame();
    }


    private void switchToFrame() {
        AqualityServices.getLogger().info("Switching to frame");
        AqualityServices.getBrowser().getDriver().switchTo().frame(frameId);
    }

    private void leaveFrame() {
        AqualityServices.getLogger().info("Leaving frame");
        AqualityServices.getBrowser().getDriver().switchTo().defaultContent();
    }

    public String getAlertText() {
        switchToFrame();
        String alertText = txbSaved.getText();
        leaveFrame();
        return alertText;
    }

}
