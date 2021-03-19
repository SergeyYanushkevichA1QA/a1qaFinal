package by.a1qa.forms;


import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;


public class MenuForm extends Form {

    private final ILink btnHome = AqualityServices.getElementFactory().getLink(By.linkText("Home"), "Home");


    public MenuForm() {
        super(By.xpath("/html"), "Menu");
    }


    public void clickHomeButton() {
        btnHome.click();
    }
}
