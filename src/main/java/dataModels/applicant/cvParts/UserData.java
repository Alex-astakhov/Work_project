package dataModels.applicant.cvParts;


import dataModels.DataGenerator;
import dataModels.apiModels.v1.errorModels.UserAboutErrorData;
import dataModels.apiModels.v1.errorModels.Users;
import dataModels.apiModels.v1.errorModels.UsersInfo;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by Alex Astakhov on 27.11.2016.
 * Класс содержит персональные данные соискателя, а также методы генерации и сравнения этих данных
 */
public class UserData extends DataGenerator {
    private String firstName;
    private String lastName;
    private String middleName;
    private int birthDayIndex;
    private int birthMonthIndex;
    private String birthYearValue;
    private boolean gender;
    private String cityValue;
    private String cityRegionValue;
    private String skype = "";
    private String phoneNumber = "";
    private String additionPhoneNumber = "";

    public UserData(String firstName, String lastName, String middleName, int birthDayIndex,
                    int birthMonthIndex, String birthYearValue, boolean gender, String cityValue, String cityRegionValue,
                    String skype, String phoneNumber, String additionPhoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.birthDayIndex = birthDayIndex;
        this.birthMonthIndex = birthMonthIndex;
        this.birthYearValue = birthYearValue;
        this.gender = gender;
        this.cityValue = cityValue;
        this.cityRegionValue = cityRegionValue;
        this.skype = skype;
        this.phoneNumber = phoneNumber;
        this.additionPhoneNumber = additionPhoneNumber;
    }

