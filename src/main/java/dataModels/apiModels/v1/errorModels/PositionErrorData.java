package dataModels.apiModels.v1.errorModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import dataModels.DataGenerator;

import java.util.List;

public class PositionErrorData extends ErrorData {
    @SerializedName("position_name")
    @Expose
    public List<String> positionName = null;
    @SerializedName("availability_id")
    @Expose
    public List<String> availabilityId = null;
    @SerializedName("move")
    @Expose
    public List<String> move = null;
    @SerializedName("salary")
    @Expose
    public List<String> salary = null;

    public PositionErrorData(List<String> positionName, List<String> availabilityId, List<String> move,
                             List<String> salary) {
        this.positionName = positionName;
        this.availabilityId = availabilityId;
        this.move = move;
        this.salary = salary;
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null) {
            System.out.println("Object is null");
            return false;
        }
        if (!PositionErrorData.class.isAssignableFrom(obj.getClass())) {
            System.out.println("Object is other class");
            return false;
        }
        final PositionErrorData other = (PositionErrorData) obj;
        if ((this.positionName == null) ? (other.positionName != null) :
                !DataGenerator.collectionStrictEquals(this.positionName, other.positionName)) {
            System.out.println("Expected positionName: " + this.positionName + ", Actual positionName: " + other.positionName);
            return false;
        }
        if ((this.availabilityId == null) ? (other.availabilityId != null) :
                !DataGenerator.collectionStrictEquals(this.availabilityId, other.availabilityId)) {
            System.out.println("Expected availabilityId: " + this.availabilityId + ", Actual availabilityId: " + other.availabilityId);
            return false;
        }
        if ((this.move == null) ? (other.move != null) :
                !DataGenerator.collectionStrictEquals(this.move, other.move)) {
            System.out.println("Expected move: " + this.move + ", Actual move: " + other.move);
            return false;
        }
        if ((this.salary == null) ? (other.salary != null) :
                !DataGenerator.collectionStrictEquals(this.salary, other.salary)) {
            System.out.println("Expected salary: " + this.salary + ", Actual salary: " + other.salary);
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PositionErrorData{" +
                "positionName=" + positionName +
                ", availabilityId=" + availabilityId +
                ", move=" + move +
                ", salary=" + salary +
                '}';
    }
}
