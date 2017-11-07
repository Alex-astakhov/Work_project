package dataModels.apiModels.v1.resume;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import dataModels.apiModels.v1.ApiData;
import dataModels.apiModels.v1.user.User;

import java.util.List;

/**
 * Created by Asta on 12.07.2017.
 */
public class Resume implements ApiData{
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("user")
    @Expose
    public User user;
    @SerializedName("position_name")
    @Expose
    public String positionName;
    @SerializedName("move")
    @Expose
    public Integer move;
    @SerializedName("ready_for_business_trip")
    @Expose
    public Integer readyForBusinessTrip;
    @SerializedName("salary")
    @Expose
    public Integer salary;
    @SerializedName("availability_id")
    @Expose
    public Integer availabilityId;
    @SerializedName("created")
    @Expose
    public String created;
    @SerializedName("updated")
    @Expose
    public String updated;
    @SerializedName("key_skills")
    @Expose
    public String keySkills;
    @SerializedName("total_experience")
    @Expose
    public Integer totalExperience;
    @SerializedName("moderated")
    @Expose
    public Integer moderated;
    @SerializedName("purpose")
    @Expose
    public String purpose;
    @SerializedName("visibility")
    @Expose
    public Integer visibility;
    @SerializedName("status")
    @Expose
    public Integer status;
    @SerializedName("language")
    @Expose
    public String language;
    @SerializedName("total_experience_detail")
    @Expose
    public TotalExperienceDetail totalExperienceDetail;
    @SerializedName("experience")
    @Expose
    public List<Experience> experience = null;
    @SerializedName("education_types")
    @Expose
    public List<String> educationTypes = null;
    @SerializedName("education")
    @Expose
    public List<Education> education = null;
    @SerializedName("cities")
    @Expose
    public List<String> cities = null;
    @SerializedName("skills")
    @Expose
    public List<Skill> skills = null;
    @SerializedName("without_experience")
    @Expose
    public Boolean withoutExperience;
    @SerializedName("languages")
    @Expose
    public List<Language> languages = null;
    @SerializedName("experience_position")
    @Expose
    public String experiencePosition;
    @SerializedName("courses")
    @Expose
    public List<Course> courses = null;
    @SerializedName("countViewed")
    @Expose
    public Integer countViewed;
    @SerializedName("allowChangeStatus")
    @Expose
    public Boolean allowChangeStatus;
    @SerializedName("requiredBlocks")
    @Expose
    public RequiredBlocks requiredBlocks;

    @Override
    public String toString() {
        return "Resume{" +
                "id=" + id +
                ", user=" + user +
                ", positionName='" + positionName + '\'' +
                ", move=" + move +
                ", readyForBusinessTrip=" + readyForBusinessTrip +
                ", salary=" + salary +
                ", availabilityId=" + availabilityId +
                ", created='" + created + '\'' +
                ", updated='" + updated + '\'' +
                ", keySkills='" + keySkills + '\'' +
                ", totalExperience=" + totalExperience +
                ", moderated=" + moderated +
                ", purpose='" + purpose + '\'' +
                ", visibility=" + visibility +
                ", status=" + status +
                ", language='" + language + '\'' +
                ", totalExperienceDetail=" + totalExperienceDetail +
                ", experience=" + experience +
                ", educationTypes=" + educationTypes +
                ", education=" + education +
                ", cities=" + cities +
                ", skills=" + skills +
                ", withoutExperience=" + withoutExperience +
                ", languages=" + languages +
                ", experiencePosition='" + experiencePosition + '\'' +
                ", courses=" + courses +
                ", countViewed=" + countViewed +
                ", allowChangeStatus=" + allowChangeStatus +
                ", requiredBlocks=" + requiredBlocks +
                '}';
    }

