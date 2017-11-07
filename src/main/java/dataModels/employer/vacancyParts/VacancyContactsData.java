package dataModels.employer.vacancyParts;

import dataModels.DataGenerator;

import java.util.Random;

/**
 * Created by Asta on 31.01.2017.
 * Класс содержит контактные данные вакансии, а также методы генерации и сравнения этих данных
 */
public class VacancyContactsData extends DataGenerator {
    private boolean addContacts;
    private String contactPerson;
    private String contactPhoneNumber;
    private boolean addAddress;
    private String contactCityValue = "0";
    private String street= "";
    private String building = "";
    private String office = "";

    public VacancyContactsData(boolean addContacts, String contactPerson, String contactNumber, boolean addAddress,
                               String contactCityValue, String street, String building, String office) {
        this.addContacts = addContacts;
        this.contactPerson = contactPerson;
        this.contactPhoneNumber = contactNumber;
        this.addAddress = addAddress;
        this.contactCityValue = contactCityValue;
        this.street = street;
        this.building = building;
        this.office = office;
    }

    public VacancyContactsData() {
        Random random = new Random();
        this.addContacts = random.nextBoolean();
        if (this.addContacts){
            String[] contactPersons = {"Александр Иванов", "Василий Бахтарадзе", "Ярослав Бекмамбетов", "Вероника Андрулис", "Лидия Харитонова"};
            this.contactPerson = randomItemFromArray(contactPersons);
            this.contactPhoneNumber = generatePhoneNumber();
        }
        this.addAddress = random.nextBoolean();
        if (this.addAddress){
            this.contactCityValue = generateCityValue();
            String[] streets = {"проспект Победы", "ул. Тюратамская", "бул. Дружюы народов", "переулок Нефтянников", "Колокольный проезд"};
            this.street = randomItemFromArray(streets);
            this.building = (random.nextInt(195) + 1) + "А";
            this.office = String.valueOf(random.nextInt(200) + 1);
        }
    }

    public boolean isAddContacts() {
        return addContacts;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public boolean isAddAddress() {
        return addAddress;
    }

    public String getAddressCityValue() {
        return contactCityValue;
    }

    public String getAddressStreet() {
        return street;
    }

    public String getAddressBuilding() {
        return building;
    }

    public String getAddressOffice() {
        return office;
    }

    public VacancyContactsData setAddContacts(boolean addContacts) {
        this.addContacts = addContacts;
        return this;
    }

    public VacancyContactsData setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
        return this;
    }

    public VacancyContactsData setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
        return this;
    }

    public VacancyContactsData setAddAddress(boolean addAddress) {
        this.addAddress = addAddress;
        return this;
    }

    public VacancyContactsData setContactCityValue(String contactCityValue) {
        this.contactCityValue = contactCityValue;
        return this;
    }

    public VacancyContactsData setStreet(String street) {
        this.street = street;
        return this;
    }

    public VacancyContactsData setBuilding(String building) {
        this.building = building;
        return this;
    }

    public VacancyContactsData setOffice(String office) {
        this.office = office;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            System.out.println("Object is null");
            return false;
        }
        if (!VacancyContactsData.class.isAssignableFrom(obj.getClass())) {
            System.out.println("Object is other class");
            return false;
        }
        final VacancyContactsData other = (VacancyContactsData) obj;
        if (this.addContacts != other.addContacts) {
            System.out.println("Expected addContacts: " + this.addContacts + ", Actual addContacts: " + other.addContacts);
            return false;
        }
        if ((this.contactPerson == null) ? (other.contactPerson != null) : !this.contactPerson.equals(other.contactPerson)) {
            System.out.println("Expected contactPerson: " + this.contactPerson + ", Actual contactPerson: " + other.contactPerson);
            return false;
        }
        if ((this.contactPhoneNumber == null) ? (other.contactPhoneNumber != null) : !this.contactPhoneNumber.equals(other.contactPhoneNumber)) {
            System.out.println("Expected contactPhoneNumber: " + this.contactPhoneNumber + ", Actual contactPhoneNumber: " + other.contactPhoneNumber);
            return false;
        }
        if (this.addAddress != other.addAddress) {
            System.out.println("Expected addAddress: " + this.addAddress + ", Actual addAddress: " + other.addAddress);
            return false;
        }
        if ((this.contactCityValue == null) ? (other.contactCityValue != null) : !this.contactCityValue.equals(other.contactCityValue)) {
            System.out.println("Expected contactCityValue: " + this.contactCityValue + ", Actual contactCityValue: " + other.contactCityValue);
            return false;
        }
        if ((this.street == null) ? (other.street != null) : !this.street.equals(other.street)) {
            System.out.println("Expected street: " + this.street + ", Actual street: " + other.street);
            return false;
        }
        if ((this.building == null) ? (other.building != null) : !this.building.equals(other.building)) {
            System.out.println("Expected building: " + this.building + ", Actual building: " + other.building);
            return false;
        }
        if ((this.office == null) ? (other.office != null) : !this.office.equals(other.office)) {
            System.out.println("Expected office: " + this.office + ", Actual office: " + other.office);
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "VacancyContactsData{" +
                "addContacts=" + addContacts +
                ", contactPerson='" + contactPerson + '\'' +
                ", contactPhoneNumber='" + contactPhoneNumber + '\'' +
                ", addAddress=" + addAddress +
                ", contactCityValue='" + contactCityValue + '\'' +
                ", street='" + street + '\'' +
                ", building='" + building + '\'' +
                ", office='" + office + '\'' +
                '}';
    }
}
