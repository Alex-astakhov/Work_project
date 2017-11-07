package pageObjects.commonPages;

import core.MethodsFactory;
import dataModels.SubscriptionData;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * Created by Asta on 27.02.2017.
 */
public class SubscriptionPage extends MethodsFactory {
    private By vacancyTitleField = By.cssSelector("[data-test=search-query]");
    private By citySelect = By.cssSelector("[data-test=search-dropdown]");
    private By salaryField = By.cssSelector("[data-test=search-salary]");
    private By experienceField = By.cssSelector("[data-test=search-experience]");
    private By ageField = By.cssSelector("[data-test=search-age]");
    private By availabilitySelect = By.cssSelector("[data-test=search-availability]");
    private By transferReadyChecker = By.cssSelector("[for=SubscriptionEmployerResume_move]");
    private By transferReadyValue = By.cssSelector("#ytSubscriptionEmployerResume_move");
    private By dailyCheckbox = By.cssSelector("[data-test=subscription-send][value='0']+label");
    private By weeklyCheckbox = By.cssSelector("[data-test=subscription-send][value='1']+label");
    private By neverCheckbox = By.cssSelector("[data-test=subscription-send][value='2']+label");
    private By cancelButton = By.cssSelector("[data-test=cancel]");
    private By saveButton = By.cssSelector("[data-test=submit]");

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
        typeVacancyTitle(data.getVacancyTitle());
        if (!data.getCityValue().equals(""))
            selectCity(data.getCityValue());
        if (!data.getAge().isEmpty())
            typeAge(data.getAge());
        if (!data.getAvailabilityTypeValue().equals("0"))
            selectAvailability(data.getAvailabilityTypeValue());
        if (data.isTransferReady())
            checkTransferReady();
    }

    @Step("Type search query: {0}")
    public void typeVacancyTitle(String vacancyTitle){
        type(vacancyTitleField, vacancyTitle);
    }

    @Step("Get search query")
    public String getVacancyTitle(){
        return getValueAttrOfElement(vacancyTitleField);
    }

    @Step("Select city by code: {0}")
    public void selectCity(String cityValue){
        selectionFromDropListByValue(citySelect, cityValue);
    }

    @Step("Get city code")
    public String getCityValue(){
        return getValueAttrOfElement(getSelectedItem(citySelect));
    }

    @Step("Type salary: {0}")
    public void typeSalary(String salary){
        type(salaryField, salary);
    }

    @Step("Get salary")
    public String getSalary(){
        String result = getValueAttrOfElement(salaryField);
        if (result.equals("0"))
            result = "";
        return result;
    }

    @Step("Type experience: {0}")
    public void typeExperience(String experience){
        type(experienceField, experience);
    }

    @Step("Get experience")
    public String getExperience(){
        String result = getValueAttrOfElement(experienceField);
        if (result.equals("0"))
            result = "";
        return result;
    }

    @Step("Type age: {0}")
    public void typeAge(String age){
        type(ageField, age);
    }

    @Step("Get age")
    public String getAge(){
        String result = getValueAttrOfElement(ageField);
        if (result.equals("0"))
            result = "";
        return result;
    }

    @Step("Select availability: {0}")
    public void selectAvailability(String value){
        selectionFromDropListByValue(availabilitySelect, value);
    }

    @Step("Get availability")
    public String getAvailabilityValue(){
        return getValueAttrOfElement(getSelectedItem(availabilitySelect));
    }

    @Step("Check transfer ready")
    public void checkTransferReady(){
        click(transferReadyChecker);
    }

    @Step("Verify if TRANSFER READY is checked")
    public boolean isTransferReadyChecked(){
        return isChecked(transferReadyValue);
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

    @Step("Click CANCEL")
    public void clickCancel(){
        click(cancelButton);
    }

    @Step("Click SAVE")
    public void clickSave(){
        click(saveButton);
    }
}
