package pageObjects.applicant.cv;

import core.MethodsFactory;
import dataModels.applicant.cvParts.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageObjects.applicant.cv.cvPartitions.*;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.*;

/**
 * Created by Asta on 23.12.2016.
 */
public class CVEditPageMobile extends MethodsFactory{

    private By editResumeLink = By.cssSelector("[data-test=edit_resume_show_link]");
    private By previewResumeLink = By.cssSelector("[data-test=preview_resume_show_link]");
    private By editPersonalDataLink = By.cssSelector("[data-test=about_edit_link]");
    private By addSocialNetLink = By.cssSelector("[data-test=add_social_link]");
    private By vkIcon = By.cssSelector("[data-test=network-1]");
    private By gpIcon = By.cssSelector("[data-test=network-2]");
    private By okIcon = By.cssSelector("[data-test=network-3]");
    private By fbIcon = By.cssSelector("[data-test=network-4]");
    private By twIcon = By.cssSelector("[data-test=network-5]");
    private By lnIcon = By.cssSelector("[data-test=network-6]");
    private By allSocNetIcons = By.cssSelector("[data-test*=network]");
    private By editPositionLink = By.cssSelector("[data-test=position_name_link]");
    private By editGoalLink = By.cssSelector("[data-test=goals_edit_link]");
    private By addEducationLink = By.cssSelector("[data-test=education_add_link]");
    private By editEducationLink = By.cssSelector("[data-test=education_edit_link]");
    private By addLanguageLink = By.cssSelector("[data-test=language_add_link]");
    private By editLanguageLink = By.cssSelector("[data-test=language_edit_link]");
    private By addTrainingLink = By.cssSelector("[data-test=courses_add_link]");
    private By editTrainingLink = By.cssSelector("[data-test=courses_edit_link]");
    private By addExperienceLink = By.cssSelector("[data-test=experience_add_link]");
    private By editExperienceLink = By.cssSelector("[data-test=experience_edit_link]");
    private By addAboutMyselfLink = By.cssSelector("[data-test=add_about_my_self]");
    private By editAboutMyselfLink = By.cssSelector("[data-test=edit_about_my_self]");
    private By addSkillsLink = By.cssSelector("[data-test=add_skills]");
    private By editSkillsLink = By.cssSelector("[data-test=edit_skills]");
    private By addDriversLicenceLink = By.cssSelector("[data-test=add_driver_licence_categories]");
    private By editDriversLicenceLink = By.cssSelector("[data-test=edit_driver_licence_categories]");
    private By editPrivateCarLink = By.cssSelector("[data-test=edit_driver_car]");
    private By isSeenByAllRadio = By.cssSelector("#Resume_status_0 + label");
    private By isSeenByRegisteredRadio = By.cssSelector("#Resume_status_1 + label");
    private By isNotSeenRadio = By.cssSelector("#Resume_status_2 + label");
    private By checkedStatusRadio = By.cssSelector("#Resume_status [checked]");
    private By readyButton = By.cssSelector("[data-test=ready_button]");
    private By backButton = By.cssSelector("[data-test=back_link_resume]");


    @Step("Click EDIT RESUME")
    public void clickEditResume(){
        click(editResumeLink);
    }

    @Step("Click PREVIEW RESUME")
    public void clickPreviewResume(){
        click(previewResumeLink);
    }

    @Step("Click EDIT USER PERSONAL INFORMATION")
    public void clickEditPersonalData(){
        click(editPersonalDataLink);
    }

    @Step("Click ADD SOCIAL NET")
    public void clickAddSocialNet(){
        click(addSocialNetLink);
    }

    @Step("Click EDIT POSITION")
    public void clickEditPosition(){
        click(editPositionLink);
    }

    @Step("Click EDIT GOAL")
    public void clickEditGoal(){
        click(editGoalLink);
    }

    @Step("Click ADD EDUCATION")
    public void clickAddEducation(){
        click(addEducationLink);
    }

