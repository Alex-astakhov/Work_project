package browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Created by Alex Astakhov on 25.07.2016.
 */
public class MyIE {
    public WebDriver getDriver(){
        DesiredCapabilities capabilities = new DesiredCapabilities();
       // System.setProperty("webdriver.ie.driver", "src/main/resources/IEDriverServer.exe");
        capabilities.setCapability("ignoreZoomSetting", true);
        capabilities.setCapability("nativeEvents", false);
        capabilities.setCapability("ignoreProtectedModeSettings", true);
        capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
        capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        return new InternetExplorerDriver(capabilities);
    }
}
