package dataModels.applicant;

import core.Constants;
import dataModels.DataGenerator;
import org.testng.annotations.DataProvider;

import java.util.Random;

/**
 * Created by Asta on 19.12.2016.
 * Класс содержит данные для заполнения личных данных при регистрации, а также методы генерации и сравнения этих данных
 */
public class CVBuilderData extends DataGenerator {
    private String firstName;
    private String lastName;
    private String email;
    private int birthDayIndex;
    private int birthMonthIndex;
    private String birthYearValue;
    private boolean man;
    private String cityValue;
    private String cityRegionValue;
    private String phoneNumber = "";

    public CVBuilderData(ApplicantRegistrationData regData) {
        this.firstName = regData.getFirstName();
        this.lastName = regData.getLastName();
        this.email = regData.getEmail();
        Random random = new Random();
        this.birthDayIndex = random.nextInt(28) + 1;
        this.birthMonthIndex = random.nextInt(12) + 1;
        this.birthYearValue = generateYearValue(18);
        String[] cityValues = {"26402", "26403", "26313", "26394", generateCityValue()};
        String[] almatyRegions = {"1", "2", "3", "4", "5", "6", "7", "8"};
        String[] astanaRegions = {"9", "10", "11"};
        String[] karagandaRegions = {"12", "13"};
        String[] shymkentRegions = {"14", "15", "16"};
        this.cityValue = randomItemFromArray(cityValues);
        switch (this.cityValue){
            case "26402": this.cityRegionValue = randomItemFromArray(almatyRegions); break;
            case "26403": this.cityRegionValue = randomItemFromArray(astanaRegions); break;
            case "26313": this.cityRegionValue = randomItemFromArray(karagandaRegions); break;
            case "26394": this.cityRegionValue = randomItemFromArray(shymkentRegions); break;
            default: this.cityRegionValue = null;
        }
        this.man = random.nextBoolean();
        if (random.nextBoolean())
            this.phoneNumber = generatePhoneNumber();
    }

    public CVBuilderData(String firstName, String lastName, String email, int birthDayIndex, int birthMonthIndex, String birthYearValue,
                         boolean man, String cityValue, String cityRegionValue, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDayIndex = birthDayIndex;
        this.birthMonthIndex = birthMonthIndex;
        this.birthYearValue = birthYearValue;
        this.man = man;
        this.cityValue = cityValue;
        this.cityRegionValue = cityRegionValue;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public int getBirthDayIndex() {
        return birthDayIndex;
    }

    public int getBirthMonthIndex() {
        return birthMonthIndex;
    }

    public String getBirthYearValue() {
        return birthYearValue;
    }

    public boolean isMan() {
        return man;
    }

    public String getCityValue() {
        return cityValue;
    }

    public String getCityRegionValue() {
        return cityRegionValue;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public CVBuilderData setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public CVBuilderData setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public CVBuilderData setBirthDayIndex(int birthDayIndex) {
        this.birthDayIndex = birthDayIndex;
        return this;
    }

    public CVBuilderData setBirthMonthIndex(int birthMonthIndex) {
        this.birthMonthIndex = birthMonthIndex;
        return this;
    }

    public CVBuilderData setBirthYearValue(String birthYearValue) {
        this.birthYearValue = birthYearValue;
        return this;
    }

    public CVBuilderData setMan(boolean man) {
        this.man = man;
        return this;
    }

    public CVBuilderData setCityValue(String cityValue) {
        this.cityValue = cityValue;
        return this;
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null) {
            System.out.println("Object is null");
            return false;
        }
        if (!CVBuilderData.class.isAssignableFrom(obj.getClass())) {
            System.out.println("Object is other class");
            return false;
        }
        final CVBuilderData other = (CVBuilderData) obj;
        if ((this.firstName == null) ? (other.firstName != null) : !this.firstName.equals(other.firstName)) {
            System.out.println("Expected firstName: " + this.firstName + ", Actual firstName: " + other.firstName);
            return false;
        }
        if ((this.lastName == null) ? (other.lastName != null) : !this.lastName.equals(other.lastName)) {
            System.out.println("Expected lastName: " + this.lastName + ", Actual lastName: " + other.lastName);
            return false;
        }
        if ((this.email == null) ? (other.email != null) : !this.email.equals(other.email)) {
            System.out.println("Expected email: " + this.email + ", Actual email: " + other.email);
            return false;
        }
        if ((this.birthDayIndex == 0) ? (other.birthDayIndex != 0) : !(this.birthDayIndex == other.birthDayIndex)) {
            System.out.println("Expected birth day: " + this.birthDayIndex + ", Actual birth day: " + other.birthDayIndex);
            return false;
        }

        if ((this.birthMonthIndex == 0) ? (other.birthMonthIndex != 0) : !(this.birthMonthIndex == other.birthMonthIndex)) {
            System.out.println("Expected birth month: " + this.birthMonthIndex + ", Actual birth month: " + other.birthMonthIndex);
            return false;
        }

        if ((this.birthYearValue == null) ? (other.birthYearValue != null) : !this.birthYearValue.equals(other.birthYearValue)) {
            System.out.println("Expected birthYear: " + this.birthYearValue + ", Actual birthYear: " + other.birthYearValue);
            return false;
        }

        if (this.man != other.man) {
            System.out.println("Expected gender: " + this.man + ", Actual gender: " + other.man);
            return false;
        }

        if ((this.cityValue == null) ? (other.cityValue != null) : !this.cityValue.equals(other.cityValue)) {
            System.out.println("Expected city #: " + this.cityValue + ", Actual city #: " + other.cityValue);
            return false;
        }

       /* if ((this.cityRegionValue == null) ? (other.cityRegionValue != null) : !this.cityRegionValue.equals(other.cityRegionValue)) {
            System.out.println("Expected cityRegionValue #: " + this.cityRegionValue + ", Actual cityRegionValue #: " + other.cityRegionValue);
            return false;
        }*/

        if ((this.phoneNumber == null) ? (other.phoneNumber != null) : !this.phoneNumber.equals(other.phoneNumber)) {
            System.out.println("Expected phone #: " + this.phoneNumber + ", Actual phone #: " + other.phoneNumber);
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CVBuilderData{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", birthDayIndex=" + birthDayIndex +
                ", birthMonthIndex=" + birthMonthIndex +
                ", birthYearValue='" + birthYearValue + '\'' +
                ", man=" + man +
                ", cityValue='" + cityValue + '\'' +
                ", cityRegionValue='" + cityRegionValue + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    @DataProvider
    public static Object[][] negative(){
        ApplicantRegistrationData regData = new ApplicantRegistrationData(Constants.APPLICANT_REG_EMAIL, Constants.PASSWORD);
        return new Object[][]{
                {new CVBuilderData(regData).setBirthDayIndex(30).setBirthMonthIndex(2)},
                {new CVBuilderData(regData).setFirstName("")},
                {new CVBuilderData(regData).setLastName("")},
                {new CVBuilderData(regData).setCityValue("")}
        };
    }
}
