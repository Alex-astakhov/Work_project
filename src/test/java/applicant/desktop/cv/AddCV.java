package applicant.desktop.cv;

import assertion.Assertion;
import core.BrowserFactory;
import core.Constants;
import dataModels.DataGenerator;
import dataModels.applicant.ApplicantRegistrationData;
import dataModels.applicant.cvParts.*;
import listeners.ListenerWithBrowserShot;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObjects.HeaderBlock;
import pageObjects.LoginPage;
import pageObjects.MainPage;
import pageObjects.applicant.cv.CVEditPage;
import pageObjects.applicant.cv.CvListPage;
import pageObjects.applicant.cv.cvPartitions.*;
import ru.yandex.qatools.allure.annotations.Features;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;


/**
 * Created by Asta on 27.12.2016.
 */
@Listeners({ListenerWithBrowserShot.class})
@Features("Desktop Full resume creation")
public class AddCV extends BrowserFactory {
    private Random random = new Random();
    private ApplicantRegistrationData appRegData = new ApplicantRegistrationData(Constants.APPLICANT_REG_EMAIL, Constants.PASSWORD);
    private UserData userData = new UserData();
    private SocialNetData socialNetData = new SocialNetData();
    private PositionData positionData = new PositionData(userData.getCityValue());
    private String goal = "Моя цель от " + DataGenerator.getCurrentDateAndTimeString();
    private ExperienceData[] experienceDatas = ExperienceData.getRandomExperiences(random.nextInt(5) + 2, false);
    private EducationData[] educationDatas = EducationData.getRandomEducations(random.nextInt(5) + 2);
    private Set<LanguageData> languageDatas = LanguageData.getRandomLanguages(random.nextInt(8) + 2);
    private TrainingData[] trainingDatas = TrainingData.getRandomTrainings(random.nextInt(5) + 1);
    private AboutAndSkillsData aboutAndSkillsData = new AboutAndSkillsData(false);
    private DriversData driversData = new DriversData();

    private CvListPage cvListPage = new CvListPage();
    private CVEditPage cvEditPage = new CVEditPage();
    private int beginCVsCount;

