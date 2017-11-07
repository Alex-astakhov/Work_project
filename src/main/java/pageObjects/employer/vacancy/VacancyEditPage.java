package pageObjects.employer.vacancy;

import core.Constants;
import core.MethodsFactory;
import dataModels.DataGenerator;
import dataModels.employer.vacancyParts.VacancyContactsData;
import dataModels.employer.vacancyParts.VacancyPropertiesData;
import dataModels.employer.vacancyParts.VacancyData;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Asta on 31.01.2017.
 */
public class VacancyEditPage extends MethodsFactory {
    private By standardButton = By.cssSelector("#standard + label");
    private By premiumButton = By.cssSelector("#premium + label");
    private By premiumChecked = By.cssSelector("#premium");
    private By responsiblePersonSelect = By.cssSelector("[name*=user_id]");
    private By titleField = By.cssSelector("#Vacancies_title");
    private By addContactsCheckBox = By.cssSelector("#Vacancies_include_more_info + label");
    private By addContactsChecked = By.cssSelector("#Vacancies_include_more_info");
    private By contactPersonField = By.cssSelector("#Vacancies_user_name");
    private By contactPhoneField = By.cssSelector("#Vacancies_user_phone");
    private By addAddressCheckBox = By.cssSelector("#Vacancies_include_office_address + label");
    private By addAddressChecked = By.cssSelector("#Vacancies_include_office_address");
    private By addressCitySelect = By.cssSelector("#Vacancies_office_city");
    private By addressStreetField = By.cssSelector("#Vacancies_office_street");
    private By addressBuildingField = By.cssSelector("#Vacancies_office_building");
    private By addressOfficeField = By.cssSelector("#Vacancies_office_unit");
    private By availabilitySelect = By.cssSelector("#Vacancies_availability_id");
    private By citySelect = By.cssSelector("#Vacancies_city_id");
    private By experienceSelect = By.cssSelector("#Vacancies_experience");
    private By salaryFromField = By.cssSelector("#Vacancies_salary_from");
    private By salaryToField = By.cssSelector("#Vacancies_salary");
    private By responseDestinationSelect = By.cssSelector("#response_only_to_notebook");
    private By addedDestinationEmail = By.cssSelector("[data-test=one-email]");
    private By destinationEmailDeleteElement = By.cssSelector("[data-test=delete-one-email]");
    private By addEmailLink = By.cssSelector("[data-test=add-email]");
    private By emailToAddField = By.cssSelector("#added_email");
    private By addEmailButton = By.cssSelector("#added_email + button");
    private By urlToApplicationField = By.cssSelector("#Vacancies_redirect_on_site_after_response");
    private By rePublicSelect = By.cssSelector("#Vacancies_auto_re_public");
    private By makeHotCheckBox = By.cssSelector("#hot_vacancy + label div:nth-child(2)");
    private By makeHotChecked = By.cssSelector("#is_hot");
    private By hotDaysSelect = By.cssSelector("[name=select_hot_items]");
    private By cancelButton = By.cssSelector("[data-test=cancel]");
    private By saveButton = By.cssSelector("[data-test^=save]");
    private By saveInDraftsButton = By.cssSelector("[data-test=rough_copy]");
    private By publishButton = By.cssSelector("[data-test=republic]");

    @Step("Fill vacancy's all fields")
    public void fillAllFields(String respPerson, VacancyData vacancy, VacancyPropertiesData properties, VacancyContactsData contacts){
        selectResponsiblePersonByValue(respPerson);
        fillVacancyPropertiesData(properties);
        fillVacancyData(vacancy);
        fillVacancyContactsData(contacts);
    }

    @Step("Fill vacancy's all fields")
    public void fillAllFields(VacancyData vacancy, VacancyPropertiesData properties, VacancyContactsData contacts){
        fillVacancyPropertiesData(properties);
        fillVacancyData(vacancy);
        fillVacancyContactsData(contacts);
    }

    @Step("Fill vacancy body")
    public void fillVacancyData(VacancyData data){
        typeTitle(data.getTitle());
        typeDescription(data.getDescription());
        selectAvailabilityByValue(data.getAvailabilityTypeValue());
        selectCityByValue(data.getCityValue());
        selectExperienceByValue(data.getExperienceValue());
        typeSalaryFrom(data.getSalaryFrom());
        typeSalaryTo(data.getSalaryTo());
    }

