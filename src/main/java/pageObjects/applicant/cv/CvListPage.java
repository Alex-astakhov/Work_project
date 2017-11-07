package pageObjects.applicant.cv;

import core.Constants;
import core.MethodsFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Asta on 27.12.2016.
 */
public class CvListPage extends MethodsFactory {

    private By createResumeButton = By.cssSelector("[data-test=create-resume-button]");
    private By resumeMoreLink = By.cssSelector("[data-test=data-more]");
    private By deleteResumeLink = By.cssSelector("[data-test=delete]");
    private By findVacanciesButton = By.cssSelector("[data-test=search]");
    private By editResumeLink = By.cssSelector("[data-test=position-name-resume]");
    private By banner = By.cssSelector(".b-banner");
    private By primaryUserInfo = By.cssSelector(".b-applicant-cv__primary-info");
    private By mobPrimaryUserInfo = By.cssSelector(".b-applicant-general-info__email");
    private By alertMessage = By.cssSelector(".b-message__description");

    @Step("Get this page")
    public void getPage(){
        String endpoint = "applicant/notebook";
        get(Constants.DOMEN + endpoint);
    }

    @Step("Click CREATE RESUME")
    public void clickCreateResume(){
        click(createResumeButton);
    }

    @Step("Click DELETE last created resume on mobile")
    public void mobDeleteLastResume(){
        int countBeforeDeleting = getAmountOfElements(deleteResumeLink);
        List<WebElement> elements = getAllElements(deleteResumeLink);
        click(elements.get(elements.size() - 1));
        acceptAlert();
        waitForAmountReduction(deleteResumeLink, countBeforeDeleting);
    }


    @Step("Delete resume on mobile by title: {0}")
    public void mobDeleteResumeByTitle(String title){
        int countBeforeDeleting = getAmountOfElements(deleteResumeLink);
        click(By.xpath(".//*[@data-test='position-name-resume' and text()='"+ title + "']/../../..//*[@data-test='delete']"));
        acceptAlert();
        waitForAmountReduction(deleteResumeLink, countBeforeDeleting);
    }

    @Step("Click DELETE last created RESUME")
    public void deleteLastResume(){
        int countBeforeDeleting = getAmountOfElements(findVacanciesButton);
        List<WebElement> elements = getAllElements(resumeMoreLink);
        click(elements.get(elements.size() - 1));
        elements = getAllElements(deleteResumeLink);
        waitForElementPresence(deleteResumeLink);
        click(elements.get(elements.size() - 1));
        acceptAlert();
        waitForAmountReduction(deleteResumeLink, countBeforeDeleting);
    }

    @Step("Delete resume by title: {0}")
    public void deleteResumeByTitle(String title){
        int countBeforeDeleting = getAmountOfElements(editResumeLink);
        System.out.println("CVs before deleting: " + countBeforeDeleting);
        By moreLink = By.xpath(".//*[@data-test='position-name-resume' and text()='" + title + "']/../../..//*[@data-test='data-more']");
        By deleteLink = By.xpath(".//*[@data-test='position-name-resume' and text()='" + title + "']/../../..//*[@data-test='delete']");
        click(moreLink);
        click(deleteLink);
        acceptAlert();
        waitForElementDisappear(moreLink);
        System.out.println("CVs after deleting: " + getAmountOfElements(editResumeLink));
    }

    @Step("Get count of created resumes")
    public int getCountOfCreatedCVs(){
        try {
            waitForElementPresence(editResumeLink);
        }catch (TimeoutException e){
            System.out.println(editResumeLink + " is absent!");
        }
        return getAmountOfElements(editResumeLink);
    }

    @Step("Click EDIT last created resume")
    public void clickEditLastResume(){
        List<WebElement> elements = driver().findElements(editResumeLink);
        click(elements.get(elements.size() - 1));
    }

    public Set<By> getIgnoreSet(){
        Set<By> ignored = new HashSet<>();
        ignored.add(banner);
        return ignored;
    }

    @Step("Verify if user info block contains text: {0}")
    public boolean primaryUserInfoContains(String text){
        return getTextByLocator(primaryUserInfo).contains(text);
    }

    @Step("Verify if user info block contains text: {0}")
    public boolean mobPrimaryUserInfoContains(String text){
        return getTextByLocator(mobPrimaryUserInfo).contains(text);
    }

    @Step("Verify if alert is shown")
    public boolean alertMessageIsShown(){
        return presenceOfElement(alertMessage);
    }
}
