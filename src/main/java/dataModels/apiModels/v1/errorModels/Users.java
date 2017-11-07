package dataModels.apiModels.v1.errorModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import dataModels.DataGenerator;

import java.util.List;

public class Users {
    @SerializedName("gender")
    @Expose
    public List<String> gender = null;
    @SerializedName("first_name")
    @Expose
    public List<String> firstName = null;
    @SerializedName("last_name")
    @Expose
    public List<String> lastName = null;
    @SerializedName("birth_day")
    @Expose
    public List<String> birthDay = null;
    @SerializedName("birth_month")
    @Expose
    public List<String> birthMonth = null;
    @SerializedName("birth_year")
    @Expose
    public List<String> birthYear = null;
    @SerializedName("city_id")
    @Expose
    public List<String> cityId = null;
    @SerializedName("birthday")
    @Expose
    public List<String> birthday = null;

    public Users(List<String> gender, List<String> firstName, List<String> lastName, List<String> birthDay,
                              List<String> birthMonth, List<String> birthYear, List<String> cityId, List<String> birthday) {
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.birthMonth = birthMonth;
        this.birthYear = birthYear;
        this.cityId = cityId;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            System.out.println("Object is null");
            return false;
        }
        if (!Users.class.isAssignableFrom(obj.getClass())) {
            System.out.println("Object is other class");
            return false;
        }
        final Users other = (Users) obj;
        if ((this.gender == null) ? (other.gender != null) :
                !DataGenerator.collectionStrictEquals(this.gender, other.gender)) {
            System.out.println("Expected gender: " + this.gender + ", Actual gender: " + other.gender);
            return false;
        }
        if ((this.firstName == null) ? (other.firstName != null) :
                !DataGenerator.collectionStrictEquals(this.firstName, other.firstName)) {
            System.out.println("Expected firstName: " + this.firstName + ", Actual firstName: " + other.firstName);
            return false;
        }
        if ((this.lastName == null) ? (other.lastName != null) :
                !DataGenerator.collectionStrictEquals(this.lastName, other.lastName)) {
            System.out.println("Expected lastName: " + this.lastName + ", Actual lastName: " + other.lastName);
            return false;
        }
        if ((this.birthDay == null) ? (other.birthDay != null) :
                !DataGenerator.collectionStrictEquals(this.birthDay, other.birthDay)) {
            System.out.println("Expected birthDay: " + this.birthDay + ", Actual birthDay: " + other.birthDay);
            return false;
        }
        if ((this.birthMonth == null) ? (other.birthMonth != null) :
                !DataGenerator.collectionStrictEquals(this.birthMonth, other.birthMonth)) {
            System.out.println("Expected birthMonth: " + this.birthMonth + ", Actual birthMonth: " + other.birthMonth);
            return false;
        }
        if ((this.birthYear == null) ? (other.birthYear != null) :
                !DataGenerator.collectionStrictEquals(this.birthYear, other.birthYear)) {
            System.out.println("Expected birthYear: " + this.birthYear + ", Actual birthYear: " + other.birthYear);
            return false;
        }
        if ((this.cityId == null) ? (other.cityId != null) :
                !DataGenerator.collectionStrictEquals(this.cityId, other.cityId)) {
            System.out.println("Expected cityId: " + this.cityId + ", Actual cityId: " + other.cityId);
            return false;
        }
        if ((this.birthday == null) ? (other.birthday != null) :
                !DataGenerator.collectionStrictEquals(this.birthday, other.birthday)) {
            System.out.println("Expected birthday: " + this.birthday + ", Actual birthday: " + other.birthday);
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UserAboutErrorData{" +
                "gender=" + gender +
                ", firstName=" + firstName +
                ", lastName=" + lastName +
                ", birthDay=" + birthDay +
                ", birthMonth=" + birthMonth +
                ", birthYear=" + birthYear +
                ", cityId=" + cityId +
                ", birthday=" + birthday +
                '}';
    }
}
