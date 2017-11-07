package pageObjects.applicant.cv.cvPartitions;

import core.Constants;
import core.MethodsFactory;
import dataModels.applicant.cvParts.ExperienceData;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.interactions.Actions;
import pageObjects.Popup;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.concurrent.TimeUnit;

/**
 * Created by Alex Astakhov on 27.11.2016.
 */
public class ExperiencePart extends Popup<ExperienceData> {
    private By companyNameField = By.id("ResumeExperience_organization_name");
    private By citySelect = By.id("ResumeExperience_city_id");
    private By companySiteField = By.id("ResumeExperience_organization_url");
    private By positionNameField = By.id("ResumeExperience_position_name");
    private By monthOfSelect = By.id("ResumeExperience_month_of");
    private By yearOfSelect = By.id("ResumeExperience_year_of");
    private By monthToSelect = By.id("ResumeExperience_month_to");
    private By yearToSelect = By.id("ResumeExperience_year_to");
    private By forPresentChecker = By.cssSelector("#ResumeExperience_sofar+label");
    private By forPresentCheckerInBuilder = By.cssSelector("[id^=for_present]+label");
    private By forPresentCheckerValue = By.id("ResumeExperience_sofar");
    private By forPresentCheckerValueInBuilder = By.cssSelector("[id^=for_present]");
    private String dutiesFrameName = "ResumeExperience_role_ifr";
    private By mobDutiesField = By.id("ResumeExperience_role");
    private By dutiesTextField = By.id("tinymce");
    private By mobCancelButton = By.cssSelector("[data-test=button-cancel]");
    private By mobSaveButton = By.cssSelector("[data-test=button-save]");
    private By mobSaveAndAddButton = By.cssSelector("[data-test=button-save-and-add]");
    private By mobDeleteLink = By.cssSelector("[data-test=button-delete]");


    private By mobNoExperienceChecker = By.cssSelector("[for=without_experience]");

    @Override
    public void fillAllFields(ExperienceData data){
        typeCompanyName(data.getCompanyName());
        selectCity(data.getCityChoiceValue());
        if (!data.getCompanySite().isEmpty())
            typeCompanySite(data.getCompanySite());
        selectMonthOf(data.getMonthOfIndex());
        selectYearOf(data.getYearOfValue());
        if (data.isForPresent()){
            checkForPresent();
        }
        else{
            selectMonthTo(data.getMonthToIndex());
            selectYearTo(data.getYearToValue());
        }
        typeDuties(data.getDuties());
        typePositionName(data.getPosition());
    }

    @Step("Fill position part on mobile")
    public void mobFillAllFields(ExperienceData data){
        if (data.isNoExperience()){
            mobCheckNoExperience();
            return;
        }
        typeCompanyName(data.getCompanyName());
        selectCity(data.getCityChoiceValue());
        if (!data.getCompanySite().isEmpty())
            typeCompanySite(data.getCompanySite());
        selectMonthOf(data.getMonthOfIndex());
        selectYearOf(data.getYearOfValue());
        if (data.isForPresent()){
            checkForPresent();
        }
        else{
            selectMonthTo(data.getMonthToIndex());
            selectYearTo(data.getYearToValue());
        }
        mobTypeDuties(data.getDuties());
        typePositionName(data.getPosition());
    }

    @Step("Add all experiences on mobile")
    public void mobAddAllExperienceData(ExperienceData[] experienceDatas){
        int count = experienceDatas.length;
        for (int i = 0; i < count; i++) {
            fillAllFields(experienceDatas[i]);
            if (i == count - 1)
                mobClickSave();
            else
                mobClickSaveAndAdd();
        }
    }

    @Step("Get experience information on mobile")
    public ExperienceData mobGetExperienceData(){
        String company = getCompanyName();
        String cityValue = getCityValue();
        String site = getCompanySite();
        String position = getPositionName();
        int monthOf = getMonthOfIndex();
        String yearOf = getYearOfValue();
        int monthTo = 0;
        String yearTo = null;
        boolean forPresent = isCheckedForPresent();
        if (!forPresent){
            monthTo = getMonthToIndex();
            yearTo = getYearToValue();
        }
        String duties = getDuties();
        return new ExperienceData(false, company, cityValue, site, position, monthOf, yearOf, monthTo, yearTo, forPresent, duties);
    }

    @Override
    public ExperienceData getDataFromPopup(){
        String company = getCompanyName();
        String cityValue = getCityValue();
        String site = getCompanySite();
        String position = getPositionName();
        int monthOf = getMonthOfIndex();
        String yearOf = getYearOfValue();
        int monthTo = 0;
        String yearTo = null;
        boolean forPresent = isCheckedForPresent();
        if (!forPresent){
            monthTo = getMonthToIndex();
            yearTo = getYearToValue();
        }
        String duties = getDuties();
        return new ExperienceData(false, company, cityValue, site, position, monthOf, yearOf, monthTo, yearTo, forPresent, duties);
    }

