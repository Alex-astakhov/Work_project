package dataModels.applicant.cvParts;

import dataModels.DataGenerator;
import dataModels.apiModels.v1.errorModels.CoursesErrorData;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by Alex Astakhov on 27.11.2016.
 * Класс содержит данные о тренингах, а также методы генерации и сравнения этих данных
 */
public class TrainingData extends DataGenerator implements Comparable {
    private String trainingName;
    private String trainingOrganizer;
    private String organizerSite;
    private String trainingGraduationYearValue;

    public TrainingData(String trainingName, String trainingOrganizer, String organizerSite, String trainingGraduationYearValue) {
        this.trainingName = trainingName;
        this.trainingOrganizer = trainingOrganizer;
        this.organizerSite = organizerSite;
        this.trainingGraduationYearValue = trainingGraduationYearValue;
    }

    public TrainingData() {
        Random random = new Random();
        String[] trainingNames = {"Кройки и шитья", "Скорочтение", "Учет и аудит", "Тестирование", "Менеджмент проектов"};
        this.trainingName = trainingNames[random.nextInt(trainingNames.length)];
        String[] trainingOrganizers = {"Текстильная фабрика", "Лицей №122", "Бухгалтерия+", "IT academy", "Институт менеджмента"};
        this.trainingOrganizer = trainingOrganizers[random.nextInt(trainingOrganizers.length)];
        String[] organizerSites = {"http://kroyka-shitiyo.kz", "http://скорочтение.ком", "https://accounting.org", "https://it-academy.kz", "https://im.com"};
        this.organizerSite = organizerSites[random.nextInt(organizerSites.length)];
        this.trainingGraduationYearValue = generateYearValue();
    }

    public static TrainingData[] getRandomTrainings(int count){
        TrainingData[] trainingDatas = new TrainingData[count];
        for (int i = 0; i < count; i++) {
            trainingDatas[i] = new TrainingData();
        }
        return trainingDatas;
    }

    public String getTrainingName() {
        return trainingName;
    }

    public String getTrainingOrganizer() {
        return trainingOrganizer;
    }

    public String getOrganizerSite() {
        return organizerSite;
    }

    public String getTrainingGraduationYearValue() {
        return trainingGraduationYearValue;
    }

    public int getTrainingGraduationYear() {
        if (trainingGraduationYearValue != null)
            return Integer.parseInt(trainingGraduationYearValue);
        else
            return 0;
    }

    public TrainingData setTrainingName(String trainingName) {
        this.trainingName = trainingName;
        return this;
    }

    public TrainingData setOrganizerSite(String organizerSite) {
        this.organizerSite = organizerSite;
        return this;
    }

    public TrainingData setTrainingGraduationYearValue(String trainingGraduationYearValue) {
        this.trainingGraduationYearValue = trainingGraduationYearValue;
        return this;
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null) {
            System.out.println("Object is null");
            return false;
        }
        if (!TrainingData.class.isAssignableFrom(obj.getClass())) {
            System.out.println("Object is other class");
            return false;
        }
        final TrainingData other = (TrainingData) obj;
        if ((this.trainingName == null) ? (other.trainingName != null) : !this.trainingName.equals(other.trainingName)) {
            System.out.println("Expected trainingName: " + this.trainingName + ", Actual trainingName: " + other.trainingName);
            return false;
        }
        if ((this.trainingOrganizer == null) ? (other.trainingOrganizer != null) : !this.trainingOrganizer.equals(other.trainingOrganizer)) {
            System.out.println("Expected trainingOrganizer: " + this.trainingOrganizer + ", Actual trainingOrganizer: " + other.trainingOrganizer);
            return false;
        }

        if ((this.organizerSite == null) ? (other.organizerSite != null) : !this.organizerSite.equals(other.organizerSite)) {
            System.out.println("Expected organizerSite: " + this.organizerSite + ", Actual organizerSite: " + other.organizerSite);
            return false;
        }

        if ((this.trainingGraduationYearValue == null) ? (other.trainingGraduationYearValue != null) : !this.trainingGraduationYearValue.equals(other.trainingGraduationYearValue)) {
            System.out.println("Expected trainingGraduationYearValue: " + this.trainingGraduationYearValue + ", Actual trainingGraduationYearValue: " + other.trainingGraduationYearValue);
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "TrainingData{" +
                "trainingName='" + trainingName + '\'' +
                ", trainingOrganizer='" + trainingOrganizer + '\'' +
                ", organizerSite='" + organizerSite + '\'' +
                ", trainingGraduationYearValue='" + trainingGraduationYearValue + '\'' +
                '}';
    }


