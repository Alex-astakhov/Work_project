package dataModels.apiModels.v1.errorModels.errorBlocks;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SocialNetworksDataParams extends ErrorParams {
    @SerializedName("socialNetworkValid")
    @Expose
    public Boolean socialNetworkValid;
    @SerializedName("socialNetworkUrlValid")
    @Expose
    public Boolean socialNetworkUrlValid;

    public SocialNetworksDataParams(Boolean socialNetworkValid, Boolean socialNetworkUrlValid) {
        this.socialNetworkValid = socialNetworkValid;
        this.socialNetworkUrlValid = socialNetworkUrlValid;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            System.out.println("Object is null");
            return false;
        }
        if (!SocialNetworksDataParams.class.isAssignableFrom(obj.getClass())) {
            System.out.println("Object is other class");
            return false;
        }
        final SocialNetworksDataParams other = (SocialNetworksDataParams) obj;
        if ((this.socialNetworkValid == null) ? (other.socialNetworkValid != null) : !this.socialNetworkValid.equals(other.socialNetworkValid)) {
            System.out.println("Expected socialNetworkValid: " + this.socialNetworkValid + ", Actual socialNetworkValid: " + other.socialNetworkValid);
            return false;
        }
        if ((this.socialNetworkUrlValid == null) ? (other.socialNetworkUrlValid != null) : !this.socialNetworkUrlValid.equals(other.socialNetworkUrlValid)) {
            System.out.println("Expected socialNetworkUrlValid: " + this.socialNetworkUrlValid + ", Actual socialNetworkUrlValid: " + other.socialNetworkUrlValid);
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SocailNetworksDataParams{" +
                "socialNetworkValid=" + socialNetworkValid +
                ", socialNetworkUrlValid=" + socialNetworkUrlValid +
                '}';
    }
}