    @Step("Fill contacts data")
    public void fillVacancyContactsData(VacancyContactsData data){
        if (data.isAddContacts()){
            checkAddContacts();
            typeContactPerson(data.getContactPerson());
            typeContactPhone(data.getContactPhoneNumber());
        }else{
            uncheckAddContacts();
        }
        if (data.isAddAddress()){
            checkAddAddress();
            selectAddressCityByValue(data.getAddressCityValue());
            typeAddressStreet(data.getAddressStreet());
            typeAddressBuilding(data.getAddressBuilding());
            typeAddressOffice(data.getAddressOffice());
        }else{
            uncheckAddAddress();
        }
    }

    @Step("Fill vacancy properties")
    public void fillVacancyPropertiesData(VacancyPropertiesData data){
        if (data.isPremium())
            clickPremium();
        else
            clickStandard();
        selectResponseDestinationByValue(data.getResponseDestinationValue());
        switch (data.getResponseDestinationValue()){
            case "0":
                waitForElementPresence(addEmailLink);
                Set<String> emails = data.getDestinationEmails();
                emails.remove(Constants.EMPLOYER_EMAIL);
                for (String email: emails) {
                    clickAddDestinationEmailLink();
                    typeDestinationEmail(email);
                    clickAddDestinationEmailButton();
                }
                clickAddDestinationEmailLink();
                typeDestinationEmail("spare-mail@mail.com");
                clickAddDestinationEmailButton();
                deleteDestinationEmail(emails.size() + 1);
                emails.add(Constants.EMPLOYER_EMAIL);
                break;
            case "redirect_on_site_after_response":
                waitForElementPresence(urlToApplicationField);
                typeUrlToApplication(data.getApplicationUrl());
                break;
        }
        if (data.isRenewPublication())
            selectRepublicByValue("1");
        else
            selectRepublicByValue("0");
        if (data.isHot()){
            checkMakeHot();
            selectHotDaysByValue(data.getHotDaysValue());
        }
    }

    @Step("Get job information from vacancy")
    public VacancyData getVacancyData(){
        waitForElementPresence(titleField);
        String title = getTitle();
        String description = getDescription();
        String availabilityTypeValue = getAvailabilityValue();
        String cityValue = getCityValue();
        String experienceValue = getExperienceValue();
        String salaryFrom = getSalaryFrom();
        String salaryTo = getSalaryTo();
        return new VacancyData(title, description, availabilityTypeValue, cityValue, experienceValue, salaryFrom, salaryTo);
    }

    @Step("Get properties from vacancy")
    public VacancyPropertiesData getVacancyPropertiesData(){
       presenceOfElement(premiumButton);
        boolean premium = isCheckedPremium();
        String responseDestinationValue = getResponseDestinationValue();
        Set<String> emailsDestination = null;
        String applicationUrl = null;
        switch (responseDestinationValue){
            case "0":
                emailsDestination = getDestinationEmails();
                break;
            case "redirect_on_site_after_response":
                applicationUrl = getUrlToApplication();
                break;
        }
        boolean renewPublication = false;
        if (getRepublicValue().equals("1"))
            renewPublication = true;
        boolean hot = false;//isCheckedMakeHot();
        String hotDaysValue = getHotDaysValue();
        return new VacancyPropertiesData(premium, responseDestinationValue, emailsDestination, applicationUrl, renewPublication, hot, hotDaysValue);
    }

    @Step("Get contact information from vacancy")
    public VacancyContactsData getVacancyContactsData(){
        waitForElementPresence(addContactsCheckBox);
        boolean addContacts = isCheckedAddContacts();
        String contactPerson = null;
        String contactPhoneNumber = null;
        if(addContacts){
            contactPerson = getContactPerson();
            contactPhoneNumber = getContactPhone();
        }
        boolean addAddress = isCheckedAddAddress();
        String contactCityValue = "0";
        String street = "";
        String building = "";
        String office = "";
        if (addAddress){
            contactCityValue = getAddressCityValue();
            street = getAddressStreet();
            building = getAddressBuilding();
            office = getAddressOffice();
        }
        return new VacancyContactsData(addContacts, contactPerson, contactPhoneNumber, addAddress, contactCityValue,
                street, building, office);
    }