    @Step("Type company name: {0}")
    public void typeCompanyName(String name){
        type(companyNameField, name);
    }

   @Step("Select city by code: {0}")
   public void selectCity(String value){
        selectionFromDropListByValue(citySelect, value);
   }

   @Step("Type company site URL: {0}")
   public void typeCompanySite(String site){
       type(companySiteField, site);
   }

    @Step("Type position name: {0}")
    public void typePositionName(String position){
        type(positionNameField, position);
    }

    @Step("Select month since: {0}")
    public void selectMonthOf(int monthIndex){
        selectionFromDropListByIndex(monthOfSelect, monthIndex);
    }

    @Step("Select year since: {0}")
    public void selectYearOf(String yearValue){
        selectionFromDropListByValue(yearOfSelect, yearValue);
    }

    @Step("Select month till: {0}")
    public void selectMonthTo(int monthIndex){
        selectionFromDropListByIndex(monthToSelect, monthIndex);
    }

    @Step("Select year till: {0}")
    public void selectYearTo(String yearValue){
        selectionFromDropListByValue(yearToSelect, yearValue);
    }

    @Step("Check FOR PRESENCE")
    public void checkForPresent(){
        click(forPresentChecker);
    }

    @Step("Check FOR PRESENCE in cv builder")
    public void checkForPresentInBuilder(){
        click(forPresentCheckerInBuilder);
    }

    @Step("Uncheck FOR PRESENT in cv builder")
    public void uncheckForPresentInCVBuilder(){
        if (isCheckedForPresentInBuilder())
            checkForPresentInBuilder();
    }

    @Step("Uncheck FOR PRESENT in CV")
    public void uncheckForPresent(){
        if (isCheckedForPresent())
            checkForPresent();
    }

    @Step("Type duties: {0}")
    public void typeDuties(String duties){
        typeInTinymce(duties);
    }

    @Step("Type duties mobile: {0}")
    public void mobTypeDuties(String duties){
        if (presenceOfElement(By.cssSelector("iframe"), 1))
            typeInTinymce(duties);
        else
            type(mobDutiesField, duties);
    }

    @Step("Click mobile CANCEL")
    public void mobClickCancel(){
        click(mobCancelButton);
    }

    @Step("Click mobile SAVE")
    public void mobClickSave(){
        click(mobSaveButton);
    }

    @Step("Click mobile SAVE AND ADD")
    public void mobClickSaveAndAdd(){
        click(mobSaveAndAddButton);
    }

    @Step("Click mobile DELETE")
    public void mobClickDelete(){
        click(mobDeleteLink);
        if(!acceptAlert()){
            click(mobDeleteLink);
            acceptAlert();
        }
    }

    @Step("Verify if NO EXPERIENCE is checked")
    public void mobCheckNoExperience(){
        click(mobNoExperienceChecker);
    }

    @Step("Get company title")
    public String getCompanyName(){
        return getValueAttrOfElement(companyNameField);
    }

    @Step("Get city code")
    public String getCityValue(){
        return getValueAttrOfElement(getSelectedItem(citySelect));
    }

    @Step("Get company's site")
    public String getCompanySite(){
        return getValueAttrOfElement(companySiteField);
    }

    @Step("Get position title")
    public String getPositionName(){
        return getValueAttrOfElement(positionNameField);
    }

    @Step("Get begin month")
    public int getMonthOfIndex(){
        return Integer.parseInt(getValueAttrOfElement(getSelectedItem(monthOfSelect)));
    }

    @Step("Get begin year")
    public String getYearOfValue(){
        return getValueAttrOfElement(getSelectedItem(yearOfSelect));
    }

    @Step("Get end month")
    public int getMonthToIndex(){
        return Integer.parseInt(getValueAttrOfElement(getSelectedItem(monthToSelect)));
    }

    @Step("Get end year")
    public String getYearToValue(){
        return getValueAttrOfElement(getSelectedItem(yearToSelect));
    }

    @Step("Verify if FOR PRESENCE is checked")
    public boolean isCheckedForPresent(){
        return isChecked(forPresentCheckerValue);
    }

    @Step("Verify if FOR PRESENCE is checked in cv builder")
    public boolean isCheckedForPresentInBuilder(){
        return isChecked(forPresentCheckerValueInBuilder);
    }

    @Step("Get duties description")
    public String getDuties(){
        return getTextFromTinymce();
    }

}
