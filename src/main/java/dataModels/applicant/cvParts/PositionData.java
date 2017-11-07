package dataModels.applicant.cvParts;

import dataModels.DataGenerator;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by Alex Astakhov on 25.11.2016.
 * Класс содержит данные о должности, а также методы генерации и сравнения этих данных
 */
public class PositionData extends DataGenerator {
    private String positionName;
    private String availabilityTypeValue;
    private String salary;
    private boolean transferPossibility;
    private Set<String> cityValues;
    private boolean businessTripPossibility;

    public PositionData(String positionName, String availabilityTypeValue, String salary, boolean transferPossibility,
                        Set<String> cityValues, boolean businessTripPossibility) {
        this.positionName = positionName;
        this.availabilityTypeValue = availabilityTypeValue;
        this.salary = salary;
        this.transferPossibility = transferPossibility;
        this.businessTripPossibility = businessTripPossibility;
        if (this.transferPossibility) {
            this.cityValues = cityValues;
        }
    }

    public PositionData(String excludeCityValue) { // чтобы город, который указан в профиле не сгенерировался для переезда
        Random random = new Random();
        String[] positions = {"Бухгалтер", "Экономист", "Директор по персоналу", "PHP-developer", "Водитель экскаватора"};
        this.positionName = positions[random.nextInt(positions.length)];
        this.availabilityTypeValue = String.valueOf(random.nextInt(3) + 1).replace("3", "5");
        this.salary = String.valueOf(random.nextInt(500000) + 10000);
        this.transferPossibility = random.nextBoolean();
        if (this.transferPossibility){
            int cityCount = random.nextInt(5) + 1;
            cityValues = new HashSet<>();
            for (int i = 0; i < cityCount; i++) {
                cityValues.add(generateCityValue(excludeCityValue));
            }
        }
        this.businessTripPossibility = random.nextBoolean();
    }

    public String getPositionName() {
        return positionName;
    }

    public String getAvailabilityTypeValue() {
        return availabilityTypeValue;
    }

    public String getSalary() {
        return salary;
    }

    public boolean isTransferPossibility() {
        return transferPossibility;
    }

    public int getTransferAsInt(){
        int move = 0;
        if (transferPossibility)
            move = 1;
        return move;
    }

    public boolean isBusinessTripPossibility() {
        return businessTripPossibility;
    }

    public int getBusinessTripAsInt(){
        int trip = 0;
        if (businessTripPossibility)
            trip = 1;
        return trip;
    }

    public Set<String> getCityValues() {
        return cityValues;
    }

    public PositionData setPositionName(String positionName) {
        this.positionName = positionName;
        return this;
    }

    public PositionData setSalary(String salary) {
        this.salary = salary;
        return this;
    }

    public PositionData setAvailabilityTypeValue(String availabilityId) {
        this.availabilityTypeValue = availabilityId;
        return this;
    }

    public PositionData setTransferPossibility(boolean transferPossibility) {
        this.transferPossibility = transferPossibility;
        return this;
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null) {
            System.out.println("Object is null");
            return false;
        }
        if (!PositionData.class.isAssignableFrom(obj.getClass())) {
            System.out.println("Object is other class");
            return false;
        }
        final PositionData other = (PositionData) obj;
        if ((this.positionName == null) ? (other.positionName != null) : !this.positionName.equals(other.positionName)) {
            System.out.println("Expected positionName: " + this.positionName + ", Actual positionName: " + other.positionName);
            return false;
        }
        if ((this.availabilityTypeValue == null) ? (other.availabilityTypeValue != null) : !this.availabilityTypeValue.equals(other.availabilityTypeValue)) {
            System.out.println("Expected availabilityTypeValue: " + this.availabilityTypeValue + ", Actual availabilityTypeValue: " + other.availabilityTypeValue);
            return false;
        }

        if ((this.salary == null) ? (other.salary != null) : !this.salary.equals(other.salary)) {
            System.out.println("Expected salary: " + this.salary + ", Actual salary: " + other.salary);
            return false;
        }

        if (this.transferPossibility != other.transferPossibility) {
            System.out.println("Expected transferPossibility: " + this.transferPossibility + ", Actual transferPossibility: " + other.transferPossibility);
            return false;
        }

        if ((this.cityValues == null) ? (other.cityValues != null) : !(this.cityValues.containsAll(other.cityValues) && other.cityValues.containsAll(this.cityValues))) {
            System.out.println("Expected cities: " + this.cityValues.toString() + ", Actual cities: " + other.cityValues.toString());
            return false;
        }

        if (this.businessTripPossibility != other.businessTripPossibility) {
            System.out.println("Expected businessTripPossibility: " + this.businessTripPossibility + ", Actual businessTripPossibility: " + other.businessTripPossibility);
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "PositionData{" +
                "positionName='" + positionName + '\'' +
                ", availabilityTypeValue='" + availabilityTypeValue + '\'' +
                ", salary='" + salary + '\'' +
                ", transferPossibility=" + transferPossibility +
                ", cityValues=" + cityValues +
                ", businessTripPossibility=" + businessTripPossibility +
                '}';
    }
}
