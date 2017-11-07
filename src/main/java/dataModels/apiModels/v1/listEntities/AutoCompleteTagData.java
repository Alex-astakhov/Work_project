package dataModels.apiModels.v1.listEntities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import dataModels.apiModels.v1.ApiData;

import java.util.List;

/**
 * Created by Asta on 13.07.2017.
 */
public class AutoCompleteTagData implements ApiData {
    @SerializedName("tags")
    @Expose
    public List<DefaultEntity> tags = null;

    @Override
    public boolean validate(){
        if (this.tags == null || this.tags.size() == 0){
            System.out.println("tags is empty!");
            return false;
        }
        for (DefaultEntity t: this.tags) {
            if (!t.validate()){
                System.out.println("tags is not valid!\n" + t);
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
}
