package dataModels.applicant.cvParts;

import dataModels.DataGenerator;

import java.util.*;

/**
 * Created by Alex Astakhov on 27.11.2016.
 * Класс содержит данные о водительских правах и наличии авто, а также методы генерации и сравнения этих данных
 */
public class DriversData extends DataGenerator {
    private Set<Character> driverCategories;
    private boolean haveCar;

    public DriversData(Set<Character> driverCategories, boolean haveCar) {
        this.driverCategories = driverCategories;
        this.haveCar = haveCar;
    }

    public DriversData(){
        Random random = new Random();
        driverCategories = new HashSet<>();
        int categoriesCount = random.nextInt(6);
        for (int i = 0; i < categoriesCount; i++) {
            boolean ok;
            do{
                char[] categories = {'A', 'B', 'C', 'D', 'E'};
                ok = driverCategories.add(categories[random.nextInt(categories.length)]);
            }while (!ok);
        }
        this.haveCar = random.nextBoolean();
    }

    public Set<Character> getDriverCategories() {
        return driverCategories;
    }

    public boolean isHaveCar() {
        return haveCar;
    }

    @Override
    public String toString() {
        return "DriversData{" +
                "driverCategories=" + driverCategories +
                ", haveCar=" + haveCar +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            System.out.println("Object is null");
            return false;
        }
        if (!DriversData.class.isAssignableFrom(obj.getClass())) {
            System.out.println("Object is other class");
            return false;
        }
        final DriversData other = (DriversData) obj;
        if ((this.driverCategories == null) ? (other.driverCategories != null) : !(this.driverCategories.containsAll(other.driverCategories) && other.driverCategories.containsAll(this.driverCategories))) {
            System.out.println("Expected driverCategories: " + this.driverCategories.toString() + ", Actual driverCategories: " + other.driverCategories.toString());
            return false;
        }
        if (this.haveCar != other.haveCar) {
            System.out.println("Expected haveCar: " + this.haveCar + ", Actual haveCar: " + other.haveCar);
            return false;
        }

        return true;
    }

    public List<Integer> getDriversList(){
        Map<Character, Integer> values = new HashMap<>();
        values.put('A', 1);
        values.put('B', 2);
        values.put('C', 3);
        values.put('D', 4);
        values.put('E', 5);
        List<Integer> driversLicence = new ArrayList<>();
        for (Character c: this.driverCategories) {
            driversLicence.add(values.get(c));
        }
        return driversLicence;
    }
}
