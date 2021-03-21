package by.a1qa.forms;

import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import by.a1qa.models.Test;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;

public class TestPage extends Form {

    private static final String infoLoc = "//div[@class='col-md-4']";
    private static final String imageSeparator = "data:image/png;base64,";

    private final ITextBox infoBox = super.getElementFactory().getTextBox(By.cssSelector(infoLoc),
            "Common info textbox");
    private final ITextBox projectNameBox = infoBox.findChildElement(
            By.cssSelector(".list-group-item:nth-child(1) > p[class*='list-group-item-text']"), ElementType.TEXTBOX);
    private final ITextBox testNameBox = infoBox.findChildElement(
            By.cssSelector(".list-group-item:nth-child(2) > p[class*='list-group-item-text']"), ElementType.TEXTBOX);
    private final ITextBox methodNameBox = infoBox.findChildElement(
            By.cssSelector(".list-group-item:nth-child(3) > p[class*='list-group-item-text']"), ElementType.TEXTBOX);
    private final ITextBox environmentBox = infoBox.findChildElement(
            By.cssSelector(".list-group-item:nth-child(6) > p[class*='list-group-item-text']"), ElementType.TEXTBOX);
    private final ITextBox logsAttachmentsBox = super.getElementFactory()
            .getTextBox(By.cssSelector("div[class*='col-md-8']"), "Logs and attachments box");
    private final ITextBox imageBox = logsAttachmentsBox.findChildElement(By.xpath("//img[@class='thumbnail']"),
            ElementType.TEXTBOX);

    public TestPage() {
        super(By.xpath(infoLoc), "Test page");
    }

    public Test getTest() {
        Test test = new Test();
        test.setName(getTestName());
        test.setMethod(getMethodName());
        test.setEnv(getEnvironment());
        return test;
    }

    public String getProjectName() {
        return projectNameBox.getText();
    }

    public String getTestName() {
        return testNameBox.getText();
    }

    public String getMethodName() {
        return methodNameBox.getText();
    }

    public String getEnvironment() {
        return environmentBox.getText();
    }

    public String getImageContent() {
        return StringUtils.substringAfter(imageBox.getAttribute("src"), imageSeparator);
    }

//    public byte[] getDecodedImageContent() {
 //       return Base64.decodeBase64(getImageContent());
 //   }
}