package pageObjects.employer.registration;

import core.Constants;
import core.MethodsFactory;
import dataModels.DataGenerator;
import dataModels.employer.EmployerRegistrationData;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import ru.yandex.qatools.allure.annotations.Step;


/**
 * Created by Asta on 20.12.2016.
 */
public class RegistrationPage extends MethodsFactory {
    private By firstNameField = By.id("Users_first_name");
    private By lastNameField = By.id("Users_last_name");
    private By userPhoneField = By.id("Users_phone");
    private By emailField = By.id("Users_email");
    private By companyTitleField = By.id("Companies_title");
    private By industryIdSelect = By.id("Companies_industry_id");
    private By numberOfStaffSelect = By.id("Companies_number_of_staff");
    private By binField = By.id("Companies_bin_nur");
    private By citySelect = By.id("Companies_city_id");
    private By siteField = By.id("Companies_site_company");
    private By companyPhoneField = By.id("Companies_phone");

    private By submitButton = By.cssSelector("[type=submit]");
    private By vkButton = By.id("social_link_vk");
    private By mailRuButton = By.id("social_link_mail");
    private By fbButton = By.id("social_link_fb");
    private By linkedInButton = By.id("social_link_in");
    private By googleButton = By.id("social_link_google");

    @Step("Get registration page")
    public void getPage(){
        get(Constants.DOMEN + "new-employer");
    }

    @Step("Type first name: {0}")
    public void typeFirstName(String text){
        type(firstNameField, text);
    }

    @Step("Type last name: {0}")
    public void typeLastName(String text){
        type(lastNameField, text);
    }

    @Step("Type user phone: {0}")
    public void typeUserPhone(String text){
        click(userPhoneField);
        type(userPhoneField, text);
    }

    @Step("Type email: {0}")
    public void typeEmail(String text){
        type(emailField, text);
    }

    @Step("Type company title: {0}")
    public void typeCompanyTitle(String text){
        type(companyTitleField, text);
    }

    @Step("Select industry by value: {0}")
    public void selectIndustryByValue(String value){
        selectionFromDropListByValue(industryIdSelect, value);
    }

    @Step("Select number of staff by value: {0}")
    public void selectNumberOfStaffByValue(String value){
        selectionFromDropListByValue(numberOfStaffSelect, value);
    }

    @Step("Type bin: {0}")
    public void typeBin(String text){
        type(binField, text);
    }

    @Step("Select city by code: {0}")
    public void selectCityByValue(String value){
        selectionFromDropListByValue(citySelect, value);
    }

    @Step("Type site: {0}")
    public void typeSite(String text){
        type(siteField, text);
    }

    @Step("Type company phone: {0}")
    public void typeCompanyPhone(String text){
        click(companyPhoneField);
        type(companyPhoneField, text);
    }

    @Step("Type company description: {0}")
    public void typeCompanyDescription(String text){
       typeInTinymce(text);
    }

    @Step("Click SUBMIT")
    public void clickSubmit(){
        click(submitButton);
    }

    @Step("Registration of employer")
    public void registerEmployer(EmployerRegistrationData data){
        fillAllFields(data);
        clickSubmit();
    }

    @Step("Fill all fields in registration form")
    public void fillAllFields(EmployerRegistrationData data){
        typeFirstName(data.getFirstName());
        typeLastName(data.getLastName());
        if (!data.getUserPhoneNumber().isEmpty())
            typeUserPhone(data.getUserPhoneNumber().substring(2).replaceAll("\\D*", ""));
        typeEmail(data.getEmail());
        typeCompanyTitle(data.getCompanyTitle());
        selectIndustryByValue(data.getIndustryValue());
        selectNumberOfStaffByValue(data.getStaffNumberValue());
        typeBin(data.getBin());
        selectCityByValue(data.getCityValue());
        typeSite(data.getSite());
        if (!data.getCompanyPhone().isEmpty())
            typeCompanyPhone(data.getCompanyPhone().substring(2).replaceAll("\\D*", ""));
        typeCompanyDescription(data.getDescription());
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


}
