package dataModels.apiModels.v1.company;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Asta on 17.07.2017.
 */
public class Company {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("city_id")
    @Expose
    public Integer cityId;
    @SerializedName("logo")
    @Expose
    public String logo;
    @SerializedName("address")
    @Expose
    public String address;
    @SerializedName("site_company")
    @Expose
    public String siteCompany;
    @SerializedName("industry_id")
    @Expose
    public Integer industryId;
    @SerializedName("status")
    @Expose
    public Integer status;

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", cityId=" + cityId +
                ", logo='" + logo + '\'' +
                ", address='" + address + '\'' +
                ", siteCompany='" + siteCompany + '\'' +
                ", industryId=" + industryId + '\'' +
                ", status=" + status +
                '}';
    }

    public boolean validate(){
        if (this.id == null || this.id.equals(0)) {
            System.out.println("id is empty!");
            return false;
        }
        if (this.title == null || this.title.equals("")) {
            System.out.println("title is empty!");
            return false;
        }
        if (this.description == null || this.description.equals("")) {
            System.out.println("description is empty!");
            return false;
        }
        if (this.cityId == null || this.cityId.equals(0)) {
            System.out.println("cityId is empty!");
            return false;
        }
        if (this.logo == null || this.logo.equals("")) {
            System.out.println("logo is empty!");
            return false;
        }
        if (this.address == null || this.address.equals("")) {
            System.out.println("address is empty!");
            return false;
        }
        if (this.siteCompany == null || this.siteCompany.equals("")) {
            System.out.println("siteCompany is empty!");
            return false;
        }
        if (this.industryId == null || this.industryId.equals(0)) {
            System.out.println("industryId is empty!");
            return false;
        }
        if (this.status == null) {
            System.out.println("status is empty!");
            return false;
        }
        return true;
    }
}
