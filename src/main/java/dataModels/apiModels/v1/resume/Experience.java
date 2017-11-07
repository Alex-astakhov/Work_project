package dataModels.apiModels.v1.resume;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import dataModels.DataGenerator;
import dataModels.applicant.cvParts.ExperienceData;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Experience {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("resume_id")
    @Expose
    public Integer resumeId;
    @SerializedName("period_of")
    @Expose
    public String periodOf;
    @SerializedName("period_to")
    @Expose
    public String periodTo;
    @SerializedName("organization_name")
    @Expose
    public String organizationName;
    @SerializedName("organization_url")
    @Expose
    public String organizationUrl;
    @SerializedName("position_name")
    @Expose
    public String positionName;
    @SerializedName("role")
    @Expose
    public String role;
    @SerializedName("sofar")
    @Expose
    public Integer sofar;
    @SerializedName("city_id")
    @Expose
    public Integer cityId;

    public boolean validate(){
        if (this.id == null || this.id.equals(0)){
            System.out.println("id s empty!");
            return false;
        }
        if (this.resumeId == null || this.resumeId.equals(0)){
            System.out.println("resumeId is empty!");
            return false;
        }
        if (this.periodOf == null || this.periodOf.equals("")){
            System.out.println("periodOf is empty!");
            return false;
        }
        if ((this.periodTo == null || this.periodTo.equals("")) && this.sofar == 0){
            System.out.println("periodTo is empty but sofar is 0!");
            return false;
        }
        if (this.periodTo != null && this.periodTo.length() > 0 && this.sofar == 1){
            System.out.println("periodTo and sofar is both filled!");
            return false;
        }
        if (this.organizationName == null || this.organizationName.equals("")){
            System.out.println("organizationName is empty!");
            return false;
        }
        if (this.organizationUrl == null || this.organizationUrl.equals("")){
            System.out.println("organizationUrl is empty!");
            return false;
        }
        if (this.role == null || this.role.equals("")){
            System.out.println("role is empty!");
            return false;
        }
        if (this.positionName == null || this.positionName.equals("")){
            System.out.println("positionName is empty!");
            return false;
        }
        if (this.sofar == null){
            System.out.println("sofar is empty!!");
            return false;
        }
        if (this.cityId == null || this.cityId.equals(0)){
            System.out.println("cityId is empty!!");
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            System.out.println("Object is null");
            return false;
        }
        if (!Experience.class.isAssignableFrom(obj.getClass())) {
            System.out.println("Object is other class");
            return false;
        }
        final Experience other = (Experience) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            System.out.println("Expected id: " + this.id + ", Actual id: " + other.id);
            return false;
        }
        if ((this.resumeId == null) ? (other.resumeId != null) : !this.resumeId.equals(other.resumeId)) {
            System.out.println("Expected resumeId: " + this.resumeId + ", Actual resumeId: " + other.resumeId);
            return false;
        }
        if ((this.periodOf == null) ? (other.periodOf != null) : !this.periodOf.equals(other.periodOf)) {
            System.out.println("Expected periodOf: " + this.periodOf + ", Actual periodOf: " + other.periodOf);
            return false;
        }
        if ((this.periodTo == null) ? (other.periodTo != null) : !this.periodTo.equals(other.periodTo)) {
            System.out.println("Expected periodTo: " + this.periodTo + ", Actual periodTo: " + other.periodTo);
            return false;
        }
        if ((this.organizationName == null) ? (other.organizationName != null) : !this.organizationName.equals(other.organizationName)) {
            System.out.println("Expected organizationName: " + this.organizationName + ", Actual organizationName: " + other.organizationName);
            return false;
        }
        if ((this.organizationUrl == null) ? (other.organizationUrl != null) : !this.organizationUrl.equals(other.organizationName)) {
            System.out.println("Expected organizationUrl: " + this.organizationUrl + ", Actual organizationUrl: " + other.organizationUrl);
            return false;
        }
        if ((this.positionName == null) ? (other.positionName != null) : !this.positionName.equals(other.positionName)) {
            System.out.println("Expected positionName: " + this.positionName + ", Actual positionName: " + other.positionName);
            return false;
        }
        if ((this.role == null) ? (other.role != null) : !this.role.equals(other.role)) {
            System.out.println("Expected role: " + this.role + ", Actual role: " + other.role);
            return false;
        }
        if ((this.sofar == null) ? (other.sofar != null) : !this.sofar.equals(other.sofar)) {
            System.out.println("Expected sofar: " + this.sofar + ", Actual sofar: " + other.sofar);
            return false;
        }
        if ((this.cityId == null) ? (other.cityId != null) : !this.cityId.equals(other.cityId)) {
            System.out.println("Expected cityId: " + this.cityId + ", Actual cityId: " + other.cityId);
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Experience{" +
                "id=" + id +
                ", resumeId=" + resumeId +
                ", periodOf='" + periodOf + '\'' +
                ", periodTo='" + periodTo + '\'' +
                ", organizationName='" + organizationName + '\'' +
                ", organizationUrl=" + organizationUrl +
                ", positionName='" + positionName + '\'' +
                ", role='" + role + '\'' +
                ", sofar=" + sofar +
                ", cityId=" + cityId +
                '}';
    }

    public ExperienceData getExperienceData(){
        String url = "";
        if (this.organizationUrl != null)
            url = this.organizationUrl;
        Date date = DataGenerator.parseDateFromString(this.periodOf, "yyyy-MM-dd");
        int monthOf = Integer.parseInt(new SimpleDateFormat("MM").format(date));
        String yearOF = new SimpleDateFormat("yyyy").format(date);
        int monthTo = 0;
        String yearTo = null;
        if (this.periodTo != null) {
            date = DataGenerator.parseDateFromString(this.periodTo, "yyyy-MM-dd");
            monthTo = Integer.parseInt(new SimpleDateFormat("MM").format(date));
            yearTo = new SimpleDateFormat("yyyy").format(date);
        }
        boolean forPresent = this.sofar.equals(1);
        return new ExperienceData(false, this.organizationName, this.cityId.toString(), url,
                this.positionName, monthOf, yearOF, monthTo, yearTo, forPresent, this.role);
    }
}
