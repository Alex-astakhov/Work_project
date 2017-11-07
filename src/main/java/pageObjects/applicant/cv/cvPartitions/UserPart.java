package pageObjects.applicant.cv.cvPartitions;

import core.Constants;
import dataModels.applicant.CVBuilderData;
import dataModels.applicant.cvParts.UserData;
import org.openqa.selenium.By;
import pageObjects.Popup;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * Created by Alex Astakhov on 27.11.2016.
 */
public class UserPart extends Popup<UserData> {
    private By firstNameField = By.id("Users_first_name");
    private By lastNameField = By.id("Users_last_name");
    private By middleNameField = By.id("Users_middle_name");
    private By birthDaySelect = By.id("Users_birth_day");
    private By birthMonthSelect = By.id("Users_birth_month");
    private By birthYearSelect = By.id("Users_birth_year");
    private By genderManRadio = By.cssSelector("#Users_gender_0 + label");
    private By genderWomanRadio = By.cssSelector("#Users_gender_1 + label");
    private By checkedGenderRadio = By.cssSelector("#Users_gender [checked=checked]");
    private By citySelect = By.id("Users_city_id");
    private By cityRegionSelect = By.id("UsersInfo_city_region_id");
    private By skypeField = By.id("UsersInfo_skype");
    private By phoneField = By.id("Users_phone");
    private By additionPhoneField = By.id("UsersInfo_additional_phone");
    private By mobCancelButton = By.cssSelector("[data-test=button-cancel]");
    private By mobSaveButton = By.cssSelector("[data-test=button-save]");

    private By emailField = By.cssSelector("[data-test=user-email]");

    @Override
    public void fillAllFields(UserData data){
        typeFirstName(data.getFirstName());
        typeLastName(data.getLastName());
        typeMiddleName(data.getMiddleName());
        selectBirthDay(data.getBirthDayIndex());
        selectBirthMonth(data.getBirthMonthIndex());
        selectBirthYear(data.getBirthYearValue());
        if (data.isGender())
            setGenderMan();
        else
            setGenderWoman();
        selectCity(data.getCityValue());
        if (Constants.CITIES_WITH_REGIONS.contains(data.getCityValue())){
            delay(500);
            selectCityRegion(data.getCityRegionValue());
        }
        typeSkype(data.getSkype());
        typePhone(data.getPhoneNumber());
        typeAdditionPhone(data.getAdditionPhoneNumber());
    }

    @Step("Fill user part on mobile")
    public void fillAllFieldsMob(UserData data){
        typeFirstName(data.getFirstName());
        typeLastName(data.getLastName());
        typeMiddleName(data.getMiddleName());
        selectBirthDay(data.getBirthDayIndex());
        selectBirthMonth(data.getBirthMonthIndex());
        selectBirthYear(data.getBirthYearValue());
        if (data.isGender())
            setGenderMan();
        else
            setGenderWoman();
        if (!data.getCityValue().isEmpty())
            selectCity(data.getCityValue());
        else
            selectCity("0");
        if (Constants.CITIES_WITH_REGIONS.contains(data.getCityValue())){
            delay(500);
            selectCityRegion(data.getCityRegionValue());
        }
        typeSkype(data.getSkype());
        typePhone(data.getPhoneNumber());
        typeAdditionPhone(data.getAdditionPhoneNumber());
    }

    @Override
    public UserData getDataFromPopup(){
        String firstName = getFirstName();
        String lastName =  getLastName();
        String middleName =  getMiddleName();
        int birthDayIndex =  getBirthDayIndex();
        int birthMonthIndex =  getBirthMonthIndex();
        String birthYearValue = getBirthYearValue();
        boolean isGender;
        isGender = isGenderMan();
        String cityValue = getCityValue();
        String cityRegionValue = null;
        if (Constants.CITIES_WITH_REGIONS.contains(cityValue)){
            cityRegionValue = getCityRegionValue();
        }
        String skype = getSkype();
        String phone = getPhone();
        String additionPhone = getAdditionPhone();
        return new UserData(firstName, lastName, middleName, birthDayIndex, birthMonthIndex, birthYearValue, isGender,
                cityValue, cityRegionValue, skype, phone, additionPhone);
    }

