package pageObjects.applicant.cv.cvPartitions;

import core.Constants;
import dataModels.applicant.cvParts.AboutAndSkillsData;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import pageObjects.Popup;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Asta on 19.01.2017.
 */
public class AboutAndSkillsPart extends Popup<AboutAndSkillsData> {

    private By aboutField = By.id("Resume_key_skills");
    private By skillField = By.cssSelector("[data-test=skill-input]");
    private By aboutTextField = By.id("tinymce");
    private By addSkillButton = By.cssSelector("[data-test=skill-submit]");
    private By addedSkill = By.cssSelector("[data-test=skill]");
    private By deleteSkillElement = By.cssSelector("[data-test=skill] [class*=delete]");
    private By mobCancelButton = By.cssSelector("[data-test=button-cancel]");
    private By mobSaveButton = By.cssSelector("[data-test=button-save]");

    @Step("Fill all fields")
    public void mobFillAllFields(AboutAndSkillsData data){
        mobTypeAbout(data.getAboutMyself());
        Set<String> skills = data.getSkills();
        int count = 0;
        for (String skill: skills) {
            addSkill(skill);
            waitForAmountIncrease(addedSkill, count++);
        }
        addSkill("Лишний скилл");
        waitForAmountIncrease(addedSkill, count);
        clickDeleteLastSkill();
    }

    @Step("Fill \"About\" field")
    public void fillAboutData(AboutAndSkillsData data){
        typeInTinymce(data.getAboutMyself());
    }

    @Step("Add skills")
    public void addAllSkills(AboutAndSkillsData data){
        Set<String> skills = data.getSkills();
        for (String skill: skills) {
            addSkill(skill);
        }
        addSkill("Лишний скилл");
        clickDeleteLastSkill();
    }

    @Step("Type about {0}")
    public void mobTypeAbout(String about){
        type(aboutField, about);
    }

    @Step("Type skill {0}")
    public void typeSkill(String skill){
        type(skillField, skill);
    }

    @Step("Add skill {0}")
    public void addSkill(String skill){
        typeSkill(skill);
        clickAddSkill();
    }

    @Step("Click ADD SKILL")
    public void clickAddSkill(){
        List<WebElement> elements = getAllElements(addedSkill);
        click(addSkillButton);
        try {
            waitForAmountIncrease(addedSkill, elements.size());
        }catch (TimeoutException e){
            System.out.println("Skill wasn't added!");
        }
    }

    @Step("Click delete on last skill")
    public void clickDeleteLastSkill(){
        waitForContentLoaded(500);
        List<WebElement> elements = getAllElements(deleteSkillElement);
        click(elements.get(elements.size() - 1));
        waitForAmountReduction(addedSkill, elements.size());
    }

   @Step("Click mobile CANCEL")
   public void mobClickCancel(){
        click(mobCancelButton);
       waitForPageToBeLoaded();
   }

   @Step("Click mobile SAVE")
   public void mobClickSave(){
       click(mobSaveButton);
       waitForPageToBeLoaded();
   }

    @Step("Get text from About field")
    public String getAboutText(){
        String aboutFrameName = "Resume_key_skills_ifr";
        switchToFrame(aboutFrameName);
       String result = getTextByLocator(aboutTextField);
       switchToDefaultContent();
       return result.trim();
    }

    @Step("Get skills")
    public Set<String> getSkills(){
        List<WebElement> elements = getAllElements(addedSkill);
        Set<String> result = new HashSet<>();
        for (WebElement element: elements) {
            result.add(getTextFromWebElement(element));
        }
        return result;
    }

    @Step("Git skills amount")
    public int getSkillsAmount(){
        return getAllElements(addedSkill).size();
    }

    @Override
    public void fillAllFields(AboutAndSkillsData data) {

    }

    @Override
    public AboutAndSkillsData getDataFromPopup() {
        return null;
    }
}
