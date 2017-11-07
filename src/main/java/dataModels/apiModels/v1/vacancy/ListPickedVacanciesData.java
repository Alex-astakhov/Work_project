package dataModels.apiModels.v1.vacancy;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import dataModels.apiModels.v1.ApiData;

import java.util.List;

public class ListPickedVacanciesData implements ApiData {
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

    @Override
    public String toString() {
        return "ListPickedVacanciesData{" +
                "listEntities=" + listEntities +
                ", countAllEntities=" + countAllEntities +
                ", limit=" + limit +
                ", page=" + page +
                '}';
    }

    @Override
    public boolean validate(){
        if (this.listEntities == null || this.listEntities.size() == 0){
            System.out.println("listEntities is empty!");
            return false;
        }
        for (Vacancy v: this.listEntities) {
            if (!v.validate()){
                System.out.println("listEntities is not valid!\n" + v);
                return false;
            }
            if (v.datePicked == null || v.datePicked.contains("0000")){
                System.out.println("datePicked is empty!" + v);
                return false;
            }
        }
        if (this.countAllEntities == null || !this.countAllEntities.equals(this.listEntities.size())){
            System.out.println("countAllEntities is null or != listEntities.size!");
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
        return true;
    }

    @Override
    public boolean validate(boolean additionalCondition) {
        return false;
    }
}
