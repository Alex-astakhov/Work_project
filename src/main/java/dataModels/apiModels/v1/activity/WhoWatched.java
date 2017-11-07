package dataModels.apiModels.v1.activity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import dataModels.apiModels.v1.company.Company;
import dataModels.apiModels.v1.resume.Resume;
import dataModels.apiModels.v1.user.User;

public class WhoWatched {

    @SerializedName("resume")
    @Expose
    public Resume resume;
    @SerializedName("company")
    @Expose
    public Company company;
    @SerializedName("whoWatchedUser")
    @Expose
    public User whoWatchedUser;
    @SerializedName("date_visit")
    @Expose
    public String dateVisit;

    public boolean validate(){
        if (this.resume == null || !this.resume.validate()){
            System.out.println("resume is not valid!\n" + this.resume);
            return false;
        }
        if (this.company == null || !this.company.validate()){
            System.out.println("company is not valid!\n" + this.company);
            return false;
        }
        if (this.whoWatchedUser == null || !this.whoWatchedUser.validate(false)){
            System.out.println("whoWatchedUser is not valid!\n" + this.whoWatchedUser);
            return false;
        }
        if (this.dateVisit == null || this.dateVisit.equals("")){
            System.out.println("dateVisit is empty!");
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "WhoWatched{" +
                "resume=" + resume +
                ", company=" + company +
                ", whoWatchedUser=" + whoWatchedUser +
                ", dateVisit='" + dateVisit + '\'' +
                '}';
    }
}
