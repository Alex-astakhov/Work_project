package dataModels.apiModels.v1.errorModels.errorBlocks;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LanguageDataParams extends ErrorParams {
    @SerializedName("languageValid")
    @Expose
    public Boolean languageValid;
    @SerializedName("possessionValid")
    @Expose
    public Boolean possessionValid;

    public LanguageDataParams(Boolean languageValid, Boolean possessionValid) {
        this.languageValid = languageValid;
        this.possessionValid = possessionValid;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            System.out.println("Object is null");
            return false;
        }
        if (!LanguageDataParams.class.isAssignableFrom(obj.getClass())) {
            System.out.println("Object is other class");
            return false;
        }
        final LanguageDataParams other = (LanguageDataParams) obj;
        if ((this.languageValid == null) ? (other.languageValid != null) : !this.languageValid.equals(other.languageValid)) {
            System.out.println("Expected languageValid: " + this.languageValid + ", Actual languageValid: " + other.languageValid);
            return false;
        }
        if ((this.possessionValid == null) ? (other.possessionValid != null) : !this.possessionValid.equals(other.possessionValid)) {
            System.out.println("Expected possessionValid: " + this.possessionValid + ", Actual possessionValid: " + other.possessionValid);
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "LanguageDataParams{" +
                "languageValid=" + languageValid +
                ", possessionValid=" + possessionValid +
                '}';
    }
}
