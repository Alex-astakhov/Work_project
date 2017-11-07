package applicant.mobile.cv;

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
import pageObjects.FooterBlock;
import pageObjects.MainPage;
import pageObjects.LoginPage;
import pageObjects.MobileMenu;
import pageObjects.applicant.cv.CvListPage;
import pageObjects.applicant.cv.CVEditPageMobile;
import pageObjects.applicant.cv.cvPartitions.*;
import ru.yandex.qatools.allure.annotations.Features;

import java.util.Arrays;
import java.util.Random;
import java.util.Set;


/**
 * Created by Asta on 27.12.2016.
 */
@Listeners({ListenerWithBrowserShot.class})
@Features("MOBILE Full resume creation")
public class AddCvMobile extends BrowserFactory {
    private Random random = new Random();
    private ApplicantRegistrationData appRegData = new ApplicantRegistrationData(Constants.APPLICANT_REG_EMAIL, Constants.PASSWORD);
    private UserData userData = new UserData();
    private SocialNetData socialNetData = new SocialNetData();
    private PositionData positionData = new PositionData(userData.getCityValue());
    private String goal = "Моя цель от " + DataGenerator.getCurrentDateAndTimeString();
    private EducationData[] educationDatas = EducationData.getRandomEducations(random.nextInt(5) + 2);
    private Set<LanguageData> languageDatas = LanguageData.getRandomLanguages(random.nextInt(8) + 2);
    private TrainingData[] trainingDatas = TrainingData.getRandomTrainings(random.nextInt(5) + 1);
    private ExperienceData[] experienceDatas = ExperienceData.getRandomExperiences(random.nextInt(5) + 2, false);
    private AboutAndSkillsData aboutAndSkillsData = new AboutAndSkillsData(false);
    private DriversData driversData = new DriversData();

