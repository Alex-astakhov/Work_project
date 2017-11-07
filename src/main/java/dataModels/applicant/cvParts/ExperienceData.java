package dataModels.applicant.cvParts;

import dataModels.DataGenerator;
import dataModels.apiModels.v1.errorModels.ExperienceErrorData;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * Created by Alex Astakhov on 27.11.2016.
 * Класс содержит данные об опыте, а также методы генерации и сравнения этих данных
 */
public class ExperienceData extends DataGenerator implements Comparable {
    boolean noExperience;
    private String companyName;
    private String cityChoiceValue;
    private String companySite;
    private String position;
    private int monthOfIndex;
    private String yearOfValue;
    private int monthToIndex;
    private String yearToValue;
    private boolean forPresent;
    private String duties;

    public ExperienceData(boolean noExperience, String companyName, String cityChoiceValue, String companySite,
                          String position, int monthOfIndex, String yearOfValue, int monthToIndex,
                          String yearToValue, boolean forPresent, String duties) {
        this.noExperience = noExperience;
        this.companyName = companyName;
        this.cityChoiceValue = cityChoiceValue;
        this.companySite = companySite;
        this.position = position;
        this.monthOfIndex = monthOfIndex;
        this.yearOfValue = yearOfValue;
        this.monthToIndex = monthToIndex;
        this.yearToValue = yearToValue;
        this.forPresent = forPresent;
        this.duties = duties;
    }

    public ExperienceData(boolean noSite, boolean noExperiencePossibility) {
        Random random = new Random();
        if (noExperiencePossibility)
            this.noExperience = random.nextBoolean();
        else
            this.noExperience = false;
        if (this.noExperience)
            return;
        String[] companyNames = {"Флирчи", "Google", "Facebook", "Вконтакте", "Авиалинии Украины"};
        this.companyName = companyNames[random.nextInt(companyNames.length)];
        this.cityChoiceValue = generateCityValue();
        String[] companySites = {"http://флирчи.ком", "https://google.com", "https://facebook.com", "https://vk.com", "http://mau.fly"};
        if (noSite)
            this.companySite = "";
        else
            this.companySite = companySites[random.nextInt(companySites.length)];
        String[] positions = {"Сантехник", "Высотник", "Пилот погрузчика", "Гардеробщик", "Верстальщик"};
        this.position = positions[random.nextInt(positions.length)];
        this.monthOfIndex = random.nextInt(12) + 1;
        this.yearOfValue = generateYearValue(3);
        this.forPresent = random.nextBoolean();
        if (!this.forPresent) {
            this.monthToIndex = random.nextInt(12) + 1;
            do {
                this.yearToValue = generateYearValue();
            } while (Integer.parseInt(this.yearToValue) <= Integer.parseInt(this.yearOfValue));
        }
        this.duties = "Мои обязанности по состоянию на " + getCurrentDateAndTimeString();
    }

