package dataModels.employer;

import dataModels.DataGenerator;
import dataModels.employer.vacancyParts.VacancyPropertiesData;

/**
 * Created by Asta on 09.03.2017.
 * Класс содержит данные пользовательские данные работодателя, а также методы генерации и сравнения этих данных
 */
public class UserData extends DataGenerator {
    private String firstName;
    private String lastName;
    private String phone;

    public UserData(String firstName, String lastName, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public UserData() {
        String[] firstNames = {"Ахмед", "Василий", "Илларион", "Иван", "Алексей"};
        this.firstName = randomItemFromArray(firstNames);
        String[] lastNames = {"Иванов", "Петров", "Сидоров", "Бондаренко", "Егермайстер"};
        this.lastName = randomItemFromArray(lastNames);
        this.phone = generatePhoneNumber();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
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
            System.out.println("Expected lastName: " + this.lastName + ", Actual lastName: " + other.lastName);
            return false;
        }
        if ((this.phone == null) ? (other.phone != null) : !this.phone.equals(other.phone)) {
            System.out.println("Expected phone: " + this.phone + ", Actual phone: " + other.phone);
            return false;
        }
        return true;
    }
}
