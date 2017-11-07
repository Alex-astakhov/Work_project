package pageObjects.applicant.cv.cvPartitions;

import dataModels.applicant.cvParts.DriversData;
import org.openqa.selenium.By;
import pageObjects.Popup;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Alex Astakhov on 27.11.2016.
 */
public class DriversPart extends Popup<DriversData> {

    private By categoryAChecker = By.cssSelector("#UsersDriverLicence_0 + label");
    private By categoryACheckerValue = By.id("UsersDriverLicence_0");
    private By categoryBChecker = By.cssSelector("#UsersDriverLicence_1 + label");
    private By categoryBCheckerValue = By.id("UsersDriverLicence_1");
    private By categoryCChecker = By.cssSelector("#UsersDriverLicence_2 + label");
    private By categoryCCheckerValue = By.id("UsersDriverLicence_2");
    private By categoryDChecker = By.cssSelector("#UsersDriverLicence_3 + label");
    private By categoryDCheckerValue = By.id("UsersDriverLicence_3");
    private By categoryEChecker = By.cssSelector("#UsersDriverLicence_4 + label");
    private By categoryECheckerValue = By.id("UsersDriverLicence_4");
    private By haveCarRadioNo = By.cssSelector("#UsersInfo_has_car_0 + label");
    private By haveCarRadioYes = By.cssSelector("#UsersInfo_has_car_1 + label");
    private By checkedCarRadio = By.cssSelector("#UsersInfo_has_car [checked]");
    private By mobSaveButton = By.cssSelector("[data-test=button-save]");
    private By mobCancelButton = By.cssSelector("[data-test=button-cancel]");

    @Override
    public void fillAllFields(DriversData data){
        clearAllCategories();
        Set<Character> categories = data.getDriverCategories();
        if (categories.contains('A'))
            checkCategoryA();
        if (categories.contains('B'))
            checkCategoryB();
        if (categories.contains('C'))
            checkCategoryC();
        if (categories.contains('D'))
            checkCategoryD();
        if (categories.contains('E'))
            checkCategoryE();
        if (data.isHaveCar())
            checkHaveCarYes();
        else
            checkHaveCarNo();
    }

    @Step("Uncheck all driver's categories")
    public void clearAllCategories(){
        uncheck(categoryAChecker, categoryACheckerValue);
        uncheck(categoryBChecker, categoryBCheckerValue);
        uncheck(categoryCChecker, categoryCCheckerValue);
        uncheck(categoryDChecker, categoryDCheckerValue);
        uncheck(categoryEChecker, categoryECheckerValue);
    }

    @Override
    public DriversData getDataFromPopup(){
        Set<Character> categories = new HashSet<>();
        if (isCheckedCategoryA())
            categories.add('A');
        if (isCheckedCategoryB())
            categories.add('B');
        if (isCheckedCategoryC())
            categories.add('C');
        if (isCheckedCategoryD())
            categories.add('D');
        if (isCheckedCategoryE())
            categories.add('E');
        return new DriversData(categories, isHaveCar());
    }

    @Step("Check category A")
    public void checkCategoryA(){
        click(categoryAChecker);
   }

    @Step("Check category B")
    public void checkCategoryB(){
        click(categoryBChecker);
    }

    @Step("Check category C")
    public void checkCategoryC(){
        click(categoryCChecker);
    }

    @Step("Check category D")
    public void checkCategoryD(){
        click(categoryDChecker);
    }

    @Step("Check category E")
    public void checkCategoryE(){
        click(categoryEChecker);
    }

    @Step("Choose HAVE CAR")
    public void checkHaveCarNo(){
        click(haveCarRadioNo);
    }

    @Step("Choose NO CAR")
    public void checkHaveCarYes(){
        click(haveCarRadioYes);
    }

    @Step("Click mobile SAVE")
    public void mobClickSave(){
        click(mobSaveButton);
    }

    @Step("Click mobile CANCEL")
    public void mobClickCancel(){
        click(mobCancelButton);
    }

    @Step("Verify if category A is checked")
    public boolean isCheckedCategoryA(){
        return isChecked(categoryACheckerValue);
    }

    @Step("Verify if category B is checked")
    public boolean isCheckedCategoryB(){
        return isChecked(categoryBCheckerValue);
    }

    @Step("Verify if category C is checked")
    public boolean isCheckedCategoryC(){
        return isChecked(categoryCCheckerValue);
    }

    @Step("Verify if category D is checked")
    public boolean isCheckedCategoryD(){
        return isChecked(categoryDCheckerValue);
    }

    @Step("Verify if category E is checked")
    public boolean isCheckedCategoryE(){
        return isChecked(categoryECheckerValue);
    }

    @Step("Verify if HAVE CAR is checked")
    public boolean isHaveCar(){
        return !getValueAttrOfElement(checkedCarRadio).equals("0");
    }
}
