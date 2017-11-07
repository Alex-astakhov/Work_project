package dataModels.apiModels.v1.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import dataModels.DataGenerator;
import dataModels.apiModels.v1.ApiData;
import dataModels.apiModels.v1.errorModels.ErrorData;
import dataModels.applicant.cvParts.UserData;

public class UserAboutData<T extends ErrorData> extends DataGenerator implements ApiData {
    @SerializedName("user")
    @Expose
    public User user;
    @SerializedName("errors")
    @Expose
    public T errors;

    @Override
    public boolean validate() {
        System.out.println("\nWrong type of validation method selected!\n");
        return false;
    }

    @Override
    public boolean validate(boolean additionalCondition) {
        if (this.user == null){
            System.out.println("user is empty!");
            return false;
        }
        if (this.user.validate(additionalCondition)){
            System.out.println("user is not filled properly!\n" + this.user);
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
        if (!UserAboutData.class.isAssignableFrom(obj.getClass())) {
            System.out.println("Object is other class");
            return false;
        }
        final UserAboutData other = (UserAboutData) obj;
        if ((this.user == null) ? (other.user != null) : !this.user.equals(other.user)) {
            System.out.println("Expected user: " + this.user + ", Actual user: " + other.user);
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
        return "UserData{" +
                "user=" + user +
                ", errors=" + errors +
                '}';
    }

    public UserData getUserData(){
        return user.getUserData();
    }
}
