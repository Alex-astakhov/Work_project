package dataModels.apiModels.v1.subscriptions;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import dataModels.DataGenerator;
import dataModels.apiModels.v1.ApiData;

import java.util.List;

public class ListSubscriptionData extends DataGenerator implements ApiData {
    @SerializedName("listSubscription")
    @Expose
    public List<Subscription> listSubscription = null;

    @Override
    public boolean validate(){
        if (listSubscription == null){
            return false;
        }
        for (Subscription l: listSubscription) {
            if (!l.validate()){
                System.out.println("listSubscription is not valid!\n" + l);
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
    public String toString() {
        return "ListSubscriptionData{" +
                "listSubscription=" + listSubscription +
                '}';
    }
}
