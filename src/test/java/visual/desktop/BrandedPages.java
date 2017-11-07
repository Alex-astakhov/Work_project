package visual.desktop;

import ashotLib.MyAshot;
import core.BrowserFactory;
import core.Constants;
import dataModels.DataProviders;
import listeners.ListenerWithBrowserShot;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Asta on 05.05.2017.
 */
@Listeners({ListenerWithBrowserShot.class})
@Features("Desktop branded pages and city pages")
public class BrandedPages extends BrowserFactory {
    private MyAshot ashot = new MyAshot(Constants.SCREENSHOT_PATH);
    private String domen;
    private Set<By> ignore = new HashSet<>();
    private long timeoutAfterLoad = 5000;
    private final double ALLOWED_ERROR = 0.5;

    @Test
    public void initialization(){
        domen = Constants.DOMEN;
    }

    @Test(dependsOnMethods = "initialization", dataProvider = "companyBrandedPages", dataProviderClass = DataProviders.class)
    public void verifyCompanyPage(String endpoint) throws InterruptedException {
        ignore.clear();
        System.out.println("Page: " + domen + endpoint);
        get(domen + endpoint);
        waitForContentLoaded(timeoutAfterLoad);
        hideElement(By.cssSelector(".b-vacancies-search"));
        hideElement(By.cssSelector(".gtm-pagination"));
        double difference = ashot.findImageDifferenceWithoutJquery(ignore, ALLOWED_ERROR, true);
        Assert.assertTrue(difference < ALLOWED_ERROR, difference + " is more then allowed (" + ALLOWED_ERROR + "%)");
    }

    @Test(dependsOnMethods = "initialization", dataProvider = "cityBrandedPages", dataProviderClass = DataProviders.class)
    public void verifyCityPage(String endpoint) throws InterruptedException {
        ignore.clear();
        System.out.println("Page: " + domen + endpoint);
        get(domen + endpoint);
        waitForContentLoaded(timeoutAfterLoad);
        hideElement(By.cssSelector(".b-vacancies-search"));
        hideElement(By.cssSelector("#google_image_div"));
        hideElement(By.cssSelector(".gtm-pagination"));
        hideElements(By.cssSelector(".b-banner"));
        double difference = ashot.findImageDifferenceWithoutJquery(ignore, ALLOWED_ERROR, true);
        Assert.assertTrue(difference < ALLOWED_ERROR, difference + " is more then allowed (" + ALLOWED_ERROR + "%)");
    }
}
