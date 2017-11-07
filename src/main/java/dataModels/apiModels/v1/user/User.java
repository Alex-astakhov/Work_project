package dataModels.apiModels.v1.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import dataModels.apiModels.v1.ApiData;
import dataModels.apiModels.v1.MoreInformation;
import dataModels.apiModels.v1.listEntities.DefaultEntity;
import dataModels.applicant.cvParts.DriversData;
import dataModels.applicant.cvParts.SocialNetData;
import dataModels.applicant.cvParts.UserData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Asta on 12.07.2017.
 */
public class User implements ApiData {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("first_name")
    @Expose
    public String firstName;
    @SerializedName("last_name")
    @Expose
    public String lastName;
    @SerializedName("middle_name")
    @Expose
    public String middleName;
    @SerializedName("last_visit")
    @Expose
    public String lastVisit;
    @SerializedName("email_confirmed")
    @Expose
    public String emailConfirmed;
    @SerializedName("phone")
    @Expose
    public String phone;
    @SerializedName("city_id")
    @Expose
    public Integer cityId;
    @SerializedName("birthday")
    @Expose
    public String birthday;
    @SerializedName("gender")
    @Expose
    public Integer gender;
    @SerializedName("more_information")
    @Expose
    public List<MoreInformation> moreInformation;
    @SerializedName("profile_info")
    @Expose
    public ProfileInfo profileInfo;
    @SerializedName("avatar")
    @Expose
    public String avatar;
    @SerializedName("driver_licence_categories")
    @Expose
    public List<DefaultEntity> driverLicenceCategories = null;
    @SerializedName("social_networks")
    @Expose
    public List<DefaultEntity> socialNetworks = null;

    @Override
    public boolean validate() {
        System.out.println("\nWrong type of validation method selected!\n");
        return false;
    }

    public boolean validate(boolean isApplicant){
        if (this.id == null || this.id.equals(0)){
            System.out.println("id is empty!");
            return false;
        }
        if (this.email == null || this.email.equals("")){
            System.out.println("email is empty!");
            return false;
        }
        if (this.firstName == null || this.firstName.equals("")){
            System.out.println("firstName is empty!");
            return false;
        }
        if (this.lastName == null || this.lastName.equals("")){
            System.out.println("lastName is empty!");
            return false;
        }

        if (this.lastVisit == null || this.lastVisit.equals("")){
            System.out.println("lastVisit is empty!");
            return false;
        }
        if (this.emailConfirmed == null || this.emailConfirmed.equals("")){
            System.out.println("emailConfirmed is empty!");
            return false;
        }
        if (this.phone == null || this.phone.equals("")){
            System.out.println("phone is empty!");
            return false;
        }
        if (isApplicant) {
            if (this.profileInfo == null || !this.profileInfo.validate()) {
                System.out.println("profileInfo is empty!");
                return false;
            }
            if (this.avatar == null || this.avatar.equals("")) {
                System.out.println("avatar is empty!");
                return false;
            }
            if (this.cityId == null || this.cityId.equals(0)) {
                System.out.println("cityId is empty!");
                return false;
            }
            if (this.middleName == null || this.middleName.equals("")) {
                System.out.println("middleName is empty!");
                return false;
            }
            if (this.birthday == null || this.birthday.equals("") || this.birthday.equals("0000-00-00")){
                System.out.println("birthday is empty!");
                return false;
            }
            if (this.driverLicenceCategories == null || this.driverLicenceCategories.size() == 0){
                System.out.println("driverLicenceCategories is empty!");
                return false;
            }
            for (DefaultEntity d: this.driverLicenceCategories) {
                if (!d.validate()){
                    System.out.println("driverLicenceCategories is not valid! " + d);
                    return false;
                }
            }
            if (this.socialNetworks == null || this.socialNetworks.size() == 0){
                System.out.println("socialNetworks is empty!");
                return false;
            }
            for (DefaultEntity s: this.socialNetworks) {
                if (!s.validate()){
                    System.out.println("socialNetworks is not valid! " + s);
                    return false;
                }
            }
        }
        else {
            if (this.birthday == null || this.birthday.equals("") || !this.birthday.equals("0000-00-00")){
                System.out.println("birthday is empty or != '0000-00-00'!");
                return false;
            }
            if (this.moreInformation == null){
                System.out.println("moreInformation is not valid!");
                return false;
            }
            for (MoreInformation m: moreInformation) {
                if (!m.validate()){
                    System.out.println("moreInformation is not valid!\n" + m);
                    return false;
                }
            }
        }
        return true;
    }

    public UserData getUserData(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        int day = 0;
        int month = 0;
        String year = null;
        try {
            date = format.parse(this.birthday);
            day = Integer.parseInt(new SimpleDateFormat("dd").format(date));
            month = Integer.parseInt(new SimpleDateFormat("MM").format(date));
            year = new SimpleDateFormat("yyyy").format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String skype, phone, additionalPhone;
        if (this.profileInfo.skype == null)
            skype = "";
        else
            skype = this.profileInfo.skype;
        if (this.phone == null)
            phone = "";
        else
            phone = this.phone;
        if (this.profileInfo.additionalPhone == null)
            additionalPhone = "";
        else
            additionalPhone = this.profileInfo.additionalPhone;
        boolean gender = this.gender.equals(1);
        String cityRegion = null;
        if (!this.profileInfo.cityRegionId.equals(0))
            cityRegion = this.profileInfo.cityRegionId.toString();
        return new UserData(this.firstName, this.lastName, this.middleName, day, month, year, gender, this.cityId.toString(),
                cityRegion, skype, phone, additionalPhone);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastVisit='" + lastVisit + '\'' +
                ", emailConfirmed='" + emailConfirmed + '\'' +
                ", phone='" + phone + '\'' +
                ", cityId=" + cityId +
                ", birthday='" + birthday + '\'' +
                ", gender=" + gender +
                ", moreInformation=" + moreInformation +
                ", profileInfo=" + profileInfo +
                ", avatar='" + avatar + '\'' +
                ", driverLicenceCategories=" + driverLicenceCategories +
                ", socialNetworks=" + socialNetworks +
                '}';
    }

    public DriversData getDriversData(){
        Set<Character> result = new HashSet<>();
        if (this.driverLicenceCategories != null) {
            for (DefaultEntity e : this.driverLicenceCategories) {
                result.add(e.title.charAt(0));
            }
        }
        boolean hasCar = this.profileInfo.hasCar.equals("1");
        return new DriversData(result, hasCar);
    }

    public SocialNetData getSocialNetData(){
        Map<String, String> result = new HashMap<>();
        if (this.socialNetworks != null && this.socialNetworks.size() > 0)
            this.socialNetworks.forEach((s) -> result.put(s.id.toString(), s.title));
        return new SocialNetData(result);
    }
}