    public boolean isNoExperience() {
        return noExperience;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCityChoiceValue() {
        return cityChoiceValue;
    }

    public int getCity() {
        if (cityChoiceValue != null)
            return Integer.parseInt(cityChoiceValue);
        else
            return 0;
    }

    public String getCompanySite() {
        return companySite;
    }

    public String getPosition() {
        return position;
    }

    public int getMonthOfIndex() {
        return monthOfIndex;
    }

    public String getYearOfValue() {
        return yearOfValue;
    }

    public int getYearOf(){
        return Integer.parseInt(yearOfValue);
    }

    public int getMonthToIndex() {
        return monthToIndex;
    }

    public String getYearToValue() {
        return yearToValue;
    }

    public int getYearTo(){
        if (yearToValue != null)
            return Integer.parseInt(yearToValue);
        else
            return 0;
    }

    public boolean isForPresent() {
        return forPresent;
    }

    public int getForPresentAsInt(){
        if (this.forPresent)
            return 1;
        else
            return 0;
    }

    public String getDuties() {
        return duties;
    }

    public ExperienceData setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public ExperienceData setCityChoiceValue(String cityChoiceValue) {
        this.cityChoiceValue = cityChoiceValue;
        return this;
    }

    public ExperienceData setCompanySite(String companySite) {
        this.companySite = companySite;
        return this;
    }

    public ExperienceData setPosition(String position) {
        this.position = position;
        return this;
    }

    public ExperienceData setMonthOfIndex(int monthOfIndex) {
        this.monthOfIndex = monthOfIndex;
        return this;
    }

    public ExperienceData setYearOfValue(String yearOfValue) {
        this.yearOfValue = yearOfValue;
        return this;
    }

    public ExperienceData setMonthToIndex(int monthToIndex) {
        this.monthToIndex = monthToIndex;
        return this;
    }

    public ExperienceData setYearToValue(String yearToValue) {
        this.yearToValue = yearToValue;
        return this;
    }

    public ExperienceData setForPresent(boolean forPresent) {
        this.forPresent = forPresent;
        return this;
    }

    public ExperienceData setDuties(String duties) {
        this.duties = duties;
        return this;
    }

    public static ExperienceData[] getRandomExperiences(int amount, boolean noSite){
        if (amount == 0)
            return null;
        ExperienceData[] data = new ExperienceData[amount];
        for (int i = 0; i < amount; i++) {
            data[i] = new ExperienceData(noSite, false);
        }
        return data;
    }

    @Override
    public String toString() {
        return "ExperienceData{" +
                "noExperience=" + noExperience +
                ", companyName='" + companyName + '\'' +
                ", cityChoiceValue='" + cityChoiceValue + '\'' +
                ", companySite='" + companySite + '\'' +
                ", position='" + position + '\'' +
                ", monthOfIndex=" + monthOfIndex +
                ", yearOfValue='" + yearOfValue + '\'' +
                ", monthToIndex=" + monthToIndex +
                ", yearToValue='" + yearToValue + '\'' +
                ", forPresent=" + forPresent +
                ", duties='" + duties + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            System.out.println("Object is null");
            return false;
        }
        if (!ExperienceData.class.isAssignableFrom(obj.getClass())) {
            System.out.println("Object is other class");
            return false;
        }
        final ExperienceData other = (ExperienceData) obj;
        if (this.noExperience != other.noExperience) {
            System.out.println("Expected noExperience: " + this.noExperience + ", Actual noExperience: " + other.noExperience);
            return false;
        }
        if ((this.companyName == null) ? (other.companyName != null) : !this.companyName.equals(other.companyName)) {
            System.out.println("Expected companyName: " + this.companyName + ", Actual companyName: " + other.companyName);
            return false;
        }
        if ((this.cityChoiceValue == null) ? (other.cityChoiceValue != null) : !this.cityChoiceValue.equals(other.cityChoiceValue)) {
            System.out.println("Expected cityChoiceValue: " + this.cityChoiceValue + ", Actual cityChoiceValue: " + other.cityChoiceValue);
            return false;
        }
        if ((this.companySite == null) ? (other.companySite != null) : !this.companySite.equals(other.companySite)) {
            System.out.println("Expected companySite: " + this.companySite + ", Actual companySite: " + other.companySite);
            return false;
        }
        if ((this.position == null) ? (other.position != null) : !this.position.equals(other.position)) {
            System.out.println("Expected position: " + this.position + ", Actual position: " + other.position);
            return false;
        }
        if (this.monthOfIndex != other.monthOfIndex) {
            System.out.println("Expected monthOfIndex: " + this.monthOfIndex + ", Actual monthOfIndex: " + other.monthOfIndex);
            return false;
        }
        if ((this.yearOfValue == null) ? (other.yearOfValue != null) : !this.yearOfValue.equals(other.yearOfValue)) {
            System.out.println("Expected yearOfValue: " + this.yearOfValue + ", Actual yearOfValue: " + other.yearOfValue);
            return false;
        }
        if (this.monthToIndex != other.monthToIndex) {
            System.out.println("Expected monthToIndex: " + this.monthToIndex + ", Actual monthToIndex: " + other.monthToIndex);
            return false;
        }
        if ((this.yearToValue == null) ? (other.yearToValue != null) : !this.yearToValue.equals(other.yearToValue)) {
            System.out.println("Expected yearToValue: " + this.yearToValue + ", Actual yearToValue: " + other.yearToValue);
            return false;
        }
        if (this.forPresent != other.forPresent) {
            System.out.println("Expected forPresent: " + this.forPresent + ", Actual forPresent: " + other.forPresent);
            return false;
        }
        if ((this.duties == null) ? (other.duties != null) : !this.duties.equals(other.duties)) {
            System.out.println("Expected duties: " + this.duties + ", Actual duties: " + other.duties);
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Object o) {
        final ExperienceData other = (ExperienceData) o;
        int thisYearTo, otherYearTo, thisMonthTo, otherMonthTo;
        if (this.yearToValue != null) {
            thisYearTo = Integer.parseInt(this.yearToValue);
            thisMonthTo = this.monthToIndex;
        }
        else {
            thisYearTo = 5000;
            thisMonthTo = 0;
        }
        if (other.yearToValue != null) {
            otherYearTo = Integer.parseInt(other.yearToValue);
            otherMonthTo = other.monthToIndex;
        }
        else {
            otherYearTo = 5000;
            otherMonthTo = 0;
        }
        int compare = Integer.compare(otherYearTo, thisYearTo);
        if (compare != 0)
            return compare;
        compare = Integer.compare(otherMonthTo, thisMonthTo);
        if (compare != 0)
            return compare;
        compare = Integer.compare(Integer.parseInt(other.yearOfValue), Integer.parseInt(this.yearOfValue));
        if (compare != 0)
            return compare;
        return Integer.compare(other.monthOfIndex, this.monthOfIndex);
    }

    @DataProvider
    public static Object[][] negativeBuilder() {
        return new Object[][]{
                {new ExperienceData(true, false).setCompanyName("")},
                {new ExperienceData(true, false).setCityChoiceValue("0")},
                {new ExperienceData(true, false).setPosition("")},
                {new ExperienceData(true, false).setPosition("q")},
                {new ExperienceData(true, false).setPosition("qw")},
                {new ExperienceData(true, false).setMonthOfIndex(0).setYearOfValue("0")
                        .setMonthToIndex(0).setYearToValue("0").setForPresent(false)},
                {new ExperienceData(true, false).setMonthOfIndex(0).setYearOfValue("0")
                        .setMonthToIndex(0).setYearToValue("0").setForPresent(true)},
                {new ExperienceData(true, false).setMonthOfIndex(0).setYearOfValue("0")},
                {new ExperienceData(true, false).setMonthOfIndex(0)},
                {new ExperienceData(true, false).setYearOfValue("0")},
                {new ExperienceData(true, false).setMonthToIndex(0).setYearToValue("0")
                        .setForPresent(false)},
                {new ExperienceData(true, false).setMonthToIndex(0)
                        .setYearToValue(DataGenerator.generateYearValue()).setForPresent(false)},
                {new ExperienceData(true, false)
                        .setMonthToIndex(new Random().nextInt(12) + 1).setYearToValue("0").setForPresent(false)},
                {new ExperienceData(true, false).setMonthOfIndex(5).setYearOfValue("2005")
                        .setMonthToIndex(4).setYearToValue("2005").setForPresent(false)},
                {new ExperienceData(true, false).setMonthOfIndex(5).setYearOfValue("2005")
                        .setMonthToIndex(6).setYearToValue("2004").setForPresent(false)},
                {new ExperienceData(true, false).setDuties("")},
                {new ExperienceData(true, false).setDuties("123456")}
        };
    }

    @DataProvider
    public static Object[][] negativeCv(){
        return new Object[][]{
                {new ExperienceData(false, false).setCityChoiceValue("0")},
                {new ExperienceData(false, false).setCompanySite("qwerty")},
                {new ExperienceData(false, false).setCompanySite("http:/гугл.ком")},
                {new ExperienceData(false, false).setCompanySite("http//гугл.ком")},
                {new ExperienceData(false, false).setCompanySite("http://google/com")},
                {new ExperienceData(false, false).setCompanySite("http://")},
                {new ExperienceData(false, false).setCompanySite("      ")},
                {new ExperienceData(false, false).setPosition("")},
                {new ExperienceData(false, false).setPosition("      ")},
                {new ExperienceData(false, false).setPosition("q")},
                {new ExperienceData(false, false).setPosition("qw")},
                {new ExperienceData(false, false).setPosition("123456")},
                {new ExperienceData(false, false).setMonthOfIndex(0)},
                {new ExperienceData(false, false).setYearOfValue("0")},
                {new ExperienceData(false, false).setMonthToIndex(0).setYearToValue("2015")
                        .setForPresent(false)},
                {new ExperienceData(false, false).setYearToValue("0").setForPresent(false)},
                {new ExperienceData(false, false).setMonthOfIndex(5).setYearOfValue("2015")
                        .setMonthToIndex(4).setYearToValue("2015").setForPresent(false)},
                {new ExperienceData(false, false).setMonthOfIndex(5).setYearOfValue("2015")
                        .setMonthToIndex(5).setYearToValue("2014").setForPresent(false)},
                {new ExperienceData(false, false).setMonthOfIndex(5).setYearOfValue("2015")
                        .setMonthToIndex(6).setYearToValue("2014").setForPresent(false)},
                {new ExperienceData(false, false).setMonthOfIndex(0).setYearOfValue("0")
                        .setMonthToIndex(0).setYearToValue("0").setForPresent(false)},
                {new ExperienceData(false, false).setMonthOfIndex(0).setForPresent(true)},
                {new ExperienceData(false, false).setYearOfValue("0").setForPresent(true)},
                {new ExperienceData(false, false).setMonthOfIndex(0).setYearOfValue("0")
                        .setForPresent(true)},
                {new ExperienceData(true, false).setDuties("")},
                {new ExperienceData(true, false).setDuties("123456")}
        };
    }

    @DataProvider
    public static Object[][] negativeForApi(){
        return new Object[][]{
                {new ExperienceData(false, false).setCityChoiceValue("0"),
                        new ExperienceErrorData(null, null, null, null,
                                null, null, new ArrayList<>(Collections.singletonList("Выберите город")), null)},

                {new ExperienceData(false, false).setCompanySite("qwerty"),
                        new ExperienceErrorData(null, null,
                                new ArrayList<>(Collections.singletonList("Сайт компании не является правильным URL.")),
                                null,null, null, null, null)},


                {new ExperienceData(false, false).setCompanySite("http:/гугл.ком"),
                        new ExperienceErrorData(null, null,
                                new ArrayList<>(Collections.singletonList("Сайт компании не является правильным URL.")),
                                null,null, null, null, null)},

                {new ExperienceData(false, false).setCompanySite("http//гугл.ком"),
                        new ExperienceErrorData(null, null,
                                new ArrayList<>(Collections.singletonList("Сайт компании не является правильным URL.")),
                                null,null, null, null, null)},

                {new ExperienceData(false, false).setCompanySite("http://google/com"),
                        new ExperienceErrorData(null, null,
                                new ArrayList<>(Collections.singletonList("Сайт компании не является правильным URL.")),
                                null,null, null, null, null)},

                {new ExperienceData(false, false).setCompanySite("http://"),
                        new ExperienceErrorData(null, null,
                                new ArrayList<>(Collections.singletonList("Сайт компании не является правильным URL.")),
                                null,null, null, null, null)},

                {new ExperienceData(false, false).setCompanySite("      "),
                        new ExperienceErrorData(null, null,
                                new ArrayList<>(Collections.singletonList("Сайт компании не является правильным URL.")),
                                null,null, null, null, null)},

                {new ExperienceData(false, false).setPosition(""),
                        new ExperienceErrorData(null, null, null,
                                new ArrayList<>(Collections.singletonList("Заполните поле Должность")), null,
                                null,null, null)},

                {new ExperienceData(false, false).setPosition("     "),
                        new ExperienceErrorData(null, null, null,
                                new ArrayList<>(Collections.singletonList("Заполните поле Должность")), null,
                                null,null, null)},

                {new ExperienceData(false, false).setPosition("q"),
                        new ExperienceErrorData(null, null, null,
                                new ArrayList<>(Collections.singletonList("Неверно указана должность")), null,
                                null,null, null)},

                {new ExperienceData(false, false).setPosition("qw"),
                        new ExperienceErrorData(null, null, null,
                                new ArrayList<>(Collections.singletonList("Неверно указана должность")), null,
                                null,null, null)},

                {new ExperienceData(false, false).setPosition("123456"),
                        new ExperienceErrorData(null, null, null,
                                new ArrayList<>(Collections.singletonList("Неверно указана должность")), null,
                                null,null, null)},

                {new ExperienceData(false, false).setMonthOfIndex(0),
                        new ExperienceErrorData(new ArrayList<>(Collections.singletonList("Укажите дату - с")),
                                null, null, null, null, null,
                                null, null)},

                {new ExperienceData(false, false).setYearOfValue("0"),
                        new ExperienceErrorData(new ArrayList<>(Collections.singletonList("Укажите дату - с")),
                                null, null, null, null, null,
                                null, null)},

                {new ExperienceData(false, false).setMonthToIndex(0).setYearToValue("2015")
                        .setForPresent(false),
                        new ExperienceErrorData(null, null, null, null,
                                null, null, null, new ArrayList<>(Collections.singletonList("Укажите дату - по")))},


                {new ExperienceData(false, false).setYearToValue("0").setForPresent(false),
                        new ExperienceErrorData(null, null, null, null,
                                null, null, null, new ArrayList<>(Arrays.asList("Укажите дату - по", "Укажите дату - по")))},

                {new ExperienceData(false, false).setMonthOfIndex(5).setYearOfValue("2015")
                        .setMonthToIndex(4).setYearToValue("2015").setForPresent(false),
                        new ExperienceErrorData(null, null, null, null,
                                null, null, null, new ArrayList<>(Collections.singletonList("Укажите дату - по")))},

                {new ExperienceData(false, false).setMonthOfIndex(5).setYearOfValue("2015")
                        .setMonthToIndex(5).setYearToValue("2014").setForPresent(false),
                        new ExperienceErrorData(null, null, null, null,
                                null, null, null, new ArrayList<>(Collections.singletonList("Укажите дату - по")))},

                {new ExperienceData(false, false).setMonthOfIndex(5).setYearOfValue("2015")
                        .setMonthToIndex(6).setYearToValue("2014").setForPresent(false),
                        new ExperienceErrorData(null, null, null, null,
                                null, null, null, new ArrayList<>(Collections.singletonList("Укажите дату - по")))},

                {new ExperienceData(false, false).setMonthOfIndex(0).setYearOfValue("0")
                        .setMonthToIndex(0).setYearToValue("0").setForPresent(false),
                        new ExperienceErrorData(new ArrayList<>(Collections.singletonList("Укажите дату - с")),
                                null, null, null,null, null,
                                null, new ArrayList<>(Collections.singletonList("Укажите дату - по")))},

                {new ExperienceData(false, false).setMonthOfIndex(0).setForPresent(true),
                        new ExperienceErrorData(new ArrayList<>(Collections.singletonList("Укажите дату - с")),
                                null, null, null, null, null,
                                null, null)},

                {new ExperienceData(false, false).setYearOfValue("0").setForPresent(true),
                        new ExperienceErrorData(new ArrayList<>(Collections.singletonList("Укажите дату - с")),
                                null, null, null, null, null,
                                null, null)},

                {new ExperienceData(false, false).setMonthOfIndex(0).setYearOfValue("0")
                        .setForPresent(true),
                        new ExperienceErrorData(new ArrayList<>(Collections.singletonList("Укажите дату - с")),
                                null, null, null, null, null,
                                null, null)},

                {new ExperienceData(false, false).setDuties(""),
                        new ExperienceErrorData(null,null, null, null,
                                new ArrayList<>(Collections.singletonList("Заполните поле Обязанности на занимаемой должности")),
                                null,null, null)},
        };
    }
}
