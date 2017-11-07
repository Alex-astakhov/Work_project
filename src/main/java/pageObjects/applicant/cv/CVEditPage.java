package pageObjects.applicant.cv;

import core.MethodsFactory;
import dataModels.applicant.cvParts.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageObjects.Popup;
import pageObjects.applicant.cv.cvPartitions.*;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.*;

/**
 * Created by Alex Astakhov on 25.11.2016.
 */
public class CVEditPage extends MethodsFactory {

    private By previewButton = By.cssSelector("[class^=gtm-tab-preview]");
    private By previewTitle = By.cssSelector(".b-preview-wrapper__info");
    private By userName = By.cssSelector(".title");

    private By editUserDataLink = By.cssSelector("[data-test=personal-data-6]");

    private By addSocialNetworkLink = By.cssSelector("[data-test=personal-data-5]");
    private By editSocialNetworkLink = By.cssSelector("[data-test*=social-button]");
    private By addPositionLink = By.cssSelector("[data-test=about]");
    private By editPositionLink = By.cssSelector("[data-test=about-3]");
    private By addGoalLink = By.cssSelector("[data-test=add-goals]");
    private By editGoalLink = By.cssSelector("[data-test=edit-goals]");
    private By addExperienceLink = By.cssSelector("[data-test=add-experience]");
    private By editExperienceLink = By.cssSelector("[data-test=edit-experience-block]");
    private By deleteExperienceElement = By.cssSelector("[data-test=delete-experience-block]");
    private By addEducationLink = By.cssSelector("[data-test=add-education]");
    private By editEducationLink = By.cssSelector("[data-test=edit-education-block]");
    private By deleteEducationElement = By.cssSelector("[data-test=delete-education-block]");
    private By addLanguageLink = By.cssSelector("[data-test=add-language]");
    private By editLanguageLink = By.cssSelector("[data-test=edit-language]");
    private By deleteLanguageElement = By.cssSelector("[data-test=delete-language]");
    private By addTrainingLink = By.cssSelector("[data-test=add-training]");
    private By editTrainingLink = By.cssSelector("[data-test=edit-training]");
    private By deleteTrainingLink = By.cssSelector("[data-test=delete-training]");
    private By addAboutInfoLink = By.cssSelector("[data-test=add-key-skill]");
    private By editAboutInfoLink = By.cssSelector("[data-test=edit-key-skill]");
    private By addDriversLicenceLink = By.cssSelector("[data-test=add-driver-licence]");
    private By editDriversLicense = By.cssSelector("[data-test=edit-driver-licence]");

    private By isSeenByAllRadio = By.cssSelector("#Resume_status_0 + label");
    private By isSeenByRegisteredRadio = By.cssSelector("#Resume_status_1 + label");
    private By isNotSeenRadio = By.cssSelector("#Resume_status_2 + label");
    private By checkedStatusRadio = By.cssSelector("#Resume_status [checked]");
    private By readyButton = By.cssSelector("[data-test=ready-button]");
    private By cancelButton = By.cssSelector("[data-test=cancel-button]");


    @Step("Click PREVIEW")
    public void clickPreview(){
        click(previewButton);
        waitForElementVisibility(previewTitle);
    }

    @Step("Click EDIT USER INFORMATION")
    public void clickEditUserData(){
        Popup<UserData> userPart = new UserPart();
        click(editUserDataLink);
        userPart.waitForPopupToBeShown();
    }

    @Step("Edit all fields of user's personal information")
    public void editUserData(UserData data){
        clickEditUserData();
        Popup<UserData> userPart = new UserPart();
        userPart.fillAllFields(data);
        userPart.clickSave();
    }

    @Step("Get user's personal information")
    public UserData getUserData(){
        waitForPageLoadedWithJQuery();
        clickEditUserData();
        Popup<UserData> userPart = new UserPart();
        UserData result = userPart.getDataFromPopup();
        userPart.clickCancel();
        return result;
    }

