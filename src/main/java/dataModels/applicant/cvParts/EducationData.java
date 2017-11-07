package dataModels.applicant.cvParts;

import dataModels.DataGenerator;
import dataModels.apiModels.v1.errorModels.EducationErrorData;
import org.testng.annotations.DataProvider;

import java.util.*;

/**
 * Created by Alex Astakhov on 25.11.2016.
 * Класс содержит данные об образовании, а также методы генерации и сравнения этих данных
 */
public class EducationData extends DataGenerator implements Comparable {
    private String educationTypeValue;
    private String institutionName;
    private String facultyName;
    private String cityChoiceValue;
    private String degree;
    private String graduationYearChoiceValue;
    private boolean studyNow;

    public EducationData(String educationTypeValue, String institutionName, String facultyName, String cityChoiceValue,
                         String degree, String graduationYearChoiceValue, boolean studyNow) {
        this.educationTypeValue = educationTypeValue;
        this.institutionName = institutionName;
        this.facultyName = facultyName;
        this.cityChoiceValue = cityChoiceValue;
        this.degree = degree;
        this.graduationYearChoiceValue = graduationYearChoiceValue;
        this.studyNow = studyNow;
    }

    public EducationData() {
        Random random = new Random();
        this.educationTypeValue = String.valueOf(random.nextInt(4) + 1);
        String[] institutions = {"НТУУ КПИ", "Ун-т ДФСУ", "ПГУ им.С.Торайгырова", "Гуманитарно-Технический Техникум", "Колледж Арна г. Кокшетау"};
        this.institutionName = institutions[random.nextInt(institutions.length)];
        if (!this.educationTypeValue.equals("2")){
            String[] faculties = {"ФИОТ", "Филологический", "Юридический", "Финансовый", "Фискальный"};
            this.facultyName = faculties[random.nextInt(faculties.length)];
        }
        else {
            this.facultyName = "";
        }

        this.cityChoiceValue = generateCityValue();
        if (this.educationTypeValue.equals("1") || this.educationTypeValue.equals("3")){
            String[] degrees = {"Бакалавр", "Специалист", "Магистр", "Кандидат наук", "Доктор наук"};
            this.degree = degrees[random.nextInt(5)];
        }
        else {
            this.degree = "";
        }
        this.studyNow = random.nextBoolean();
        if (this.educationTypeValue.equals("2"))
            this.studyNow = false;
        if (!this.studyNow) {
            this.graduationYearChoiceValue = generateYearValue(-6);
        }
    }

    public String getEducationTypeValue() {
        return educationTypeValue;
    }

