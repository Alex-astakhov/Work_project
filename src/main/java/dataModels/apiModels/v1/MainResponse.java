package dataModels.apiModels.v1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import dataModels.apiModels.v1.errorModels.Error;

/**
 * Created by Asta on 13.07.2017.
 */
public class MainResponse<T extends ApiData> {
    @SerializedName("status")
    @Expose
    public Integer status;
    @SerializedName("error")
    @Expose
    public Error error;
    @SerializedName("data")
    @Expose
    public T data;

    public boolean validate(){
        return data.validate();
    }

    public boolean validate(boolean additionalCondition){
        return data.validate(additionalCondition);
    }

    @Override
    public String toString() {
        return "MainResponse{" +
                "status=" + status +
                ", error=" + error +
                ", data=" + data +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            System.out.println("Object is null");
            return false;
        }
        if (!MainResponse.class.isAssignableFrom(obj.getClass())) {
            System.out.println("Object is other class");
            return false;
        }
        final MainResponse other = (MainResponse) obj;
        if ((this.status == null) ? (other.status != null) : !this.status.equals(other.status)) {
            System.out.println("Expected status: " + this.status + ", Actual status: " + other.status);
            return false;
        }
        if ((this.error == null) ? (other.error != null) : !this.error.equals(other.error)) {
            System.out.println("Expected error: " + this.error + ", Actual error: " + other.error);
            return false;
        }
        if ((this.data == null) ? (other.data != null) : !this.data.equals(other.data)) {
            System.out.println("Expected data: " + this.data + ", Actual data: " + other.data);
            return false;
        }
        return true;
    }
}
