package dataModels.apiModels.v1.listEntities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import dataModels.DataGenerator;

import java.util.List;

/**
 * Created by Asta on 13.07.2017.
 */
public class City extends DataGenerator{
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("decline")
    @Expose
    public String decline;
    @SerializedName("district")
    @Expose
    public List<DefaultEntity> district = null;
    @SerializedName("region")
    @Expose
    public Region region;
    @SerializedName("kk_seo")
    @Expose
    public String kkSeo;

    @Override
    public String toString() {
        return "City{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", decline='" + decline + '\'' +
                ", district=" + district +
                ", region=" + region +
                ", kkSeo='" + kkSeo + '\'' +
                '}';
    }

    public boolean validate(){
        if (this.id == null || this.id.equals(0)){
            System.out.println("id is empty!");
            return false;
        }
        if (this.name == null || this.name.equals("")){
            System.out.println("name is empty!");
            return false;
        }
        if (this.decline == null || this.decline.equals("")){
            System.out.println("decline is empty!");
            return false;
        }
        /*if (this.district == null || this.district.size() == 0){
            System.out.println("district is empty!");
            return false;
        }*/
        for (DefaultEntity d: this.district) {
            if (!d.validate()){
                System.out.println("district is not valid!\n" + d);
                return false;
            }
        }
        if (this.region == null && !this.id.equals(26468)){
            System.out.println("region is empty!");
            return false;
        }
        if (!this.id.equals(26468) && !this.region.validate()){
            System.out.println("region is not valid!");
            return false;
        }
        if (this.kkSeo == null || this.kkSeo.equals("")){
            System.out.println("kkSeo is empty!");
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            System.out.println("Object is null");
            return false;
        }
        if (!City.class.isAssignableFrom(obj.getClass())) {
            System.out.println("Object is other class");
            return false;
        }
        final City other = (City) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            System.out.println("Expected id: " + this.id + ", Actual id: " + other.id);
            return false;
        }
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            System.out.println("Expected name: " + this.name + ", Actual name: " + other.name);
            return false;
        }
        if ((this.decline == null) ? (other.decline != null) : !this.decline.equals(other.decline)) {
            System.out.println("Expected decline: " + this.decline + ", Actual decline: " + other.decline);
            return false;
        }
        if ((this.district == null) ? (other.district != null) : !collectionStrictEquals(this.district, other.district)) {
            System.out.println("Expected district: " + this.district + ", Actual district: " + other.district);
            return false;
        }
        if ((this.region == null) ? (other.region != null) : !this.region.equals(other.region)) {
            System.out.println("Expected region: " + this.region + ", Actual region: " + other.region);
            return false;
        }
        if ((this.kkSeo == null) ? (other.kkSeo != null) : !this.kkSeo.equals(other.kkSeo)) {
            System.out.println("Expected kkSeo: " + this.kkSeo + ", Actual kkSeo: " + other.kkSeo);
            return false;
        }
        return true;
    }

    public class Region {
        @SerializedName("id")
        @Expose
        public Integer id;
        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("kk_seo")
        @Expose
        public String kkSeo;
        @SerializedName("nearestCities")
        @Expose
        public List<DefaultEntity> nearestCities = null;

        @Override
        public String toString() {
            return "Region{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", kkSeo='" + kkSeo + '\'' +
                    ", nearestCities=" + nearestCities +
                    '}';
        }

        public boolean validate(){
            if (this.id == null || this.id.equals(0)){
                System.out.println("id is empty!");
                return false;
            }
            if (this.name == null || this.name.equals("")){
                System.out.println("name is empty!");
                return false;
            }
            if (this.kkSeo == null || this.kkSeo.equals("")){
                System.out.println("kkSeo is empty!");
                return false;
            }
            if (this.nearestCities == null || this.nearestCities.size() == 0){
                System.out.println("nearestCities is empty!");
                return false;
            }
            for (DefaultEntity n: this.nearestCities) {
                if (!n.validate()){
                    System.out.println("nearestCities is not valid!");
                    return false;
                }
            }
            return true;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                System.out.println("Object is null");
                return false;
            }
            if (!Region.class.isAssignableFrom(obj.getClass())) {
                System.out.println("Object is other class");
                return false;
            }
            final Region other = (Region) obj;
            if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
                System.out.println("Expected id: " + this.id + ", Actual id: " + other.id);
                return false;
            }
            if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
                System.out.println("Expected name: " + this.name + ", Actual name: " + other.name);
                return false;
            }
            if ((this.kkSeo == null) ? (other.kkSeo != null) : !this.kkSeo.equals(other.kkSeo)) {
                System.out.println("Expected kkSeo: " + this.kkSeo + ", Actual kkSeo: " + other.kkSeo);
                return false;
            }
            return true;
        }
    }
}
