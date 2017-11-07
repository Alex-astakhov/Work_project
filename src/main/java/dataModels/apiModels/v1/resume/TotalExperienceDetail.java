package dataModels.apiModels.v1.resume;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TotalExperienceDetail {
    @SerializedName("year")
    @Expose
    public Integer year;
    @SerializedName("month")
    @Expose
    public Integer month;
    @SerializedName("month_all")
    @Expose
    public Integer monthAll;

    @Override
    public String toString() {
        return "TotalExperienceDetail{" +
                "year=" + year +
                ", month=" + month +
                ", monthAll=" + monthAll +
                '}';
    }

    public boolean validate(){
        if (this.year == null){
            System.out.println("year is empty!");
            return false;
        }
        if (this.month == null){
            System.out.println("month is empty!");
            return false;
        }
        if (this.monthAll == null){
            System.out.println("monthAll is empty!");
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
        if (!TotalExperienceDetail.class.isAssignableFrom(obj.getClass())) {
            System.out.println("Object is other class");
            return false;
        }
        final TotalExperienceDetail other = (TotalExperienceDetail) obj;
        if ((this.year == null) ? (other.year != null) : !this.year.equals(other.year)) {
            System.out.println("Expected year " + this.year + ", Actual year: " + other.year);
            return false;
        }
        if ((this.month == null) ? (other.month != null) : !this.month.equals(other.month)) {
            System.out.println("Expected month " + this.month + ", Actual month: " + other.month);
            return false;
        }
        if ((this.monthAll == null) ? (other.monthAll != null) : !this.monthAll.equals(other.monthAll)) {
            System.out.println("Expected monthAll " + this.monthAll + ", Actual monthAll: " + other.monthAll);
            return false;
        }
        return true;
    }
}
