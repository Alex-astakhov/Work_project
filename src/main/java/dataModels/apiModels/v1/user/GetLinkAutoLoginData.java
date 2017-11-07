package dataModels.apiModels.v1.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import dataModels.apiModels.v1.ApiData;

public class GetLinkAutoLoginData implements ApiData {
    @SerializedName("link")
    @Expose
    public String link;

    @Override
    public boolean validate(){
        if (this.link == null || this.link.length() == 0){
            System.out.println("link is empty!");
            return false;
        }
        if (!this.link.contains("https://rabota")){
            System.out.println("link is not valid! " + this.link);
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
        return "GetLinkAutoLoginData{" +
                "link='" + link + '\'' +
                '}';
    }
}