    @Test
    public void login() {
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
    public void verifyResumeCreation() {
        waitForPageLoadedWithJQuery();
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
    }

    @Test(dependsOnMethods = "verifyResumeCreation")
    public void verifyUserDataEditing(){
        cvEditPage.editUserData(userData);
        waitForPageLoadedWithJQuery();
        UserData actual = cvEditPage.getUserData();
        Assert.assertEquals(actual, userData);
    }


     @Test(dependsOnMethods = "verifyResumeCreation")
    public void verifyAdditionOfSocialNets(){
        cvEditPage.clearAllSocialNets();
        cvEditPage.addAllSocialNets(socialNetData);
        SocialNetData actual = cvEditPage.getSocialNetData();
        System.out.println("Actual:   " + actual);
        System.out.println("Expected: " + socialNetData);
        Assert.assertEquals(actual, socialNetData);
    }


    @Test(dependsOnMethods = "verifyUserDataEditing")
    public void verifyPositionEditing(){
        cvEditPage.addPosition(positionData);
        waitForPageLoadedWithJQuery();
        PositionData actual = cvEditPage.getPositionData();
        System.out.println("Actual:   " + actual);
        System.out.println("Expected: " + positionData);
        Assert.assertEquals(actual, positionData);
    }


    @Test(dependsOnMethods = "verifyResumeCreation")
    public void verifyGoalEditing(){
        cvEditPage.addGoal(goal);
        waitForPageToBeLoaded();
        String actual = cvEditPage.getGoal();
        System.out.println("Actual:   " + actual);
        System.out.println("Expected: " + goal);
        Assert.assertEquals(actual, goal);
        cvEditPage.editGoal("");
        Assert.assertFalse(cvEditPage.goalIsPresent(), "Perhaps goal isn't deleted!");
    }


    @Test(dependsOnMethods = "verifyResumeCreation")
    public void verifyAdditionOfExperiences(){
        cvEditPage.addAllExperiences(experienceDatas);
        ExperienceData[] actual = cvEditPage.getAllExperienceData();
        Arrays.sort(experienceDatas);
        System.out.println("Actual:   " + Arrays.toString(actual));
        System.out.println("Expected: " + Arrays.toString(experienceDatas));
        Assert.assertEquals(actual, experienceDatas);
        cvEditPage.clickDeleteExperience(random.nextInt(actual.length));
        Assert.assertEquals(cvEditPage.getExperiencesCount(), experienceDatas.length - 1);
    }

    @Test(dependsOnMethods = "verifyResumeCreation")
    public void verifyAdditionOfSkills(){
        AboutAndSkillsPart skillsPart = new AboutAndSkillsPart();
        skillsPart.addAllSkills(aboutAndSkillsData);
        refreshPage();
        Set<String> actual = skillsPart.getSkills();
        System.out.println("Actual:   " + actual.toString());
        System.out.println("Expected: " + aboutAndSkillsData.getSkills().toString());
        Assert.assertEquals(actual, aboutAndSkillsData.getSkills());
    }

    @Test(dependsOnMethods = "verifyResumeCreation")
    public void verifyEducationEditing(){
        cvEditPage.addAllEducations(educationDatas);
        EducationData[] actual = cvEditPage.getAllEducationData();
        Arrays.sort(educationDatas);
        System.out.println("Actual:   " + Arrays.toString(actual));
        System.out.println("Expected: " + Arrays.toString(educationDatas));
        Assert.assertEquals(actual, educationDatas);
        cvEditPage.clickDeleteEducation(random.nextInt(actual.length));
        Assert.assertEquals(cvEditPage.getEducationsCount(), educationDatas.length - 1);
    }

    @Test(dependsOnMethods = "verifyResumeCreation")
    public void verifyAdditionOfLanguages(){
        cvEditPage.addAllLanguages(languageDatas);
        Set<LanguageData> actual = cvEditPage.getAllLanguages();
        System.out.println("Actual:   " + actual.toString());
        System.out.println("Expected: " + languageDatas.toString());
        Assert.assertEquals(actual.toString(), languageDatas.toString());
        cvEditPage.clickDeleteLanguage(random.nextInt(languageDatas.size()));
        Assert.assertEquals(cvEditPage.getLanguagesCount(), languageDatas.size() - 1);
    }

    @Test(dependsOnMethods = "verifyResumeCreation")
    public void verifyAdditionOfTrainings(){
        cvEditPage.addAllTrainings(trainingDatas);
        TrainingData[] actual = cvEditPage.getAllTrainingData();
        Arrays.sort(trainingDatas);
        System.out.println("Actual:   " + Arrays.toString(actual));
        System.out.println("Expected: " + Arrays.toString(trainingDatas));
        Assert.assertEquals(actual, trainingDatas);
        cvEditPage.clickDeleteTraining(random.nextInt(trainingDatas.length));
        Assert.assertEquals(cvEditPage.getTrainingsCount(), trainingDatas.length - 1);
    }

    @Test(dependsOnMethods = "verifyResumeCreation")
    public void verifyAdditionOfAboutInformation(){
        waitForContentLoaded(1000);
        cvEditPage.addAboutInfo(aboutAndSkillsData);
        String actual = cvEditPage.getAboutData();
        System.out.println("Actual:   " + actual);
        System.out.println("Expected: " + aboutAndSkillsData.getAboutMyself());
        Assert.assertTrue(actual.contains(aboutAndSkillsData.getAboutMyself()), "Actual result doesn't contain expected text!");
        cvEditPage.editAboutInfo(new AboutAndSkillsData("", null));
        Assert.assertFalse(cvEditPage.aboutInfoIsPresent(), "Perhaps key skills is not deleted!");
    }

    @Test(dependsOnMethods = "verifyResumeCreation")
    public void verifyEditingOfDriversData(){
        cvEditPage.editDriversData(driversData);
        DriversData actual = cvEditPage.getDriversData();
        System.out.println("Actual:   " + actual);
        System.out.println("Expected: " + driversData);
        Assert.assertEquals(actual, driversData);
        cvEditPage.editDriversData(new DriversData(new HashSet<>(), random.nextBoolean()));
        Assert.assertFalse(cvEditPage.driversLicenseIsPresent(), "Perhaps driver's license isn't deleted!");
    }



    @Test(dependsOnMethods = {"verifyEducationEditing", "verifyAdditionOfLanguages", "verifyPositionEditing"})
    public void saveResume(){
        int status = random.nextInt(3) + 1;
        cvEditPage.chooseVisibilityOfCV(status);
        cvEditPage.clickReady();
        Assertion.urlContains("notebook", 5);
        int finalCVsCount = cvListPage.getCountOfCreatedCVs();
        System.out.println("final CVs count: " + finalCVsCount);
        System.out.println("expected CVs count: " + (beginCVsCount + 1));
        Assert.assertEquals(finalCVsCount, beginCVsCount + 1);
        waitForPageLoadedWithJQuery();
        cvListPage.clickEditLastResume();
        int actual = cvEditPage.getCheckedStatus();
        System.out.println("Actual CV status:   " + actual);
        System.out.println("Expected CV status: " + status);
        Assert.assertEquals(actual, status);
    }


    @Test(dependsOnMethods = "saveResume", alwaysRun = true)
    public void deleteCreatedResume(){
        refreshPage();
        HeaderBlock headerBlock = new HeaderBlock();
        headerBlock.openCVs();
        try {
            cvListPage.deleteResumeByTitle(positionData.getPositionName());
        }catch (Exception e){
            cvListPage.deleteLastResume();
        }
        Assert.assertEquals(cvListPage.getCountOfCreatedCVs(), beginCVsCount);
    }
}
