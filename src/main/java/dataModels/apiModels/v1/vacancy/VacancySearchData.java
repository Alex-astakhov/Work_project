package dataModels.apiModels.v1.vacancy;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import dataModels.apiModels.v1.ApiData;

import java.util.List;

public class VacancySearchData implements ApiData {
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
    public boolean validate() {
        System.out.println("\nWrong type of validation method selected!\n");
        return false;
    }

    @Override
    public boolean validate(boolean guest){
        if (this.listEntities == null || this.listEntities.size() == 0){
            System.out.println("listEntities is empty!");
            return false;
        }
        for (int i = 0; i < this.countAllEntities; i++) {
            if (!this.listEntities.get(i).validate()) {
                System.out.println("listEntities is not valid!\n" + this.listEntities.get(i));
                return false;
            }
        }
        if (this.countAllEntities == null || this.countAllEntities > this.listEntities.size()){
            System.out.println("countAllEntities empty or != listEntities.size!");
            return false;
        }
        if (this.limit == null || this.limit.equals(0)){
            System.out.println("limit is empty!");
            return false;
        }
        if (this.page == null || this.page.equals(0)){
            System.out.println("page is empty!");
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

    @Override
    public String toString() {
        return "VacancySearchData{" +
                "listEntities=" + listEntities +
                ", countAllEntities=" + countAllEntities +
                ", limit=" + limit +
                ", page=" + page +
                ", pickedVacancies=" + pickedVacancies +
                '}';
    }
}
