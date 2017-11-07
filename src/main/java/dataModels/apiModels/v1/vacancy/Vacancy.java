package dataModels.apiModels.v1.vacancy;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import dataModels.apiModels.v1.company.Company;

/**
 * Created by Asta on 17.07.2017.
 */
public class Vacancy {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("company")
    @Expose
    public Company company;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("salary")
    @Expose
    public Integer salary;
    @SerializedName("salary_from")
    @Expose
    public Integer salaryFrom;
    @SerializedName("period")
    @Expose
    public String period;
    @SerializedName("availability_id")
    @Expose
    public String availabilityId;
    @SerializedName("experience")
    @Expose
    public Integer experience;
    @SerializedName("updated")
    @Expose
    public String updated;
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("city_id")
    @Expose
    public Integer cityId;
    @SerializedName("office_city")
    @Expose
    public String officeCity;
    @SerializedName("office_street")
    @Expose
    public String officeStreet;
    @SerializedName("office_building")
    @Expose
    public String officeBuilding;
    @SerializedName("office_unit")
    @Expose
    public String officeUnit;
    @SerializedName("is_hot")
    @Expose
    public Boolean isHot;
    @SerializedName("is_anonymous")
    @Expose
    public Boolean isAnonymous;
    @SerializedName("date_picked")
    @Expose
    public String datePicked;
    @SerializedName("status")
    @Expose
    public Integer status;

    public boolean validate(){
        if (this.id == null || this.id.equals(0)){
            System.out.println("id is empty!!");
            return false;
        }
        if (this.company == null || !this.company.validate()){
            System.out.println("company is not filled properly!");
            return false;
        }
        if (this.title == null || this.title.equals("")){
            System.out.println("title is empty!");
            return false;
        }
        if (this.description == null || this.description.equals("")){
            System.out.println("description is empty!");
            return false;
        }
        if (this.salary == null){
            System.out.println("salary is empty!");
            return false;
        }
        if (this.salaryFrom == null){
            System.out.println("salaryFrom is empty!");
            return false;
        }
        if (this.period == null || this.period.equals("")){
            System.out.println("period is empty!");
            return false;
        }
        if (this.availabilityId == null || this.availabilityId.equals("")){
            System.out.println("availabilityId is empty!");
            return false;
        }
        if (this.experience == null || this.experience.equals(0)){
            System.out.println("experience is empty!");
            return false;
        }
        if (this.updated == null || this.updated.equals("") || this.updated.equals("000-00-00")){
            System.out.println("updated is empty!");
            return false;
        }
        /*if (this.url == null || this.url.equals("")){
            System.out.println("url is empty!");
            return false;
        }*/
        if (this.officeCity == null || this.officeCity.equals("")){
            System.out.println("officeCity is empty!");
            return false;
        }
        if (this.officeStreet == null || this.officeStreet.equals("")){
            System.out.println("officeStreet is empty!");
            return false;
        }
        if (this.officeBuilding == null || this.officeBuilding.equals("")){
            System.out.println("officeBuilding is empty!");
            return false;
        }
        if (this.officeUnit == null || this.officeUnit.equals("")){
            System.out.println("officeUnit is empty!");
            return false;
        }
        if (this.isHot == null){
            System.out.println("isHot is empty!");
            return false;
        }
        if (this.isAnonymous == null){
            System.out.println("isAnonymous is empty!");
            return false;
        }
        if (this.status == null || this.status.equals(0)){
            System.out.println("status is empty!");
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Vacancy{" +
                "id=" + id +
                ", company=" + company +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", salary=" + salary +
                ", salaryFrom=" + salaryFrom +
                ", period='" + period + '\'' +
                ", availabilityId='" + availabilityId + '\'' +
                ", experience=" + experience +
                ", updated='" + updated + '\'' +
                ", url=" + url +
                ", cityId=" + cityId +
                ", officeCity=" + officeCity +
                ", officeStreet=" + officeStreet +
                ", officeBuilding=" + officeBuilding +
                ", officeUnit=" + officeUnit +
                ", isHot=" + isHot +
                ", isAnonymous=" + isAnonymous +
                ", status=" + status +
                '}';
    }
}
