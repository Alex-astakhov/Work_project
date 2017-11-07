package formValidation;

import assertion.Assertion;
import core.BrowserFactory;
import core.Constants;
import dataModels.DataGenerator;
import dataModels.DataProviders;
import dataModels.employer.vacancyParts.VacancyContactsData;
import dataModels.employer.vacancyParts.VacancyData;
import dataModels.employer.vacancyParts.VacancyPropertiesData;
import listeners.ListenerWithBrowserShot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import pageObjects.MainPage;
import pageObjects.employer.vacancy.VacancyEditPage;
import ru.yandex.qatools.allure.annotations.Features;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Asta on 23.03.2017.
 */
@Listeners({ListenerWithBrowserShot.class})
@Features("Validation of Vacancy forms")
public class VacancyForm extends BrowserFactory {
    private VacancyData vacancyDefaultData = new VacancyData();
    private VacancyContactsData contactsDefaultData = new  VacancyContactsData();
    private VacancyPropertiesData propertiesDefaultData = new VacancyPropertiesData();

    private VacancyEditPage vacancyEditPage = new VacancyEditPage();

    @Test
    public void login() {
        MainPage mainPage = new MainPage();
        mainPage.getPage();
        mainPage.clickLogin();
        LoginPage loginPage = new LoginPage();
        loginPage.loginUser(Constants.EMPLOYER_BOSS, Constants.EMPLOYER_BOSS);
        Assertion.urlContains("vacancy/list/manager", 10);
    }

    @Test(dependsOnMethods = "login")
    public void initVacancyDataTest(){
        get(Constants.DOMEN + "vacancy-create");
        vacancyEditPage.fillVacancyPropertiesData(propertiesDefaultData);
        vacancyEditPage.fillVacancyContactsData(contactsDefaultData);
    }

    @DataProvider
    public Object[][] vacancyData() {
        return new Object[][]{
                {new VacancyData().setTitle("")},
                {new VacancyData().setDescription("")},
                {new VacancyData().setDescription("123456")},
                {new VacancyData().setDescription("!@#$^&*()_")},
                {new VacancyData().setCityValue("0")},
                {new VacancyData().setSalaryFrom("-2000").setSalaryTo("-3000")},
                {new VacancyData().setSalaryFrom("-2000").setSalaryTo("-1000")},
                {new VacancyData().setSalaryFrom("-2000").setSalaryTo("0")},
                {new VacancyData().setSalaryFrom("-2000").setSalaryTo("3000")},
                {new VacancyData().setSalaryFrom("5000").setSalaryTo("4000")}
        };
    }

    @Test(dependsOnMethods = "initVacancyDataTest", dataProvider = "vacancyData")
    public void vacancyBodyForm(VacancyData data){
        System.out.println("Try: " + data);
        vacancyEditPage.fillVacancyData(data);
        vacancyEditPage.clickSaveInDrafts();
        try {
            Assertion.urlDoesNtContains("vacancy/list", 2,  "Form was validated!");
        }catch (AssertionError e){
            get(Constants.DOMEN + "vacancy-create");
            throw e;
        }
    }

    @Test(dependsOnMethods = "vacancyBodyForm", alwaysRun = true)
    public void initContactsDataTest(){
        vacancyEditPage.fillVacancyPropertiesData(propertiesDefaultData);
        vacancyEditPage.fillVacancyData(vacancyDefaultData);
    }

    @DataProvider
    public Object[][] contactsData() {
        return new Object[][]{
                {new VacancyContactsData().setAddContacts(true).setContactPerson("").setContactPhoneNumber("")},
                //{new VacancyContactsData().setAddContacts(true).setContactPerson("").setContactPhoneNumber("+7(689) 330-90-60")},
                //{new VacancyContactsData().setAddContacts(true).setContactPerson("Менеджер по персоналу").setContactPhoneNumber("")},
                {new VacancyContactsData().setAddAddress(true).setContactCityValue("0").setStreet("")},
                {new VacancyContactsData().setAddAddress(true).setContactCityValue("0").setStreet("Комсомольская")},
                {new VacancyContactsData().setAddAddress(true).setContactCityValue(DataGenerator.generateCityValue()).setStreet("")},
        };
    }

    @Test(dependsOnMethods = "vacancyBodyForm", dataProvider = "contactsData", alwaysRun = true)
    public void vacancyContactsForm(VacancyContactsData data){
        System.out.println("Try: " + data);
        vacancyEditPage.fillVacancyContactsData(data);
        vacancyEditPage.clickSaveInDrafts();
        try {
            Assertion.urlDoesNtContains("vacancy/list", 2);
        }catch (AssertionError e){
            get(Constants.DOMEN + "vacancy-create");
            initContactsDataTest();
            throw e;
        }
    }

    @Test(dependsOnMethods = "vacancyContactsForm", alwaysRun = true)
    public void initDestinationEmailsTest(){
        Set<String> emails = new HashSet<>();
        emails.add(Constants.EMPLOYER_EMAIL);
        vacancyEditPage.fillAllFields(vacancyDefaultData, propertiesDefaultData.setResponseDestinationValue("0").setEmailsDestination(emails), contactsDefaultData);
        vacancyEditPage.clickAddDestinationEmailLink();
    }

    @Test(dependsOnMethods = "initDestinationEmailsTest", dataProvider = "wrongEmails", dataProviderClass = DataProviders.class)
    public void addDestinationEmailField(String email){
        int beginEmailsCount = vacancyEditPage.getDestinationEmails().size();
        vacancyEditPage.typeDestinationEmail(email);
        vacancyEditPage.clickAddDestinationEmailButton();
        try {
            Assert.assertEquals(vacancyEditPage.getDestinationEmails().size(), beginEmailsCount, "Wrong email was added!");
        }catch (AssertionError e){
            pngAttachment("ErrorData");
            vacancyEditPage.clickAddDestinationEmailLink();
            throw e;
        }
    }

    @Test(dependsOnMethods = "addDestinationEmailField", alwaysRun = true)
    public void initDestinationUrlTest(){
        waitForContentLoaded(500);
        vacancyEditPage.clickPremium();
        vacancyEditPage.selectResponseDestinationByValue("redirect_on_site_after_response");
    }

    @Test(dependsOnMethods = "initDestinationUrlTest", dataProvider = "wrongUrls", dataProviderClass = DataProviders.class)
    public void destinationUrlField(String url){
        vacancyEditPage.typeUrlToApplication(url);
        vacancyEditPage.clickSaveInDrafts();
        try {
            Assertion.urlDoesNtContains("vacancy/list", 2, "Form was validated!");
        }catch (AssertionError e){
            get(Constants.DOMEN + "vacancy-create");
            vacancyEditPage.fillAllFields(vacancyDefaultData, propertiesDefaultData, contactsDefaultData);
            initDestinationUrlTest();
            throw e;
        }
    }
}