    @Step("Click ADD SOCIAL NET")
    public void clickAddSocialNet(){
        SocialNetPart socialNetPart = new SocialNetPart();
        click(addSocialNetworkLink);
        socialNetPart.waitForPopupToBeShown();
    }

    @Step("Delete all social nets")
    public void clearAllSocialNets(){
        List<WebElement> elements = getAllElements(editSocialNetworkLink);
        SocialNetPart socialNetPart = new SocialNetPart();
        while (!elements.isEmpty()){
            click(elements.get(0));
            socialNetPart.clickDelete();
            elements = getAllElements(editSocialNetworkLink);
        }
    }

    @Step("Click ADD POSITION")
    public void clickAddPosition(){
        Popup<PositionData> positionPart = new PositionPart();
        click(addPositionLink);
        positionPart.waitForPopupToBeShown();
    }

    @Step("Click EDIT POSITION")
    public void clickEditPosition(){
        Popup<PositionData> positionPart = new PositionPart();
        click(editPositionLink);
        positionPart.waitForPopupToBeShown();
    }

    @Step("Add information about position")
    public void addPosition(PositionData data){
        waitForPageToBeLoaded();
        clickAddPosition();
        Popup<PositionData> positionPart = new PositionPart();
        positionPart.fillAllFields(data);
        positionPart.clickSave();
    }

    @Step("Get information about position")
    public PositionData getPositionData(){
        waitForPageLoadedWithJQuery();
        clickEditPosition();
        Popup<PositionData> positionPart = new PositionPart();
        PositionData result = positionPart.getDataFromPopup();
        positionPart.clickCancel();
        return result;
    }

    @Step("Click ADD GOAL")
    public void clickAddGoal(){
        Popup<String> goalPart = new GoalPart();
        click(addGoalLink);
        goalPart.waitForPopupToBeShown();
    }

    @Step("Click EDIT GOAL")
    public void clickEditGoal(){
        Popup<String> goalPart = new GoalPart();
        click(editGoalLink);
        goalPart.waitForPopupToBeShown();
    }

    @Step("Add goal: {0}")
    public void addGoal(String goal){
        waitForPageToBeLoaded();
        clickAddGoal();
        Popup<String> goalPart = new GoalPart();
        goalPart.fillAllFields(goal);
        goalPart.clickSave();
    }

    @Step("Get goal text")
    public String getGoal(){
        waitForPageToBeLoaded();
        clickEditGoal();
        Popup<String> goalPart = new GoalPart();
        String result = goalPart.getDataFromPopup();
        goalPart.clickCancel();
        return result;
    }

    @Step("Edit goal")
    public void editGoal(String goal){
        clickEditGoal();
        Popup<String> goalPart = new GoalPart();
        goalPart.fillAllFields(goal);
        goalPart.clickSave();
    }

    @Step("Verify if goal is present")
    public boolean goalIsPresent(){
        return getAllElements(editGoalLink).size() > 0;
    }

    @Step("Click ADD EXPERIENCE")
    public void clickAddExperience(){
        Popup<ExperienceData> experiencePart = new ExperiencePart();
        click(addExperienceLink);
        experiencePart.waitForPopupToBeShown();
    }

    @Step("Click EDIT EXPERIENCE at place {0}")
    public void clickEditExperience(int number){
        Popup<ExperienceData> experiencePart = new ExperiencePart();
        List<WebElement> elements = getAllElements(editExperienceLink);
        click(elements.get(number));
        experiencePart.waitForPopupToBeShown();
    }

    @Step("Click DELETE EXPERIENCE at place {0}")
    public void clickDeleteExperience(int number){
        List<WebElement> elements = getAllElements(deleteExperienceElement);
        click(elements.get(number));
        acceptAlert();
        waitForAmountReduction(deleteExperienceElement, elements.size());
    }

