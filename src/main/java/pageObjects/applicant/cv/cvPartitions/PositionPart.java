package pageObjects.applicant.cv.cvPartitions;

import core.Constants;
import dataModels.applicant.cvParts.PositionData;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import pageObjects.Popup;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Alex Astakhov on 25.11.2016.
 */
public class PositionPart extends Popup<PositionData> {

    private By positionNameField = By.id("Resume_position_name");
    private By employmentTypeSelect = By.id("Resume_availability_id");
    private By mobFullTimeRadio = By.cssSelector("#Resume_availability_id_0 + label");
    private By mobPartTimeRadio = By.cssSelector("#Resume_availability_id_1 + label");
    private By mobRemoteWorkRadio = By.cssSelector("#Resume_availability_id_2 + label");
    private By mobCheckedEmploymentType = By.cssSelector("#Resume_availability_id [checked]");
    private By salaryField = By.id("Resume_salary");
    private By transferRadioNo = By.cssSelector("#Resume_move_0 + label");
    private By transferRadioYes = By.cssSelector("#Resume_move_1 + label");
    private By checkedTransferRadio = By.cssSelector("#Resume_move [checked]");
    private By mobCityToMoveSelect = By.cssSelector("[data-test=already-added-cities-wrapper] [data-test=city-drop-down]");
    private By cityToMoveSelect = By.id("cities_list");
    private By cityToMoveDeleteElement = By.cssSelector("[data-test=delete-city]");
    private By mobLastCityToMoveSelect = By.xpath("(.//*[@id='Cities'])[last()]");
    private By mobDeleteCityToMoveLink = By.cssSelector("[data-test=delete-city-drop-down]");
    private By mobAddCityToMoveLink = By.cssSelector("[data-test=add-city-in]");
    private By businessTripRadioNo = By.cssSelector("#Resume_ready_for_business_trip_0 + label");
    private By businessTripRadioYes = By.cssSelector("#Resume_ready_for_business_trip_1 + label");
    private By checkedBusinessTripRadio = By.cssSelector("#Resume_ready_for_business_trip [checked]");
    private By mobSaveButton = By.cssSelector("[data-test=button-save]");
    private By mobCancelButton = By.cssSelector("[data-test=button-cancel]");


    @Override
    public void fillAllFields(PositionData data){
        typeSalary(data.getSalary());
        if (data.isTransferPossibility())
            checkTransferYes();
        else
            checkTransferNo();
        if (data.isTransferPossibility()){
            waitForElementPresence(cityToMoveSelect);
            Set<String> cities = data.getCityValues();
            for (String c: cities) {
                selectCityToMove(c);
            }
            selectCityToMove("26255");
            deleteLastCityToMove();
        }
        boolean popup = presenceOfElement(Constants.POPUP_CONTENT);
        if (popup)
            scrollToElementOnPopup(Constants.POPUP_CONTENT, businessTripRadioYes);
        if (data.isBusinessTripPossibility())
            checkBusinessTripYes();
        else
            checkBusinessTripNo();
        selectEmploymentType(data.getAvailabilityTypeValue());
        if (popup)
            scrollToElementOnPopup(Constants.POPUP_CONTENT, positionNameField);
        typePositionName(data.getPositionName());
    }

    @Step("Fill position part")
    public void mobFillAllFields(PositionData data){
        switch (data.getAvailabilityTypeValue()){
            case "1":
                click(mobFullTimeRadio);
                break;
            case "2":
                click(mobPartTimeRadio);
                break;
            case "5":
                click(mobRemoteWorkRadio);
                break;
        }
        typeSalary(data.getSalary());
        if (data.isTransferPossibility()){
            click(transferRadioYes);
            waitForElementVisibility(mobLastCityToMoveSelect);
            Set<String> cities = data.getCityValues();
            int count = 1;
            for (String c: cities) {
                selectionFromDropListByValue(mobLastCityToMoveSelect, c);
                click(mobAddCityToMoveLink);
                waitForAmountIncrease(mobCityToMoveSelect, count++);
            }
            List<WebElement> deleteLinks = driver().findElements(mobDeleteCityToMoveLink);
            click(deleteLinks.get(deleteLinks.size() - 1));
            waitForAmountReduction(mobCityToMoveSelect, count);
        }
        else{
            click(transferRadioNo);
        }
        if (data.isBusinessTripPossibility())
            checkBusinessTripYes();
        else
            checkBusinessTripNo();
        typePositionName(data.getPositionName());
    }

