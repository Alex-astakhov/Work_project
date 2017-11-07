package applicant.desktop.cv;

import assertion.Assertion;
import core.BrowserFactory;
import core.Constants;
import core.Email;
import dataModels.DataGenerator;
import dataModels.applicant.ApplicantRegistrationData;
import dataModels.applicant.cvParts.*;
import listeners.ListenerWithBrowserShot;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import pageObjects.MainPage;
import pageObjects.applicant.cv.CVEditPage;
import pageObjects.applicant.cv.CvListPage;
import pageObjects.applicant.cv.cvPartitions.AboutAndSkillsPart;
import ru.yandex.qatools.allure.annotations.Features;

import javax.mail.MessagingException;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.Set;

import static core.Constants.DOMEN;

/**
 * Created by Asta on 08.05.2017.
 */
@Listeners({ListenerWithBrowserShot.class})
@Features("Desktop restore deleted resume from email")
public class RestoreDeletedCV extends BrowserFactory{
    private Random random = new Random();
    private ApplicantRegistrationData appRegData = new ApplicantRegistrationData(Constants.APPLICANT_REG_EMAIL, Constants.PASSWORD);
    private UserData userData;
    private SocialNetData socialNetData = new SocialNetData();
    private PositionData positionData;
    private String goal = "Моя цель от " + DataGenerator.getCurrentDateAndTimeString();
    private ExperienceData[] experienceDatas = ExperienceData.getRandomExperiences(random.nextInt(5) + 2, false);
    private EducationData[] educationDatas = EducationData.getRandomEducations(random.nextInt(5) + 2);
    private Set<LanguageData> languageDatas = LanguageData.getRandomLanguages(random.nextInt(8) + 2);
    private TrainingData[] trainingDatas = TrainingData.getRandomTrainings(random.nextInt(5) + 1);
    private AboutAndSkillsData aboutAndSkillsData = new AboutAndSkillsData(false);
    private DriversData driversData;
    private int resumeStatus = random.nextInt(3) + 1;

    private CvListPage cvListPage = new CvListPage();
    private CVEditPage cvEditPage = new CVEditPage();
    private AboutAndSkillsPart skillsPart = new AboutAndSkillsPart();
    private int beginCVsCount;
    private Date actionDate;

    @Test
    public void login() {
        actionDate = DataGenerator.getCurrentDate(0);
        MainPage mainPage = new MainPage();
        mainPage.getPage();
        waitForPageLoadedWithJQuery();
        mainPage.clickLogin();
        LoginPage loginPage = new LoginPage();
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
        System.out.println(appRegData);
        System.out.println(userData);
        System.out.println(socialNetData);
        System.out.println(positionData);
        System.out.println(Arrays.toString(educationDatas));
        System.out.println(languageDatas);
        System.out.println(Arrays.toString(trainingDatas));
        System.out.println(Arrays.toString(experienceDatas));
        System.out.println(aboutAndSkillsData);
        System.out.println(driversData);
        waitForContentLoaded(1000);
        userData = cvEditPage.getUserData();
        positionData = new PositionData(userData.getCityValue());
        driversData = cvEditPage.getDriversData();
    }

    @Test(dependsOnMethods = "createResume")
    public void fillResume(){
        cvEditPage.addPosition(positionData);
        cvEditPage.addGoal(goal);
        cvEditPage.addAllExperiences(experienceDatas);
        skillsPart.addAllSkills(aboutAndSkillsData);
        cvEditPage.addAllEducations(educationDatas);
        cvEditPage.addAllLanguages(languageDatas);
        cvEditPage.addAllTrainings(trainingDatas);
        cvEditPage.addAboutInfo(aboutAndSkillsData);
    }

    @Test(dependsOnMethods = {"fillResume"})
    public void saveResume(){
        cvEditPage.chooseVisibilityOfCV(resumeStatus);
        pngAttachment("CvBeforeSaving");
        cvEditPage.clickReady();
        Assertion.urlContains("notebook", 5);
        int finalCVsCount = cvListPage.getCountOfCreatedCVs();
        System.out.println("final CVs count: " + finalCVsCount);
        System.out.println("expected CVs count: " + (beginCVsCount + 1));
        Assert.assertEquals(finalCVsCount, beginCVsCount + 1);
    }

