package pageObjects.applicant.registration;

import core.Constants;
import core.MethodsFactory;
import dataModels.applicant.ApplicantRegistrationData;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * Created by Asta on 16.12.2016.
 */
public class RegistrationPage extends MethodsFactory {
    private By firstNameField = By.id("Users_first_name");
    private By lastNameField = By.id("Users_last_name");
    private By emailField = By.id("Users_email");
    private By passwordField = By.id("Users_password");
    private By submitButton = By.cssSelector("[name=yt0]");
    private By termsLink = By.cssSelector("[href=/terms_applicant]");

    private By vkButton = By.cssSelector("#social_link_vk");
    private By mailRuButton = By.cssSelector("#social_link_mail");
    private By fbButton = By.cssSelector("#social_link_fb");
    private By linkedInButton = By.cssSelector("#social_link_in");
    private By googleButton = By.cssSelector("#social_link_google");

    @Step("Get registration page")
    public void getPage(){
        get(Constants.DOMEN + "new-applicant");
        waitForPageToBeLoaded();
    }

    @Step("Type first name {0}")
    public void typeFirstName(String text){
        type(firstNameField, text);
    }

    @Step("Type last name {0}")
    public void typeLastName(String text){
        type(lastNameField, text);
    }

    @Step("Type email {0}")
    public void typeEmail(String text){
        type(emailField, text);
    }

    @Step("Type password {0}")
    public void typePassword(String text){
        type(passwordField, text);
    }

    @Step("Click SUBMIT")
    public void clickSubmit(){
        click(submitButton);
    }

    @Step("Click VK.COM")
    public void clickVk(){
        click(vkButton);
    }

    @Step("Click MAIL.RU")
    public void clickMailRu(){
        click(mailRuButton);
    }

    @Step("Click FB")
    public void clickFb(){
        click(fbButton);
    }

    @Step("Click LINKED IN")
    public void clickLinkedIn(){
        click(linkedInButton);
    }

    @Step("Click GOOGLE")
    public void clickGoogle(){
        click(googleButton);
    }

    @Step("Registration of applicant")
    public void registerApplicant(ApplicantRegistrationData data){
        fillAllFields(data);
        clickSubmit();
    }

    @Step("Fill all fields in registration form")
    public void fillAllFields(ApplicantRegistrationData data){
        typeFirstName(data.getFirstName());
        typeLastName(data.getLastName());
        typeEmail(data.getEmail());
        typePassword(data.getPassword());
    }

    @Step("Verify CV Builder's page is shown")
    public boolean mobileApplicantIsRegistered(){
        try{
            waitForUrlContains("resumeBuilder");
        }catch (Exception e){
            return false;
        }
        return true;
    }

}
