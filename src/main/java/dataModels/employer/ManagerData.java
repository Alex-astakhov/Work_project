package dataModels.employer;

import dataModels.DataGenerator;

/**
 * Created by Asta on 13.03.2017.
 * Класс содержит данные о менеджере работодателя, а также методы генерации и сравнения этих данных
 */
public class ManagerData extends DataGenerator {
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String position;
    private String region;

    public ManagerData(String email, String firstName, String lastName, String phone, String position, String region) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.position = position;
        this.region = region;
    }

    public ManagerData(String email) {
        this.email = email;
        String[] firstNames = {"Ахмед", "Василий", "Илларион", "Иван", "Алексей"};
        this.firstName = randomItemFromArray(firstNames);
        String[] lastNames = {"Иванов", "Петров", "Сидоров", "Бондаренко", "Егермайстер"};
        this.lastName = randomItemFromArray(lastNames);
        this.phone = generatePhoneNumber();
        String[] positions = {"Бухгалтер", "Экономист", "Директор по персоналу", "PHP-developer", "Водитель экскаватора"};
        this.position = randomItemFromArray(positions);
        String[] regions = {"Алматы", "Астана", "Караганда", "Усть-Каменогорск", "Нижний Тагил"};
        this.region = randomItemFromArray(regions);
    }

    public String getEmail() {
        return email;
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

    public String getPosition() {
        return position;
    }

    public String getRegion() {
        return region;
    }

    @Override
    public String toString() {
        return "ManagerData{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", position='" + position + '\'' +
                ", region='" + region + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            System.out.println("Object is null");
            return false;
        }
        if (!ManagerData.class.isAssignableFrom(obj.getClass())) {
            System.out.println("Object is other class");
            return false;
        }
        final ManagerData other = (ManagerData) obj;
        if ((this.email == null) ? (other.email != null) : !this.email.equals(other.email)) {
            System.out.println("Expected email: " + this.email + ", Actual email: " + other.email);
            return false;
        }
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
        if ((this.position == null) ? (other.position != null) : !this.position.equals(other.position)) {
            System.out.println("Expected position: " + this.position + ", Actual position: " + other.position);
            return false;
        }
        if ((this.region == null) ? (other.region != null) : !this.region.equals(other.region)) {
            System.out.println("Expected region: " + this.region + ", Actual region: " + other.region);
            return false;
        }
        return true;
    }
}
