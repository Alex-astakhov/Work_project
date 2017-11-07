package ashotLib;

import core.MethodsFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;


/**
 * Created by Alex Astakhov on 17.12.2016.
 */
public class MyAshot extends MethodsFactory {

    private String actualDir;
    private String expectedDir;
    private String markedImages;
    private String gifImages;

    private FileIO fileIO = new FileIO();

    public MyAshot(String absolutePath) {
        this.actualDir = absolutePath + "\\actual\\";
        this.expectedDir = absolutePath + "\\expected\\";
        this.markedImages = absolutePath + "\\markedImages\\";
        this.gifImages = absolutePath + "\\gifImages\\";
        fileIO.createDirectory(absolutePath);
        fileIO.createDirectory(actualDir);
        fileIO.createDirectory(expectedDir);
        fileIO.createDirectory(markedImages);
        fileIO.createDirectory(gifImages);
    }



    private Screenshot getExpectedScreenshot(String additionName){

        try {
            return new Screenshot(ImageIO.read(new File(expectedDir+getScreenshotName()+ additionName + ".png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean expectedIsPresent(String additionName){
        File file = new File(expectedDir+getScreenshotName()+ additionName +".png");
        return file.exists();
    }

    private Screenshot takeScreenshotWithoutJquery(Set<By> setIgnoredElements, boolean scroll){
        Robot bot = null;
        try {
            bot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        bot.mouseMove(0, 0);
        if (scroll)
            return new AShot().coordsProvider(new WebDriverCoordsProvider()).ignoredElements(setIgnoredElements).shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(driver());
        else
            return new AShot().coordsProvider(new WebDriverCoordsProvider()).ignoredElements(setIgnoredElements).takeScreenshot(driver());

    }

    public double findImageDifferenceWithoutJquery(Set<By> setIgnoredElements, double allowedError, boolean scroll){
        File actualFile = new File( actualDir + getScreenshotName()+".png");
        File expectedFile = new File(expectedDir + getScreenshotName()+".png");
        Screenshot actual = null;
        Screenshot expected = null;
        try {
            if (expectedIsPresent("")) {
                expected = getExpectedScreenshot("");
                actual = takeScreenshotWithoutJquery(setIgnoredElements, scroll);
                ImageIO.write(actual.getImage(), "png", actualFile);
            }
            else {
                expected = takeScreenshotWithoutJquery(setIgnoredElements, scroll);
                ImageIO.write(expected.getImage(), "png", expectedFile);
                return 0.0D;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        expected.setIgnoredAreas(actual.getIgnoredAreas());
        ImageDiff diff = new ImageDiffer().makeDiff(expected, actual);
        if (diff.getDiffSize() > allowedError){
            File diffFile = new File(markedImages+getScreenshotName()+".png");
            try {
                ImageIO.write(diff.getMarkedImage(), "png", diffFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            imgAttachment(actualFile);
            imgAttachment(expectedFile);
            imgAttachment(diffFile);
            makeGif(actualFile, expectedFile, diffFile, "");
        }
        return diff.getDiffSize() / (double) getPixelsCount() * 100;
    }

    public double findImageDifferenceWithoutJquery(Set<By> setIgnoredElements, double allowedError, boolean scroll, String additionToName){
        File actualFile = new File( actualDir + getScreenshotName() + additionToName+ ".png");
        File expectedFile = new File(expectedDir + getScreenshotName() + additionToName + ".png");
        Screenshot expected = null;
        Screenshot actual = null;
        try {
            if (expectedIsPresent(additionToName)) {
                expected = getExpectedScreenshot(additionToName);
                actual = takeScreenshotWithoutJquery(setIgnoredElements, scroll);
                ImageIO.write(actual.getImage(), "png", actualFile);
            }
            else {
                expected = takeScreenshotWithoutJquery(setIgnoredElements, scroll);
                ImageIO.write(expected.getImage(), "png", expectedFile);
                return 0.0D;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        expected.setIgnoredAreas(actual.getIgnoredAreas());
        ImageDiff diff = new ImageDiffer().makeDiff(expected, actual);
        if (diff.getDiffSize() > allowedError){
            File diffFile = new File(markedImages+getScreenshotName() + additionToName + ".png");
            try {
                ImageIO.write(diff.getMarkedImage(), "png", diffFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            imgAttachment(actualFile);
            imgAttachment(expectedFile);
            imgAttachment(diffFile);
            makeGif(actualFile, expectedFile, diffFile, additionToName);
        }
        return diff.getDiffSize() / (double) getPixelsCount() * 100;
    }

    private void makeGif(File actual, File expected, File diff, String additionName){
        try {
            BufferedImage actualImage = ImageIO.read(actual);
            BufferedImage expectedImage = ImageIO.read(expected);
            BufferedImage diffImage = ImageIO.read(diff);
            File gifFile = new File(gifImages + getScreenshotName() + additionName +".gif");
            ImageOutputStream output =
                     new FileImageOutputStream(gifFile);

             GifSequenceWriter writer =
                      new GifSequenceWriter(output, actualImage.getType(), 1000, true);

            writer.writeToSequence(actualImage);
            writer.writeToSequence(expectedImage);
            writer.writeToSequence(diffImage);

            writer.close();
            output.close();
            imgAttachment(gifFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getScreenshotName(){
        Capabilities cap = ((RemoteWebDriver) driver()).getCapabilities();
        String browserName = cap.getBrowserName().toLowerCase();
        return "screen_" + driver().getCurrentUrl().substring(7).replace("/", "_").replace(".", "-").replace("&", "").replace("?", "")
                + "~" + browserName + "_" + driver().manage().window().getSize();
    }

    private int getPixelsCount(){
        int height = driver().manage().window().getSize().getHeight();
        int width = driver().manage().window().getSize().getWidth();
        return height * width;
    }

    @Attachment(value = "{0}", type = "image/png")
    public static byte[] pngAttachment(Screenshot screenshot, String name){
        try {
            File file = new File(name + ".png");
            ImageIO.write(screenshot.getImage(), "png", file);
            return Files.readAllBytes(Paths.get(file.getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    @Attachment(value = "{0}", type = "image/png")
    private static byte[] imgAttachment(File file){
        try {

            return Files.readAllBytes(Paths.get(file.getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    public boolean clearAllExpectedImages(){
        fileIO.deleteDirectory(expectedDir);
        return !fileIO.directoryExists(expectedDir);
    }

    public boolean clearAllActualImages(){
        fileIO.deleteDirectory(actualDir);
        fileIO.createDirectory(actualDir);
        return fileIO.directoryIsEmpty(actualDir);
    }

}
