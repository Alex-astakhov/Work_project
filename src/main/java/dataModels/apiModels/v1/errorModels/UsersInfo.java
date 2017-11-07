package dataModels.apiModels.v1.errorModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import dataModels.DataGenerator;

import java.util.List;

public class UsersInfo {
    @SerializedName("city_region_id")
    @Expose
    public List<String> cityRegionId = null;

    public UsersInfo(List<String> cityRegionId) {
        this.cityRegionId = cityRegionId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            System.out.println("Object is null");
            return false;
        }
        if (!UsersInfo.class.isAssignableFrom(obj.getClass())) {
            System.out.println("Object is other class");
            return false;
        }
        final UsersInfo other = (UsersInfo) obj;
        if ((this.cityRegionId == null) ? (other.cityRegionId != null) :
                !DataGenerator.collectionStrictEquals(this.cityRegionId, other.cityRegionId)) {
            System.out.println("Expected cityRegionId: " + this.cityRegionId + ", Actual cityRegionId: " + other.cityRegionId);
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UsersInfo{" +
                "cityRegionId=" + cityRegionId +
                '}';
    }
}
