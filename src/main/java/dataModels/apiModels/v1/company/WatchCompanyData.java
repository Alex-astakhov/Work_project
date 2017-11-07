package dataModels.apiModels.v1.company;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import dataModels.apiModels.v1.ApiData;
import dataModels.apiModels.v1.vacancy.Vacancy;

import java.util.List;

/**
 * Created by Asta on 17.07.2017.
 */
public class WatchCompanyData implements ApiData {
    @SerializedName("company")
    @Expose
    public Company company;
    @SerializedName("listEntities")
    @Expose
    public List<Vacancy> listEntities = null;
    @SerializedName("countAllEntities")
    @Expose
    public Integer countAllEntities;
    @SerializedName("limit")
    @Expose
    public Integer limit;
    @SerializedName("page")
    @Expose
    public Integer page;
    @SerializedName("pickedVacancies")
    @Expose
    public List<Integer> pickedVacancies = null;

    @Override
    public String toString() {
        return "WatchData{" +
                "company=" + company +
                ", listEntities=" + listEntities +
                ", countAllEntities=" + countAllEntities +
                ", limit=" + limit +
                ", page=" + page +
                ", pickedVacancies=" + pickedVacancies +
                '}';
    }

    @Override
    public boolean validate() {
        System.out.println("\nWrong type of validation method selected!\n");
        return false;
    }

    @Override
    public boolean validate(boolean guest){
        if (!this.company.validate()) {
            System.out.println("company is not filled properly!");
            return false;
        }
        if (this.listEntities == null || this.listEntities.size() == 0) {
            System.out.println("listEntities is empty!");
            return false;
        }
        if (this.countAllEntities == null || this.countAllEntities.equals(0)) {
            System.out.println("countAllEntities = 0");
            return false;
        }
        if (this.limit == null || this.limit.equals(0)) {
            System.out.println("limit = 0");
            return false;
        }
        if (this.page == null || this.page.equals(0)) {
            System.out.println("page = 0");
            return false;
        }
        if (!guest) {
            if (this.pickedVacancies == null || this.pickedVacancies.size() == 0) {
                System.out.println("pickedVacancies is empty!");
                return false;
            }
            for (Integer p : pickedVacancies) {
                if (p == null || p.equals(0)) {
                    System.out.println("pickedVacancies is nt filled properly!");
                    return false;
                }
            }
        }
        else {
            if (this.pickedVacancies == null || this.pickedVacancies.size() > 0) {
                System.out.println("pickedVacancies is null or size is not 0 gor GUEST!");
                return false;
            }
        }
        return true;
    }
}
