package dataModels.apiModels.v1.listEntities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DefaultEntity {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("title")
    @Expose
    public String title;

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }

    public boolean validate(){
        if (this.id == null){
            System.out.println("id is empty!");
            return false;
        }
        if (this.title == null || this.title.equals("")){
            System.out.println("title is empty!");
            return false;
        }
        return true;
    }
}