    public UserData() {
        Random random = new Random();
        String[] firstNames = {"Александр", "Василий", "Ярослав", "Вероника", "Лидия"};
        this.firstName = randomItemFromArray(firstNames);
        String[] lastNames = {"Иванов", "Бахтарадзе", "Бекмамбетов", "Андрулис", "Харитонова"};
        this.lastName = randomItemFromArray(lastNames);
        String[] middleNames = {"Олегович", "Асланович", "Петровна", "Георгиевна", "Витальевич"};
        this.middleName = randomItemFromArray(middleNames);
        this.birthDayIndex = random.nextInt(28) + 1;
        this.birthMonthIndex = random.nextInt(12) + 1;
        this.birthYearValue = generateYearValue(18);
        this.gender = random.nextBoolean();
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
        if (random.nextBoolean())
            this.skype = "skype " + getCurrentDateAndTimeString();
        if (random.nextBoolean())
            this.phoneNumber = generatePhoneNumber();
        if (random.nextBoolean())
            this.additionPhoneNumber = generatePhoneNumber();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
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

    public boolean isGender() {
        return gender;
    }

    public int getGenderAsInt() {
        if (gender)
            return 1;
        else
            return 2;
    }

    public String getCityValue() {
        return cityValue;
    }

    public String getCityRegionValue() {
        return cityRegionValue;
    }

    public String getSkype() {
        return skype;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAdditionPhoneNumber() {
        return additionPhoneNumber;
    }

    public UserData setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserData setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserData setCityValue(String cityValue) {
        this.cityValue = cityValue;
        return this;
    }

    public UserData setCityRegionValue(String cityRegionValue) {
        this.cityRegionValue = cityRegionValue;
        return this;
    }

    public UserData setBirthDayIndex(int birthDayIndex) {
        this.birthDayIndex = birthDayIndex;
        return this;
    }

    public UserData setBirthMonthIndex(int birthMonthIndex) {
        this.birthMonthIndex = birthMonthIndex;
        return this;
    }

    public UserData setBirthYearValue(String birthYearValue) {
        this.birthYearValue = birthYearValue;
        return this;
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null) {
            System.out.println("Object is null");
            return false;
        }
        if (!UserData.class.isAssignableFrom(obj.getClass())) {
            System.out.println("Object is other class");
            return false;
        }
        final UserData other = (UserData) obj;
        if ((this.firstName == null) ? (other.firstName != null) : !this.firstName.equals(other.firstName)) {
            System.out.println("Expected firstName: " + this.firstName + ", Actual firstName: " + other.firstName);
            return false;
        }
        if ((this.lastName == null) ? (other.lastName != null) : !this.lastName.equals(other.lastName)) {
            System.out.println("Expected lastName: " + this.getLastName() + ", Actual lastName: " + other.getLastName());
            return false;
        }

        if ((this.middleName == null) ? (other.middleName != null) : !this.middleName.equals(other.middleName)) {
            System.out.println("Expected middleName: " + this.middleName + ", Actual middleName: " + other.middleName);
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
            System.out.println("Expected birthYear: " + this.birthYearValue+ ", Actual birthYear: " + other.birthYearValue);
            return false;
        }

        if (this.gender != other.gender) {
            System.out.println("Expected gender: " + this.gender + ", Actual gender: " + other.gender);
            return false;
        }

        if ((this.cityValue == null) ? (other.cityValue != null) : !this.cityValue.equals(other.cityValue)) {
            System.out.println("Expected city #: " + this.cityValue + ", Actual city #: " + other.cityValue);
            return false;
        }

        if ((this.cityRegionValue == null) ? (other.cityRegionValue != null) : !this.cityRegionValue.equals(other.cityRegionValue)) {
            System.out.println("Expected cityRegionValue #: " + this.cityRegionValue + ", Actual cityRegionValue #: " + other.cityRegionValue);
            return false;
        }

        if ((this.skype == null) ? (other.skype != null) : !this.skype.equals(other.skype)) {
            System.out.println("Expected skype: " + this.skype + ", Actual skype: " + other.skype);
            return false;
        }

        if ((this.phoneNumber == null) ? (other.phoneNumber != null) : !this.phoneNumber.equals(other.phoneNumber)) {
            System.out.println("Expected phoneNumber: " + this.getPhoneNumber() + ", Actual phoneNumber: " + other.getPhoneNumber());
            return false;
        }

        if ((this.additionPhoneNumber == null) ? (other.additionPhoneNumber != null) : !this.additionPhoneNumber.equals(other.additionPhoneNumber)) {
            System.out.println("Expected additionPhoneNumber: " + this.additionPhoneNumber + ", Actual additionPhoneNumber: " + other.additionPhoneNumber);
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", birthDayIndex=" + birthDayIndex +
                ", birthMonthIndex=" + birthMonthIndex +
                ", birthYearValue='" + birthYearValue + '\'' +
                ", gender=" + gender +
                ", cityValue='" + cityValue + '\'' +
                ", cityRegionValue='" + cityRegionValue + '\'' +
                ", skype='" + skype + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", additionPhoneNumber='" + additionPhoneNumber + '\'' +
                '}';
    }

    @DataProvider
    public static Object[][] negative(){
        return new Object[][]{
                {new UserData().setFirstName("")},
                {new UserData().setFirstName("       ")},
                {new UserData().setLastName("")},
                {new UserData().setLastName("      ")},
                {new UserData().setCityValue("")}
        };
    }

    @DataProvider
    public static Object[][] negativeForApi(){
        return new Object[][]{
                {new UserData().setFirstName(""),
                        new UserAboutErrorData(new Users(null, new ArrayList<>(Arrays.asList("Заполните поле Имя")),
                                null, null, null, null, null, null), null)},

                {new UserData().setFirstName("       "),
                        new UserAboutErrorData(new Users(null, new ArrayList<>(Arrays.asList("Заполните поле Имя")),
                                null, null, null, null, null, null), null)},

                {new UserData().setLastName("      "),
                        new UserAboutErrorData(new Users(null, null, new ArrayList<>(Arrays.asList("Заполните поле Фамилия")),
                                null, null, null, null, null), null)},

                {new UserData().setLastName("      "),
                        new UserAboutErrorData(new Users(null, null, new ArrayList<>(Arrays.asList("Заполните поле Фамилия")),
                                null, null, null, null, null), null)},

                {new UserData().setCityValue("0"),
                        new UserAboutErrorData(new Users(null, null, null,
                                null, null, null, new ArrayList<>(Arrays.asList("Выберите город")),
                                null), null)},

                {new UserData().setCityRegionValue("100"),
                        new UserAboutErrorData(null,
                                new UsersInfo(new ArrayList<>(Arrays.asList("Не найдено совпадений в списке регионов города"))))},

                {new UserData().setBirthDayIndex(0),
                        new UserAboutErrorData(new Users(null, null, null,
                                new ArrayList<>(Arrays.asList("")), new ArrayList<>(Arrays.asList("")), new ArrayList<>(Arrays.asList("")),
                                null, new ArrayList<>(Arrays.asList("Заполните дату рождения"))), null)},

                {new UserData().setBirthMonthIndex(0),
                        new UserAboutErrorData(new Users(null, null, null,
                                new ArrayList<>(Arrays.asList("")), new ArrayList<>(Arrays.asList("")), new ArrayList<>(Arrays.asList("")),
                                null, new ArrayList<>(Arrays.asList("Заполните дату рождения"))), null)},

                {new UserData().setBirthYearValue("0"),
                        new UserAboutErrorData(new Users(null, null, null,
                                new ArrayList<>(Arrays.asList("")), new ArrayList<>(Arrays.asList("")), new ArrayList<>(Arrays.asList("")),
                                null, new ArrayList<>(Arrays.asList("Заполните дату рождения"))), null)},
        };
    }
}
