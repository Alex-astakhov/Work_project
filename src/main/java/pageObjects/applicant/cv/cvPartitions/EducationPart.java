package pageObjects.applicant.cv.cvPartitions;

import dataModels.applicant.cvParts.EducationData;
import org.openqa.selenium.By;
import pageObjects.Popup;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * Created by Alex Astakhov on 26.11.2016.
 */
public class EducationPart extends Popup<EducationData> {
    private By educationTypeSelect = By.id("ResumeEducation_type_education");
    private By institutionNameField = By.id("ResumeEducation_organization_name");
    private By facultyNameField = By.id("ResumeEducation_department");
    private By educationCitySelect = By.id("ResumeEducation_city_id");
    private By degreeField = By.id("ResumeEducation_diploma");
    private By graduationYearSelect = By.id("ResumeEducation_year_end");
    private By studyNowChecker = By.cssSelector("[name*=still_learning] + label");
    private By studyNowCheckerValue = By.id("ResumeEducation_still_learning");
    private By mobCancel = By.cssSelector("[data-test=button-cancel]");
    private By mobSaveButton = By.cssSelector("[data-test=button-save]");
    private By mobSaveAndAddButton = By.cssSelector("[data-test=button-save-and-add]");
    private By mobDeleteLink = By.cssSelector("[data-test=button-delete]");

    @Override
    public void fillAllFields(EducationData data){
         selectEducationType(data.getEducationTypeValue());
         typeInstitutionName(data.getInstitutionName());
         if (!data.getFacultyName().isEmpty())
            typeFacultyName(data.getFacultyName());
         selectEducationCity(data.getCityChoiceValue());
         if (!data.getDegree().isEmpty())
            typeDegreeField(data.getDegree());
         if (data.isStudyNow())
             checkStudyNow();
         else
             selectGraduationYear(data.getGraduationYearChoiceValue());
    }

    @Step("Add all educations on mobile")
    public void mobAddAllEducations(EducationData[] educations){
        int count = educations.length;
        for (int i = 0; i < count; i++) {
            fillAllFields(educations[i]);
            if (i == count-1)
                mobClickSave();
            else
                mobClickSaveAndAdd();
        }
    }

    @Override
    public EducationData getDataFromPopup(){
        String educationTypeValue = getEducationTypeValue();
        String institutionName = getInstitutionName();
        String facultyName = getFacultyName();
        String educationCityValue = getEducationCityValue();
        String degree = getDegreeField();
        boolean studyNow = isCheckedStudyNow();
        String graduationYearValue = null;
        if (!studyNow)
            graduationYearValue = getGraduationYearValue();
        return new EducationData(educationTypeValue, institutionName, facultyName, educationCityValue, degree, graduationYearValue, studyNow);
    }


    @Step("Select education type {0}")
    public void selectEducationType(String typeValue){
        selectionFromDropListByValue(educationTypeSelect, typeValue);
    }

    @Step("Get education type")
    public String getEducationTypeValue(){
        return getValueAttrOfElement(getSelectedItem(educationTypeSelect));
    }

    @Step("Type institution: {0}")
    public void typeInstitutionName(String name){
        type(institutionNameField, name);
    }

    @Step("Get institution name")
    public String getInstitutionName(){
        return getValueAttrOfElement(institutionNameField);
    }

    @Step("Type faculty: {0}")
    public void typeFacultyName(String name){
        type(facultyNameField, name);
    }

    @Step("Get faculty title")
    public String getFacultyName(){
        return getValueAttrOfElement(facultyNameField);
    }

    @Step("Select education city by code: {0}")
    public void selectEducationCity(String cityValue){
        selectionFromDropListByValue(educationCitySelect, cityValue);
    }


    @Step("Get education city code")
    public String getEducationCityValue(){
        return getValueAttrOfElement(getSelectedItem(educationCitySelect));
    }

    @Step("Type degree: {0}")
    public void typeDegreeField(String degree){
        type(degreeField, degree);
    }

    @Step("Get degree")
    public String getDegreeField(){
        return getValueAttrOfElement(degreeField);
    }

    @Step("Select graduation year {0}")
    public void selectGraduationYear(String yearValue){
        selectionFromDropListByValue(graduationYearSelect, yearValue);
    }

    @Step("Get graduation year")
    public String getGraduationYearValue(){
        return getValueAttrOfElement(getSelectedItem(graduationYearSelect));
    }

    @Step("Check STUDY NOW")
    public void checkStudyNow(){
        click(studyNowChecker);
    }

    @Step("Verify if STUDY NOW is checked")
    public boolean isCheckedStudyNow(){
        return isChecked(studyNowCheckerValue);
    }

    @Step("Click mobile CANCEL")
    public void mobClickCancel(){
        click(mobCancel);
    }

    @Step("Click mobile SAVE")
    public void mobClickSave(){
        click(mobSaveButton);
    }

    @Step("Click mobile SAVE AND ADD")
    public void mobClickSaveAndAdd(){
        click(mobSaveAndAddButton);
    }

    @Step("Click mobile DELETE")
    public void mobClickDelete(){
        click(mobDeleteLink);
    }
}