    @Test(dependsOnMethods = "saveResume")
    public void deleteCreatedResume(){
        get(DOMEN + "applicant/notebook");
        try {
            cvListPage.deleteResumeByTitle(positionData.getPositionName());
        }catch (Exception e){
            cvListPage.deleteLastResume();
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
        cvListPage.clickEditLastResume();
        waitForContentLoaded(1000);
    }

    @Test(dependsOnMethods = "restoreResumeFromEmail")
    public void verifyRestoredPositionData(){
        PositionData actual = cvEditPage.getPositionData();
        System.out.println("Actual:   " + actual);
        System.out.println("Expected: " + positionData);
        Assert.assertEquals(actual, positionData);
    }

    @Test(dependsOnMethods = "restoreResumeFromEmail")
    public void verifyRestoredGoal(){
        String actual = cvEditPage.getGoal();
        System.out.println("Actual:   " + actual);
        System.out.println("Expected: " + goal);
        Assert.assertEquals(actual, goal);
    }

    @Test(dependsOnMethods = "restoreResumeFromEmail")
    public void verifyRestoredExperienceData(){
        ExperienceData[] actual = cvEditPage.getAllExperienceData();
        Arrays.sort(experienceDatas);
        System.out.println("Actual:   " + Arrays.toString(actual));
        System.out.println("Expected: " + Arrays.toString(experienceDatas));
        Assert.assertEquals(actual, experienceDatas);
    }

    @Test(dependsOnMethods = "restoreResumeFromEmail")
    public void verifyRestoredSkillsData(){
        Set<String> actual = skillsPart.getSkills();
        System.out.println("Actual:   " + actual);
        System.out.println("Expected: " + aboutAndSkillsData.getSkills());
        Assert.assertEquals(actual, aboutAndSkillsData.getSkills());
    }

    @Test(dependsOnMethods = "restoreResumeFromEmail")
    public void verifyRestoredEducationData(){
        EducationData[] actual = cvEditPage.getAllEducationData();
        Arrays.sort(educationDatas);
        System.out.println("Actual:   " + Arrays.toString(actual));
        System.out.println("Expected: " + Arrays.toString(educationDatas));
        Assert.assertEquals(actual, educationDatas);
    }

    @Test(dependsOnMethods = "restoreResumeFromEmail")
    public void verifyRestoredLanguagesData(){
        Set<LanguageData> actual = cvEditPage.getAllLanguages();
        System.out.println("Actual:   " + actual);
        System.out.println("Expected: " + languageDatas);
        Assert.assertEquals(actual.toString(), languageDatas.toString());
    }

    @Test(dependsOnMethods = "restoreResumeFromEmail")
    public void verifyRestoredTrainingsData(){
        TrainingData[] actual = cvEditPage.getAllTrainingData();
        Arrays.sort(trainingDatas);
        System.out.println("Actual:   " + Arrays.toString(actual));
        System.out.println("Expected: " + Arrays.toString(trainingDatas));
        Assert.assertEquals(actual, trainingDatas);
    }

    @Test(dependsOnMethods = "restoreResumeFromEmail")
    public void verifyRestoredAboutInfo(){
        String actual = cvEditPage.getAboutData();
        System.out.println("Actual:   " + actual);
        System.out.println("Expected: " + aboutAndSkillsData.getAboutMyself());
        Assert.assertTrue(actual.contains(aboutAndSkillsData.getAboutMyself()), "Actual result doesn't contain expected text!");
    }

    /*@Test(dependsOnMethods = "restoreResumeFromEmail")
    public void verifyRestoredResumeStatus(){
        int actual = cvEditPage.getCheckedStatus();
        System.out.println("Actual:   " + actual);
        System.out.println("Expected: " + resumeStatus);
        Assert.assertEquals(actual, resumeStatus);
    }*/

    @Test(dependsOnMethods = "verifyRestoredTrainingsData", alwaysRun = true)
    public void finallyDeleteResume(){
        get(DOMEN + "applicant/notebook");
        try {
            cvListPage.deleteResumeByTitle(positionData.getPositionName());
        }catch (Exception e){
            cvListPage.deleteLastResume();
        }
        Assert.assertEquals(cvListPage.getCountOfCreatedCVs(), beginCVsCount);
    }
}
