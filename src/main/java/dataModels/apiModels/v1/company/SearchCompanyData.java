package dataModels.apiModels.v1.company;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import dataModels.DataGenerator;
import dataModels.apiModels.v1.ApiData;

import java.util.List;

/**
 * Created by Asta on 17.07.2017.
 */
public class SearchCompanyData extends DataGenerator implements ApiData {
    @SerializedName("listEntities")
    @Expose
    public List<Company> listEntities = null;
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
        return "SearchData{" +
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
        for (Company c: this.listEntities) {
            if (!c.validate()){
                System.out.println("listEntities is not valid!\n" + c);
                return false;
            }
        }
        if (this.countAllEntities == null || this.countAllEntities.equals(0)){
            System.out.println("countAllEntities is empty!");
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
        System.out.println("\nWrong type of validation method selected!\n");
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            System.out.println("Object is null");
            return false;
        }
        if (!SearchCompanyData.class.isAssignableFrom(obj.getClass())) {
            System.out.println("Object is other class");
            return false;
        }
        final SearchCompanyData other = (SearchCompanyData) obj;
        if ((this.listEntities == null) ? (other.listEntities != null) : !collectionStrictEquals(this.listEntities, other.listEntities)) {
            System.out.println("Expected listEntities: " + this.listEntities + ", Actual listEntities: " + other.listEntities);
            return false;
        }
        if ((this.countAllEntities == null) ? (other.countAllEntities != null) : !this.countAllEntities.equals(other.countAllEntities)) {
            System.out.println("Expected countAllEntities: " + this.countAllEntities + ", Actual countAllEntities: " + other.countAllEntities);
            return false;
        }
        if ((this.limit == null) ? (other.limit != null) : !this.limit.equals(other.limit)) {
            System.out.println("Expected limit: " + this.limit + ", Actual limit: " + other.limit);
            return false;
        }
        if ((this.page == null) ? (other.page != null) : !this.page.equals(other.page)) {
            System.out.println("Expected page: " + this.page + ", Actual page: " + other.page);
            return false;
        }
        return true;
    }
}