    public boolean validate(){
        if (this.id == null || this.id.equals(0)){
            System.out.println("id is empty!");
            return false;
        }
        if (this.user == null || !this.user.validate(true)){
            System.out.println("user is not filled properly!");
            return false;
        }
        if (this.move == null){
            System.out.println("move is empty!");
            return false;
        }
        if (this.readyForBusinessTrip == null){
            System.out.println("readyForBusinessTrip is empty!");
            return false;
        }
        if (this.salary == null){
            System.out.println("salary is empty!");
            return false;
        }
        if (this.availabilityId == null || this.availabilityId.equals(0)){
            System.out.println("availabilityId is empty!");
            return false;
        }
        if (this.created == null || this.created.equals("") || this.created.contains("0000")){
            System.out.println("created is empty!");
            return false;
        }
        if (this.updated == null || this.updated.equals("") || this.updated.contains("0000")){
            System.out.println("updated is empty!");
            return false;
        }
        if (this.keySkills == null || this.keySkills.equals("")){
            System.out.println("keySkills is empty!");
            return false;
        }
        if (this.totalExperience == null || this.totalExperience.equals(0)){
            System.out.println("totalExperience is empty!");
            return false;
        }
        if (this.moderated == null){
            System.out.println("moderated is empty!");
            return false;
        }
        if (this.purpose == null || this.purpose.equals("")){
            System.out.println("purpose is empty!");
            return false;
        }
        if (this.visibility == null){
            System.out.println("visibility is empty!");
            return false;
        }
        if (this.status == null){
            System.out.println("status is empty!");
            return false;
        }
        if (this.language == null || this.language.equals("")){
            System.out.println("language is empty!");
            return false;
        }
        if (this.totalExperienceDetail == null || !this.totalExperienceDetail.validate()){
            System.out.println("totalExperienceDetail is not filled properly!");
            return false;
        }
        if (this.withoutExperience == null){
            System.out.println("withoutExperience is empty!");
            return false;
        }
        if (this.experience == null && this.withoutExperience.equals(true)){
            System.out.println("totalExperienceDetail is empty!");
            return false;
        }
        if (this.experience !=null) {
            for (Experience e : this.experience) {
                if (!e.validate()) {
                    System.out.println("experience is not filled properly!\n" + e);
                    return false;
                }
            }
        }
        if (this.educationTypes == null || this.educationTypes.size() == 0){
            System.out.println("totalExperienceDetail is empty!");
            return false;
        }
        for (String e: this.educationTypes) {
            if (e == null || e.equals("")){
                System.out.println("educationTypes is not filled properly!\n" + e);
                return false;
            }
        }
        if (this.education == null || this.education.size() == 0){
            System.out.println("education is empty!");
            return false;
        }
        for (Education e: this.education) {
            if (!e.validate()){
                System.out.println("education is not filled properly!\n" + e);
                return false;
            }
        }
        if (this.cities == null || this.cities.size() == 0){
            System.out.println("cities is empty!");
            return false;
        }
        if (this.move.equals(1) && this.cities.size() < 2){
            System.out.println("cities < 2 but move = 1!");
            return false;
        }
        for (String c: this.cities) {
            if (c == null || c.equals("")){
                System.out.println("cities is not filled properly!\n" + c);
                return false;
            }
        }
        if (this.skills == null || this.skills.size() == 0){
            System.out.println("skills is empty!");
            return false;
        }
        for (Skill s: this.skills) {
            if (!s.validate()){
                System.out.println("skills is not filled properly!\n" + s);
                return false;
            }
        }
        if (this.languages == null || this.languages.size() == 0){
            System.out.println("languages is empty!");
            return false;
        }
        for (Language l: this.languages) {
            if (!l.validate()){
                System.out.println("languages is not filled properly!\n" + l);
                return false;
            }
        }
        if (this.experiencePosition == null || this.experiencePosition.equals("")){
            System.out.println("experiencePosition is empty!");
            return false;
        }
        if (this.courses == null || this.courses.size() == 0){
            System.out.println("courses is empty!");
            return false;
        }
        for (Course c: this.courses) {
            if (!c.validate()){
                System.out.println("courses is not filled properly!\n" + c);
                return false;
            }
        }
        if (this.countViewed == null){
            System.out.println("countViewed is empty!");
            return false;
        }
        if (this.allowChangeStatus == null){
            System.out.println("allowChangeStatus is empty!");
            return false;
        }
        if (this.requiredBlocks == null){
            System.out.println("requiredBlocks is empty!");
            return false;
        }
        if (!this.requiredBlocks.validate()){
            System.out.println("requiredBlocks is not valid!");
            return false;
        }
        return true;
    }

    @Override
    public boolean validate(boolean additionalCondition) {
        System.out.println("\nWrong type of validation method selected!\n");
        return false;
    }

}
