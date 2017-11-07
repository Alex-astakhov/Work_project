package dataModels.apiModels.v1.listEntities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import dataModels.DataGenerator;
import dataModels.apiModels.v1.ApiData;

import java.util.List;

/**
 * Created by Asta on 13.07.2017.
 */
public class GetCitiesData extends DataGenerator implements ApiData {
    @SerializedName("cities")
    @Expose
    public List<City> cities = null;

    @Override
    public String toString() {
        return "Data{" +
                    "cities=" + cities +
                    '}';
    }

    public boolean validate(){
        if (this.cities == null || this.cities.size() == 0){
            System.out.println("cities is empty!");
            return false;
        }
        for (City c: this.cities) {
            if (!c.validate()){
                System.out.println("cities is not valid!\n" + c);
                return false;
            }
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
        if (!GetCitiesData.class.isAssignableFrom(obj.getClass())) {
            System.out.println("Object is other class");
            return false;
        }
        final GetCitiesData other = (GetCitiesData) obj;
        if ((this.cities == null) ? (other.cities != null) : !collectionStrictEquals(this.cities, other.cities)) {
            System.out.println("Expected cities: " + this.cities + ", Actual cities: " + other.cities);
            return false;
        }
        return true;
    }
}
