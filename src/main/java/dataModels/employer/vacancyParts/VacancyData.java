package dataModels.employer.vacancyParts;

import dataModels.DataGenerator;

import java.util.Random;

/**
 * Created by Asta on 31.01.2017.
 * Класс содержит данные вакансии, а также методы генерации и сравнения этих данных
 */
public class VacancyData extends DataGenerator {
    private String title;
    private String description;
    private String availabilityTypeValue;
    private String cityValue;
    private String experienceValue;
    private String salaryFrom = "0";
    private String salaryTo = "0";


    public VacancyData(String title, String description, String availabilityTypeValue, String cityValue,
                       String experienceValue, String salaryFrom, String salaryTo) {
        this.title = title;
        this.description = description;
        this.availabilityTypeValue = availabilityTypeValue;
        this.cityValue = cityValue;
        this.experienceValue = experienceValue;
        this.salaryFrom = salaryFrom;
        this.salaryTo = salaryTo;
    }

    public VacancyData() {
        Random random = new Random();
        String[] titles = {"Ведущий кочегар", "Генеральный сантехник", "Учитель астрофизики", "Главный грузчик", "Дантист 3-й категории"};
        this.title = randomItemFromArray(titles);
        this.description = "Описание тестовой вакансии от " + getCurrentDateAndTimeString();
        this.availabilityTypeValue = String.valueOf(random.nextInt(3) + 1).replace("3", "5");
        this.cityValue = generateCityValue();
        this.experienceValue = String.valueOf(random.nextInt(11));
        if (random.nextBoolean())
            this.salaryFrom = String.valueOf(random.nextInt(30000) + 10000);
        if (random.nextBoolean())
            this.salaryTo = String.valueOf(random.nextInt(50000) + 40000);
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getAvailabilityTypeValue() {
        return availabilityTypeValue;
    }

    public String getCityValue() {
        return cityValue;
    }

    public String getExperienceValue() {
        return experienceValue;
    }

    public String getSalaryFrom() {
        return salaryFrom;
    }

    public String getSalaryTo() {
        return salaryTo;
    }

    public VacancyData setTitle(String title) {
        this.title = title;
        return this;
    }

    public VacancyData setDescription(String description) {
        this.description = description;
        return this;
    }

    public VacancyData setCityValue(String cityValue) {
        this.cityValue = cityValue;
        return this;
    }

    public VacancyData setSalaryFrom(String salaryFrom) {
        this.salaryFrom = salaryFrom;
        return this;
    }

    public VacancyData setSalaryTo(String salaryTo) {
        this.salaryTo = salaryTo;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            System.out.println("Object is null");
            return false;
        }
        if (!VacancyData.class.isAssignableFrom(obj.getClass())) {
            System.out.println("Object is other class");
            return false;
        }
        final VacancyData other = (VacancyData) obj;
        if ((this.title == null) ? (other.title != null) : !this.title.equals(other.title)) {
            System.out.println("Expected title: " + this.title + ", Actual title: " + other.title);
            return false;
        }
        if ((this.description == null) ? (other.description != null) : !this.description.equals(other.description)) {
            System.out.println("Expected description: " + this.description.trim() + ", Actual description: " + other.description.trim());
            return false;
        }
        if ((this.availabilityTypeValue == null) ? (other.availabilityTypeValue != null) : !this.availabilityTypeValue.equals(other.availabilityTypeValue)) {
            System.out.println("Expected availabilityTypeValue: " + this.availabilityTypeValue + ", Actual availabilityTypeValue: " + other.availabilityTypeValue);
            return false;
        }
        if ((this.cityValue == null) ? (other.cityValue != null) : !this.cityValue.equals(other.cityValue)) {
            System.out.println("Expected cityValue: " + this.cityValue + ", Actual cityValue: " + other.cityValue);
            return false;
        }
        if ((this.experienceValue == null) ? (other.experienceValue != null) : !this.experienceValue.equals(other.experienceValue)) {
            System.out.println("Expected experienceValue: " + this.experienceValue + ", Actual experienceValue: " + other.experienceValue);
            return false;
        }
        if ((this.salaryFrom == null) ? (other.salaryFrom != null) : !this.salaryFrom.equals(other.salaryFrom)) {
            System.out.println("Expected salaryFrom: " + this.salaryFrom + ", Actual salaryFrom: " + other.salaryFrom);
            return false;
        }
        if ((this.salaryTo == null) ? (other.salaryTo != null) : !this.salaryTo.equals(other.salaryTo)) {
            System.out.println("Expected salaryTo: " + this.salaryTo + ", Actual salaryTo: " + other.salaryTo);
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "VacancyData{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", availabilityTypeValue='" + availabilityTypeValue + '\'' +
                ", cityValue='" + cityValue + '\'' +
                ", experienceValue='" + experienceValue + '\'' +
                ", salaryFrom='" + salaryFrom + '\'' +
                ", salaryTo='" + salaryTo + '\'' +
                '}';
    }
}
