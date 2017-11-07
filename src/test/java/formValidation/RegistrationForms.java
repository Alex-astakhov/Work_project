package formValidation;

import assertion.Assertion;
import core.BrowserFactory;
import core.Constants;
import dataModels.applicant.ApplicantRegistrationData;
import dataModels.employer.EmployerRegistrationData;
import http.HttpActions;
import listeners.ListenerWithBrowserShot;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObjects.HeaderBlock;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * Created by Asta on 15.03.2017.
 */
@Listeners({ListenerWithBrowserShot.class})
@Features("User registration form test")
public class RegistrationForms extends BrowserFactory {
    private pageObjects.applicant.registration.RegistrationPage applicantRegPage;
    private pageObjects.employer.registration.RegistrationPage employerRegPage;

    @Test
    public void initialization(){
        applicantRegPage = new pageObjects.applicant.registration.RegistrationPage();
        employerRegPage = new pageObjects.employer.registration.RegistrationPage();
    }

    @Test(dataProvider = "negative", dataProviderClass = ApplicantRegistrationData.class, dependsOnMethods = "initialization")
    public void applicantRegistrationForm(ApplicantRegistrationData data){
        applicantRegPage.getPage();
        System.out.println("Trying to submit: " + data);
        applicantRegPage.registerApplicant(data);
        try {
            Assertion.urlDoesNtContains("create", 2, "URL was changed after form submit!");
        } catch (AssertionError e){
            pngAttachment("screen.png");
            new HeaderBlock().logout();
            deleteUserFromAdmin();
            throw e;
        }
    }

    @Test(dataProvider = "negative", dataProviderClass = EmployerRegistrationData.class, dependsOnMethods = "initialization")
    public void employerRegistrationForm(EmployerRegistrationData data){
        employerRegPage.getPage();
        System.out.println("Trying to submit: " + data);
        employerRegPage.registerEmployer(data);
        try{
        Assertion.urlDoesNtContains("success", 2, "Form was validated!!!");
        } catch (AssertionError e){
            pngAttachment("screen.png");
            new HeaderBlock().logout();
            deleteUserFromAdmin();
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
