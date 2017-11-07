package pageObjects;

import core.MethodsFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * Created by Asta on 28.12.2016.
 */
public class MobileMenu extends MethodsFactory {

    private By menuButton = By.cssSelector("[data-test=toggle-main-menu]");
    private By searchVacanciesLink = By.id("menu_search_vacancies");
    private By myResumesLink = By.id("menu_my_resume_list");
    private By whoWatchedLink = By.id("menu_who_watched");
    private By invitationsLink = By.id("menu_invite_to_review");
    private By responsesHistoryLink = By.id("menu_applicant_history");
    private By pickedVacanciesLink = By.id("favorite_list");
    private By blogLink = By.id("menu_blog");
    private By subscriptionsLink = By.id("menu_mailing");
    private By settingsLink = By.id("menu_settings");
    private By exitLink = By.id("menu_exit");

    @Step("Open subscriptions")
    public void openSubscriptions(){
        clickMenu();
        clickSubscriptions();
    }

    @Step("Open CVs")
    public void openCVs(){
        clickMenu();
        clickMyResumes();
    }

    @Step("Open settings")
    public void openSettings(){
        clickMenu();
        clickSettings();
    }

    @Step("Click MENU mobile")
    public void clickMenu(){
        waitForContentLoaded(300);
        click(menuButton);
        waitForElementVisibility(searchVacanciesLink);
    }

    @Step("Open settings")
    public void openPickedVacancies(){
        clickMenu();
        clickPickedvacancies();
    }

    @Step("Click SEARCH VACANCIES")
    public void clickSearchVacancies(){
        click(searchVacanciesLink);
    }

    @Step("Click MY RESUMES")
    public void clickMyResumes(){
        click(myResumesLink);
    }

    @Step("Click WHO WATCHED")
    public void clickWhoWatched(){
        click(whoWatchedLink);
    }

    @Step("Click INVITATIONS")
    public void clickInvitations(){
        click(invitationsLink);
    }

    @Step("Click PICKED VACANCIES")
    public void clickPickedvacancies(){
        click(pickedVacanciesLink);
    }

    @Step("Click RESPONSES HISTORY")
    public void clickResponsesHistory(){
        click(responsesHistoryLink);
    }

    @Step("Click BLOG")
    public void clickBlog(){
        click(blogLink);
    }

    @Step("Click SUBSCRIPTIONS")
    public void clickSubscriptions(){
        click(subscriptionsLink);
    }

    @Step("Click SETTINGS")
    public void clickSettings(){
        click(settingsLink);
    }

    @Step("Click EXIT")
    public void clickExit(){
        click(exitLink);
    }

    @Step("Logout")
    public void logout(){
        clickMenu();
        clickExit();
    }
}
