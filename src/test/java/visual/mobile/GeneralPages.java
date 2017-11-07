package visual.mobile;

import ashotLib.MyAshot;
import core.BrowserFactory;
import core.Constants;
import core.MethodsFactory;
import dataModels.applicant.ApplicantRegistrationData;
import listeners.ListenerLogger;
import listeners.ListenerWithBrowserShot;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObjects.FooterBlock;
import pageObjects.LoginPage;
import pageObjects.MainPage;
import pageObjects.applicant.registration.RegistrationPage;
import ru.yandex.qatools.allure.annotations.Features;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Asta on 15.02.2017.
 */
@Listeners({ListenerWithBrowserShot.class})
@Features("General mobile pages without login")
public class GeneralPages extends BrowserFactory {
    private MyAshot ashot = new MyAshot(Constants.SCREENSHOT_PATH);
    private String domen;
    private Set<By> ignore = new HashSet<>();
    private long timeoutAfterLoad = 5000;
    private final double ALLOW_ERROR = 0.5;

    @Test
    public void initialization(){
        domen = Constants.DOMEN;
        get(domen);
        if (!MethodsFactory.getUserAgent().toLowerCase().contains("mobile")) {
            FooterBlock footer = new FooterBlock();
            footer.clickMobileVersion();
        }
    }

    @Test(dependsOnMethods = "initialization")
    public void verifyLoginPage(){
        ignore.clear();
        System.out.println("Page: " + domen + "login");
        get(domen + "login");
        LoginPage loginPage = new LoginPage();
        loginPage.typeEmail("some-email@mail.ru");
        loginPage.typePassword("SomePassword");
        waitForContentLoaded(timeoutAfterLoad);
        double difference = ashot.findImageDifferenceWithoutJquery(ignore, ALLOW_ERROR, true, "(MOB)");
        Assert.assertTrue(difference < ALLOW_ERROR, difference + " is more then allowed (" + ALLOW_ERROR + "%)");
    }

    @Test(dependsOnMethods = "initialization")
    public void verifyApplicantRegPage(){
        ignore.clear();
        System.out.println("Page: " + domen + "new-applicant");
        get(domen + "new-applicant");
        RegistrationPage registrationPage = new RegistrationPage();
        registrationPage.fillAllFields(new ApplicantRegistrationData("Имя", "Фамилия", "someEmail@mail.ru", "somePassword"));
        waitForContentLoaded(timeoutAfterLoad);
        double difference = ashot.findImageDifferenceWithoutJquery(ignore, ALLOW_ERROR, true, "(MOB)");
        Assert.assertTrue(difference < ALLOW_ERROR, difference + " is more then allowed (" + ALLOW_ERROR + "%)");
    }

}
