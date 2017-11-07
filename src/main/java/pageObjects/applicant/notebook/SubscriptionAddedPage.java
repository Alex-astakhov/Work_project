package pageObjects.applicant.notebook;

import core.MethodsFactory;
import dataModels.SubscriptionData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Asta on 14.03.2017.
 */
public class SubscriptionAddedPage extends MethodsFactory {
    private By addButton = By.cssSelector("[data-test=add-subscription]");
    private By editLink = By.cssSelector("[data-test=edit-subscription]");
    private By deleteLink = By.cssSelector("[data-test=delete-subscription]");
    private By dailyRadio = By.cssSelector("[data-test=subscription-daily]");
    private By weeklyRadio = By.cssSelector("[data-test=subscription-weekly]");
    private By neverRadio = By.cssSelector("[data-test=subscription-never]");

    @Step("Delete all subscriptions")
    public int clearAllSubscriptions(){
        waitForPageToBeLoaded();
        int count = getAmountOfElements(deleteLink);
        for (int i = count; i > 0; i--) {
            clickDeleteSubscription(i - 1);
        }
        return count;
    }

    @Step("Set periodicity for subscription # {0}")
    public void setPeriodicity(int number, SubscriptionData data){
        waitForPageToBeLoaded();
        switch (data.getPeriodicity()){
            case 0:
                checkDaily(number);
                break;
            case 1:
                checkWeekly(number);
                break;
            case 2:
                checkNever(number);
        }
    }

    @Step("Get periodicity")
    public int getPeriodicity(int number){
        int periodicity = -1;
        if (isCheckedDaily(number))
            periodicity = 0;
        if (isCheckedWeekly(number))
            periodicity = 1;
        if (isCheckedNever(number))
            periodicity = 2;
        return periodicity;
    }

    @Step("Get subscription data")
    public SubscriptionData getSubscriptionData(int index){
        int periodicity = getPeriodicity(index);
        clickEdit(index);
        SubscriptionPageMobile subscriptionPage = new SubscriptionPageMobile();
        String title = subscriptionPage.getVacancyTitle();
        String city = subscriptionPage.getCity();
        String xp = subscriptionPage.getExperience();
        String salary = subscriptionPage.getSalary();
        String avail = subscriptionPage.getAvailability();
        subscriptionPage.clickCancel();
        return new SubscriptionData(title, city, salary, xp, avail, periodicity);
    }

    @Step("Get subscriptions set")
    public Set<SubscriptionData> getSubscriptionsSet(){
        Set<SubscriptionData> result = new HashSet<>();
        waitForPageToBeLoaded();
        int count = getAmountOfElements(editLink);
        for (int i = 0; i < count; i++) {
            result.add(getSubscriptionData(i));
        }
        return result;
    }

    @Step("Click ADD subscription")
    public void clickAdd(){
        click(addButton);
    }

    @Step("Click EDIT subscription")
    public void clickEdit(int number){
        click(getAllElements(editLink).get(number));
    }

    @Step("Click DELETE subscription")
    public void clickDeleteSubscription(int number){
        click(getAllElements(deleteLink).get(number));
    }

    @Step("Choose periodicity DAILY at place {0}")
    public void checkDaily(int number){
        List<WebElement> elements = getAllElements(dailyRadio);
        click(elements.get(number));
    }

    @Step("Verify if DAILY is checked at place {0}")
    public boolean isCheckedDaily(int number){
        List<WebElement> elements = getAllElements(dailyRadio);
        return elements.get(number).getAttribute("class").contains("active");
    }

    @Step("Choose periodicity WEEKLY at place {0}")
    public void checkWeekly(int number){
        List<WebElement> elements = getAllElements(weeklyRadio);
        click(elements.get(number));
    }

    @Step("Verify if WEEKLY is checked at place {0}")
    public boolean isCheckedWeekly(int number){
        List<WebElement> elements = getAllElements(weeklyRadio);
        return elements.get(number).getAttribute("class").contains("active");
    }

    @Step("Choose periodicity NEVER at place {0}")
    public void checkNever(int number){
        List<WebElement> elements = getAllElements(neverRadio);
        click(elements.get(number));
    }

    @Step("Verify if NEVER is checked at place {0}")
    public boolean isCheckedNever(int number){
        List<WebElement> elements = getAllElements(neverRadio);
        return elements.get(number).getAttribute("class").contains("active");
    }

}