    @Step("Get information which was add in CV Builder")
    public CVBuilderData getCVBuilderData(){
        String firstName = getFirstName();
        String lastName =  getLastName();
        String email = getEmail();
        int birthDayIndex =  getBirthDayIndex();
        int birthMonthIndex =  getBirthMonthIndex();
        String birthYearValue = getBirthYearValue();
        boolean isGender;
        isGender = isGenderMan();
        String cityValue = getCityValue();
        String cityRegionValue = null;
        if (Constants.CITIES_WITH_REGIONS.contains(cityValue)){
            cityRegionValue = getCityRegionValue();
        }
        String phone = getPhone();
        return new CVBuilderData(firstName, lastName, email, birthDayIndex, birthMonthIndex, birthYearValue, isGender,
                cityValue, cityRegionValue, phone);
    }

    @Step("Type first name: {0}")
    public void typeFirstName(String name){
        type(firstNameField, name);
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

    @Step("Type middle name: {0}")
    public void typeMiddleName(String middleName){
        type(middleNameField, middleName);
    }

    @Step("Get middle name")
    public String getMiddleName(){
        return getValueAttrOfElement(middleNameField);
    }

    @Step("Select day of birth: {0}")
    public void selectBirthDay(int index){
        selectionFromDropListByIndex(birthDaySelect, index);
    }

    @Step("Get day of birth")
    public int getBirthDayIndex(){
        return Integer.parseInt(getValueAttrOfElement(getSelectedItem(birthDaySelect)));
    }

    @Step("Select month of birth: {0}")
    public void selectBirthMonth(int index){
        selectionFromDropListByIndex(birthMonthSelect, index);
    }

    @Step("Get month of birth")
    public int getBirthMonthIndex(){
        return Integer.parseInt(getValueAttrOfElement(getSelectedItem(birthMonthSelect)));
    }

    @Step("Select year of birth: {0}")
    public void selectBirthYear(String value){
        selectionFromDropListByValue(birthYearSelect, value);
    }

    @Step("Get year of birth")
    public String getBirthYearValue(){
        return getValueAttrOfElement(getSelectedItem(birthYearSelect));
    }

    @Step("Check gender - MAN")
    public void setGenderMan(){
        click(genderManRadio);
    }

    @Step("Verify if gender MAN is checked")
    public boolean isGenderMan(){
        return getValueAttrOfElement(checkedGenderRadio).equals("1");
    }

    @Step("Check gender - WOMAN")
    public void setGenderWoman(){
        click(genderWomanRadio);
    }

    @Step("Verify if gender WOMAN is checked")
    public boolean isGenderWoman(){
        return getValueAttrOfElement(checkedGenderRadio).equals("2");
    }

    @Step("Select city code: {0}")
    public void selectCity(String value){
        selectionFromDropListByValue(citySelect, value);
    }

    @Step("Select city region code: {0}")
    public void selectCityRegion(String value){
        selectionFromDropListByValue(cityRegionSelect, value);
    }

    @Step("Get city code")
    public String getCityValue(){
        return getValueAttrOfElement(getSelectedItem(citySelect));
    }

    @Step("Get city region code")
    public String getCityRegionValue(){
        return getValueAttrOfElement(getSelectedItem(cityRegionSelect));
    }

    @Step("Type skype: {0}")
    public void typeSkype(String skype){
        type(skypeField, skype);
    }

    @Step("Get skype")
    public String getSkype(){
        String result = getValueAttrOfElement(skypeField);
        if (result == null)
            return "";
        return result;
    }

    @Step("Type contact phone: {0}")
    public void typePhone(String phone){
        type(phoneField, phone);
    }

    @Step("Get contact phone")
    public String getPhone(){
        String result = getValueAttrOfElement(phoneField);
        if (result == null)
            return "";
        return result;
    }

    @Step("Type additional phone: {0}")
    public void typeAdditionPhone(String phone){
        type(additionPhoneField, phone);
    }

    @Step("Get additional phone")
    public String getAdditionPhone(){
        String result = getValueAttrOfElement(additionPhoneField);
        if (result == null)
            return "";
        return result;
    }

    @Step("Click mobile SAVE")
    public void mobClickSave(){
        click(mobSaveButton);
    }

    @Step("Click mobile CANCEL")
    public void mobClickCancel(){
        click(mobCancelButton);
    }

    @Step("Get email")
    public String getEmail(){
        return getTextByLocator(emailField);
    }
}
