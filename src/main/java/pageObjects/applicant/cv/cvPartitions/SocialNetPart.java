package pageObjects.applicant.cv.cvPartitions;

import dataModels.applicant.cvParts.SocialNetData;
import org.openqa.selenium.By;
import pageObjects.Popup;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by Asta on 28.12.2016.
 */
public class SocialNetPart extends Popup {
    private By socNetSelect = By.id("UsersSocialNetworks_network_id");
    private By socNetUrlField = By.id("UsersSocialNetworks_network_url");
    private By mobSaveButton = By.cssSelector("[data-test=button-save]");
    private By mobSaveAndAddButton = By.cssSelector("[data-test=button-save-and-add]");
    private By mobCancelButton = By.cssSelector("[data-test=button-cancel]");
    private By mobDeleteLink = By.cssSelector("[data-test=button-delete]");
    private By deleteElement = By.cssSelector("[data-test=social-networ-delete]");

    @Step("Select social net by value {0}, type url {1}")
    public void fillAllFields(String socNetValue, String socNetUrl){
        selectionFromDropListByValue(socNetSelect, socNetValue);
        type(socNetUrlField, socNetUrl);
    }

    @Step("Click mobile SAVE")
    public void mobClickSave(){
        click(mobSaveButton);
    }

    @Step("Click mobile CANCEL")
    public void mobClickCancel(){
        click(mobCancelButton);
    }

    @Step("Click mobile SAVE AND ADD")
    public void mobClickSaveAndAdd(){
        click(mobSaveAndAddButton);
    }

    @Step("Click mobile DELETE")
    public void mobClickDelete(){
        click(mobDeleteLink);
    }

    @Step("Click DELETE")
    public void clickDelete(){
        waitForElementPresence(deleteElement);
        click(deleteElement);
        acceptAlert();
        waitForPopupToBeHidden();
    }

    @Override
    public void fillAllFields(Object data) {

    }

    @Override
    public Object getDataFromPopup() {
        return null;
    }

    @Step("Add all social nets on mobile")
    public void mobAddAllSocNets(SocialNetData data){
        Map<String, String> socNets = data.getSocNets();
        Iterator<Map.Entry<String, String>> iterator = socNets.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, String> pair = iterator.next();
            fillAllFields(pair.getKey(), pair.getValue());
            if (iterator.hasNext()){
                mobClickSaveAndAdd();
            }
            else {
                mobClickSave();
            }
        }
    }

    @Step("Get socail net's type")
    public String getSocNetValue(){
        return getValueAttrOfElement(getSelectedItem(socNetSelect));
    }
    @Step("Get social net's URL")
    public String getSocNetUrl(){
        return getValueAttrOfElement(socNetUrlField);
    }
}
