package dataModels.apiModels.v1.vacancy;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import dataModels.apiModels.v1.ApiData;
import dataModels.apiModels.v1.company.Company;

import java.util.List;

public class VacancyWatchData implements ApiData {
    @SerializedName("vacancy")
    @Expose
    public Vacancy vacancy;
    @SerializedName("company")
    @Expose
    public Company company;
    @SerializedName("similarVacancies")
    @Expose
    public List<Vacancy> similarVacancies = null;
    @SerializedName("response")
    @Expose
    public Response response;
    @SerializedName("isScam")
    @Expose
    public Boolean isScam;
    @SerializedName("isPickedVacancy")
    @Expose
    public Boolean isPickedVacancy;

    @Override
    public boolean validate() {
        System.out.println("\nWrong type of validation method selected!\n");
        return false;
    }

    @Override
    public boolean validate(boolean guest){
        if (this.vacancy == null || !this.vacancy.validate()){
            System.out.println("vacancy is not filled properly!\n" + this.vacancy);
            return false;
        }
        if (this.company == null || !this.company.validate()){
            System.out.println("company is not filled properly!\n" + this.company);
            return false;
        }
        if (this.similarVacancies == null){
            System.out.println("similarVacancies is empty!");
            return false;
        }
       /* for (Vacancy v: this.similarVacancies) {
            if (!v.validate()){
                System.out.println("similarVacancies is not valid!\n" + v);
                return false;
            }
        }*/
        if (this.response == null || !this.response.validate(guest)){
            System.out.println("response is not valid!\n" + this.response);
            return false;
        }
        if (this.isScam == null){
            System.out.println("isScam is empty!");
            return false;
        }
        if (this.isPickedVacancy == null){
            System.out.println("isPickedVacancy is empty!");
            return false;
        }
        if (guest && this.isPickedVacancy.equals(true)){
            System.out.println("isPickedVacancy is true for GUEST!");
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "VacancyWatchData{" +
                "vacancy=" + vacancy +
                ", company=" + company +
                ", similarVacancies=" + similarVacancies +
                ", response=" + response +
                ", isScam=" + isScam +
                ", isPickedVacancy=" + isPickedVacancy +
                '}';
    }
}