    @Override
    public int compareTo(Object o) {
        final TrainingData other = (TrainingData) o;
        if (this.trainingGraduationYearValue == null && other.trainingGraduationYearValue == null)
            return 0;
        if (this.trainingGraduationYearValue == null)
            return 1;
        if (other.trainingGraduationYearValue == null)
            return -1;
        return Integer.compare(Integer.parseInt(other.trainingGraduationYearValue), Integer.parseInt(this.trainingGraduationYearValue));
    }

    @DataProvider
    public static Object[][] negative() {
        return new Object[][]{
                {new TrainingData().setTrainingName("")},
                {new TrainingData().setTrainingName("/\\)(*&^%$#@")},
                {new TrainingData().setTrainingName("123456")},
                {new TrainingData().setTrainingName("     ")},
                {new TrainingData().setOrganizerSite("     ")},
                {new TrainingData().setOrganizerSite("qwerty")},
                {new TrainingData().setOrganizerSite("http:/гугл.ком")},
                {new TrainingData().setOrganizerSite("http//гугл.ком")},
                {new TrainingData().setOrganizerSite("http://google/com")},
                {new TrainingData().setOrganizerSite("http:/гугл.ком")},
                {new TrainingData().setOrganizerSite("http://")},
                {new TrainingData().setTrainingGraduationYearValue("0")}
        };
    }

    @DataProvider
    public static Object[][] negativeMob() {
        return new Object[][]{
                {new TrainingData().setTrainingName("")},
                {new TrainingData().setTrainingName("/\\)(*&^%$#@")},
                {new TrainingData().setTrainingName("123456")},
                {new TrainingData().setTrainingName("     ")},
                {new TrainingData().setOrganizerSite("     ")},
                {new TrainingData().setOrganizerSite("qwerty")},
                {new TrainingData().setOrganizerSite("http:/гугл.ком")},
                {new TrainingData().setOrganizerSite("http//гугл.ком")},
                {new TrainingData().setOrganizerSite("http://google/com")},
                {new TrainingData().setOrganizerSite("http:/гугл.ком")},
                {new TrainingData().setOrganizerSite("http://")},
                {new TrainingData().setTrainingGraduationYearValue("")}
        };
    }

    @DataProvider
    public static Object[][] negativeForApi() {
        return new Object[][]{
                {new TrainingData().setTrainingName(""),
                        new CoursesErrorData(new ArrayList<>(Collections.singletonList("Заполните поле Название")), null, null)},

                {new TrainingData().setTrainingName("/\\)(*&^%$#@"),
                        new CoursesErrorData(new ArrayList<>(Collections.singletonList("Заполните поле Название")), null, null)},

                {new TrainingData().setTrainingName("123456"),
                        new CoursesErrorData(new ArrayList<>(Collections.singletonList("Заполните поле Название")), null, null)},

                {new TrainingData().setTrainingName("     "),
                        new CoursesErrorData(new ArrayList<>(Collections.singletonList("Заполните поле Название")), null, null)},

                {new TrainingData().setOrganizerSite("     "),
                        new CoursesErrorData(null, new ArrayList<>(Collections.singletonList("Сайт организатора не является правильным URL.")), null)},

                {new TrainingData().setOrganizerSite("qwerty"),
                        new CoursesErrorData(null, new ArrayList<>(Collections.singletonList("Сайт организатора не является правильным URL.")), null)},

                {new TrainingData().setOrganizerSite("http:/гугл.ком"),
                        new CoursesErrorData(null, new ArrayList<>(Collections.singletonList("Сайт организатора не является правильным URL.")), null)},

                {new TrainingData().setOrganizerSite("http//гугл.ком"),
                        new CoursesErrorData(null, new ArrayList<>(Collections.singletonList("Сайт организатора не является правильным URL.")), null)},

                {new TrainingData().setOrganizerSite("http://google/com"),
                        new CoursesErrorData(null, new ArrayList<>(Collections.singletonList("Сайт организатора не является правильным URL.")), null)},

                {new TrainingData().setOrganizerSite("http:/гугл.ком"),
                        new CoursesErrorData(null, new ArrayList<>(Collections.singletonList("Сайт организатора не является правильным URL.")), null)},

                {new TrainingData().setOrganizerSite("http://"),
                        new CoursesErrorData(null, new ArrayList<>(Collections.singletonList("Сайт организатора не является правильным URL.")), null)},

                {new TrainingData().setTrainingGraduationYearValue("0"),
                        new CoursesErrorData(null, null,
                                new ArrayList<>(Collections.singletonList("Год окончания слишком мал (Минимум: " + (DataGenerator.getCurrentYear() - 75) + ").")))},

                {new TrainingData().setTrainingGraduationYearValue(String.valueOf(DataGenerator.getCurrentYear() + 1)),
                        new CoursesErrorData(null, null,
                                new ArrayList<>(Collections.singletonList("Год окончания слишком велик (Максимум: " + DataGenerator.getCurrentYear() + ").")))},
        };
    }
}
