package browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * Created by Alex Astakhov on 25.07.2016.
 */
public class MyChrome {

    @Step("Start CHROME")
    public WebDriver getDriver(){
        System.out.println("Start CHROME");
        ChromeOptions chrome_options = new ChromeOptions();
        chrome_options.addArguments("--dns-prefetch-disable");
        return new ChromeDriver(chrome_options);
    }
}
