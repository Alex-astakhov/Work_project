package dataModels.employer;

import core.Constants;
import dataModels.DataGenerator;
import org.testng.annotations.DataProvider;

import java.util.Random;

/**
 * Created by Asta on 20.12.2016.
 * Класс содержит данные для регистрации работодателя, а также методы генерации и сравнения этих данных
 */
public class EmployerRegistrationData extends DataGenerator {
    private String firstName;
    private String lastName;
    private String userPhoneNumber;
    private String email;
    private String companyTitle;
    private String industryValue;
    private String staffNumberValue;
    private String bin;
    private String cityValue;
    private String site = "";
    private String companyPhone = "";
    private String description;

    public EmployerRegistrationData(String firstName, String lastName, String userPhoneNumber, String email,
                                    String companyTitle, String industryValue, String staffNumberValue,
                                    String bin, String cityValue, String site, String companyPhone, String description) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userPhoneNumber = userPhoneNumber;
        this.email = email;
        this.companyTitle = companyTitle;
        this.industryValue = industryValue;
        this.staffNumberValue = staffNumberValue;
        this.bin = bin;
        this.cityValue = cityValue;
        this.site = site;
        this.companyPhone = companyPhone;
        this.description = description;
    }

    public EmployerRegistrationData(String email, String bin) {
        Random random = new Random();
        String[] firstNames = {"Ахмед", "Василий", "Илларион", "Иван", "Алексей"};
        this.firstName = firstNames[random.nextInt(5)];
        String[] lastNames = {"Иванов", "Петров", "Сидоров", "Бондаренко", "Егермайстер"};
        this.lastName = lastNames[random.nextInt(5)];
        this.userPhoneNumber = generatePhoneNumber();
        this.email = email;
        String[] companyTitles = {"Google", "Яндекс", "Веселка", "Ромашка", "Nur.kz"};
        this.companyTitle = companyTitles[random.nextInt(5)];
        this.industryValue = String.valueOf(random.nextInt(27) + 1);
        this.staffNumberValue = String.valueOf(random.nextInt(7) + 1);
        this.bin = bin;
        this.cityValue = String.valueOf(random.nextInt(183) + 26402);
        String[] sites = {"https://google.com/", "https://yandex.ua/", "http://веселка.уа", "http://ромашка.орг", "http://nur.kz"};
        if (random.nextBoolean())
            this.site = sites[random.nextInt(5)];
        if (random.nextBoolean())
            this.companyPhone = generatePhoneNumber();
        this.description = "Описание компании по состоянию на " + getCurrentDateAndTimeString();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getCompanyTitle() {
        return companyTitle;
    }

    public String getIndustryValue() {
        return industryValue;
    }

    public String getStaffNumberValue() {
        return staffNumberValue;
    }

    public String getBin() {
        return bin;
    }

    public String getCityValue() {
        return cityValue;
    }

    public String getSite() {
        return site;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public String getDescription() {
        return description;
    }

    public EmployerRegistrationData setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public EmployerRegistrationData setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public EmployerRegistrationData setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
        return this;
    }

    public EmployerRegistrationData setCompanyTitle(String companyTitle) {
        this.companyTitle = companyTitle;
        return this;
    }

    public EmployerRegistrationData setIndustryValue(String industryValue) {
        this.industryValue = industryValue;
        return this;
    }

    public EmployerRegistrationData setStaffNumberValue(String staffNumberValue) {
        this.staffNumberValue = staffNumberValue;
        return this;
    }

    public EmployerRegistrationData setCityValue(String cityValue) {
        this.cityValue = cityValue;
        return this;
    }

    public EmployerRegistrationData setSite(String site) {
        this.site = site;
        return this;
    }

    public EmployerRegistrationData setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
        return this;
    }

    public EmployerRegistrationData setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public String toString() {
        return "EmployerRegistrationData{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userPhoneNumber='" + userPhoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", companyTitle='" + companyTitle + '\'' +
                ", industryValue='" + industryValue + '\'' +
                ", staffNumberValue='" + staffNumberValue + '\'' +
                ", bin='" + bin + '\'' +
                ", cityValue='" + cityValue + '\'' +
                ", site='" + site + '\'' +
                ", companyPhone='" + companyPhone + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @DataProvider
    public static Object[][] negative(){
        return new Object[][]{
                {new EmployerRegistrationData("", "12345678988")},
                {new EmployerRegistrationData("12345678988", "12345678988")},
                {new EmployerRegistrationData("email34234324", "12345678988")},
                {new EmployerRegistrationData("email12321312.mail.tu", "12345678988")},
                {new EmployerRegistrationData("email12312312@email@mail.ru", "12345678988")},
                {new EmployerRegistrationData("email23234@.ru", "12345678988")},
                {new EmployerRegistrationData("email2342323.ru", "12345678988")},
                {new EmployerRegistrationData("email543534.@mail.ru", "12345678988")},
                {new EmployerRegistrationData("email543534@.mailru", "12345678988")},
                {new EmployerRegistrationData(Constants.APPLICANT_REG_EMAIL, "")},
                {new EmployerRegistrationData(Constants.APPLICANT_REG_EMAIL, "1")},
                {new EmployerRegistrationData(Constants.APPLICANT_REG_EMAIL, "123456789")},
                {new EmployerRegistrationData(Constants.APPLICANT_REG_EMAIL, "1234567890")},
                {new EmployerRegistrationData(Constants.APPLICANT_REG_EMAIL, "1234567890D")},
                {new EmployerRegistrationData(Constants.APPLICANT_REG_EMAIL, "12345678-90")},
                {new EmployerRegistrationData(Constants.APPLICANT_REG_EMAIL, "123435678.90")},
                {new EmployerRegistrationData(Constants.APPLICANT_REG_EMAIL, "1234567890123")},
                {new EmployerRegistrationData(Constants.APPLICANT_REG_EMAIL, "412345678900").setFirstName("")},
                {new EmployerRegistrationData(Constants.APPLICANT_REG_EMAIL, "412345678900").setLastName("")},
                {new EmployerRegistrationData(Constants.APPLICANT_REG_EMAIL, "412345678900").setUserPhoneNumber("")},
                {new EmployerRegistrationData(Constants.APPLICANT_REG_EMAIL, "412345678900").setCompanyTitle("")},
                {new EmployerRegistrationData(Constants.APPLICANT_REG_EMAIL, "412345678900").setIndustryValue("0")},
                {new EmployerRegistrationData(Constants.APPLICANT_REG_EMAIL, "412345678900").setStaffNumberValue("0")},
                {new EmployerRegistrationData(Constants.APPLICANT_REG_EMAIL, "412345678900").setCityValue("0")},
                {new EmployerRegistrationData(Constants.APPLICANT_REG_EMAIL, "412345678900").setSite("http://")},
                {new EmployerRegistrationData(Constants.APPLICANT_REG_EMAIL, "412345678900").setSite("http://site")},
                {new EmployerRegistrationData(Constants.APPLICANT_REG_EMAIL, "412345678900").setSite("https://сайт")},
                {new EmployerRegistrationData(Constants.APPLICANT_REG_EMAIL, "412345678900").setSite("https://site.")},
                {new EmployerRegistrationData(Constants.APPLICANT_REG_EMAIL, "412345678900").setSite("http://сайт.")},
                {new EmployerRegistrationData(Constants.APPLICANT_REG_EMAIL, "412345678900").setSite("www.site.com")},
                {new EmployerRegistrationData(Constants.APPLICANT_REG_EMAIL, "412345678900").setSite("site")},
                {new EmployerRegistrationData(Constants.APPLICANT_REG_EMAIL, "412345678900").setSite("сайт")},
                {new EmployerRegistrationData(Constants.APPLICANT_REG_EMAIL, "412345678900").setSite("/site/")},
                {new EmployerRegistrationData(Constants.APPLICANT_REG_EMAIL, "412345678900").setDescription("")},
        };
    }

}
