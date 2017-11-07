package dataModels.apiModels.v1.resume;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequiredBlocks {
    @SerializedName("birthday")
    @Expose
    public Boolean birthday;
    @SerializedName("cityId")
    @Expose
    public Boolean cityId;
    @SerializedName("positionName")
    @Expose
    public Boolean positionName;
    @SerializedName("educations")
    @Expose
    public Boolean educations;
    @SerializedName("languages")
    @Expose
    public Boolean languages;

    public boolean validate(){
        if (this.birthday == null){
            System.out.println("birthday is empty!");
            return false;
        }
        if (this.cityId == null){
            System.out.println("cityId is empty!");
            return false;
        }
        if (this.positionName == null){
            System.out.println("positionName is empty!");
            return false;
        }
        if (this.educations == null){
            System.out.println("educations is empty!");
            return false;
        }
        if (this.languages == null){
            System.out.println("languages is empty!");
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RequiredBlocks{" +
                "birthday=" + birthday +
                ", cityId=" + cityId +
                ", positionName=" + positionName +
                ", educations=" + educations +
                ", languages=" + languages +
                '}';
    }
}