    @Step("Choose STANDARD")
    public void clickStandard(){
        click(standardButton);
    }

    @Step("Choose PREMIUM")
    public void clickPremium(){
        click(premiumButton);
    }

    @Step("Verify if PREMIUM is chosen")
    public boolean isCheckedPremium(){
        return isChecked(premiumChecked);
    }

    @Step("Get list of managers from select")
    public List<String> getResponsiblePersonsValues(){
        return getAllOptionsValuesFromSelect(responsiblePersonSelect);
    }

    @Step("Select manager by value: {0}")
    public void selectResponsiblePersonByValue(String value){
        selectionFromDropListByValue(responsiblePersonSelect, value);
    }

    @Step("Get selected manager value")
    public String getSelectedResponsiblePersonValue(){
        return getValueOfSelectedItem(responsiblePersonSelect);
    }

    @Step("Type vacancy title")
    public void typeTitle(String text){
        type(titleField, text);
    }

    @Step("Get vacancy title")
    public String getTitle(){
        return getValueAttrOfElement(titleField);
    }

    @Step("Type description")
    public void typeDescription(String text){
        typeInTinymce(text);
    }

    @Step("Get description")
    public String getDescription(){
        return getTextFromTinymce();
    }

    @Step("Check ADD CONTACTS")
    public void checkAddContacts(){
        if (!isCheckedAddContacts())
            click(addContactsCheckBox);
    }

    @Step("Uncheck ADD CONTACTS")
    public void uncheckAddContacts(){
        if (isCheckedAddContacts())
            click(addContactsCheckBox);
    }

    @Step("Verify if ADD CONTACTS is checked")
    public boolean isCheckedAddContacts(){
        return isChecked(addContactsChecked);
    }

    @Step("Type contact person")
    public void typeContactPerson(String text){
        type(contactPersonField, text);
    }

    @Step("Get contact person")
    public String getContactPerson(){
        return getValueAttrOfElement(contactPersonField);
    }

    @Step("Type contact phone")
    public void typeContactPhone(String phone){
        type(contactPhoneField, phone);
    }

    @Step("Get contact phone")
    public String getContactPhone(){
        return getValueAttrOfElement(contactPhoneField);
    }

    @Step("Check ADD ADDRESS")
    public void checkAddAddress(){
        if(!isCheckedAddAddress())
            click(addAddressCheckBox);
    }

    @Step("uncheck ADD ADDRESS")
    public void uncheckAddAddress(){
        if(isCheckedAddAddress())
            click(addAddressCheckBox);
    }

    @Step("Verify if ADD ADDRESS is checked")
    public boolean isCheckedAddAddress(){
        return isChecked(addAddressChecked);
    }

    @Step("Select address city by code: {0}")
    public void selectAddressCityByValue(String value){
        selectionFromDropListByValue(addressCitySelect, value);
    }

    @Step("Get Address city code")
    public String getAddressCityValue(){
        return getValueOfSelectedItem(addressCitySelect);
    }

    @Step("Type address street: {0}")
    public void typeAddressStreet(String text){
        type(addressStreetField, text);
    }

    @Step("Get address stret")
    public String getAddressStreet(){
        return getValueAttrOfElement(addressStreetField);
    }

    @Step("Type address building")
    public void typeAddressBuilding(String text){
        type(addressBuildingField, text);
    }

    @Step("Get address building")
    public String getAddressBuilding(){
        return getValueAttrOfElement(addressBuildingField);
    }

    @Step("Type address office: {0}")
    public void typeAddressOffice(String text){
        type(addressOfficeField, text);
    }

    @Step("Get address office")
    public String getAddressOffice(){
        return getValueAttrOfElement(addressOfficeField);
    }

    @Step("Select availability by value: {0}")
    public void selectAvailabilityByValue(String value){
        selectionFromDropListByValue(availabilitySelect, value);
    }

    @Step("Get availability value")
    public String getAvailabilityValue(){
        return getValueOfSelectedItem(availabilitySelect);
    }

