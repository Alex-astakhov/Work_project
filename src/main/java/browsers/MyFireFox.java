package browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * Created by Alex Astakhov on 25.07.2016.
 */
public class MyFireFox {

    @Step("Get FIREFOX driver")
    public WebDriver getDriver(){
        System.setProperty("webdriver.gecko.driver","C:\\BrowserDrivers\\geckodriver.exe");
        System.out.println("Start FIREFOX");
        return new FirefoxDriver(DesiredCapabilities.firefox());
    }
}
