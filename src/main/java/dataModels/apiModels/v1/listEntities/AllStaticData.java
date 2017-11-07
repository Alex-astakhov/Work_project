package dataModels.apiModels.v1.listEntities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import dataModels.apiModels.v1.ApiData;

import java.util.List;

public class AllStaticData implements ApiData {
    @SerializedName("languages")
    @Expose
    public List<DefaultEntity> languages = null;
    @SerializedName("languagesPossessions")
    @Expose
    public List<DefaultEntity> languagesPossessions = null;
    @SerializedName("typeEducation")
    @Expose
    public List<DefaultEntity> typeEducation = null;
    @SerializedName("typeExperience")
    @Expose
    public List<DefaultEntity> typeExperience = null;
    @SerializedName("typeAvailabilities")
    @Expose
    public List<DefaultEntity> typeAvailabilities = null;
    @SerializedName("driverLicences")
    @Expose
    public List<DefaultEntity> driverLicences = null;
    @SerializedName("resumeStatuses")
    @Expose
    public List<DefaultEntity> resumeStatuses = null;
    @SerializedName("vacancyStatuses")
    @Expose
    public List<DefaultEntity> vacancyStatuses = null;
    @SerializedName("companyStatuses")
    @Expose
    public List<DefaultEntity> companyStatuses = null;
    @SerializedName("scamVacancyTypes")
    @Expose
    public List<DefaultEntity> scamVacancyTypes = null;
    @SerializedName("pushTypes")
    @Expose
    public List<DefaultEntity> pushTypes = null;
    @SerializedName("inviteRefuseStatuses")
    @Expose
    public List<DefaultEntity> inviteRefuseStatuses = null;
    @SerializedName("responseStatuses")
    @Expose
    public List<DefaultEntity> responseStatuses = null;
    @SerializedName("socialNetwork")
    @Expose
    public List<DefaultEntity> socialNetwork = null;

    @Override
    public boolean validate(){
        if (this.languages == null || this.languages.size() == 0){
            System.out.println("languages is empty!");
            return false;
        }
        for (DefaultEntity e: this.languages) {
            if (!e.validate()){
                System.out.println("languages is not valid!\n" + e);
                return false;
            }
        }
        if (this.languagesPossessions == null || this.languagesPossessions.size() == 0){
            System.out.println("languagesPossessions is empty!");
            return false;
        }
        for (DefaultEntity e: this.languagesPossessions) {
            if (!e.validate()){
                System.out.println("languagesPossessions is not valid!\n" + e);
                return false;
            }
        }
        if (this.typeEducation == null || this.typeEducation.size() == 0){
            System.out.println("languagesPossessions is empty!");
            return false;
        }
        for (DefaultEntity e: this.typeEducation) {
            if (!e.validate()){
                System.out.println("typeEducation is not valid!\n" + e);
                return false;
            }
        }
        if (this.typeExperience == null || this.typeExperience.size() == 0){
            System.out.println("typeExperience is empty!");
            return false;
        }
        for (DefaultEntity e: this.typeExperience) {
            if (!e.validate()){
                System.out.println("typeExperience is not valid!\n" + e);
                return false;
            }
        }
        if (this.typeAvailabilities == null || this.typeAvailabilities.size() == 0){
            System.out.println("typeExperience is empty!");
            return false;
        }
        for (DefaultEntity e: this.typeAvailabilities) {
            if (!e.validate()){
                System.out.println("typeAvailabilities is not valid!\n" + e);
                return false;
            }
        }
        if (this.driverLicences == null || this.driverLicences.size() == 0){
            System.out.println("driverLicences is empty!");
            return false;
        }
        for (DefaultEntity e: this.driverLicences) {
            if (!e.validate()){
                System.out.println("driverLicences is not valid!\n" + e);
                return false;
            }
        }
        if (this.resumeStatuses == null || this.resumeStatuses.size() == 0){
            System.out.println("resumeStatuses is empty!");
            return false;
        }
        for (DefaultEntity e: this.resumeStatuses) {
            if (!e.validate()){
                System.out.println("resumeStatuses is not valid!\n" + e);
                return false;
            }
        }
        if (this.vacancyStatuses == null || this.vacancyStatuses.size() == 0){
            System.out.println("vacancyStatuses is empty!");
            return false;
        }
        for (DefaultEntity e: this.vacancyStatuses) {
            if (!e.validate()){
                System.out.println("vacancyStatuses is not valid!\n" + e);
                return false;
            }
        }
        if (this.companyStatuses == null || this.companyStatuses.size() == 0){
            System.out.println("companyStatuses is empty!");
            return false;
        }
        for (DefaultEntity e: this.companyStatuses) {
            if (!e.validate()){
                System.out.println("companyStatuses is not valid!\n" + e);
                return false;
            }
        }
        if (this.scamVacancyTypes == null || this.scamVacancyTypes.size() == 0){
            System.out.println("scamVacancyTypes is empty!");
            return false;
        }
        for (DefaultEntity e: this.scamVacancyTypes) {
            if (!e.validate()){
                System.out.println("scamVacancyTypes is not valid!\n" + e);
                return false;
            }
        }
        if (this.pushTypes == null || this.pushTypes.size() == 0){
            System.out.println("pushTypes is empty!");
            return false;
        }
        for (DefaultEntity e: this.pushTypes) {
            if (!e.validate()){
                System.out.println("pushTypes is not valid!\n" + e);
                return false;
            }
        }
        if (this.inviteRefuseStatuses == null || this.inviteRefuseStatuses.size() == 0){
            System.out.println("inviteRefuseStatuses is empty!");
            return false;
        }
        for (DefaultEntity e: this.inviteRefuseStatuses) {
            if (!e.validate()){
                System.out.println("inviteRefuseStatuses is not valid!\n" + e);
                return false;
            }
        }
        if (this.responseStatuses == null || this.responseStatuses.size() == 0){
            System.out.println("responseStatuses is empty!");
            return false;
        }
        for (DefaultEntity e: this.responseStatuses) {
            if (!e.validate()){
                System.out.println("responseStatuses is not valid!\n" + e);
                return false;
            }
        }
        if (this.socialNetwork == null || this.socialNetwork.size() == 0){
            System.out.println("socialNetwork is empty!");
            return false;
        }
        for (DefaultEntity e: this.socialNetwork) {
            if (!e.validate()){
                System.out.println("socialNetwork is not valid!\n" + e);
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean validate(boolean additionalCondition) {
        System.out.println("\nWrong type of validation method selected!\n");
        return false;
    }

    @Override
    public String toString() {
        return "AllStaticData{" +
                "languages=" + languages +
                ", languagesPossessions=" + languagesPossessions +
                ", typeEducation=" + typeEducation +
                ", typeExperience=" + typeExperience +
                ", typeAvailabilities=" + typeAvailabilities +
                ", driverLicences=" + driverLicences +
                ", resumeStatuses=" + resumeStatuses +
                ", vacancyStatuses=" + vacancyStatuses +
                ", companyStatuses=" + companyStatuses +
                ", scamVacancyTypes=" + scamVacancyTypes +
                ", pushTypes=" + pushTypes +
                '}';
    }
}
