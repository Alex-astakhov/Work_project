package pageObjects.applicant.cv.cvPartitions;

import core.Constants;
import core.MethodsFactory;
import dataModels.applicant.cvParts.TrainingData;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import pageObjects.Popup;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * Created by Alex Astakhov on 27.11.2016.
 */
public class TrainingPart extends Popup<TrainingData> {
    private By trainingNameField = By.id("ResumeCourse_title");
    private By trainingOrganizerField = By.id("ResumeCourse_organizer");
    private By organizerSiteField = By.id("ResumeCourse_url");
    private By trainingGraduationYearSelect = By.id("ResumeCourse_graduation_year");

    private By mobSaveButton = By.cssSelector("[data-test=button-save]");
    private By mobSaveAndAddButton = By.cssSelector("[data-test=button-save-and-add]");
    private By mobCancelButton = By.cssSelector("[data-test=button-cancel]");
    private By mobDeleteLink = By.cssSelector("[data-test=button-delete]");

    @Override
    public void fillAllFields(TrainingData data){
        typeTrainingName(data.getTrainingName());
        typeTrainingOrganizer(data.getTrainingOrganizer());
        typeOrganizerSite(data.getOrganizerSite());
        selectGraduationYear(data.getTrainingGraduationYearValue());
    }

    @Step("Add all trainings on mobile")
    public void mobAddAllTrainingData(TrainingData[] trainingDatas){
        int count = trainingDatas.length;
        for (int i = 0; i < count; i++) {
            fillAllFields(trainingDatas[i]);
            if (i == count - 1)
                mobClickSave();
            else
                mobClickSaveAndAdd();
        }
    }

    @Override
    public TrainingData getDataFromPopup(){
        String name = getValueAttrOfElement(trainingNameField);
        String organizer = getValueAttrOfElement(trainingOrganizerField);
        String site = getValueAttrOfElement(organizerSiteField);
        String yearValue = getValueAttrOfElement(getSelectedItem(trainingGraduationYearSelect));
        return new TrainingData(name, organizer, site, yearValue);
    }

    @Step("Type training name: {0}")
    public void typeTrainingName(String name){
        type(trainingNameField, name);
    }

    @Step("Type training orgazire: {0}")
    public void typeTrainingOrganizer(String organizer){
        type(trainingOrganizerField, organizer);
    }

    @Step("Type organizer site URL: {0}")
    public void typeOrganizerSite(String url){
        type(organizerSiteField, url);
    }

    @Step("Select graduation year: {0}")
    public void selectGraduationYear(String yearValue){
        selectionFromDropListByValue(trainingGraduationYearSelect, yearValue);
    }
    @Step("Click mobile SAVE")
    public void mobClickSave(){
        click(mobSaveButton);
    }

    @Step("Click mobile SAVE AND ADD")
    public void mobClickSaveAndAdd(){
        click(mobSaveAndAddButton);
    }

    @Step("Click mobile CANCEL")
    public void mobClickCancel(){
        click(mobCancelButton);
    }

    @Step("Click mobile DELETE")
    public void mobClickDelete(){
        click(mobDeleteLink);
    }
}
