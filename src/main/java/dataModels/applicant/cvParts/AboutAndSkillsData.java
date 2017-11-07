package dataModels.applicant.cvParts;

import dataModels.DataGenerator;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by Asta on 23.12.2016.
 * Класс содержит данные о навыках и "о себе", а также методы генерации и сравнения этих данных
 */
public class AboutAndSkillsData extends DataGenerator {
    boolean skip;
    private String aboutMyself;
    private Set<String> skills;

    public AboutAndSkillsData(String aboutMyself, Set<String> skills) {
        this.aboutMyself = aboutMyself;
        this.skills = skills;
        this.skip = aboutMyself.isEmpty() && skills == null;
    }

    public AboutAndSkillsData(boolean noDataPossibility) {
        Random random = new Random();
        this.skip = noDataPossibility && random.nextBoolean();
        if (!this.skip) {
            this.skills = new HashSet<>();
            this.aboutMyself = "Данные о себе по состоянию на " + getCurrentDateAndTimeString();
            int skillCount = random.nextInt(5) + 1;
            for (int i = 0; i < skillCount; i++) {
                boolean success;
                do {
                    String[] variants = {"Разработка", "Укладка", "Готовка", "Вышивка крестиком", "Игра на нервах",
                            "Моделирование", "Юридическая консультация"};
                    success = this.skills.add(variants[random.nextInt(variants.length)]);
                } while (!success);
            }
        } else {
            this.skills = null;
            this.aboutMyself = "";
        }
    }

    public String getAboutMyself() {
        return aboutMyself;
    }

    public Set<String> getSkills() {
        return skills;
    }

    public boolean isSkip() {
        return skip;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            System.out.println("Object is null");
            return false;
        }
        if (!AboutAndSkillsData.class.isAssignableFrom(obj.getClass())) {
            System.out.println("Object is other class");
            return false;
        }
        final AboutAndSkillsData other = (AboutAndSkillsData) obj;

        if (this.skip != other.skip) {
            System.out.println("Expected skip: " + this.skip + ", Actual skip: " + other.skip);
            return false;
        }

        if ((this.aboutMyself == null) ? (other.aboutMyself != null) : !this.aboutMyself.equals(other.aboutMyself)) {
            System.out.println("Expected aboutMyself: " + this.aboutMyself + ", Actual aboutMyself: " + other.aboutMyself);
            return false;
        }
        if ((this.skills == null) ? (other.skills != null) : !(this.skills.containsAll(other.skills) && other.skills.containsAll(this.skills))) {
            System.out.println("Expected skills: " + this.skills + ", Actual skills: " + other.skills);
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AboutAndSkillsData{" +
                "skip=" + skip +
                ", aboutMyself='" + aboutMyself + '\'' +
                ", skills=" + skills +
                '}';
    }
}
