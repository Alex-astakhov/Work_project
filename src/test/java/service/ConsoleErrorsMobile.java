package service;

import assertion.Assertion;
import core.BrowserFactory;
import core.Constants;
import core.MethodsFactory;
import dataModels.DataProviders;
import listeners.ListenerWithBrowserShot;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObjects.FooterBlock;
import pageObjects.LoginPage;
import pageObjects.MobileMenu;
import ru.yandex.qatools.allure.annotations.Features;

import java.util.List;

/**
 * Created by Asta on 22.03.2017.
 */
@Listeners({ListenerWithBrowserShot.class})
@Features("Verify browser console for errors on mobile")
public class ConsoleErrorsMobile extends BrowserFactory {
    private MobileMenu mobileMenu = new MobileMenu();

    @Test()
    public void loginApplicant(){
        LoginPage loginPage = new LoginPage();
        loginPage.getPage();
        System.out.println(getUserAgent());
        if (!getUserAgent().toLowerCase().contains("mobile"))
            new FooterBlock().clickMobileVersion();
        loginPage.loginUser(Constants.APPLICANT_VISUAL_TEST_EMAIL, Constants.PASSWORD);
        Assertion.urlContains("applicant/notebook", 10);
    }

    @Test(dependsOnMethods = "loginApplicant", dataProvider = "urlsApplicant", dataProviderClass = DataProviders.class)
    public void verifyApplicantPages(String url){
        verifyPages(url);
    }

    @Test(dependsOnMethods = "verifyApplicantPages", alwaysRun = true)
    public void logout() {
        mobileMenu.logout();
    }

    @Test(dependsOnMethods = "logout", dataProvider = "urlsWithoutLogin", dataProviderClass = DataProviders.class)
    public void verifyGeneralPages(String url){
        verifyPages(url);
    }
}
