package formValidation;

import assertion.Assertion;
import core.BrowserFactory;
import core.Constants;
import dataModels.applicant.ApplicantRegistrationData;
import http.HttpActions;
import listeners.ListenerWithBrowserShot;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObjects.FooterBlock;
import pageObjects.MobileMenu;
import pageObjects.applicant.registration.RegistrationPage;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * Created by Asta on 15.03.2017.
 */
@Listeners({ListenerWithBrowserShot.class})
@Features("User mobile registration form test")
public class RegistrationFormsMobile extends BrowserFactory {
    private RegistrationPage applicantRegPage;

    @Test
    public void initialization(){
        applicantRegPage = new RegistrationPage();
        applicantRegPage.getPage();
        if (!getUserAgent().toLowerCase().contains("mobile")) {
            new FooterBlock().clickMobileVersion();
        }
    }

    @Test(dataProvider = "negative", dataProviderClass = ApplicantRegistrationData.class, dependsOnMethods = "initialization")
    public void applicantRegistrationForm(ApplicantRegistrationData data){
        System.out.println("Trying to submit: " + data);
        applicantRegPage.registerApplicant(data);
        try {
            Assertion.urlDoesNtContains("resumeBuilder", 2, "URL was changed after form wrong submit!");
        } catch (AssertionError e){
            pngAttachment("screen.png");
            new MobileMenu().logout();
            deleteUserFromAdmin();
            applicantRegPage.getPage();
            throw e;
        }
    }

    @Step
    public void deleteUserFromAdmin() {
        System.out.println("Deleting user with email: " + Constants.APPLICANT_REG_EMAIL);
        HttpActions actions = new HttpActions(Constants.HOST);
        actions.deleteUserByEmail(Constants.APPLICANT_REG_EMAIL);
    }

}
