package pageObjects.applicant.notebook;

import core.MethodsFactory;
import dataModels.SubscriptionData;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * Created by Asta on 06.03.2017.
 */
public class SubscriptionPageMobile extends MethodsFactory {
    private By vacancyTitleField = By.cssSelector("#SubscriptionApplicantVacancy_title");
    private By citySelect = By.cssSelector("#SubscriptionApplicantVacancy_city_id");
    private By experienceField = By.cssSelector("#SubscriptionApplicantVacancy_experience");
    private By salaryField = By.cssSelector("#SubscriptionApplicantVacancy_salary");
    private By availabilitySelect = By.cssSelector("#SubscriptionApplicantVacancy_availability_id");
    private By dailyCheckbox = By.cssSelector("#SubscriptionApplicantVacancy_interval_send_0 + label");
    private By weeklyCheckbox = By.cssSelector("#SubscriptionApplicantVacancy_interval_send_1 + label");
    private By neverCheckbox = By.cssSelector("#SubscriptionApplicantVacancy_interval_send_2 + label");
    private By saveButton = By.cssSelector("[data-test=button-save]");
    private By cancelButton = By.cssSelector("[data-test=button-cancel]");

    @Step("Fill subscription  ")
    public void fillSubscriptionBody(SubscriptionData data){
        switch (data.getPeriodicity()){
            case 0:
                checkDaily();
                break;
            case 1:
                checkWeekly();
                break;
            case 2:
                checkNever();
        }
        if (!data.getSalary().isEmpty())
            typeSalary(data.getSalary());
        if (!data.getExperience().isEmpty())
            typeExperience(data.getExperience());
        if (!data.getCityValue().equals(""))
            selectCity(data.getCityValue());
        typeVacancyTitle(data.getVacancyTitle());
        if (!data.getAvailabilityTypeValue().equals("0"))
            selectAvailability(data.getAvailabilityTypeValue());
    }

    @Step("Type QUERY: {0}")
    public void typeVacancyTitle(String title){
        type(vacancyTitleField, title);
    }

    @Step("Get QUERY")
    public String getVacancyTitle(){
        return getValueAttrOfElement(vacancyTitleField);
    }

    @Step("Select CITY by code: {0}")
    public void selectCity(String value){
        selectionFromDropListByValue(citySelect, value);
    }

    @Step("Get CITY code")
    public String getCity(){
        return getValueAttrOfElement(getSelectedItem(citySelect));
    }

    @Step("Type experience: {0}")
    public void typeExperience(String experience){
        type(experienceField, experience);
    }

    @Step("Get experience")
    public String getExperience(){
        String exp = getValueAttrOfElement(experienceField);
        if (exp.equals("0"))
            exp = "";
        return exp;
    }

    @Step("Type salary: {0}")
    public void typeSalary(String salary){
        type(salaryField, salary);
    }

    @Step("Get salary")
    public String getSalary(){
        String sal = getValueAttrOfElement(salaryField);
        if (sal.equals("0"))
            sal = "";
        return sal;
    }

    @Step("Select availability by value: {0}")
    public void selectAvailability(String value){
        selectionFromDropListByValue(availabilitySelect, value);
    }

    @Step("Get availability value")
    public String getAvailability(){
        return getValueAttrOfElement(getSelectedItem(availabilitySelect));
    }

    @Step("Check daily")
    public void checkDaily(){
        click(dailyCheckbox);
    }

    @Step("Check daily")
    public void checkWeekly(){
        click(weeklyCheckbox);
    }

    @Step("Check daily")
    public void checkNever(){
        click(neverCheckbox);
    }

    @Step("Click SAVE")
    public void clickSave(){
        click(saveButton);
    }

    @Step("Click CANCEL")
    public void clickCancel(){
        click(cancelButton);
    }
}