    @Step("Click EDIT EDUCATION at place {0}")
    public void clickEditEducation(int number){
        List<WebElement> elements = getAllElements(editEducationLink);
        click(elements.get(number));
    }

    @Step("Get count of all educations")
    public int getEducationsAmount(){
        return getAmountOfElements(editEducationLink);
    }

    @Step("Click ADD LANGUAGE")
    public void clickAddLanguage(){
        click(addLanguageLink);
    }

    @Step("Click EDIT LANGUAGE at place {0}")
    public void clickEditLanguage(int number){
        List<WebElement> elements = getAllElements(editLanguageLink);
        click(elements.get(number));
    }

    @Step("Click ADD TRAINING")
    public void clickAddTraining(){
        click(addTrainingLink);
    }

    @Step ("Click EDIT TRAINING at place {0}")
    public void clickEditTraining(int number){
        List<WebElement> elements = getAllElements(editTrainingLink);
        click(elements.get(number));
    }

    @Step("Click ADD EXPERIENCE")
    public void clickAddExperience(){
        click(addExperienceLink);
    }

    @Step("Click EDIT EXPERIENCE at place {0}")
    public void clickEditExperience(int number){
        List<WebElement> elements = getAllElements(editExperienceLink);
        click(elements.get(number));
    }

    @Step("Click ADD INFORMATION ABOUT")
    public void clickAddAboutMyself(){
        click(addAboutMyselfLink);
    }

    @Step("Click EDIT INFORMATION ABOUT")
    public void clickEditAboutMyself(){
        click(editAboutMyselfLink);
    }

    @Step("Click ADD SKILLS")
    public void clickAddSkills(){
        click(addSkillsLink);
    }

    @Step("Click EDIT SKILLS")
    public void clickEditSkills(){
        click(editSkillsLink);
    }

    @Step("Click EDIT DRIVER'S LICENSE")
    public void clickEditDriversLicence(){
        if (presenceOfElement(addDriversLicenceLink))
            click(addDriversLicenceLink);
        else
            click(editDriversLicenceLink);
    }

    @Step("Choose VISIBLE FOR ALL")
    public void clickIsSeenByAll(){
        click(isSeenByAllRadio);
    }

    @Step("Choose VISIBLE FOR REGISTERED")
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

    @Step("Click BACK")
    public void clickBack(){
        click(backButton);
    }

    @Step("Click VK")
    public void clickVk(){
        click(vkIcon);
    }

    @Step("Click GOOGLE PLUS")
    public void clickGp(){
        click(gpIcon);
    }

    @Step("Click OK.RU")
    public void clickOk(){
        click(okIcon);
    }

    @Step("Click FB")
    public void clickFb(){
        click(fbIcon);
    }

    @Step("Click TWITTER")
    public void clickTw(){
        click(twIcon);
    }

    @Step("Click LINKED IN")
    public void clickLn(){
        click(lnIcon);
    }

    @Step("Get all social nets")
    public SocialNetData getSocialNetData(){
        Map<String, String> result = new HashMap<>();
        List<WebElement> icons = getAllElements(allSocNetIcons);
        SocialNetPart socialNetPart = new SocialNetPart();
        int iconsCount = icons.size();
        for (int i = 0; i < iconsCount; i++) {
            waitForPageToBeLoaded();
            icons.get(i).click();
            result.put(socialNetPart.getSocNetValue(), socialNetPart.getSocNetUrl());
            socialNetPart.mobClickCancel();
            icons = getAllElements(allSocNetIcons);
        }
        return new SocialNetData(result);
    }

    @Step("Delete all social nets")
    public boolean clearAllSocialNets(){
        List<WebElement> icons = getAllElements(allSocNetIcons);
        SocialNetPart socialNetPart = new SocialNetPart();
        int iconsCount = icons.size();
        for (int i = 0; i < iconsCount; i++) {
            click(icons.get(0));
            socialNetPart.mobClickDelete();
            acceptAlert();
            icons = getAllElements(allSocNetIcons);
        }
        return getAmountOfElements(allSocNetIcons) == 0;
    }

