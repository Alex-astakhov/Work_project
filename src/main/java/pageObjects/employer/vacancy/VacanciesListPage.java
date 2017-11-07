package pageObjects.employer.vacancy;

import core.MethodsFactory;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.List;

/**
 * Created by Asta on 21.12.2016.
 */
public class VacanciesListPage extends MethodsFactory {

    private By managersSelect = By.cssSelector("[data-test=change-manager-vacancy]");
    private By editVacancyLink = By.cssSelector("[data-test=link-to-edit-vacancy]");
    private By createVacancyButton = By.cssSelector("[data-test=add-vacancy]");

    @Step("Get count of active vacancies")
    public int getCountOfVacancies(){
        return getAmountOfElements(editVacancyLink);
    }

    @Step("Get list of managers from select")
    public List<String> getManagersValues(){
        return getAllOptionsValuesFromSelect(managersSelect);
    }

    @Step("Click CREATE VACANCY")
    public void clickCreateVacancy(){
        click(createVacancyButton);
    }

    @Step("Click EDIT VACANCY by title: {0}")
    public void clickEditVacancyByTitle(String title){
        click(By.xpath(getXpathOfEditLinkByTitle(title)));
    }

    @Step("Suspend vacancy by title: {0}")
    public void suspendVacancyByTitle(String title){
        click(By.xpath(getXpathOfMoreSpanByVacancyTitle(title)));
        click(By.xpath(getXpathOfSuspenseLinkByVacancyTitle(title)));
    }

    @Step("Restore vacancy by title: {0}")
    public void restoreVacancyByTitle(String title){
        click(By.xpath(getXpathOfRestoreLinkByVacancyTitle(title)));
    }

    @Step("Finish vacancy by title: {0}")
    public void finishVacancyByTitle(String title){
        click(By.xpath(getXpathOfMoreSpanByVacancyTitle(title)));
        click(By.xpath(getXpathOfFinishLinkByVacancyTitle(title)));
        acceptAlert();
    }

    @Step("Delete vacancy by title: {0}")
    public void deleteVacancyByTitle(String title){
        int beginCount = getAmountOfElements(editVacancyLink);
        click(By.xpath(getXpathOfMoreSpanByVacancyTitle(title)));
        click(By.xpath(getXpathOfDeleteLinkByVacancyTitle(title)));
        acceptAlert();
        waitForAmountReduction(editVacancyLink, beginCount);
    }

    @Step("Click responses of vacancy {0}")
    public void clickResponsesByVacancyTitle(String title){
        click(By.xpath(getResponseButtonXpathByTitle(title)));
    }

    private String getXpathOfMoreSpanByVacancyTitle(String title){
        return ".//*[@data-test='link-to-edit-vacancy' and text()='" + title + "']/..//*[@data-test='more-action-on-vacancy']";
    }

    private String getXpathOfFinishLinkByVacancyTitle(String title){
        return ".//*[@data-test='link-to-edit-vacancy' and text()='" + title + "']/..//*[@data-test='finish']";
    }

    private String getXpathOfSuspenseLinkByVacancyTitle(String title){
        return ".//*[@data-test='link-to-edit-vacancy' and text()='" + title + "']/..//*[@data-test='suspend']";
    }

    private String getXpathOfRestoreLinkByVacancyTitle(String title){
        return ".//*[@data-test='link-to-edit-vacancy' and text()='" + title + "']/..//*[contains(@class,'restore')]";
    }

    private String getXpathOfDeleteLinkByVacancyTitle(String title){
        return ".//*[@data-test='link-to-edit-vacancy' and text()='" + title + "']/..//*[@data-test='delete']";
    }

    private String getXpathOfEditLinkByTitle(String title){
        return ".//*[@data-test='link-to-edit-vacancy' and text()='" + title + "']";
    }

    public String getVacancyStatusByTitle(String title){
        By status = By.xpath(".//*[@data-test='link-to-edit-vacancy' and text()='" + title + "']/../..//*[contains(@class,'b-vacancy-status')]");
        return getTextByLocator(status);
    }

    public String getResponseButtonXpathByTitle(String title){
        return ".//*[@data-test='link-to-edit-vacancy' and text()='" + title + "']/../..//*[@data-test='show-response']";
    }

    @Step("Select all managers")
    public void selectAllManagersVacancies(){
        waitForElementPresence(managersSelect);
        waitForContentLoaded(500);
        selectionFromDropListByValue(managersSelect, "0");
        waitForUrlContains("manager_id/0");
        waitForPageToBeLoaded();
    }

    @Step("Select manager by value {0}")
    public void selectManagerByValue(String value){
        waitForContentLoaded(500);
        selectionFromDropListByValue(managersSelect, value);
        waitForUrlContains("manager_id/" + value);
        waitForPageToBeLoaded();
    }

}
