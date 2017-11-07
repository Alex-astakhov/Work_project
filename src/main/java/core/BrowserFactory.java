package core;

import browsers.*;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Alex Astakhov on 18.05.2016.
 * Класс содержит методы запуска и закрытия браузера
 */
public class BrowserFactory extends MethodsFactory {
    public static String BROWSER;

    @BeforeTest
    @Parameters({"browser", "stage", "log"})
    public void setupUp(@Optional("CH") String browser, @Optional("true") boolean stage, @Optional("true") boolean log) {
        Constants.BROWSER_LOG = log;
        switch (browser){
            case "CH": driver = new MyChrome().getDriver(); BROWSER = "Google Chrome"; break;
            case "MCH": driver = new MyChrome().getDriver(); BROWSER = "Google Chrome Mobile"; break;
            case "FF":
                Constants.BROWSER_LOG = false;
                BROWSER = "Mozilla Firefox";
                driver = new MyFireFox().getDriver(); break;
            case "IE": driver = new MyIE().getDriver(); BROWSER = "Internet Explorer"; break;
            case "O": driver = new MyOpera().getDriver(); BROWSER = "Opera"; break;
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Constants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        DRIVER.set(driver);

        initDomen(stage);
        }

        public static void initDomen(boolean stage){
            if (stage){
                Constants.DOMEN = "https://rabotadev.nur.kz/";
                Constants.HOST = "rabotadev.nur.kz";
            }
            else {
                Constants.DOMEN = "https://rabota.nur.kz/";
                Constants.HOST = "rabota.nur.kz";
            }
        }

    @AfterTest
    public void tearDown() {
        try {
            driver().close();
            driver().quit();
        }catch (Exception e){
            System.out.println("Driver error preventing from Quitting.");
        }
    }

    private List<String> getErrors(){
        List<String> list = MethodsFactory.getBrowserLog(false);
        if (list.isEmpty())
            return list;
        List<String> errors = new ArrayList<>();
        for (String s: list) {
            if (s.contains(".css") || s.contains(".js"))
                errors.add(s);
        }
        return errors;
    }

    protected void verifyPages(String url) {
        get(url);
        waitForContentLoaded(3000);
        List<String> errors = getErrors();
        System.out.println(errors);
        Assert.assertTrue(errors.isEmpty(), "There are errors in console! (" + errors.toString() + ")");
    }
}
