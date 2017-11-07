package dataModels.apiModels.v1.activity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import dataModels.DataGenerator;
import dataModels.apiModels.v1.ApiData;
import dataModels.apiModels.v1.resume.Resume;
import dataModels.apiModels.v1.user.User;

import java.util.List;

/**
 * Created by Asta on 12.07.2017.
 */
public class ResumeListData extends DataGenerator implements ApiData {
    @SerializedName("user")
    @Expose
    public User user;
    @SerializedName("resumes")
    @Expose
    public List<Resume> resumes = null;

    @Override
    public String toString() {
            return "Data{" +
                    "user=" + user +
                    ", resumes=" + resumes +
                    '}';
    }

    @Override
    public boolean validate(){
        if (this.user == null || !this.user.validate(true)){
            System.out.println("user is note filled properly!\n" + this.user);
            return false;
        }
        if (this.resumes == null || this.resumes.size() == 0){
            System.out.println("user is note filled properly!");
            return false;
        }
        for (Resume r: this.resumes) {
            if (!r.validate()){
                System.out.println("resume is not filled properly!\n" + this.resumes);
                return false;
            }
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
        if (!ResumeListData.class.isAssignableFrom(obj.getClass())) {
            System.out.println("Object is other class");
            return false;
        }
        final ResumeListData other = (ResumeListData) obj;
        if ((this.user == null) ? (other.user != null) : !this.user.equals(other.user)) {
            System.out.println("Expected user: " + this.user + ", Actual user: " + other.user);
            return false;
        }
        if ((this.resumes == null) ? (other.resumes != null) : !collectionStrictEquals(this.resumes, other.resumes)) {
            System.out.println("Expected resumes: " + this.resumes + ", Actual resumes: " + other.resumes);
            return false;
        }
        return true;
    }
}