    @Step("Get all educations")
    public EducationData[] getAllEducationData(){
        List<WebElement> educations = getAllElements(editEducationLink);
        EducationPart educationPart = new EducationPart();
        int count = educations.size();
        EducationData[] datas = new EducationData[count];
        for (int i = 0; i < count; i++) {
            click(educations.get(i));
            datas[i] = educationPart.getDataFromPopup();
            educationPart.mobClickCancel();
            educations = getAllElements(editEducationLink);
        }
        return datas;
    }

    @Step("Get count of all languages")
    public int getLanguagesCount(){
        return getAmountOfElements(editLanguageLink);
    }

    @Step("Get all languages")
    public Set<LanguageData> getAllLanguages(){
        List<WebElement> elements = getAllElements(editLanguageLink);
        Set<LanguageData> result = new LinkedHashSet<>();
        LanguagePart languagePart = new LanguagePart();
        int languageCount = elements.size();
        for (int i = 0; i < languageCount; i++) {
            click(elements.get(i));
            result.add(languagePart.getDataFromPopup());
            languagePart.mobClickCancel();
            elements = getAllElements(editLanguageLink);
        }
        return result;
    }

    @Step("Get all experiences")
    public ExperienceData[] getAllExperienceData(){
        List<WebElement> elements = getAllElements(editExperienceLink);
        ExperiencePart experiencePart = new ExperiencePart();
        ExperienceData[] result;
        int count = elements.size();
        if (count == 0){
            result = new ExperienceData[1];
            result[0] = new ExperienceData(true, null, null, null,null, 0, null, 0, null, false, null);
            return result;
        }
        result = new ExperienceData[count];
        for (int i = 0; i < count; i++) {
            click(elements.get(i));
            result[i]= experiencePart.mobGetExperienceData();
            experiencePart.mobClickCancel();
            elements = getAllElements(editExperienceLink);
        }
        return result;
    }

    @Step("Get count of all trainings")
    public int getTrainingsCount(){
        return getAmountOfElements(editTrainingLink);
    }

    @Step("Get all trainings")
    public TrainingData[] getAllTrainings(){
        List<WebElement> elements = getAllElements(editTrainingLink);
        int count = elements.size();
        TrainingData[] result = new TrainingData[elements.size()];
        TrainingPart trainingPart = new TrainingPart();
        for (int i = 0; i < count; i++) {
            click(elements.get(i));
            result[i] = trainingPart.getDataFromPopup();
            trainingPart.mobClickCancel();
            elements = getAllElements(editTrainingLink);
        }
        return result;
    }

    @Step("Get count of experiences")
    public int getExperiencesCount(){
        return getAmountOfElements(editExperienceLink);
    }

    @Step("Get text about applicant")
    public String getAboutText(){
        AboutAndSkillsPart aboutAndSkillsPart = new AboutAndSkillsPart();
        String result = "";
        if (presenceOfElement(editAboutMyselfLink)){
            click(editAboutMyselfLink);
            result = aboutAndSkillsPart.getAboutText();
            aboutAndSkillsPart.mobClickCancel();
        }
        return result;
    }

    @Step("Get all skills")
    public Set<String> getSkills(){
        AboutAndSkillsPart aboutAndSkillsPart = new AboutAndSkillsPart();
        Set<String> result = null;
        if (presenceOfElement(editSkillsLink)){
            click(editSkillsLink);
            result = aboutAndSkillsPart.getSkills();
            aboutAndSkillsPart.mobClickCancel();
        }
        return result;
    }

    @Step("Choose visibility of CV: {0} (1 - is seen by all, 2 - is seen by registered, 3 - is hidden)")
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

    @Step("Get CV's visibility status")
    public int getCheckedStatus(){
        return Integer.parseInt(getValueAttrOfElement(checkedStatusRadio));
    }

}