    @Step("Select city code:")
    public void selectCityByValue(String value){
        selectionFromDropListByValue(citySelect, value);
    }

    @Step("Get city code")
    public String getCityValue(){
        return getValueOfSelectedItem(citySelect);
    }

    @Step("Select experience by value: {0}")
    public void selectExperienceByValue(String value){
        selectionFromDropListByValue(experienceSelect, value);
    }

    @Step("Get experience value")
    public String getExperienceValue(){
        return getValueOfSelectedItem(experienceSelect);
    }

    @Step("Type salary from: {0}")
    public void typeSalaryFrom(String text){
        type(salaryFromField, text);
    }

    @Step("Get salary from")
    public String getSalaryFrom(){
        return getValueAttrOfElement(salaryFromField);
    }

    @Step("Type salary to: {0}")
    public void typeSalaryTo(String text){
        type(salaryToField, text);
    }

    @Step("Get salary to")
    public String getSalaryTo(){
        return getValueAttrOfElement(salaryToField);
    }

    @Step("Select response destination by value: {0}")
    public void selectResponseDestinationByValue(String value){
        selectionFromDropListByValue(responseDestinationSelect, value);
    }

    @Step("Get response destination value")
    public String getResponseDestinationValue(){
        return getValueOfSelectedItem(responseDestinationSelect);
    }

    @Step("Get destination emails")
    public Set<String> getDestinationEmails(){
        List<WebElement> elements = getAllElements(addedDestinationEmail);
        Set<String> result = new HashSet<>();
        for (WebElement e: elements) {
            result.add(getTextFromWebElement(e).trim());
        }
        return result;
    }

    @Step("Delete destination email at place: {0}")
    public void deleteDestinationEmail(int number){
        getAllElements(destinationEmailDeleteElement).get(number).click();
    }

    @Step("Click ADD DESTINATION EMAIL")
    public void clickAddDestinationEmailLink(){
        click(addEmailLink);
        waitForElementPresence(emailToAddField);
    }

    @Step("Type destination email: {0}")
    public void typeDestinationEmail(String text){
        type(emailToAddField, text);
    }

    @Step("Click ADD EMAIL button")
    public void clickAddDestinationEmailButton(){
        int beginCount = getAmountOfElements(addedDestinationEmail);
        click(addEmailButton);
        try {
            waitForAmountIncrease(addedDestinationEmail, beginCount);
        }catch (TimeoutException e){
            System.out.println("Email wasn't added!");
        }
    }

    @Step("Type URL of company's page: {0}")
    public void typeUrlToApplication(String text){
        type(urlToApplicationField, text);
    }

    @Step("Get URL of company's page")
    public String getUrlToApplication(){
        return getValueAttrOfElement(urlToApplicationField);
    }

    @Step("Select action after publication time ends: {0}")
    public void selectRepublicByValue(String value){
        selectionFromDropListByValue(rePublicSelect, value);
    }

    @Step("Get value of selected action after publication time ends")
    public String getRepublicValue(){
        return getValueOfSelectedItem(rePublicSelect);
    }

    @Step("Check Make HOT")
    public void checkMakeHot(){
        click(makeHotCheckBox);
    }

    @Step("Verify if MAKE HOT is checked")
    public boolean isCheckedMakeHot(){
        return isChecked(makeHotChecked);
    }

    @Step("Select hot time by value: {0}")
    public void selectHotDaysByValue(String value){
        selectionFromDropListByValue(hotDaysSelect, value);
    }

    @Step("Get hot time value")
    public String getHotDaysValue(){
        return getValueOfSelectedItem(hotDaysSelect);
    }

    @Step("Click CANCEL")
    public void clickCancel(){
        click(cancelButton);
    }

    @Step("Click SAVE")
    public void clickSave(){
        click(saveButton);
    }

    @Step("Click SAVE TO DRAFTS")
    public void clickSaveInDrafts(){
        click(saveInDraftsButton);
    }

    @Step("Click PUBLISH")
    public void clickPublish(){
        click(publishButton);
    }

    public Set<By> getIgnoreSet(){
        Set<By> ignore = new HashSet<>();
        ignore.add(standardButton);
        ignore.add(premiumButton);
        return ignore;
    }
}
