package dataModels.apiModels.v1.vacancy;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response {
    @SerializedName("allowedSend")
    @Expose
    public Boolean allowedSend;
    @SerializedName("isSendOut")
    @Expose
    public Boolean isSendOut;
    @SerializedName("lastDateSend")
    @Expose
    public String lastDateSend;

    public boolean validate(boolean guest){
        if (this.allowedSend == null){
            System.out.println("allowedSend is empty!");
            return false;
        }
        if (this.isSendOut == null){
            System.out.println("isSendOut is empty!");
            return false;
        }
        if (this.allowedSend.equals(false) && this.isSendOut.equals(false) && !guest){
            System.out.println("allowedSend = false but isSendOut = false!");
            return false;
        }
        if (this.lastDateSend == null){
            System.out.println("lastDateSend is empty!");
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Response{" +
                "allowedSend=" + allowedSend +
                ", isSendOut=" + isSendOut +
                ", lastDateSend='" + lastDateSend + '\'' +
                '}';
    }
}
