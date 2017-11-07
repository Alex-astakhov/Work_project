package dataModels.apiModels.v1.resume;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import dataModels.applicant.cvParts.EducationData;

public class Education {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("resume_id")
    @Expose
    public Integer resumeId;
    @SerializedName("education_id")
    @Expose
    public Integer educationId;
    @SerializedName("organization_name")
    @Expose
    public String organizationName;
    @SerializedName("year_end")
    @Expose
    public String yearEnd;
    @SerializedName("department")
    @Expose
    public String department;
    @SerializedName("city_id")
    @Expose
    public Integer cityId;
    @SerializedName("diploma")
    @Expose
    public String diploma;
    @SerializedName("still_learning")
    @Expose
    public Integer stillLearning;
    @SerializedName("type_education")
    @Expose
    public Integer typeEducation;

    @Override
    public String toString() {
        return "Education{" +
                "id=" + id +
                ", resumeId=" + resumeId +
                ", educationId=" + educationId +
                ", organizationName='" + organizationName + '\'' +
                ", yearEnd='" + yearEnd + '\'' +
                ", department='" + department + '\'' +
                ", cityId=" + cityId +
                ", diploma='" + diploma + '\'' +
                ", stillLearning=" + stillLearning +
                ", typeEducation=" + typeEducation +
                '}';
    }

    public boolean validate(){
        if (this.id == null || this.id.equals(0)){
            System.out.println("id is empty!");
            return false;
        }
        if (this.resumeId == null || this.resumeId.equals(0)){
            System.out.println("resumeId is empty!");
            return false;
        }
        if (this.educationId == null){
            System.out.println("educationId is empty");
            return false;
        }
        if (this.organizationName == null || this.organizationName.equals("")){
            System.out.println("organizationName is empty!");
            return false;
        }
        if ((this.yearEnd == null || this.yearEnd.equals("")) && this.stillLearning.equals(0)){
            System.out.println("yearEnd and stillLearning is empty!");
            return false;
        }
        if (this.yearEnd != null && this.yearEnd.length() > 0 && this.stillLearning.equals(1)){
            System.out.println("yearEnd and stillLearning is both filled!");
            return false;
        }
        if ((this.department == null || this.department.equals("")) && !this.typeEducation.equals(2)){
            System.out.println("department is empty but educationType is not 2!");
            return false;
        }
        if (this.cityId == null || this.cityId.equals(0)){
            System.out.println("cityId is empty!");
            return false;
        }
        if (((this.diploma == null || this.diploma.equals("")) && !(this.typeEducation.equals(2) || this.typeEducation.equals(4)))){
            System.out.println("diploma is empty but typeEducation is not 2 or 4!");
            return false;
        }
        if (this.stillLearning == null){
            System.out.println("stillLearning is empty!");
            return false;
        }
        if (this.typeEducation == null || this.typeEducation.equals(0)){
            System.out.println("typeEducation is empty!");
            return false;
        }
        return true;
    }

    public EducationData getEducationData(){
        String faculty, degree;
        if (this.department != null)
            faculty = this.department;
        else
            faculty = "";
        if (this.diploma != null)
            degree = this.diploma;
        else
            degree = "";
        boolean studyNow = this.stillLearning.equals(1);
        return new EducationData(String.valueOf(this.typeEducation), this.organizationName, faculty,
                String.valueOf(this.cityId), degree, yearEnd, studyNow);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            System.out.println("Object is null");
            return false;
        }
        if (!Education.class.isAssignableFrom(obj.getClass())) {
            System.out.println("Object is other class");
            return false;
        }
        final Education other = (Education) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            System.out.println("Expected id: " + this.id + ", Actual id: " + other.id);
            return false;
        }
        if ((this.resumeId == null) ? (other.resumeId != null) : !this.resumeId.equals(other.resumeId)) {
            System.out.println("Expected resumeId: " + this.resumeId + ", Actual resumeId: " + other.resumeId);
            return false;
        }
        if ((this.educationId == null) ? (other.educationId != null) : !this.educationId.equals(other.educationId)) {
            System.out.println("Expected educationId: " + this.educationId + ", Actual educationId: " + other.educationId);
            return false;
        }
        if ((this.organizationName == null) ? (other.organizationName != null) : !this.organizationName.equals(other.organizationName)) {
            System.out.println("Expected organizationName: " + this.organizationName + ", Actual organizationName: " + other.organizationName);
            return false;
        }
        if ((this.yearEnd == null) ? (other.yearEnd != null) : !this.yearEnd.equals(other.yearEnd)) {
            System.out.println("Expected yearEnd: " + this.yearEnd + ", Actual yearEnd: " + other.yearEnd);
            return false;
        }
        if ((this.department == null) ? (other.department != null) : !this.department.equals(other.department)) {
            System.out.println("Expected department: " + this.department + ", Actual department: " + other.department);
            return false;
        }
        if ((this.cityId == null) ? (other.cityId != null) : !this.cityId.equals(other.cityId)) {
            System.out.println("Expected cityId: " + this.cityId + ", Actual cityId: " + other.cityId);
            return false;
        }
        if ((this.diploma == null) ? (other.diploma != null) : !this.diploma.equals(other.diploma)) {
            System.out.println("Expected diploma: " + this.diploma + ", Actual diploma: " + other.diploma);
            return false;
        }
        if ((this.stillLearning == null) ? (other.stillLearning != null) : !this.stillLearning.equals(other.stillLearning)) {
            System.out.println("Expected stillLearning: " + this.stillLearning + ", Actual stillLearning: " + other.stillLearning);
            return false;
        }
        if ((this.typeEducation == null) ? (other.typeEducation != null) : !this.typeEducation.equals(other.typeEducation)) {
            System.out.println("Expected typeEducation: " + this.typeEducation + ", Actual typeEducation: " + other.typeEducation);
            return false;
        }
        return true;
    }
}
