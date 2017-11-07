package dataModels.apiModels.v1.errorModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Asta on 12.07.2017.
 */
public class Error {
    @SerializedName("code")
    @Expose
    public Integer code;
    @SerializedName("message")
    @Expose
    public String message;

    @Override
    public String toString() {
        return "ErrorData{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            System.out.println("Object is null");
            return false;
        }
        if (!Error.class.isAssignableFrom(obj.getClass())) {
            System.out.println("Object is other class");
            return false;
        }
        final Error other = (Error) obj;
        if ((this.code == null) ? (other.code != null) : !this.code.equals(other.code)) {
            System.out.println("Expected code: " + this.code + ", Actual code: " + other.code);
            return false;
        }
        if ((this.message == null) ? (other.message != null) : !this.message.equals(other.message)) {
            System.out.println("Expected message: " + this.message + ", Actual message: " + other.message);
            return false;
        }
        return true;
    }
}
