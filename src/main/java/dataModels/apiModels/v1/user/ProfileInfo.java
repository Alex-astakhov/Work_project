package dataModels.apiModels.v1.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileInfo {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("user_id")
    @Expose
    public Integer userId;
    @SerializedName("skype")
    @Expose
    public String skype;
    @SerializedName("additional_phone")
    @Expose
    public String additionalPhone;
    @SerializedName("has_car")
    @Expose
    public String hasCar;
    @SerializedName("city_region_id")
    @Expose
    public Integer cityRegionId;

    public boolean validate(){
        if (this.id == null || this.id.equals(0)){
            System.out.println("id is empty!");
            return false;
        }
        if (this.userId == null || this.userId.equals(0)){
            System.out.println("userId is empty!");
            return false;
        }
        if (this.skype == null || this.skype.equals("")){
            System.out.println("skype is empty!");
            return false;
        }
        if (this.additionalPhone == null || this.additionalPhone.equals("")){
            System.out.println("additionalPhone is empty!");
            return false;
        }
        if (this.hasCar == null || this.hasCar.equals("")){
            System.out.println("hasCar is empty!");
            return false;
        }
        if (this.cityRegionId == null){
            System.out.println("cityRegionId is empty!");
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProfileInfo{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", skype='" + skype + '\'' +
                ", additionalPhone='" + additionalPhone + '\'' +
                ", hasCar='" + hasCar + '\'' +
                ", cityRegionId='" + cityRegionId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            System.out.println("Object is null");
            return false;
        }
        if (!ProfileInfo.class.isAssignableFrom(obj.getClass())) {
            System.out.println("Object is other class");
            return false;
        }
        final ProfileInfo other = (ProfileInfo) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            System.out.println("Expected id: " + this.id + ", Actual id: " + other.id);
            return false;
        }
        if ((this.userId == null) ? (other.userId != null) : !this.userId.equals(other.userId)) {
            System.out.println("Expected userId: " + this.userId + ", Actual userId: " + other.userId);
            return false;
        }
        if ((this.skype == null) ? (other.skype != null) : !this.skype.equals(other.skype)) {
            System.out.println("Expected skype: " + this.skype + ", Actual skype: " + other.skype);
            return false;
        }
        if ((this.additionalPhone == null) ? (other.additionalPhone != null) : !this.additionalPhone.equals(other.additionalPhone)) {
            System.out.println("Expected additionalPhone: " + this.additionalPhone + ", Actual additionalPhone: " + other.additionalPhone);
            return false;
        }
        if ((this.hasCar == null) ? (other.hasCar != null) : !this.hasCar.equals(other.hasCar)) {
            System.out.println("Expected hasCar: " + this.hasCar + ", Actual hasCar: " + other.hasCar);
            return false;
        }
        if ((this.cityRegionId == null) ? (other.cityRegionId != null) : !this.cityRegionId.equals(other.cityRegionId)) {
            System.out.println("Expected cityRegionId: " + this.cityRegionId + ", Actual cityRegionId: " + other.cityRegionId);
            return false;
        }
        return true;
    }
}
