package dataModels.applicant;

import core.Constants;
import dataModels.DataGenerator;
import org.testng.annotations.DataProvider;

import java.util.Random;

/**
 * Created by Asta on 16.12.2016.
 * Класс содержит данные для регистрации соискателя, а также методы генерации и сравнения этих данных
 */
public class ApplicantRegistrationData extends DataGenerator {
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    private String[] firstNames = {"Ахмед", "Василий", "Илларион", "Иван", "Алексей"};
    private String[] lastNames = {"Иванов", "Петров", "Сидоров", "Бондаренко", "Егермайстер"};

    public ApplicantRegistrationData(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public ApplicantRegistrationData(String email, String password) {
        Random random = new Random();
        this.firstName = firstNames[random.nextInt(5)];
        this.lastName = lastNames[random.nextInt(5)];
        this.email = email;
        this.password = password;
    }

    public ApplicantRegistrationData(String password) {
        Random random = new Random();
        this.firstName = firstNames[random.nextInt(5)];
        this.lastName = lastNames[random.nextInt(5)];
        this.email = "applicant.test." + getCurrentDateString().replace("/", "") + "@testmail.com";
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public ApplicantRegistrationData setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ApplicantRegistrationData setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ApplicantRegistrationData setEmail(String email) {
        this.email = email;
        return this;
    }

    public ApplicantRegistrationData setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public String toString() {
        return "ApplicantRegistrationData{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @DataProvider
    public static Object[][] negative(){
        return new Object[][]{
                {new ApplicantRegistrationData(Constants.APPLICANT_REG_EMAIL, "qwerty123").setFirstName("")},
                {new ApplicantRegistrationData(Constants.APPLICANT_REG_EMAIL, "qwerty123").setLastName("")},
                {new ApplicantRegistrationData(Constants.APPLICANT_REG_EMAIL, "qwerty123").setEmail("")},
                {new ApplicantRegistrationData(Constants.APPLICANT_REG_EMAIL, "qwerty123").setPassword("")},
                {new ApplicantRegistrationData(Constants.APPLICANT_REG_EMAIL, "qwerty123").setPassword("q")},
                {new ApplicantRegistrationData(Constants.APPLICANT_REG_EMAIL, "qwerty123").setPassword("qw")},
                {new ApplicantRegistrationData(Constants.APPLICANT_REG_EMAIL, "qwerty123").setPassword("qwe")},
                {new ApplicantRegistrationData(Constants.APPLICANT_REG_EMAIL, "qwerty123").setPassword("qwer")},
                {new ApplicantRegistrationData(Constants.APPLICANT_REG_EMAIL, "qwerty123").setPassword("qwert")},
                {new ApplicantRegistrationData("", "qwerty123")},
                {new ApplicantRegistrationData("56375634745", "qwerty123")},
                {new ApplicantRegistrationData("email4534534", "qwerty123")},
                {new ApplicantRegistrationData("email12321312.mail.tu", "qwerty123")},
                {new ApplicantRegistrationData("email12312312@email@mail.ru", "qwerty123")},
                {new ApplicantRegistrationData("email23234@.ru", "qwerty123")},
                {new ApplicantRegistrationData("email2342323.ru", "qwerty123")},
                {new ApplicantRegistrationData("email543534.@mail.ru", "qwerty123")},
                {new ApplicantRegistrationData("email543534@.mailru", "qwerty123")},
                {new ApplicantRegistrationData(Constants.APPLICANT_REG_EMAIL, "")},
                {new ApplicantRegistrationData(Constants.APPLICANT_REG_EMAIL, "q")},
                {new ApplicantRegistrationData(Constants.APPLICANT_REG_EMAIL, "qwe")},
                {new ApplicantRegistrationData(Constants.APPLICANT_REG_EMAIL, "qwer")},
                {new ApplicantRegistrationData(Constants.APPLICANT_REG_EMAIL, "qwert")}
        };
    }
}
