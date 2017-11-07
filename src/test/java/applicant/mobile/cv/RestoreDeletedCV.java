package applicant.mobile.cv;

import assertion.Assertion;
import core.BrowserFactory;
import core.Constants;
import core.Email;
import dataModels.DataGenerator;
import listeners.ListenerWithBrowserShot;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObjects.FooterBlock;
import pageObjects.LoginPage;
import pageObjects.MainPage;
import pageObjects.applicant.cv.CvListPage;
import ru.yandex.qatools.allure.annotations.Features;

import javax.mail.MessagingException;
import java.util.Date;

import static core.Constants.DOMEN;

@Listeners({ListenerWithBrowserShot.class})
@Features("Mobile restore deleted resume from email")
public class RestoreDeletedCV extends BrowserFactory {
    private CvListPage cvListPage = new CvListPage();

    private int beginCVsCount;
    private Date actionDate;

    @Test
    public void initialization(){
        MainPage mainPage = new MainPage();
        mainPage.getPage();
        if (!getUserAgent().toLowerCase().contains("mobile")) {
            FooterBlock footer = new FooterBlock();
            footer.clickMobileVersion();
        }
    }

    @Test(dependsOnMethods = "initialization")
    public void login() {
        actionDate = DataGenerator.getCurrentDate(0);
        LoginPage loginPage = new LoginPage();
        loginPage.getPage();
        loginPage.loginUser(Constants.APPLICANT_LOGIN_EMAIL, Constants.PASSWORD);
        Assertion.urlContains("notebook", 10);
        beginCVsCount = cvListPage.getCountOfCreatedCVs();
        System.out.println("CV's before creation: " + beginCVsCount);
    }

    @Test(dependsOnMethods = "login")
    public void createResume() {
        waitForPageToBeLoaded();
        cvListPage.clickCreateResume();
        Assertion.urlContains("editPreview", 10);
        waitForContentLoaded(1000);
    }

    @Test(dependsOnMethods = "createResume")
    public void deleteCreatedResume(){
        get(DOMEN + "applicant/notebook");
        try {
            cvListPage.mobDeleteResumeByTitle("Не заполнено");
        }catch (Exception e){
            cvListPage.mobDeleteLastResume();
        }
        Assert.assertEquals(cvListPage.getCountOfCreatedCVs(), beginCVsCount);
    }

    @Test(dependsOnMethods = "deleteCreatedResume")
    public void restoreResumeFromEmail() throws MessagingException {
        Email email;
        if (Constants.DOMEN.contains("dev"))
            email = new Email(Constants.DEV_EMAIL, Constants.DEV_PASSWORD);
        else
            email = new Email(Constants.APPLICANT_LOGIN_EMAIL, Constants.PASSWORD);
        String text;
        text = Email.getLetterBody(email.getLastLetterFromGmail("Вы удалили резюме", 120000, actionDate), 1);
        String restoreUrl = email.getUrlFromEmailContent(text, "/resume/restore/");
        System.out.println("Restoring URL: " + restoreUrl);
        driver().get(restoreUrl);
        waitForContentLoaded(1000);
        Assert.assertEquals(cvListPage.getCountOfCreatedCVs(), beginCVsCount + 1);
    }

    @Test(dependsOnMethods = "restoreResumeFromEmail", alwaysRun = true)
    public void finallyDeleteResume(){
        get(DOMEN + "applicant/notebook");
        try {
            cvListPage.mobDeleteResumeByTitle("Не заполнено");
        }catch (Exception e){
            cvListPage.mobDeleteLastResume();
        }
        Assert.assertEquals(cvListPage.getCountOfCreatedCVs(), beginCVsCount);
    }
}
