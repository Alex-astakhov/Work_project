package dataModels.apiModels.v1.resume;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import dataModels.DataGenerator;
import dataModels.apiModels.v1.ApiData;
import dataModels.apiModels.v1.errorModels.ErrorData;
import dataModels.applicant.cvParts.PositionData;

import java.util.HashSet;
import java.util.Set;

public class ResumeData<T extends ErrorData> extends DataGenerator implements ApiData {
    @SerializedName("resume")
    @Expose
    public Resume resume;
    @SerializedName("errors")
    @Expose
    public T errors;

    @Override
    public boolean validate(){
        if (this.resume == null){
            System.out.println("resume is empty!");
            return false;
        }
        if (this.resume.validate()){
            System.out.println("resume is not filled properly!\n" + this.resume);
            return false;
        }
        return true;
    }

    @Override
    public boolean validate(boolean additionalCondition) {
        System.out.println("\nWrong type of validation method selected!\n");
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            System.out.println("Object is null");
            return false;
        }
        if (!ResumeData.class.isAssignableFrom(obj.getClass())) {
            System.out.println("Object is other class");
            return false;
        }
        final ResumeData other = (ResumeData) obj;
        if ((this.resume == null) ? (other.resume != null) : !this.resume.equals(other.resume)) {
            System.out.println("Expected resume: " + this.resume + ", Actual resume: " + other.resume);
            return false;
        }
        if ((this.errors == null) ? (other.errors != null) : !this.errors.equals(other.errors)) {
            System.out.println("Expected errors: " + this.errors + ", Actual errors: " + other.errors);
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ResumeData{" +
                "resume=" + resume +
                ", errors=" + errors +
                '}';
    }

    public PositionData getPositionData(){
        boolean move = this.resume.move.equals(1);
        boolean businessTrip = this.resume.readyForBusinessTrip.equals(1);
        Set<String> cities = new HashSet<>(this.resume.cities);
        cities.remove(this.resume.user.cityId.toString());
        return new PositionData(this.resume.positionName, this.resume.availabilityId.toString(),
                this.resume.salary.toString(), move, cities, businessTrip);
    }
}
