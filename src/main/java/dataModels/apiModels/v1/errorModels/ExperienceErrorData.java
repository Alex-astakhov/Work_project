package dataModels.apiModels.v1.errorModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import dataModels.DataGenerator;

import java.util.List;

public class ExperienceErrorData extends ErrorData {
    @SerializedName("period_of")
    @Expose
    public List<String> periodOf = null;
    @SerializedName("organization_name")
    @Expose
    public List<String> organizationName = null;
    @SerializedName("organization_url")
    @Expose
    public List<String> organizationUrl = null;
    @SerializedName("position_name")
    @Expose
    public List<String> positionName = null;
    @SerializedName("role")
    @Expose
    public List<String> role = null;
    @SerializedName("sofar")
    @Expose
    public List<String> sofar = null;
    @SerializedName("city_id")
    @Expose
    public List<String> cityId = null;
    @SerializedName("period_to")
    @Expose
    public List<String> periodTo = null;

    public ExperienceErrorData(List<String> periodOf, List<String> organizationName, List<String> organizationUrl, List<String> positionName,
                               List<String> role, List<String> sofar, List<String> cityId, List<String> periodTo) {
        this.periodOf = periodOf;
        this.organizationName = organizationName;
        this.organizationUrl = organizationUrl;
        this.positionName = positionName;
        this.role = role;
        this.sofar = sofar;
        this.cityId = cityId;
        this.periodTo = periodTo;

    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            System.out.println("Object is null");
            return false;
        }
        if (!ExperienceErrorData.class.isAssignableFrom(obj.getClass())) {
            System.out.println("Object is other class");
            return false;
        }
        final ExperienceErrorData other = (ExperienceErrorData) obj;
        if ((this.periodOf == null) ? (other.periodOf != null) :
                !DataGenerator.collectionStrictEquals(this.periodOf, other.periodOf)) {
            System.out.println("Expected periodOf: " + this.periodOf + ", Actual periodOf: " + other.periodOf);
            return false;
        }
        if ((this.organizationName == null) ? (other.organizationName != null) :
                !DataGenerator.collectionStrictEquals(this.organizationName, other.organizationName)) {
            System.out.println("Expected organizationName: " + this.organizationName + ", Actual organizationName: " + other.organizationName);
            return false;
        }
        if ((this.organizationUrl == null) ? (other.organizationUrl != null) :
                !DataGenerator.collectionStrictEquals(this.organizationUrl, other.organizationUrl)) {
            System.out.println("Expected organizationUrl: " + this.organizationUrl + ", Actual organizationUrl: " + other.organizationUrl);
            return false;
        }
        if ((this.positionName == null) ? (other.positionName != null) :
                !DataGenerator.collectionStrictEquals(this.positionName, other.positionName)) {
            System.out.println("Expected positionName: " + this.positionName + ", Actual positionName: " + other.positionName);
            return false;
        }
        if ((this.role == null) ? (other.role != null) :
                !DataGenerator.collectionStrictEquals(this.role, other.role)) {
            System.out.println("Expected role: " + this.role + ", Actual role: " + other.role);
            return false;
        }
        if ((this.sofar == null) ? (other.sofar != null) :
                !DataGenerator.collectionStrictEquals(this.sofar, other.sofar)) {
            System.out.println("Expected sofar: " + this.sofar + ", Actual sofar: " + other.sofar);
            return false;
        }
        if ((this.cityId == null) ? (other.cityId != null) :
                !DataGenerator.collectionStrictEquals(this.cityId, other.cityId)) {
            System.out.println("Expected cityId: " + this.cityId + ", Actual cityId: " + other.cityId);
            return false;
        }
        if ((this.periodTo == null) ? (other.periodTo != null) :
                !DataGenerator.collectionStrictEquals(this.periodTo, other.periodTo)) {
            System.out.println("Expected periodTo: " + this.periodTo + ", Actual periodTo: " + other.periodTo);
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ExperienceErrorData{" +
                "periodOf=" + periodOf +
                ", organizationName=" + organizationName +
                ", organizationUrl=" + organizationUrl +
                ", positionName=" + positionName +
                ", role=" + role +
                ", sofar=" + sofar +
                ", cityId=" + cityId +
                ", periodTo=" + periodTo +
                '}';
    }
}
