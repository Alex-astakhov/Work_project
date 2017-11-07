package browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Created by Alex Astakhov on 25.07.2016.
 */
public class MyOpera {

    public WebDriver getDriver(){
        DesiredCapabilities capabilities = new DesiredCapabilities().operaBlink();
        //capabilities.setCapability("opera.binary", "/path/to/your/opera");
        System.setProperty("webdriver.chrome.driver", "C:\\BrowserDrivers\\operadriver.exe");
        //capabilities.setCapability("opera.log.level", "CONFIG");
        return new ChromeDriver(capabilities);
    }
}
