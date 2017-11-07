package dataModels.apiModels.v1.errorModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import dataModels.DataGenerator;

import java.util.List;

public class EducationErrorData extends ErrorData {
    @SerializedName("type_education")
    @Expose
    public List<String> typeEducation = null;
    @SerializedName("city_id")
    @Expose
    public List<String> cityId = null;
    @SerializedName("still_learning")
    @Expose
    public List<String> stillLearning = null;
    @SerializedName("organization_name")
    @Expose
    public List<String> organizationName = null;
    @SerializedName("department")
    @Expose
    public List<String> department = null;
    @SerializedName("diploma")
    @Expose
    public List<String> diploma = null;
    @SerializedName("year_end")
    @Expose
    public List<String> yearEnd = null;

    public EducationErrorData(List<String> typeEducation, List<String> cityId, List<String> stillLearning,
                              List<String> organizationName, List<String> department, List<String> diploma,
                              List<String> yearEnd) {
        this.typeEducation = typeEducation;
        this.cityId = cityId;
        this.stillLearning = stillLearning;
        this.organizationName = organizationName;
        this.department = department;
        this.diploma = diploma;
        this.yearEnd = yearEnd;
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null) {
            System.out.println("Object is null");
            return false;
        }
        if (!EducationErrorData.class.isAssignableFrom(obj.getClass())) {
            System.out.println("Object is other class");
            return false;
        }
        final EducationErrorData other = (EducationErrorData) obj;
        if ((this.typeEducation == null) ? (other.typeEducation != null) :
                !DataGenerator.collectionStrictEquals(this.typeEducation, other.typeEducation)) {
            System.out.println("Expected typeEducation: " + this.typeEducation + ", Actual typeEducation: " + other.typeEducation);
            return false;
        }
        if ((this.cityId == null) ? (other.cityId != null) :
                !DataGenerator.collectionStrictEquals(this.cityId, other.cityId)) {
            System.out.println("Expected cityId: " + this.cityId + ", Actual cityId: " + other.cityId);
            return false;
        }
        if ((this.stillLearning == null) ? (other.stillLearning != null) :
                !DataGenerator.collectionStrictEquals(this.stillLearning, other.stillLearning)) {
            System.out.println("Expected stillLearning: " + this.stillLearning + ", Actual stillLearning: " + other.stillLearning);
            return false;
        }
        if ((this.organizationName == null) ? (other.organizationName != null) :
                !DataGenerator.collectionStrictEquals(this.organizationName, other.organizationName)) {
            System.out.println("Expected organizationName: " + this.organizationName + ", Actual organizationName: " +
                    other.organizationName);
            return false;
        }
        if ((this.department == null) ? (other.department != null) :
                !DataGenerator.collectionStrictEquals(this.department, other.department)) {
            System.out.println("Expected department: " + this.department + ", Actual department: " + other.department);
            return false;
        }
        if ((this.diploma == null) ? (other.diploma != null) :
                !DataGenerator.collectionStrictEquals(this.diploma, other.diploma)) {
            System.out.println("Expected diploma: " + this.diploma + ", Actual diploma: " + other.diploma);
            return false;
        }
        if ((this.yearEnd == null) ? (other.yearEnd != null) :
                !DataGenerator.collectionStrictEquals(this.yearEnd, other.yearEnd)) {
            System.out.println("Expected yearEnd: " + this.yearEnd + ", Actual yearEnd: " + other.yearEnd);
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EducationErrorData{" +
                "typeEducation=" + typeEducation +
                ", cityId=" + cityId +
                ", stillLearning=" + stillLearning +
                ", organizationName=" + organizationName +
                ", department=" + department +
                ", diploma=" + diploma +
                ", yearEnd=" + yearEnd +
                '}';
    }
}
