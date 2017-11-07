package dataModels.apiModels.v1.resume;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import dataModels.applicant.cvParts.LanguageData;

public class Language {
    @SerializedName("language_id")
    @Expose
    public String languageId;
    @SerializedName("language_title")
    @Expose
    public String languageTitle;
    @SerializedName("possession")
    @Expose
    public String possession;
    @SerializedName("possession_title")
    @Expose
    public String possessionTitle;

    @Override
    public String toString() {
        return "Language{" +
                "languageId='" + languageId + '\'' +
                ", languageTitle='" + languageTitle + '\'' +
                ", possession='" + possession + '\'' +
                ", possessionTitle='" + possessionTitle + '\'' +
                '}';
    }

    public boolean validate(){
        if (this.languageId == null || this.languageId.equals("") || this.languageId.equals("0")){
            System.out.println("languageId is empty!");
            return false;
        }
        if (this.languageTitle == null || this.languageTitle.equals("")){
            System.out.println("languageTitle is empty!");
            return false;
        }
        if (this.possession == null || this.possession.equals("") || this.possession.equals("0")){
            System.out.println("possession is empty!");
            return false;
        }
        if (this.possessionTitle == null || this.possessionTitle.equals("")){
            System.out.println("possessionTitle is empty!");
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
        if (!Language.class.isAssignableFrom(obj.getClass())) {
            System.out.println("Object is other class");
            return false;
        }
        final Language other = (Language) obj;
        if ((this.languageId == null) ? (other.languageId != null) : !this.languageId.equals(other.languageId)) {
            System.out.println("Expected languageId: " + this.languageId + ", Actual languageId: " + other.languageId);
            return false;
        }
        if ((this.languageTitle == null) ? (other.languageTitle != null) : !this.languageTitle.equals(other.languageTitle)) {
            System.out.println("Expected languageTitle: " + this.languageTitle + ", Actual languageTitle: " + other.languageTitle);
            return false;
        }
        if ((this.possession == null) ? (other.possession != null) : !this.possession.equals(other.possession)) {
            System.out.println("Expected possession: " + this.possession + ", Actual possession: " + other.possession);
            return false;
        }
        if ((this.possessionTitle == null) ? (other.possessionTitle != null) : !this.possessionTitle.equals(other.possessionTitle)) {
            System.out.println("Expected possessionTitle: " + this.possessionTitle + ", Actual possessionTitle: " + other.possessionTitle);
            return false;
        }
        return true;
    }

    public LanguageData getLanguageData(){
        return new LanguageData(this.languageId, this.possession);
    }
}
