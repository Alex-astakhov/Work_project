package dataModels;


import javafx.util.Pair;

import java.util.*;

/**
 * Created by Asta on 27.02.2017.
 * Класс содержит данные о подписках на вакансии/резюме, а также методы генерации и сравнения этих данных
 */
public class SubscriptionData extends DataGenerator {
    private String vacancyTitle;
    private String cityValue = "";
    private String salary = "";
    private String experience = "";
    private String age = "";
    private String availabilityTypeValue = "0";
    private boolean transferReady;
    private int periodicity;

    public SubscriptionData(String vacancyTitle, String cityValue, String salaryFrom, String experienceFrom,
                            String availabilityTypeValue, int periodicity) {
        this.vacancyTitle = vacancyTitle;
        this.cityValue = cityValue;
        this.salary = salaryFrom;
        this.experience = experienceFrom;
        this.availabilityTypeValue = availabilityTypeValue;
        this.periodicity = periodicity;
    }

    public SubscriptionData(String vacancyTitle, String cityValue, String salaryFrom, String experienceFrom,
                            String age, String availabilityTypeValue, boolean transferReady, int periodicity) {
        this.vacancyTitle = vacancyTitle;
        this.cityValue = cityValue;
        this.salary = salaryFrom;
        this.experience = experienceFrom;
        this.age = age;
        this.availabilityTypeValue = availabilityTypeValue;
        this.transferReady = transferReady;
        this.periodicity = periodicity;
    }

    public SubscriptionData(boolean employer) {
        Random random = new Random();
        String[] vacancies = {"Бухгалтер", "Экономист", "Директор по персоналу", "Php разработчик", "Экскаваторщик"};
        this.vacancyTitle = randomItemFromArray(vacancies);
        if (random.nextBoolean())
            this.cityValue = generateCityValue();
        if (random.nextBoolean())
            this.salary = String.valueOf(random.nextInt(200000) + 10);
        if (random.nextBoolean())
            this.experience = String.valueOf(random.nextInt(5) + 1);
        if (employer) {
            if (random.nextBoolean())
                this.age = String.valueOf(random.nextInt(40) + 20);
            this.transferReady = random.nextBoolean();
        }
        this.availabilityTypeValue = String.valueOf(random.nextInt(4)).replace("3", "5");
        this.periodicity = random.nextInt(3);
    }

    public static Set<SubscriptionData> getRandomSubscriptions(int count, boolean employer){
        Map<Pair<String, String>, SubscriptionData> subscriptions = new HashMap<>();
        int size = 0;
        while (size < count){
            SubscriptionData current = new SubscriptionData(employer);
            subscriptions.put(current.getHashPair(), current);
            size = subscriptions.size();
        }
        return new HashSet<>(subscriptions.values());
    }

    public String getVacancyTitle() {
        return vacancyTitle;
    }

    public String getCityValue() {
        return cityValue;
    }

    private Pair getHashPair(){
        return new Pair(vacancyTitle, cityValue);
    }

    public String getSalary() {
        return salary;
    }

    public String getExperience() {
        return experience;
    }

    public String getAvailabilityTypeValue() {
        return availabilityTypeValue;
    }

    public String getAge() {
        return age;
    }

    public boolean isTransferReady() {
        return transferReady;
    }

    public int getPeriodicity() {
        return periodicity;
    }

    @Override
    public String toString() {
        return "SubscriptionList{" +
                "vacancyTitle='" + vacancyTitle + '\'' +
                ", cityValue='" + cityValue + '\'' +
                ", salary='" + salary + '\'' +
                ", experience='" + experience + '\'' +
                ", availabilityTypeValue='" + availabilityTypeValue + '\'' +
                ", periodicity=" + periodicity +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            System.out.println("Object is null");
            return false;
        }
        if (!SubscriptionData.class.isAssignableFrom(obj.getClass())) {
            System.out.println("Object is other class");
            return false;
        }
        final SubscriptionData other = (SubscriptionData) obj;
        if ((this.vacancyTitle == null) ? (other.vacancyTitle != null) : !this.vacancyTitle.replace("-", " ").equals(other.vacancyTitle.replace("-", " "))) {
            System.out.println("Expected vacancyTitle: " + this.vacancyTitle + ", Actual vacancyTitle: " + other.vacancyTitle);
            return false;
        }
        if ((this.cityValue == null) ? (other.cityValue != null) : !this.cityValue.equals(other.cityValue)) {
            System.out.println("Expected cityValue: " + this.cityValue + ", Actual cityValue: " + other.cityValue);
            return false;
        }

        if ((this.salary == null) ? (other.salary != null) : !this.salary.equals(other.salary)) {
            System.out.println("Expected salary: " + this.salary + ", Actual salary: " + other.salary);
            return false;
        }

        if ((this.experience == null) ? (other.experience != null) : !this.experience.equals(other.experience)) {
            System.out.println("Expected experience: " + this.experience + ", Actual experience: " + other.experience);
            return false;
        }

        if ((this.availabilityTypeValue == null) ? (other.availabilityTypeValue != null) : !this.availabilityTypeValue.equals(other.availabilityTypeValue)) {
            System.out.println("Expected availabilityTypeValue: " + this.availabilityTypeValue + ", Actual availabilityTypeValue: " + other.availabilityTypeValue);
            return false;
        }

        if (this.periodicity != other.periodicity) {
            System.out.println("Expected periodicity: " + this.periodicity + ", Actual periodicity: " + other.periodicity);
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int code = 0;
        if (this.cityValue.length() != 0)
            code = Integer.parseInt(this.cityValue);
        return this.vacancyTitle.length() + code;
    }
}
