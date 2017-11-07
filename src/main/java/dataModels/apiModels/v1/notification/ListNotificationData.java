package dataModels.apiModels.v1.notification;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import dataModels.apiModels.v1.ApiData;

import java.util.List;

public class ListNotificationData implements ApiData {
    @SerializedName("listNotificationUser")
    @Expose
    public List<NotificationUser> listNotificationUser = null;

    @Override
    public boolean validate(){
        if (this.listNotificationUser == null || this.listNotificationUser.size() < 5){
            System.out.println("listNotificationUser is empty or size < 5 !");
            return false;
        }
        for (NotificationUser n: this.listNotificationUser) {
            if (!n.validate()){
                System.out.println("listNotificationUser is not valid! " + n);
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
        return "EditNotification{" +
                "listNotificationUser=" + listNotificationUser +
                '}';
    }

    public class NotificationUser {
        @SerializedName("id")
        @Expose
        public Integer id;
        @SerializedName("isApplicant")
        @Expose
        public Integer isApplicant;
        @SerializedName("type")
        @Expose
        public Integer type;
        @SerializedName("typeTitle")
        @Expose
        public String typeTitle;
        @SerializedName("isActive")
        @Expose
        public Integer isActive;

        public boolean validate() {
            if (this.id == null || this.id.equals(0)){
                System.out.println("id is empty!");
                return false;
            }
            if (this.isApplicant == null || this.isApplicant.equals(0)){
                System.out.println("isApplicant is empty or = 0!");
                return false;
            }
            if (this.type == null || this.type.equals(0)){
                System.out.println("type is empty!");
                return false;
            }
            if (this.typeTitle == null || this.typeTitle.equals("")){
                System.out.println("typeTitle is empty!");
                return false;
            }
            if (this.isActive == null){
                System.out.println("isActive is empty!");
                return false;
            }
            return true;
        }

        @Override
        public String toString() {
            return "NotificationUser{" +
                    "id=" + id +
                    ", isApplicant=" + isApplicant +
                    ", type=" + type +
                    ", typeTitle='" + typeTitle + '\'' +
                    ", isActive=" + isActive +
                    '}';
        }
    }
}
