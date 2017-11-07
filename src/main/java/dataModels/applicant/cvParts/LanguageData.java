package dataModels.applicant.cvParts;

import dataModels.DataGenerator;
import dataModels.apiModels.v1.errorModels.errorBlocks.LanguageDataParams;
import org.testng.annotations.DataProvider;

import java.util.*;

/**
 * Created by Alex Astakhov on 27.11.2016.
 * Класс содержит данные о знании языков, а также методы генерации и сравнения этих данных
 */
public class LanguageData extends DataGenerator {
    private String languageValue;
    private String languageLevelValue;

    public LanguageData() {
        Random random = new Random();
        this.languageValue = String.valueOf(random.nextInt(73) + 1);
        this.languageLevelValue = String.valueOf(random.nextInt(6) + 1);
    }

    public LanguageData(String languageValue, String languageLevelValue) {
        this.languageValue = languageValue;
        this.languageLevelValue = languageLevelValue;
    }

    public String getLanguageValue() {
        return languageValue;
    }

    public String getLanguageLevelValue() {
        return languageLevelValue;
    }

    public LanguageData setLanguageValue(String languageValue) {
        this.languageValue = languageValue;
        return this;
    }

    public LanguageData setLanguageLevelValue(String languageLevelValue) {
        this.languageLevelValue = languageLevelValue;
        return this;
    }

    public static Set<LanguageData> getRandomLanguages(int count){
        Map<String, LanguageData> languages = new HashMap<>();
        for (int i = 0; i < count; i++) {
            boolean ok;
            do{
                int size = languages.size();
                LanguageData data = new LanguageData();
                languages.put(data.getLanguageValue(), data);
                ok = size < languages.size();
            } while (!ok);
        }
        return new LinkedHashSet<>(languages.values());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            System.out.println("Object is null");
            return false;
        }
        if (!LanguageData.class.isAssignableFrom(obj.getClass())) {
            System.out.println("Object is other class");
            return false;
        }
        final LanguageData other = (LanguageData) obj;

       if ((this.languageValue == null) ? (other.languageValue != null) : !this.languageValue.equals(other.languageValue)) {
            System.out.println("Expected languageValue: " + this.languageValue + ", Actual languageValue: " + other.languageValue);
            return false;
        }
        if ((this.languageLevelValue == null) ? (other.languageLevelValue != null) : !this.languageLevelValue.equals(other.languageLevelValue)) {
            System.out.println("Expected languageLevelValue: " + this.languageLevelValue + ", Actual languageLevelValue: " + other.languageLevelValue);
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "languageValue: " + this.languageValue + ", languageLevelValue: " + this.languageLevelValue;
    }

    @Override
    public int hashCode() {
        return Integer.parseInt(this.languageValue);
    }

    @DataProvider
    public static Object[][] negative() {
        return new Object[][]{
                {new LanguageData("0", "0")},
                {new LanguageData().setLanguageValue("0")},
                {new LanguageData().setLanguageLevelValue("0")}
        };
    }

    @DataProvider
    public static Object[][] negativeMob() {
        return new Object[][]{
                {new LanguageData("", "")},
                {new LanguageData().setLanguageValue("")},
                {new LanguageData().setLanguageLevelValue("")}
        };
    }

    @DataProvider
    public static Object[][] negativeForApi() {
        return new Object[][]{
                {new HashSet<>(Collections.singleton(new LanguageData("0", "0"))),
                        new LanguageDataParams(false, false)},
                {new HashSet<>(Collections.singleton(new LanguageData().setLanguageValue("0"))),
                        new LanguageDataParams(false, true)},
                {new HashSet<>(Collections.singleton(new LanguageData().setLanguageLevelValue("0"))),
                        new LanguageDataParams(true, false)}
        };
    }
}