    @Step("Get position information on mobile")
    public PositionData mobGetPositionData(){
        String positionName = getPositionName();
        String emplTypeValue = mobGetEmploymentTypeValue();
        String salary = getSalary();
        boolean transferPossibility;
        Set<String> cityValues = null;
        if (isTransferNo()){
            transferPossibility = false;
        }
        else {
            transferPossibility = true;
            List<WebElement> cities = driver().findElements(mobCityToMoveSelect);
            cityValues = new HashSet<>();
            for (int i = 0; i < cities.size(); i++) {
                cityValues.add(getValueAttrOfElement(getSelectedItem(cities.get(i))));
            }
        }
        boolean businessTrip;
        businessTrip = !isBusinessTripNo();
        return new PositionData(positionName, emplTypeValue, salary, transferPossibility, cityValues, businessTrip);
    }

    @Override
    public PositionData getDataFromPopup(){
        String positionName = getPositionName();
        String emplTypeValue = getEmploymentTypeValue();
        String salary = getSalary();
        boolean transferPossibility;
        Set<String> cityValues = null;
        if (isTransferNo()){
            transferPossibility = false;
        }
        else {
            transferPossibility = true;
            List<WebElement> cities = driver().findElements(cityToMoveDeleteElement);
            cityValues = new HashSet<>();
            for (int i = 0; i < cities.size(); i++) {
                cityValues.add(getAttributeOfElement(cities.get(i), "data-id"));
            }
        }
        boolean businessTrip;
        businessTrip = !isBusinessTripNo();
        return new PositionData(positionName, emplTypeValue, salary, transferPossibility, cityValues, businessTrip);
    }

    @Step("Type position name: {0}")
    public void typePositionName(String name){
        type(positionNameField, name);
    }

    @Step("get position title")
    public String getPositionName(){
        return getValueAttrOfElement(positionNameField);
    }

    @Step("Select employment type: {0}")
    public void selectEmploymentType(String typeValue){
        selectionFromDropListByValue(employmentTypeSelect, typeValue);
    }

    @Step("Get employment type")
    public String getEmploymentTypeValue(){
        return getValueAttrOfElement(getSelectedItem(employmentTypeSelect));
    }

    @Step("Get employment type on mobile")
    public String mobGetEmploymentTypeValue(){
        return getValueAttrOfElement(mobCheckedEmploymentType);
    }

    @Step("Type salary: {0}")
    public void typeSalary(String salary){
        type(salaryField, salary);
    }

    @Step("Get salary")
    public String getSalary(){
        return getValueAttrOfElement(salaryField);
    }

    @Step("Check TRANSFER NO")
    public void checkTransferNo(){
        if (!isTransferNo())
            click(transferRadioNo);
    }

    @Step("Verify if TRANSFER NO is checked")
    public boolean isTransferNo(){
        return getValueAttrOfElement(checkedTransferRadio).equals("0");
    }

    @Step("Check TRANSFER YES")
    public void checkTransferYes(){
        if (!isTransferYes())
            click(transferRadioYes);
    }

    @Step("Verify if TRANSFER YES is checked")
    public boolean isTransferYes(){
        return getValueAttrOfElement(checkedTransferRadio).equals("1");
    }

    @Step("Check BUSINESS TRIP NO")
    public void checkBusinessTripNo(){
        click(businessTripRadioNo);
    }

    @Step("Verify if BUSINESS TRIP NO is checked")
    public boolean isBusinessTripNo(){
        return getValueAttrOfElement(checkedBusinessTripRadio).equals("0");
    }

    @Step("Check BUSINESS TRIP YES")
    public void checkBusinessTripYes(){
        click(businessTripRadioYes);
    }

    @Step("Verify if BUSINESS TRIP NO is checked")
    public boolean isBusinessTripYes(){
        return getValueAttrOfElement(checkedBusinessTripRadio).equals("1");
    }

    @Step("Select city for transfer")
    private void selectCityToMove(String value){
        int count = getAllElements(cityToMoveDeleteElement).size();
        selectionFromDropListByValue(cityToMoveSelect, value);
        try {
            waitForAmountIncrease(cityToMoveDeleteElement, count);
        }catch (TimeoutException e){
            e.printStackTrace();
        }
    }

    @Step("Delete last selected city for transfer")
    private void deleteLastCityToMove(){
        List<WebElement> elements = getAllElements(cityToMoveDeleteElement);
        int count = elements.size();
        if (presenceOfElement(Constants.POPUP_CONTENT))
            scrollToElementOnPopup(Constants.POPUP_CONTENT, positionNameField);
        click(elements.get(count - 1));
        waitForAmountReduction(cityToMoveDeleteElement, count);
    }

    @Step("Click mobile CANCEL")
    public void mobClickCancel(){
        click(mobCancelButton);
    }

    @Step("Click mobile SAVE")
    public void mobClickSave(){
        click(mobSaveButton);
    }

}
