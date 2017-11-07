package dataModels.apiModels.v1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MoreInformation {
    @SerializedName("more_information_id")
    @Expose
    public Integer moreInformationId;
    @SerializedName("value")
    @Expose
    public String value;

    @Override
    public String toString() {
        return "MoreInformation{" +
                "moreInformationId='" + moreInformationId + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    public boolean validate(){
        if (this.moreInformationId == null || this.moreInformationId.equals(0)){
            System.out.println("moreInformationId is empty");
            return false;
        }
        if (this.value == null || this.value.equals("0")){
            System.out.println("value is empty");
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
        if (!MoreInformation.class.isAssignableFrom(obj.getClass())) {
            System.out.println("Object is other class");
            return false;
        }
        final MoreInformation other = (MoreInformation) obj;
        if ((this.moreInformationId == null) ? (other.moreInformationId != null) : !this.moreInformationId.equals(other.moreInformationId)) {
            System.out.println("Expected moreInformationId: " + this.moreInformationId + ", Actual moreInformationId: " + other.moreInformationId);
            return false;
        }
        if ((this.value == null) ? (other.value != null) : !this.value.equals(other.value)) {
            System.out.println("Expected value: " + this.value + ", Actual value: " + other.value);
            return false;
        }
        return true;
    }
}
