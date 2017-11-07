package pageObjects.applicant.cv.cvPartitions;

import org.openqa.selenium.By;
import pageObjects.Popup;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * Created by Asta on 28.12.2016.
 */
public class GoalPart extends Popup<String> {

    private By mobGoalSaveButton = By.cssSelector("[data-test=button-save]");
    private By mobGoalCancelButton = By.cssSelector("[data-test=button-cancel]");

    @Override
    public void fillAllFields(String text){
        typeInTinymce(text);
    }

    @Step("Click mobile SAVE")
    public void mobClickSave(){
        click(mobGoalSaveButton);
    }

    @Step("Click mobile CANCEL")
    public void mobClickCancel(){
        click(mobGoalCancelButton);
    }

    @Override
    public String getDataFromPopup(){
        String result = getTextFromTinymce();
        return result;
    }
}
