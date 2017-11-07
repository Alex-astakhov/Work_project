package pageObjects;

import core.Constants;
import core.MethodsFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Asta on 16.12.2016.
 */
public class MainPage extends MethodsFactory {

    private By makeResumeButton = By.id("create_resume_button_form");
    private By mobLoginButton = By.id("enter_to_the_notebook");
    private By loginButton = By.cssSelector("[data-test=login]");
    private By searchField = By.cssSelector("#query_top_search_form");
    private By searchButton = By.cssSelector("#mp+button");

    private By logosBlock = By.cssSelector(".b-logo-top-company");
    private By topVacanciesList = By.cssSelector(".b-top-list-vacancies");
    private By mobBanner = By.cssSelector("[class*=b-banner]");
    private By mobUrgentVacancies = By.cssSelector("[data-test=urgently-vacancies]");

    private By employerSideComments = By.cssSelector(".review-slider");
    private By employerSideLogoSlider = By.cssSelector(".review-slider__pagination-wrapper");

    @Step("Get main page")
    public void getPage(){
        get(Constants.DOMEN);
    }

    @Step("Click MAKE RESUME")
    public void clickMakeResume(){
        click(makeResumeButton);
    }

    @Step("Click LOGIN on mobile")
    public void mobClickLogin(){
        click(mobLoginButton);
    }

    @Step("Click LOGIN")
    public void clickLogin(){
        click(loginButton);
    }

    public Set<By> getIgnoreSet(){
        Set<By> ignore = new HashSet<>();
        ignore.add(logosBlock);
        ignore.add(topVacanciesList);
        return ignore;
    }

    public Set<By> getIgnoreSetEmployerSide(){
        Set<By> ignore = new HashSet<>();
        ignore.add(employerSideComments);
        ignore.add(employerSideLogoSlider);
        return ignore;
    }

    public Set<By> getIgnoreSetMobile(){
        Set<By> ignore = new HashSet<>();
        ignore.add(logosBlock);
        ignore.add(mobBanner);
        ignore.add(mobUrgentVacancies);
        return ignore;
    }

    public List<By> getElementsToHide(){
        List<By> locators = new ArrayList<>();
        locators.add(topVacanciesList);
        return locators;
    }

    @Step("Type searching query: {0}")
    public void typeSearchQuery(String query){
        type(searchField, query);
    }

    @Step("Click SEARCH VACANCY")
    public void clickSearchVacancy(){
        click(searchButton);
    }

}
