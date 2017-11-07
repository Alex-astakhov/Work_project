package dataModels;

import java.util.Random;

/**
 * Created by Asta on 27.02.2017.
 * Класс содержит данные о подписках на новости, а также методы генерации и сравнения этих данных
 */
public class SubscriptionAdditionalData extends DataGenerator {
    private boolean recommendedSubscription;
    private boolean statistic;
    private boolean dailyStatistic;
    private boolean weeklyStatistic;

    public SubscriptionAdditionalData(boolean recommendedSubscription, boolean statistic, boolean dailyStatistic, boolean weeklyStatistic) {
        this.recommendedSubscription = recommendedSubscription;
        this.statistic = statistic;
        this.dailyStatistic = dailyStatistic;
        this.weeklyStatistic = weeklyStatistic;
    }

    public SubscriptionAdditionalData() {
        Random random = new Random();
        this.recommendedSubscription = random.nextBoolean();
        this.statistic = random.nextBoolean();
        this.dailyStatistic = random.nextBoolean();
        this.weeklyStatistic = random.nextBoolean();
    }

    public boolean isRecommendedSubscription() {
        return recommendedSubscription;
    }

    public boolean isStatistic() {
        return statistic;
    }

    public boolean isDailyStatistic() {
        return dailyStatistic;
    }

    public boolean isWeeklyStatistic() {
        return weeklyStatistic;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            System.out.println("Object is null");
            return false;
        }
        if (!SubscriptionAdditionalData.class.isAssignableFrom(obj.getClass())) {
            System.out.println("Object is other class");
            return false;
        }
        final SubscriptionAdditionalData other = (SubscriptionAdditionalData) obj;
        if (this.recommendedSubscription != other.recommendedSubscription) {
            System.out.println("Expected recommendedSubscription: " + this.recommendedSubscription + ", Actual recommendedSubscription: " + other.recommendedSubscription);
            return false;
        }
        if (this.statistic != other.statistic) {
            System.out.println("Expected statistic: " + this.statistic + ", Actual statistic: " + other.statistic);
            return false;
        }
        if (this.dailyStatistic != other.dailyStatistic) {
            System.out.println("Expected dailyStatistic: " + this.dailyStatistic + ", Actual dailyStatistic: " + other.dailyStatistic);
            return false;
        }
        if (this.weeklyStatistic != other.weeklyStatistic) {
            System.out.println("Expected weeklyStatistic: " + this.weeklyStatistic + ", Actual weeklyStatistic: " + other.weeklyStatistic);
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SubscriptionAdditionalData{" +
                "recommendedSubscription=" + recommendedSubscription +
                ", statistic=" + statistic +
                ", dailyStatistic=" + dailyStatistic +
                ", weeklyStatistic=" + weeklyStatistic +
                '}';
    }
}