    public int getEducationType() {
        return Integer.parseInt(educationTypeValue);
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public String getFacultyName() {
        return facultyName;
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

    public String getDegree() {
        return degree;
    }

    public String getGraduationYearChoiceValue() {
        return graduationYearChoiceValue;
    }

    public int getGraduationYear() {
        if (graduationYearChoiceValue != null)
            return Integer.parseInt(graduationYearChoiceValue);
        else
            return 0;
    }

    public boolean isStudyNow() {
        return studyNow;
    }

    public int getStudyNowAsInt() {
        if (studyNow)
            return 1;
        else
            return 0;
    }

    public EducationData setEducationTypeValue(String educationTypeValue) {
        System.out.println("Set education type value: " + educationTypeValue);
        this.educationTypeValue = educationTypeValue;
        return this;
    }

    public EducationData setInstitutionName(String institutionName) {
        System.out.println("Set institution title: " + institutionName);
        this.institutionName = institutionName;
        return this;
    }

    public EducationData setFacultyName(String facultyName) {
        System.out.println("Set faculty title: " + facultyName);
        this.facultyName = facultyName;
        return this;
    }

    public EducationData setCityChoiceValue(String cityChoiceValue) {
        System.out.println("Set city value: " + cityChoiceValue);
        this.cityChoiceValue = cityChoiceValue;
        return this;
    }

    public EducationData setDegree(String degree) {
        System.out.println("Set degree: " + degree);
        this.degree = degree;
        return this;
    }

    public EducationData setGraduationYearChoiceValue(String graduationYearChoiceValue) {
        System.out.println("Set graduation year: " + graduationYearChoiceValue);
        this.graduationYearChoiceValue = graduationYearChoiceValue;
        return this;
    }

    public EducationData setStudyNow(boolean studyNow) {
        System.out.println("Set study now: " + studyNow);
        this.studyNow = studyNow;
        return this;
    }

    public static EducationData[] getRandomEducations(int amount){
        EducationData[] data = new EducationData[amount];
        boolean isStudyNowGenerated = false;
        for (int i = 0; i < amount; i++) {
            data[i] = new EducationData();
            if (isStudyNowGenerated){     // чтобы образование с "Учусь сейчас" генерировалось единожды
                while (data[i].isStudyNow())
                    data[i] = new EducationData();
            }
            if (data[i].isStudyNow())
                isStudyNowGenerated = true;
        }
        return data;
    }

    @Override
    public String toString() {
        return "EducationData{" +
                "educationTypeValue=" + educationTypeValue +
                ", institutionName='" + institutionName + '\'' +
                ", facultyName='" + facultyName + '\'' +
                ", cityChoiceValue='" + cityChoiceValue + '\'' +
                ", degree='" + degree + '\'' +
                ", graduationYearChoiceValue='" + graduationYearChoiceValue + '\'' +
                ", studyNow=" + studyNow +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        final EducationData other = (EducationData) o;
        if (this.graduationYearChoiceValue == null && other.graduationYearChoiceValue == null)
            return 0;
        if (this.graduationYearChoiceValue == null)
            return -1;
        if (other.graduationYearChoiceValue == null)
            return 1;
        return Integer.compare(Integer.parseInt(other.graduationYearChoiceValue), Integer.parseInt(this.graduationYearChoiceValue));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            System.out.println("Object is null");
            return false;
        }
        if (!EducationData.class.isAssignableFrom(obj.getClass())) {
            System.out.println("Object is other class");
            return false;
        }
        final EducationData other = (EducationData) obj;
        if ((this.educationTypeValue == null) ? (other.educationTypeValue != null) : !this.educationTypeValue.equals(other.educationTypeValue)) {
            System.out.println("Expected educationTypeValue: " + this.educationTypeValue + ", Actual educationTypeValue: " + other.educationTypeValue);
            return false;
        }
        if ((this.institutionName == null) ? (other.institutionName != null) : !this.institutionName.equals(other.institutionName)) {
            System.out.println("Expected institutionName: " + this.institutionName + ", Actual institutionName: " + other.institutionName);
            return false;
        }
        if ((this.facultyName == null) ? (other.facultyName != null) : !this.facultyName.equals(other.facultyName)) {
            System.out.println("Expected facultyName: " + this.facultyName + ", Actual facultyName: " + other.facultyName);
            return false;
        }
        if ((this.cityChoiceValue == null) ? (other.cityChoiceValue != null) : !this.cityChoiceValue.equals(other.cityChoiceValue)) {
            System.out.println("Expected cityChoiceValue: " + this.cityChoiceValue + ", Actual cityChoiceValue: " + other.cityChoiceValue);
            return false;
        }
        if ((this.degree == null) ? (other.degree != null) : !this.degree.equals(other.degree)) {
            System.out.println("Expected degree: " + this.degree + ", Actual degree: " + other.degree);
            return false;
        }
        if ((this.graduationYearChoiceValue == null) ? (other.graduationYearChoiceValue != null) : !this.graduationYearChoiceValue.equals(other.graduationYearChoiceValue)) {
            System.out.println("Expected graduationYearChoiceValue: " + this.graduationYearChoiceValue + ", Actual graduationYearChoiceValue: " + other.graduationYearChoiceValue);
            return false;
        }

        if (this.studyNow != other.studyNow) {
            System.out.println("Expected studyNow: " + this.studyNow + ", Actual studyNow: " + other.studyNow);
            return false;
        }
        return true;
    }

    @DataProvider
    public static Object[][] negative() {
        return new Object[][]{
                {new EducationData("1", "", "qwerty", DataGenerator.generateCityValue(),
                        "qwerty", DataGenerator.generateYearValue(), false)},

                {new EducationData("1", "    ", "qwerty", DataGenerator.generateCityValue(),
                        "qwerty", DataGenerator.generateYearValue(), false)},

                {new EducationData("1", "Q", "qwerty", DataGenerator.generateCityValue(),
                        "qwerty", DataGenerator.generateYearValue(), false)},

                {new EducationData("1", "QW", "qwerty", DataGenerator.generateCityValue(),
                        "qwerty", DataGenerator.generateYearValue(), false)},

                {new EducationData("1", "QWE", "qwerty", DataGenerator.generateCityValue(),
                        "qwerty", DataGenerator.generateYearValue(), false)},

                {new EducationData("1", "QWER", "qwerty", DataGenerator.generateCityValue(),
                        "qwerty", DataGenerator.generateYearValue(), false)},

                {new EducationData("1", "QWERTY", "    ", DataGenerator.generateCityValue(),
                        "qwerty", DataGenerator.generateYearValue(), false)},

                {new EducationData("1", "QWERTY", "qwerty", "0",
                        "qwerty", DataGenerator.generateYearValue(), false)},

                {new EducationData("1", "QWERTY", "qwerty", DataGenerator.generateCityValue(),
                        " ", DataGenerator.generateYearValue(), false)},

                {new EducationData("1", "QWERTY", "qwerty", DataGenerator.generateCityValue(),
                        "qwerty", "0", false)},

                {new EducationData("3", "", "qwerty", DataGenerator.generateCityValue(),
                        "qwerty", DataGenerator.generateYearValue(), false)},

                {new EducationData("3", "Q", "qwerty", DataGenerator.generateCityValue(),
                        "qwerty", DataGenerator.generateYearValue(), false)},

                {new EducationData("3", "QW", "qwerty", DataGenerator.generateCityValue(),
                        "qwerty", DataGenerator.generateYearValue(), false)},

                {new EducationData("3", "QWE", "qwerty", DataGenerator.generateCityValue(),
                        "qwerty", DataGenerator.generateYearValue(), false)},

                {new EducationData("3", "QWER", "qwerty", DataGenerator.generateCityValue(),
                        "qwerty", DataGenerator.generateYearValue(), false)},

                {new EducationData("3", "QWERTY", " ", DataGenerator.generateCityValue(),
                        "qwerty", DataGenerator.generateYearValue(), false)},

                {new EducationData("3", "QWERTY", "qwerty", "0",
                        "qwerty", DataGenerator.generateYearValue(), false)},

                {new EducationData("3", "QWERTY", "qwerty", DataGenerator.generateCityValue(),
                        " ", DataGenerator.generateYearValue(), false)},

                {new EducationData("3", "QWERTY", "qwerty", DataGenerator.generateCityValue(),
                        "qwerty", "0", false)}
        };
    }

    @DataProvider
    public static Object[][] negativeForApi() {
        return new Object[][]{
                {new EducationData("1", "", "qwerty", DataGenerator.generateCityValue(),
                        "qwerty", DataGenerator.generateYearValue(), false),
                new EducationErrorData(null, null, null, new ArrayList<>(Collections.singletonList("Заполните поле Учебное заведение")),
                        null, null, null)},

                {new EducationData("1", "    ", "qwerty", DataGenerator.generateCityValue(),
                        "qwerty", DataGenerator.generateYearValue(), false),
                new EducationErrorData(null, null, null, new ArrayList<>(Collections.singletonList("Заполните поле Учебное заведение")),
                        null, null, null)},

                {new EducationData("1", "Q", "qwerty", DataGenerator.generateCityValue(),
                        "qwerty", DataGenerator.generateYearValue(), false),
                        new EducationErrorData(null, null, null,
                                new ArrayList<>(Collections.singletonList("Учебное заведение слишком короткий (Минимум: 5 симв.).")),
                                null, null, null)},

                {new EducationData("1", "QW", "qwerty", DataGenerator.generateCityValue(),
                        "qwerty", DataGenerator.generateYearValue(), false),
                        new EducationErrorData(null, null, null,
                                new ArrayList<>(Collections.singletonList("Учебное заведение слишком короткий (Минимум: 5 симв.).")),
                                null, null, null)},

                {new EducationData("1", "QWE", "qwerty", DataGenerator.generateCityValue(),
                        "qwerty", DataGenerator.generateYearValue(), false),
                        new EducationErrorData(null, null, null,
                                new ArrayList<>(Collections.singletonList("Учебное заведение слишком короткий (Минимум: 5 симв.).")),
                                null, null, null)},

                {new EducationData("1", "QWER", "qwerty", DataGenerator.generateCityValue(),
                        "qwerty", DataGenerator.generateYearValue(), false),
                        new EducationErrorData(null, null, null,
                                new ArrayList<>(Collections.singletonList("Учебное заведение слишком короткий (Минимум: 5 симв.).")),
                                null, null, null)},

                {new EducationData("1", "QWERTY", "    ", DataGenerator.generateCityValue(),
                        "qwerty", DataGenerator.generateYearValue(), false),
                        new EducationErrorData(null, null, null, null,
                                new ArrayList<>(Collections.singletonList("Заполните поле Факультет, специальность")), null, null)},

                {new EducationData("1", "QWERTY", "qwerty", "0",
                        "qwerty", DataGenerator.generateYearValue(), false),
                        new EducationErrorData(null, new ArrayList<>(Collections.singletonList("Выберите город")),
                                null, null,null, null, null)},

                {new EducationData("1", "QWERTY", "qwerty", DataGenerator.generateCityValue(),
                        " ", DataGenerator.generateYearValue(), false),
                        new EducationErrorData(null, null, null, null,
                                null, new ArrayList<>(Collections.singletonList("Заполните поле Диплом / Степень")), null)},

                {new EducationData("1", "QWERTY", "qwerty", DataGenerator.generateCityValue(),
                        "qwerty", "0", false),
                        new EducationErrorData(null, null, null, null,
                                null, null, new ArrayList<>(Collections.singletonList("Выберите год")))},

                {new EducationData("3", "", "qwerty", DataGenerator.generateCityValue(),
                        "qwerty", DataGenerator.generateYearValue(), false),
                        new EducationErrorData(null, null, null,
                                new ArrayList<>(Collections.singletonList("Заполните поле Учебное заведение")),
                                null, null, null)},

                {new EducationData("3", "Q", "qwerty", DataGenerator.generateCityValue(),
                        "qwerty", DataGenerator.generateYearValue(), false),
                        new EducationErrorData(null, null, null,
                                new ArrayList<>(Collections.singletonList("Учебное заведение слишком короткий (Минимум: 5 симв.).")),
                                null, null, null)},

                {new EducationData("3", "QW", "qwerty", DataGenerator.generateCityValue(),
                        "qwerty", DataGenerator.generateYearValue(), false),
                        new EducationErrorData(null, null, null,
                                new ArrayList<>(Collections.singletonList("Учебное заведение слишком короткий (Минимум: 5 симв.).")),
                                null, null, null)},

                {new EducationData("3", "QWE", "qwerty", DataGenerator.generateCityValue(),
                        "qwerty", DataGenerator.generateYearValue(), false),
                        new EducationErrorData(null, null, null,
                                new ArrayList<>(Collections.singletonList("Учебное заведение слишком короткий (Минимум: 5 симв.).")),
                                null, null, null)},

                {new EducationData("3", "QWER", "qwerty", DataGenerator.generateCityValue(),
                        "qwerty", DataGenerator.generateYearValue(), false),
                        new EducationErrorData(null, null, null,
                                new ArrayList<>(Collections.singletonList("Учебное заведение слишком короткий (Минимум: 5 симв.).")),
                                null, null, null)},

                {new EducationData("3", "QWERTY", " ", DataGenerator.generateCityValue(),
                        "qwerty", DataGenerator.generateYearValue(), false),
                        new EducationErrorData(null, null, null, null,
                                new ArrayList<>(Collections.singletonList("Заполните поле Факультет, специальность")), null, null)},

                {new EducationData("3", "QWERTY", "qwerty", "0",
                        "qwerty", DataGenerator.generateYearValue(), false),
                        new EducationErrorData(null, new ArrayList<>(Collections.singletonList("Выберите город")),
                                null, null,null, null, null)},

                {new EducationData("3", "QWERTY", "qwerty", DataGenerator.generateCityValue(),
                        " ", DataGenerator.generateYearValue(), false),
                        new EducationErrorData(null, null, null, null,
                                null, new ArrayList<>(Collections.singletonList("Заполните поле Диплом / Степень")), null)},

                {new EducationData("3", "QWERTY", "qwerty", DataGenerator.generateCityValue(),
                        "qwerty", "0", false),
                        new EducationErrorData(null, null, null, null,
                                null, null, new ArrayList<>(Collections.singletonList("Выберите год")))}
        };
    }

}
