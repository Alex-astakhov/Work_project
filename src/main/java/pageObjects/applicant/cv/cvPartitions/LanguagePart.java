package pageObjects.applicant.cv.cvPartitions;

import dataModels.applicant.cvParts.LanguageData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageObjects.Popup;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by Asta on 11.01.2017.
 */
public class LanguagePart extends Popup<LanguageData> {
    private By languageSelect = By.id("ResumeLanguages_language_id");
    private By languageLevelSelect = By.id("ResumeLanguages_possession");
    private By mobSaveButton = By.cssSelector("[data-test=button-save]");
    private By mobSaveAndAddButton = By.cssSelector("[data-test=button-save-and-add]");
    private By mobCancelButton = By.cssSelector("[data-test=button-cancel]");
    private By mobDeleteLink = By.cssSelector("[data-test=button-delete]");

    @Override
    public void fillAllFields(LanguageData data){
        selectLastLanguage(data.getLanguageValue());
        selectLastLanguageLevel(data.getLanguageLevelValue());
    }

    @Step("Add all languages on mobile")
    public void mobAddAllLanguages(Set<LanguageData> data){
        Iterator<LanguageData> iterator = data.iterator();
        while (iterator.hasNext()){
            fillAllFields(iterator.next());
            if (iterator.hasNext()){
                mobClickSaveAndAdd();
            }
            else {
                mobClickSave();
            }
        }
    }

    @Override
    public LanguageData getDataFromPopup(){
        String languageValue = getValueAttrOfElement(languageSelect);
        String languageLevelValue = getValueAttrOfElement(languageLevelSelect);
        return new LanguageData(languageValue, languageLevelValue);
    }

    @Step("Select language value {0}")
    public void selectLanguage(String value){
        selectionFromDropListByValue(languageSelect, value);
    }

    @Step("Select language level value {0}")
    public void selectLanguageLevel(String value){
        selectionFromDropListByValue(languageLevelSelect, value);
    }

    @Step("Select last language value {0}")
    public void selectLastLanguage(String value){
        List<WebElement> elements = driver().findElements(languageSelect);
        selectionFromDropListByValue(elements.get(elements.size() - 1), value);
    }

    @Step("Select last language level value {0}")
    public void selectLastLanguageLevel(String value){
        List<WebElement> elements = driver().findElements(languageLevelSelect);
        selectionFromDropListByValue(elements.get(elements.size() - 1), value);
    }

    @Step("Click mobile SAVE")
    public void mobClickSave(){
        click(mobSaveButton);
    }

    @Step("Click mobile SAVE AND ADD")
    public void mobClickSaveAndAdd(){
        click(mobSaveAndAddButton);
    }

    @Step("Click mobile CANCEL")
    public void mobClickCancel(){
        click(mobCancelButton);
    }

    @Step("Click mobile DELETE")
    public void mobClickDelete(){
        click(mobDeleteLink);
    }
}
