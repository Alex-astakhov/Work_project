package pageObjects.applicant.notebook;

import core.MethodsFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.ArrayList;
import java.util.List;

public class PickedVacanciesPage extends MethodsFactory {
    private By vacancyBlock = By.cssSelector(".b-note-wrapper__row");
    private By vacancyBlockMobile = By.cssSelector(".b-one-entity--favorite");
    private By favoriteLink = By.cssSelector(".b-toggle-vacancy-in-favorite");

    @Step("Clear all picked vacancies")
    public void clearAllPickedVacancies(){
        List<WebElement> elements = getAllElements(favoriteLink);
        while (elements.size() > 0){
            clickFavorite(0);
            waitForPageToBeLoaded();
            elements = getAllElements(favoriteLink);
        }
    }

    @Step("Get picked vacancies count")
    public int getCount(){
        return getAllElements(vacancyBlock).size();
    }

    @Step("Get picked vacancies count on mobile")
    public int getCountMobile(){
        return getAllElements(vacancyBlockMobile).size();
    }

    public boolean isPicked(int index){
        String classAttr = getAllElements(favoriteLink).get(index).getAttribute("class");
        return classAttr.contains("active");
    }

    @Step("Click FAVORITE by index {0}")
    public void clickFavorite(int index){
        List<WebElement> elements = getAllElements(favoriteLink);
        click(elements.get(index));
        delay(500);
    }

    @Step("Get vacancy id by index {0}")
    public String getVacancyId(int index){
        return getAllElements(favoriteLink).get(index).getAttribute("data-vacancy-id");
    }

    @Step("Get all picked vacancies IDs")
    public List<String> getAllPickedVacancies(){
        int count = getCount();
        List<String> idList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            idList.add(getVacancyId(i));
        }
        return idList;
    }

    @Step("Get all picked vacancies IDs")
    public List<String> getAllPickedVacanciesMobile(){
        int count = getCountMobile();
        List<String> idList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            idList.add(getVacancyId(i));
        }
        return idList;
    }
}