    @Step("Click ADD EDUCATION")
    public void clickAddEducation(){
        Popup<EducationData> educationPart = new EducationPart();
        click(addEducationLink);
        educationPart.waitForPopupToBeShown();
    }

    @Step("Click EDIT EDUCATION at place{0}")
    public void clickEditEducation(int number){
        Popup<EducationData> educationPart = new EducationPart();
        List<WebElement> elements = getAllElements(editEducationLink);
        click(elements.get(number));
        educationPart.waitForPopupToBeShown();
    }

    @Step("Click DELETE EDUCATION at place {0}")
    public void clickDeleteEducation(int number){
        List<WebElement> elements = getAllElements(deleteEducationElement);
        click(elements.get(number));
        acceptAlert();
        waitForAmountReduction(deleteEducationElement, elements.size());
    }

    @Step("Click ADD LANGUAGE")
    public void clickAddLanguage(){
        Popup<LanguageData> languagePart = new LanguagePart();
        click(addLanguageLink);
        languagePart.waitForPopupToBeShown();
    }

    @Step("Click EDIT LANGUAGE at place {0}")
    public void clickEditLanguage(int number){
        Popup<LanguageData> languagePart = new LanguagePart();
        List<WebElement> elements = getAllElements(editLanguageLink);
        click(elements.get(number));
        languagePart.waitForPopupToBeShown();
    }

    @Step("Click DELETE LANGUAGE at place {0}")
    public void clickDeleteLanguage(int number){
        List<WebElement> elements = getAllElements(deleteLanguageElement);
        click(elements.get(number));
        acceptAlert();
        waitForAmountReduction(deleteLanguageElement, elements.size());
    }

    @Step("Click ADD TRAINING")
    public void clickAddTraining(){
        Popup<TrainingData> trainingPart = new TrainingPart();
        click(addTrainingLink);
        trainingPart.waitForPopupToBeShown();
    }

    @Step("Click EDIT TRAINING at place {0}")
    public void clickEditTraining(int number){
        Popup<TrainingData> trainingPart = new TrainingPart();
        List<WebElement> elements = getAllElements(editTrainingLink);
        click(elements.get(number));
        trainingPart.waitForPopupToBeShown();
    }

    @Step("Click DELETE TRAINING at place {0}")
    public void clickDeleteTraining(int number){
        List<WebElement> elements = getAllElements(deleteTrainingLink);
        click(elements.get(number));
        acceptAlert();
        waitForAmountReduction(deleteTrainingLink, elements.size());
    }

    @Step("Click ADD INFORMATION ABOUT")
    public void clickAddAboutInfo(){
        Popup<AboutAndSkillsData> aboutPart = new AboutAndSkillsPart();
        click(addAboutInfoLink);
        aboutPart.waitForPopupToBeShown();
    }

    @Step("Click EDIT INFORMATION ABOUT")
    public void clickEditAboutInfo(){
        Popup<AboutAndSkillsData> aboutPart = new AboutAndSkillsPart();
        click(editAboutInfoLink);
        aboutPart.waitForPopupToBeShown();
    }

    @Step("Verify if driver's license is presnt")
    public boolean driversLicenseIsPresent(){
        return getAllElements(editDriversLicense).size() > 0;
    }

    @Step("Click EDIT DRIVER'S LICENSE")
    public void clickEditDriversLicence(){
        Popup<DriversData> driversPart = new DriversPart();
        if (driversLicenseIsPresent())
            click(editDriversLicense);
        else
            click(addDriversLicenceLink);
        driversPart.waitForPopupToBeShown();
    }

    @Step("Choose VISIBLE TO ALL")
    public void clickIsSeenByAll(){
        click(isSeenByAllRadio);
    }

    @Step("Choose VISIBLE TO REGISTERED")
    public void clickIsSeenByRegistered(){
        click(isSeenByRegisteredRadio);
    }

    @Step("Choose INVISIBLE")
    public void clickIsNotSeen(){
        click(isNotSeenRadio);
    }

