package dataModels.apiModels.v1.errorModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KeySkillsErrorData extends ErrorData {
    @SerializedName("usersDriverLicence")
    @Expose
    public String usersDriverLicence;

    public KeySkillsErrorData(String usersDriverLicence) {
        this.usersDriverLicence = usersDriverLicence;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            System.out.println("Object is null");
            return false;
        }
        if (!KeySkillsErrorData.class.isAssignableFrom(obj.getClass())) {
            System.out.println("Object is other class");
            return false;
        }
        final KeySkillsErrorData other = (KeySkillsErrorData) obj;
        if ((this.usersDriverLicence == null) ? (other.usersDriverLicence != null) : !this.usersDriverLicence.equals(other.usersDriverLicence)) {
            System.out.println("Expected usersDriverLicence: " + this.usersDriverLicence + ", Actual usersDriverLicence: " + other.usersDriverLicence);
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "KeySkillsErrorData{" +
                "usersDriverLicence='" + usersDriverLicence + '\'' +
                '}';
    }
}
