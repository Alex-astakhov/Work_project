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
import pageObjects.HeaderBlock;
import pageObjects.LoginPage;
import ru.yandex.qatools.allure.annotations.Features;

import java.util.*;

/**
 * Created by Asta on 22.03.2017.
 */
@Listeners({ListenerWithBrowserShot.class})
@Features("Verify browser console for errors")
public class ConsoleErrors extends BrowserFactory {
    private HeaderBlock headerBlock = new HeaderBlock();

    @Test()
    public void loginApplicant() {
        LoginPage loginPage = new LoginPage();
        loginPage.getPage();
        loginPage.loginUser(Constants.APPLICANT_VISUAL_TEST_EMAIL, Constants.PASSWORD);
        Assertion.urlContains("applicant/notebook", 10);
    }

    @Test(dependsOnMethods = "loginApplicant", dataProvider = "urlsApplicant", dataProviderClass = DataProviders.class)
    public void verifyApplicantPages(String url) {
        verifyPages(url);
    }

    @Test(dependsOnMethods = "verifyApplicantPages", alwaysRun = true)
    public void loginEmployer() {
        headerBlock.logout();
        LoginPage loginPage = new LoginPage();
        loginPage.getPage();
        loginPage.loginUser(Constants.EMPLOYER_EMAIL, Constants.EMPLOYER_EMAIL);
        Assertion.urlContains("vacancy/list", 10);
    }

    @Test(dependsOnMethods = "loginEmployer", dataProvider = "urlsEmployer", dataProviderClass = DataProviders.class)
    public void verifyEmployerPages(String url) {
        verifyPages(url);
    }

    @Test(dependsOnMethods = "verifyEmployerPages", alwaysRun = true)
    public void logout() {
        headerBlock.logout();
    }

    @Test(dependsOnMethods = "logout", dataProvider = "urlsWithoutLogin", dataProviderClass = DataProviders.class)
    public void verifyGeneralPages(String url) {
        verifyPages(url);
    }
    
}
