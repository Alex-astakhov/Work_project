package employer.desktop.vacancies;

import assertion.Assertion;
import core.BrowserFactory;
import core.Constants;
import dataModels.DataGenerator;
import dataModels.employer.vacancyParts.VacancyContactsData;
import dataModels.employer.vacancyParts.VacancyPropertiesData;
import dataModels.employer.vacancyParts.VacancyData;
import http.HttpActions;
import listeners.ListenerWithBrowserShot;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import pageObjects.MainPage;
import pageObjects.employer.vacancy.VacanciesListPage;
import pageObjects.employer.vacancy.VacancyEditPage;
import ru.yandex.qatools.allure.annotations.Features;

/**
 * Created by Asta on 31.01.2017.
 */
@Listeners({ListenerWithBrowserShot.class})
@Features("Desktop vacancy functional")
public class CreateVacancy extends BrowserFactory {
    private int beginVacancyCount;
    private String respPersonValue;
    private VacancyData vacancyData = new VacancyData();
    private VacancyPropertiesData propertiesData = new VacancyPropertiesData();
    private VacancyContactsData contactsData = new VacancyContactsData();

    private VacanciesListPage vacanciesListPage = new VacanciesListPage();
    private VacancyEditPage editPage = new VacancyEditPage();

    @Test
    public void login() {
        MainPage mainPage = new MainPage();
        mainPage.getPage();
        mainPage.clickLogin();
        LoginPage loginPage = new LoginPage();
        loginPage.loginUser(Constants.EMPLOYER_EMAIL, Constants.EMPLOYER_PASSWORD);
        Assertion.urlContains("vacancy/list/manager", 10);
        respPersonValue = DataGenerator.randomItemFromList(vacanciesListPage.getManagersValues(), "0");
        get(Constants.DOMEN + "vacancy/list/manager_id/" + respPersonValue + "/tab/all");
        beginVacancyCount = vacanciesListPage.getCountOfVacancies();
    }

    @Test(dependsOnMethods = "login")
    public void verifyVacancyCreation() {
        vacanciesListPage.clickCreateVacancy();
        Assertion.urlContains("vacancy-create", 10);
        System.out.println(vacancyData);
        System.out.println(propertiesData);
        System.out.println(contactsData);
    }

    @Test(dependsOnMethods = "verifyVacancyCreation")
    public void verifyVacancyPublishing() {
        waitForPageToBeLoaded();
        editPage.fillAllFields(respPersonValue, vacancyData, propertiesData, contactsData);
        pngAttachment("before_publishing");
        editPage.clickPublish();
        waitForContentLoaded(500);
        get(Constants.DOMEN + "vacancy/list/manager_id/" + respPersonValue + "/tab/all");
        Assert.assertEquals(vacanciesListPage.getCountOfVacancies(), beginVacancyCount + 1);
    }

    @Test(dependsOnMethods = "verifyVacancyPublishing")
    public void verifyVacancyData() throws InterruptedException {
        vacanciesListPage.selectManagerByValue(respPersonValue);
        vacanciesListPage.clickEditVacancyByTitle(vacancyData.getTitle());
        VacancyData actual = editPage.getVacancyData();
        System.out.println("Actual:   " + actual);
        System.out.println("Expected: " + vacancyData);
        Assert.assertEquals(actual, vacancyData);
    }

    @Test(dependsOnMethods = "verifyVacancyData")
    public void verifyVacancyPropertiesData(){
        VacancyPropertiesData actual = editPage.getVacancyPropertiesData();
        System.out.println("Actual:   " + actual);
        System.out.println("Expected: " + propertiesData);
        Assert.assertEquals(actual, propertiesData);
        Assert.assertEquals(editPage.getSelectedResponsiblePersonValue(), respPersonValue);
    }

    @Test(dependsOnMethods = "verifyVacancyPropertiesData")
    public void verifyVacancyContactsData(){
        VacancyContactsData actual = editPage.getVacancyContactsData();
        System.out.println("Actual:   " + actual);
        System.out.println("Expected: " + contactsData);
        Assert.assertEquals(actual, contactsData);
    }

    @Test(dependsOnMethods = "verifyVacancyContactsData", alwaysRun = true)
    public void verifyVacancyIsOnModeration() {
        HttpActions actions = new HttpActions(Constants.HOST);
        actions.doLogin(Constants.ADMIN_EMAIL, Constants.ADMIN_PASSWORD);
        String result = actions.findVacancyByEmployerId(Constants.EMPLOYER_ID);
        Assert.assertTrue(result.contains(vacancyData.getTitle()), "Vacancy " + vacancyData.getTitle() + " isn't on moderation!\n\n");
        String vacancyId = result.substring(0, result.indexOf("\">редактировать"));
        vacancyId = vacancyId.substring(vacancyId.lastIndexOf("id/") + 3);
        result = actions.sendGetRequestToEndpoint("/admin/vacancy/verify/id/" + vacancyId);
        Assert.assertFalse(result.contains(vacancyData.getTitle()), "Vacancy " + vacancyData.getTitle() + " is still on moderation!\n\n");
    }

    @Test(dependsOnMethods = "verifyVacancyIsOnModeration", alwaysRun = true)
    public void verifyVacancySuspense(){
        editPage.clickCancel();
        get(Constants.DOMEN + "vacancy/list/manager_id/" + respPersonValue + "/tab/all");
        Assert.assertEquals(vacanciesListPage.getVacancyStatusByTitle(vacancyData.getTitle()), "Активна");
        vacanciesListPage.suspendVacancyByTitle(vacancyData.getTitle());
        waitForContentLoaded(2000);
        Assert.assertEquals(vacanciesListPage.getVacancyStatusByTitle(vacancyData.getTitle()), "Приостановлена");
    }

    @Test(dependsOnMethods = "verifyVacancySuspense")
    public void verifyVacancyRestore(){
        vacanciesListPage.restoreVacancyByTitle(vacancyData.getTitle());
        waitForContentLoaded(2000);
        Assert.assertEquals(vacanciesListPage.getVacancyStatusByTitle(vacancyData.getTitle()), "Активна");
    }

    @Test(dependsOnMethods = "verifyVacancyRestore", alwaysRun = true)
    public void verifyVacancyFinishing(){
        vacanciesListPage.finishVacancyByTitle(vacancyData.getTitle());
        waitForContentLoaded(2000);
        Assert.assertEquals(vacanciesListPage.getVacancyStatusByTitle(vacancyData.getTitle()), "Архив");
    }

    @Test(dependsOnMethods = "verifyVacancyFinishing", alwaysRun = true)
    public void verifyDeletingOfVacancy() throws InterruptedException {
        get(Constants.DOMEN + "vacancy/list/manager_id/" + respPersonValue + "/tab/all");
        vacanciesListPage.deleteVacancyByTitle(vacancyData.getTitle());
        get(Constants.DOMEN + "vacancy/list/manager_id/" + respPersonValue + "/tab/all");
        Assert.assertEquals(vacanciesListPage.getCountOfVacancies(), beginVacancyCount);
    }
}
