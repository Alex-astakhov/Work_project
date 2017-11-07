package dataModels.apiModels.v1.user;

import assertion.CheckValid;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import dataModels.apiModels.v1.ApiData;

/**
 * Created by Asta on 11.07.2017.
 */
public class ApplicantLoginData implements ApiData {
    @SerializedName("token")
    @Expose
    public String token;
    @SerializedName("role")
    @Expose
    public Integer role;
    @SerializedName("userID")
    @Expose
    public Integer userID;

    @Override
    public boolean validate(){
        if (this.token == null || this.token.equals("")){
            System.out.println("token is empty!");
            return false;
        }
        if (this.role == null){
            System.out.println("role is empty!");
            return false;
        }
        if (this.userID == null || this.userID.equals(0)){
            System.out.println("userID is empty!");
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
        public String toString() {
            return "Data{" +
                    "token='" + token + '\'' +
                    ", role='" + role + '\'' +
                    ", userID='" + userID + '\'' +
                    '}';
    }
}
