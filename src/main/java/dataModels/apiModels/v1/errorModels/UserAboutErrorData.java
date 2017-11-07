package dataModels.apiModels.v1.errorModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserAboutErrorData extends ErrorData {
    @SerializedName("users")
    @Expose
    public Users users;
    @SerializedName("usersInfo")
    @Expose
    public UsersInfo usersInfo;

    public UserAboutErrorData(Users users, UsersInfo usersInfo) {
        this.users = users;
        this.usersInfo = usersInfo;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            System.out.println("Object is null");
            return false;
        }
        if (!UserAboutErrorData.class.isAssignableFrom(obj.getClass())) {
            System.out.println("Object is other class");
            return false;
        }
        final UserAboutErrorData other = (UserAboutErrorData) obj;
        if ((this.users == null) ? (other.users != null) : !this.users.equals(other.users)) {
            System.out.println("Expected users: " + this.users + ", Actual users: " + other.users);
            return false;
        }
        if ((this.usersInfo == null) ? (other.usersInfo != null) : !this.usersInfo.equals(other.usersInfo)) {
            System.out.println("Expected usersInfo: " + this.usersInfo + ", Actual usersInfo: " + other.usersInfo);
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UserAboutErrorData{" +
                "users=" + users +
                ", usersInfo=" + usersInfo +
                '}';
    }
}


