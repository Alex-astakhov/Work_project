package pageObjects.employer.notebook;

import core.MethodsFactory;
import dataModels.employer.ManagerData;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * Created by Asta on 13.03.2017.
 */
public class ManagerPage extends MethodsFactory {
    private By emailField = By.cssSelector("#Users_email");
    private By firstNameField = By.cssSelector("#Users_first_name");
    private By lastNameField = By.cssSelector("#Users_last_name");
    private By phoneField = By.cssSelector("#Users_phone");
    private By positionField = By.cssSelector("#MoreInfo_8");
    private By regionField = By.cssSelector("#MoreInfo_9");
    private By cancelButton = By.cssSelector("[data-test=cancel]");
    private By saveButton = By.cssSelector("[data-test=save]");

    @Step("Fill manager data")
    public void fillManagerData(ManagerData data){
        typeEmail(data.getEmail());
        typeFirstName(data.getFirstName());
        typeLastName(data.getLastName());
        typePhone(data.getPhone());
        typePosition(data.getPosition());
        typeRegion(data.getRegion());
    }

    @Step("Get manager data")
    public ManagerData getManagerData(){
        String email = getEmail();
        String firstName = getFirstName();
        String lastName = getLastName();
        String phone = getPhone();
        String position = getPosition();
        String region = getRegion();
        return new ManagerData(email, firstName, lastName, phone, position, region);
    }

    @Step("Type email: {0}")
    public void typeEmail(String email){
        type(emailField, email);
    }

    @Step("Get email")
    public String getEmail(){
        return getValueAttrOfElement(emailField);
    }

    @Step("Type first name: {0}")
    public void typeFirstName(String firstName){
        type(firstNameField, firstName);
    }

    @Step("Get first name")
    public String getFirstName(){
        return getValueAttrOfElement(firstNameField);
    }

    @Step("Type last name: {0}")
    public void typeLastName(String lastName){
        type(lastNameField, lastName);
    }

    @Step("Get last name")
    public String getLastName(){
        return getValueAttrOfElement(lastNameField);
    }

    @Step("Type phone: {0}")
    public void typePhone(String phone){
        type(phoneField, phone);
    }

    @Step("Get phone")
    public String getPhone(){
        return getValueAttrOfElement(phoneField);
    }

    @Step("Type position: {0}")
    public void typePosition(String position){
        type(positionField, position);
    }

    @Step("Get position")
    public String getPosition(){
        return getValueAttrOfElement(positionField);
    }

    @Step("Type region: {0}")
    public void typeRegion(String region){
        type(regionField, region);
    }

    @Step("Get region")
    public String getRegion(){
        return getValueAttrOfElement(regionField);
    }

    @Step("Click CANCEL")
    public void clickCancel(){
        click(cancelButton);
    }

    @Step("Click SAVE")
    public void clickSave(){
        click(saveButton);
    }
}