    @Step("Click READY")
    public void clickReady(){
        click(readyButton);
    }

    @Step("Click CANCEL")
    public void clickCancel(){
        click(cancelButton);
    }

    @Step("Add all social nets")
    public void addAllSocialNets(SocialNetData data){
        waitForPageToBeLoaded();
        SocialNetPart socialNetPart = new SocialNetPart();
        Map<String, String> socNets = data.getSocNets();
        Iterator<Map.Entry<String, String>> iterator = socNets.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, String> pair = iterator.next();
            waitForPageToBeLoaded();
            clickAddSocialNet();
            socialNetPart.fillAllFields(pair.getKey(), pair.getValue());
            socialNetPart.clickSave();
        }
    }

    @Step("Get all social nets")
    public SocialNetData getSocialNetData(){
        List<WebElement> elements = getAllElements(editSocialNetworkLink);
        Map<String, String> result = new HashMap<>();
        SocialNetPart socialNetPart = new SocialNetPart();
        int count = elements.size();
        for (int i = 0; i < count; i++) {
            waitForPageToBeLoaded();
            click(elements.get(i));
            socialNetPart.waitForPopupToBeShown();
            result.put(socialNetPart.getSocNetValue(), socialNetPart.getSocNetUrl());
            socialNetPart.clickCancel();
        }
        return new SocialNetData(result);
    }

    @Step("Add all experiences")
    public void addAllExperiences(ExperienceData[] datas){
        Popup<ExperienceData> experiencePart = new ExperiencePart();
        for (ExperienceData data: datas) {
            waitForPageToBeLoaded();
            clickAddExperience();
            experiencePart.fillAllFields(data);
            experiencePart.clickSave();
        }
    }

    @Step("Get all experiences")
    public ExperienceData[] getAllExperienceData(){
        waitForPageToBeLoaded();
        Popup<ExperienceData> experiencePart = new ExperiencePart();
        int count = getAmountOfElements(editExperienceLink, 1000);
        if (count == 0)
            return null;
        ExperienceData[] result = new ExperienceData[count];
        for (int i = 0; i < count; i++) {
            clickEditExperience(i);
            result[i] = experiencePart.getDataFromPopup();
            experiencePart.clickCancel();
        }
        return result;
    }

    @Step("Get count of experiences")
    public int getExperiencesCount(){
        return getAmountOfElements(editExperienceLink, 1000);
    }

    @Step("Add all educations")
    public void addAllEducations(EducationData[] datas){
        Popup<EducationData> educationPart = new EducationPart();
        for (EducationData data: datas) {
            waitForPageToBeLoaded();
            clickAddEducation();
            educationPart.fillAllFields(data);
            educationPart.clickSave();
        }
    }

    @Step("Get all educations")
    public EducationData[] getAllEducationData(){
        Popup<EducationData> educationPart = new EducationPart();
        int count = getAmountOfElements(editEducationLink, 1000);
        EducationData[] result = new EducationData[count];
        for (int i = 0; i < count; i++) {
            clickEditEducation(i);
            result[i] = educationPart.getDataFromPopup();
            educationPart.clickCancel();
        }
        return result;
    }

    @Step("Get count of educations")
    public int getEducationsCount(){
        return getAmountOfElements(editEducationLink, 1000);
    }

    @Step("Add all languages")
    public void addAllLanguages(Set<LanguageData> datas){
        Popup<LanguageData> languagePart = new LanguagePart();
        int count = 0;
        for (LanguageData data: datas) {
            waitForContentLoaded(500);
            clickAddLanguage();
            languagePart.fillAllFields(data);
            languagePart.clickSave();
            waitForAmountIncrease(editLanguageLink, count++);
        }

    }

    @Step("Get all languages")
    public Set<LanguageData> getAllLanguages(){
        Popup<LanguageData> languagePart = new LanguagePart();
        int count = getAmountOfElements(editLanguageLink);
        Set<LanguageData> result = new LinkedHashSet<>();
        for (int i = 0; i < count; i++) {
            waitForPageToBeLoaded();
            clickEditLanguage(i);
            result.add(languagePart.getDataFromPopup());
            languagePart.clickCancel();
        }
        return result;
    }

    @Step("Get count of languages")
    public int getLanguagesCount(){
        return getAmountOfElements(editLanguageLink, 1000);
    }

    @Step("Add all trainings")
    public void addAllTrainings(TrainingData[] datas){
        Popup<TrainingData> trainingPart = new TrainingPart();
        for (TrainingData data: datas) {
            waitForPageToBeLoaded();
            clickAddTraining();
            trainingPart.fillAllFields(data);
            trainingPart.clickSave();
        }
    }

    @Step("Get all trainings")
    public TrainingData[] getAllTrainingData(){
        Popup<TrainingData> trainingPart = new TrainingPart();
        int count = getAmountOfElements(editTrainingLink, 1000);
        TrainingData[] result = new TrainingData[count];
        for (int i = 0; i < count; i++) {
            waitForPageToBeLoaded();
            clickEditTraining(i);
            result[i] = trainingPart.getDataFromPopup();
            trainingPart.clickCancel();
        }
        return result;
    }

    @Step("Get count of trainings")
    public int getTrainingsCount(){
        return getAmountOfElements(editTrainingLink, 1000);
    }

    @Step("Add information about applicant")
    public void addAboutInfo(AboutAndSkillsData data){
        waitForPageToBeLoaded();
        clickAddAboutInfo();
        AboutAndSkillsPart aboutAndSkillsPart = new AboutAndSkillsPart();
        aboutAndSkillsPart.fillAboutData(data);
        aboutAndSkillsPart.clickSave();
    }

    @Step("Edit information about applicant")
    public void editAboutInfo(AboutAndSkillsData data){
        waitForPageToBeLoaded();
        clickEditAboutInfo();
        AboutAndSkillsPart aboutAndSkillsPart = new AboutAndSkillsPart();
        aboutAndSkillsPart.fillAboutData(data);
        aboutAndSkillsPart.clickSave();
    }

    @Step("Get information about applicant")
    public String getAboutData(){
        waitForPageToBeLoaded();
        AboutAndSkillsPart aboutAndSkillsPart = new AboutAndSkillsPart();
        clickEditAboutInfo();
        String result = aboutAndSkillsPart.getAboutText();
        aboutAndSkillsPart.clickCancel();
        return result;
    }

    @Step("Verify if information about applicant is present")
    public boolean aboutInfoIsPresent(){
        return getAllElements(editAboutInfoLink).size() > 0;
    }

    @Step("Edit driver's information")
    public void editDriversData(DriversData data){
        waitForPageToBeLoaded();
        clickEditDriversLicence();
        Popup<DriversData> driversPart = new DriversPart();
        driversPart.fillAllFields(data);
        driversPart.clickSave();
    }

    @Step("Get driver's information")
    public DriversData getDriversData(){
        waitForPageToBeLoaded();
        clickEditDriversLicence();
        Popup<DriversData> driversPart = new DriversPart();
        DriversData result = driversPart.getDataFromPopup();
        driversPart.clickCancel();
        return result;
    }

    @Step("Choose visibility of CV: {0} (0 - is seen by all, 1 - is seen by registered, 2 - is hidden)")
    public void chooseVisibilityOfCV(int var){
        switch (var){
            case 1:
                clickIsSeenByAll();
                break;
            case 2:
                clickIsSeenByRegistered();
                break;
            case 3:
                clickIsNotSeen();
                break;
            default:
                System.out.println("Wrong variant of CV visibility");
        }
    }

    @Step("Get chosen visibility status")
    public int getCheckedStatus(){
        waitForElementPresence(checkedStatusRadio);
        return Integer.parseInt(getValueAttrOfElement(checkedStatusRadio));
    }
}
