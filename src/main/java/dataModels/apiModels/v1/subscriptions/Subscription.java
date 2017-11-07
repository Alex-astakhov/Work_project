package dataModels.apiModels.v1.subscriptions;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Subscription {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("type")
    @Expose
    public Integer type;
    @SerializedName("active")
    @Expose
    public Integer active;

    public boolean validate(){
        if (this.id == null || this.id.equals(0)){
            System.out.println("id is empty!");
            return false;
        }
        if (this.title == null || this.title.equals("")){
            System.out.println("title is empty!");
            return false;
        }
        if (this.type == null || this.type.equals(0)){
            System.out.println("type is empty!");
            return false;
        }
        if (this.active == null){
            System.out.println("active is empty!");
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ListSubscription{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", active=" + active +
                '}';
    }
}