    private CvListPage cvListPage = new CvListPage();
    private CVEditPageMobile editCVPageMobile = new CVEditPageMobile();
    private int beginCVsCount;

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
        MainPage mainPage = new MainPage();
        mainPage.mobClickLogin();
        LoginPage loginPage = new LoginPage();
        loginPage.loginUser(Constants.APPLICANT_LOGIN_EMAIL, Constants.PASSWORD);
        Assertion.urlContains("notebook", 10);
        beginCVsCount = cvListPage.getCountOfCreatedCVs();
        System.out.println("CV's before creation: " + beginCVsCount);
    }

    @Test(dependsOnMethods = "login")
    public void verifyResumeCreation() {
        MobileMenu menu = new MobileMenu();
        menu.clickMenu();
        menu.clickMyResumes();
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
    }


    @Test(dependsOnMethods = "verifyResumeCreation")
    public void verifyUserDataEditing(){
        editCVPageMobile.clickEditPersonalData();
        UserPart userPart = new UserPart();
        userPart.fillAllFieldsMob(userData);
        userPart.mobClickSave();
        editCVPageMobile.clickEditPersonalData();
        UserData actualData = userPart.getDataFromPopup();
        userPart.mobClickCancel();
        Assert.assertEquals(actualData, userData);

    }

   @Test(dependsOnMethods = "verifyResumeCreation")
    public void verifyAdditionOfSocialNets(){
        editCVPageMobile.clearAllSocialNets();
        editCVPageMobile.clickAddSocialNet();
        SocialNetPart socialNetPart = new SocialNetPart();
        socialNetPart.mobAddAllSocNets(socialNetData);
        SocialNetData actual = editCVPageMobile.getSocialNetData();
        System.out.println("Actual:   " + actual);
        System.out.println("Expected: " + socialNetData);
        Assert.assertEquals(actual, socialNetData);
    }

    @Test(dependsOnMethods = "verifyUserDataEditing")
    public void verifyPositionEditing(){
        editCVPageMobile.clickEditPosition();
        PositionPart positionPart = new PositionPart();
        positionPart.mobFillAllFields(positionData);
        positionPart.mobClickSave();
        editCVPageMobile.clickEditPosition();
        PositionData actual = positionPart.mobGetPositionData();
        positionPart.mobClickCancel();
        System.out.println("Actual:   " + actual);
        System.out.println("Expected: " + positionData);
        Assert.assertEquals(actual, positionData);
    }

    @Test(dependsOnMethods = "verifyResumeCreation")
    public void verifyGoalEditing(){
        editCVPageMobile.clickEditGoal();
        GoalPart goalPart = new GoalPart();
        goalPart.fillAllFields(goal);
        goalPart.mobClickSave();
        editCVPageMobile.clickEditGoal();
        String actual = goalPart.getDataFromPopup();
        goalPart.mobClickCancel();
        System.out.println("Actual:   " + actual);
        System.out.println("Expected: " + goal);
        Assert.assertEquals(actual, goal);
    }

    @Test(dependsOnMethods = "verifyResumeCreation")
    public void verifyEducationEditing(){
        EducationPart educationPart = new EducationPart();
        editCVPageMobile.clickAddEducation();
        educationPart.mobAddAllEducations(educationDatas);
        EducationData[] actual = editCVPageMobile.getAllEducationData();
        Arrays.sort(educationDatas);
        System.out.println("Actual:   " + Arrays.toString(actual));
        System.out.println("Expected: " + Arrays.toString(educationDatas));
        Assert.assertTrue(Arrays.equals(educationDatas, actual), "Массивы объектов образования не идентичны!");
        editCVPageMobile.clickEditEducation(random.nextInt(educationDatas.length));
        educationPart.mobClickDelete();
        acceptAlert();
        Assert.assertEquals(editCVPageMobile.getEducationsAmount(), educationDatas.length - 1);
    }

    @Test(dependsOnMethods = "verifyResumeCreation")
    public void verifyAdditionOfLanguages(){
        LanguagePart languagePart = new LanguagePart();
        editCVPageMobile.clickAddLanguage();
        languagePart.mobAddAllLanguages(languageDatas);
        Assert.assertEquals(editCVPageMobile.getLanguagesCount(), languageDatas.size());
        Set<LanguageData> actual = editCVPageMobile.getAllLanguages();
        System.out.println("Actual:   " + actual.toString());
        System.out.println("Expected: " + languageDatas.toString());
        Assert.assertEquals(actual.toString(), languageDatas.toString());
        editCVPageMobile.clickEditLanguage(random.nextInt(languageDatas.size()));
        languagePart.mobClickDelete();
        acceptAlert();
        Assert.assertEquals(editCVPageMobile.getLanguagesCount(), languageDatas.size() - 1);
    }

    @Test(dependsOnMethods = "verifyResumeCreation")
    public void verifyAdditionOfTrainings(){
        TrainingPart trainingPart = new TrainingPart();
        editCVPageMobile.clickAddTraining();
        trainingPart.mobAddAllTrainingData(trainingDatas);
        Assert.assertEquals(editCVPageMobile.getTrainingsCount(), trainingDatas.length);
        TrainingData[] actual = editCVPageMobile.getAllTrainings();
        Arrays.sort(trainingDatas);
        System.out.println("Actual:   " + Arrays.toString(actual));
        System.out.println("Expected: " + Arrays.toString(trainingDatas));
        Assert.assertEquals(actual, trainingDatas);
        editCVPageMobile.clickEditTraining(random.nextInt(trainingDatas.length));
        trainingPart.mobClickDelete();
        acceptAlert();
        Assert.assertEquals(editCVPageMobile.getTrainingsCount(), trainingDatas.length - 1);
    }

    @Test(dependsOnMethods = "verifyResumeCreation")
    public void verifyAdditionOfExperiences(){
        ExperiencePart experiencePart = new ExperiencePart();
        editCVPageMobile.clickAddExperience();
        experiencePart.mobAddAllExperienceData(experienceDatas);
        Assert.assertEquals(editCVPageMobile.getExperiencesCount(), experienceDatas.length);
        ExperienceData[] actual = editCVPageMobile.getAllExperienceData();
        Arrays.sort(experienceDatas);
        System.out.println("Actual:   " + Arrays.toString(actual));
        System.out.println("Expected: " + Arrays.toString(experienceDatas));
        Assert.assertEquals(actual, experienceDatas);
        editCVPageMobile.clickEditExperience(random.nextInt(experienceDatas.length));
        waitForContentLoaded(1000);
        //System.out.println(driver().getPageSource());
        experiencePart.mobClickDelete();
        Assert.assertEquals(editCVPageMobile.getExperiencesCount(), experienceDatas.length - 1);
    }

    @Test(dependsOnMethods = "verifyResumeCreation")
    public void verifyAdditionOfAboutAndSkills(){
        AboutAndSkillsPart aboutAndSkillsPart = new AboutAndSkillsPart();
        editCVPageMobile.clickAddAboutMyself();
        aboutAndSkillsPart.fillAboutData(aboutAndSkillsData);
        aboutAndSkillsPart.mobClickSave();
        editCVPageMobile.clickAddSkills();
        aboutAndSkillsPart.addAllSkills(aboutAndSkillsData);
        aboutAndSkillsPart.mobClickSave();
        editCVPageMobile.clickEditAboutMyself();
        String about = aboutAndSkillsPart.getAboutText();
        aboutAndSkillsPart.mobClickCancel();
        editCVPageMobile.clickEditSkills();
        Set<String> skills = aboutAndSkillsPart.getSkills();
        aboutAndSkillsPart.mobClickCancel();
        AboutAndSkillsData actual = new AboutAndSkillsData(about, skills);
        System.out.println("Actual:   " + actual);
        System.out.println("Expected: " + aboutAndSkillsData);
        Assert.assertEquals(actual, aboutAndSkillsData);
    }

    @Test(dependsOnMethods = "verifyResumeCreation")
    public void verifyAdditionOfDriversData(){
        DriversPart driversPart = new DriversPart();
        editCVPageMobile.clickEditDriversLicence();
        driversPart.fillAllFields(driversData);
        driversPart.mobClickSave();
        editCVPageMobile.clickEditDriversLicence();
        DriversData actual = driversPart.getDataFromPopup();
        driversPart.mobClickCancel();
        System.out.println("Actual:   " + actual);
        System.out.println("Expected: " + driversData);
        Assert.assertEquals(actual, driversData);
    }

    @Test(dependsOnMethods = {"verifyEducationEditing", "verifyAdditionOfLanguages", "verifyPositionEditing"})
    public void saveResume(){
        int status = random.nextInt(3) + 1;
        editCVPageMobile.chooseVisibilityOfCV(status);
        editCVPageMobile.clickReady();
        Assertion.urlContains("notebook", 5);
        int finalCVsCount = cvListPage.getCountOfCreatedCVs();
        System.out.println("final CVs count: " + finalCVsCount);
        System.out.println("expected CVs count: " + (beginCVsCount + 1));
        Assert.assertEquals(finalCVsCount, beginCVsCount + 1);
        cvListPage.clickEditLastResume();
        int actual = editCVPageMobile.getCheckedStatus();
        System.out.println("Actual CV status:   " + actual);
        System.out.println("Expected CV status: " + status);
        editCVPageMobile.clickBack();
        Assert.assertEquals(actual, status);
    }

    @Test(dependsOnMethods = "saveResume", alwaysRun = true)
    public void deleteCreatedResume(){
        new MobileMenu().openCVs();
        try {
            cvListPage.mobDeleteResumeByTitle(positionData.getPositionName());
        } catch (Exception e){
            cvListPage.mobDeleteLastResume();
        }
        Assert.assertEquals(cvListPage.getCountOfCreatedCVs(), beginCVsCount);
    }
}
