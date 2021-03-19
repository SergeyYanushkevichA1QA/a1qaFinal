package by.a1qa.testRail.utils;

import aquality.selenium.browser.AqualityServices;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

public class ImageUtils {

    private static final String resourcesPath = System.getProperty("user.dir") + "/src/test/resources/";
    private static final String imageFormat = "png";
    private static final String imagePathFormat = resourcesPath + "%s." + imageFormat;

    public static void saveScreenshot(String methodName) {
        byte[] screenshot = AqualityServices.getBrowser().getScreenshot();
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(screenshot)) {
            BufferedImage image = ImageIO.read(byteArrayInputStream);
            File file = new File(String.format(imagePathFormat, methodName));
            ImageIO.write(image, imageFormat, file);
        } catch (IOException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
    }

    public static String getScreenshotPath(String methodName) {
        return String.format(imagePathFormat, methodName);
    }

    public static void deleteScreenshot(String methodName) {
        File file = new File(String.format(imagePathFormat, methodName));
        file.delete();
    }
}
