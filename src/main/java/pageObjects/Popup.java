package pageObjects;

import core.Constants;
import core.MethodsFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import ru.yandex.qatools.allure.annotations.Step;

public abstract class Popup <T> extends MethodsFactory {
    private By shownPopup = By.cssSelector(".b-popup.active");
    private By loadingPopup = By.cssSelector(".b-popup.active.load");
    private By saveButton = Constants.CSS_POP_UP_SAVE;
    private By cancelButton = Constants.CSS_POP_UP_CANCEL;

    @Step("Wait for popup to be shown")
    public void waitForPopupToBeShown(){
        waitForElementPresence(shownPopup);
        waitForElementVisibility(saveButton);
        waitForContentToBeLoaded();
        delay(500);
    }

    @Step("Wait for popup to be hidden")
    protected boolean waitForPopupToBeHidden(){
        try {
            waitForElementDisappear(shownPopup);
        } catch (TimeoutException e){
            System.out.println("POPUP HIDE ERROR!!!!!");
            return false;
        }
        return true;
    }

    @Step("Wait for popup content to be loaded")
    private void waitForContentToBeLoaded(){
        waitForElementDisappear(loadingPopup);
    }

    @Step("Click SAVE")
    public boolean clickSave(){
        click(saveButton);
        return waitForPopupToBeHidden();
    }

    @Step("Click CANCEL")
    public void clickCancel(){
        click(cancelButton);
        waitForPopupToBeHidden();
    }

    @Step("Fill all fields")
    public abstract void fillAllFields(T data);

    @Step("Get data from popup")
    public abstract T getDataFromPopup();
}
