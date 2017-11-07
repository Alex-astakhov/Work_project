package dataModels.employer;

import dataModels.DataGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by Asta on 15.06.2017.
 */
public class OrderData extends DataGenerator{
    private boolean dbAccessRegional;
    private int dbAccessType = -1;
    private int hotVacancies = 0;
    private int standardPublications = 0;
    private int premiumPublications = 0;
    private int anonymousVacancies = 0;
    private int premiumBrandingMonths = 0;
    private boolean mailingService = false;
    private int topLogoMonths = 0;
    private int serviceCounter = 0;

    private int[] hotVariants = {1, 4, 8, 16};
    private int[] publicationVariants = {1, 5, 10, 25, 50, 100};
    private int[] anonymousVariants = {1, 5, 10, 25};
    private int[] brandingMonths = {1, 3, 6, 12};

    public OrderData(boolean dbAccessRegional, int dbAccessType, int hotVacancies, int standardPublications, int premiumPublications,
                     int anonymousVacancies, int premiumBrandingMonths, int topLogoMonths) {
        this.dbAccessRegional = dbAccessRegional;
        this.dbAccessType = dbAccessType;
        this.hotVacancies = hotVacancies;
        this.standardPublications = standardPublications;
        this.premiumPublications = premiumPublications;
        this.anonymousVacancies = anonymousVacancies;
        this.premiumBrandingMonths = premiumBrandingMonths;
        this.topLogoMonths = topLogoMonths;
    }

    public OrderData() {
        Random random= new Random();
        if (random.nextBoolean()) {
            this.dbAccessRegional = random.nextBoolean();
            this.dbAccessType = random.nextInt(5);
            serviceCounter++;
        }
        if (random.nextBoolean()) {
            this.hotVacancies = randomItemFromArray(hotVariants);
            serviceCounter++;
        }
        if (random.nextBoolean()){
            if (random.nextBoolean())
                this.standardPublications = randomItemFromArray(publicationVariants);
            else
                this.premiumPublications = randomItemFromArray(publicationVariants);
            serviceCounter++;
        }
        if (random.nextBoolean()) {
            this.anonymousVacancies = randomItemFromArray(anonymousVariants);
            serviceCounter++;
        }
        if (random.nextBoolean()) {
            this.premiumBrandingMonths = randomItemFromArray(brandingMonths);
            serviceCounter++;
        }
        if (random.nextBoolean()) {
            this.mailingService = true;
            serviceCounter++;
        }
        if (random.nextBoolean()) {
            this.topLogoMonths = randomItemFromArray(brandingMonths);
            serviceCounter++;
        }
    }

    public OrderData(List<String> values){
        for (String value: values) {
            switch (value){
                case "20": this.dbAccessType = 0; dbAccessRegional = false; break;
                case "21": this.dbAccessType = 1; dbAccessRegional = false; break;
                case "22": this.dbAccessType = 2; dbAccessRegional = false; break;
                case "38": this.dbAccessType = 3; dbAccessRegional = false; break;
                case "39": this.dbAccessType = 4; dbAccessRegional = false; break;
                case "41": this.dbAccessType = 0; dbAccessRegional = true; break;
                case "42": this.dbAccessType = 1; dbAccessRegional = true; break;
                case "43": this.dbAccessType = 2; dbAccessRegional = true; break;
                case "44": this.dbAccessType = 3; dbAccessRegional = true; break;
                case "45": this.dbAccessType = 4; dbAccessRegional = true; break;
                case "7": this.hotVacancies = 1; break;
                case "8": this.hotVacancies = 4; break;
                case "9": this.hotVacancies = 8; break;
                case "32": this.hotVacancies = 16; break;
                case "1": this.standardPublications = 1; break;
                case "2": this.standardPublications = 5; break;
                case "3": this.standardPublications = 10; break;
                case "26": this.standardPublications = 25; break;
                case "27": this.standardPublications = 50; break;
                case "28": this.standardPublications = 100; break;
                case "4": this.premiumPublications = 1; break;
                case "5": this.premiumPublications = 5; break;
                case "6": this.premiumPublications = 10; break;
                case "29": this.premiumPublications = 25; break;
                case "30": this.premiumPublications = 50; break;
                case "31": this.premiumPublications = 100; break;
                case "10": this.anonymousVacancies = 1; break;
                case "11": this.anonymousVacancies = 5; break;
                case "12": this.anonymousVacancies = 10; break;
                case "33": this.anonymousVacancies = 25; break;
                case "17": this.premiumBrandingMonths = 1; break;
                case "18": this.premiumBrandingMonths = 3; break;
                case "19": this.premiumBrandingMonths = 6; break;
                case "37": this.premiumBrandingMonths = 12; break;
                case "23": this.topLogoMonths = 1; break;
                case "24": this.topLogoMonths = 3; break;
                case "25": this.topLogoMonths = 6; break;
                case "40": this.topLogoMonths = 12; break;
                case "46": this.mailingService = true; break;
            }
        }
    }

    public boolean isDbAccessRegional() {
        return dbAccessRegional;
    }

    public int getDbAccessType() {
        return dbAccessType;
    }

    public int getHotVacancies() {
        return hotVacancies;
    }

    public int getStandardPublications() {
        return standardPublications;
    }

    public int getPremiumPublications() {
        return premiumPublications;
    }

    public int getAnonymousVacancies() {
        return anonymousVacancies;
    }

    public int getPremiumBrandingMonths() {
        return premiumBrandingMonths;
    }

    public boolean isMailingService(){
        return mailingService;
    }

    public int getTopLogoMonths() {
        return topLogoMonths;
    }

    public int servicesCount(){
        return serviceCounter;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            System.out.println("Object is null");
            return false;
        }
        if (!OrderData.class.isAssignableFrom(obj.getClass())) {
            System.out.println("Object is other class");
            return false;
        }
        final OrderData other = (OrderData) obj;
        if (this.dbAccessRegional != other.dbAccessRegional) {
            System.out.println("Expected dbAccessRegional: " + this.dbAccessRegional + ", Actual dbAccessRegional: " + other.dbAccessRegional);
            return false;
        }
        if (this.dbAccessType != other.dbAccessType) {
            System.out.println("Expected dbAccessType: " + this.dbAccessType + ", Actual dbAccessType: " + other.dbAccessType);
            return false;
        }
        if (this.hotVacancies != other.hotVacancies) {
            System.out.println("Expected hotVacancies: " + this.hotVacancies + ", Actual hotVacancies: " + other.hotVacancies);
            return false;
        }
        if (this.standardPublications != other.standardPublications) {
            System.out.println("Expected standardPublications: " + this.standardPublications + ", Actual standardPublications: " + other.standardPublications);
            return false;
        }
        if (this.premiumPublications != other.premiumPublications) {
            System.out.println("Expected premiumPublications: " + this.premiumPublications + ", Actual premiumPublications: " + other.premiumPublications);
            return false;
        }
        if (this.anonymousVacancies != other.anonymousVacancies) {
            System.out.println("Expected anonymousVacancies: " + this.anonymousVacancies + ", Actual anonymousVacancies: " + other.anonymousVacancies);
            return false;
        }
        if (this.premiumBrandingMonths != other.premiumBrandingMonths) {
            System.out.println("Expected premiumBrandingMonths: " + this.premiumBrandingMonths + ", Actual premiumBrandingMonths: " + other.premiumBrandingMonths);
            return false;
        }
        if (this.topLogoMonths != other.topLogoMonths) {
            System.out.println("Expected topLogoMonths: " + this.topLogoMonths + ", Actual topLogoMonths: " + other.topLogoMonths);
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OrderData{" +
                "dbAccessRegional=" + dbAccessRegional +
                ", dbAccessType=" + dbAccessType +
                ", hotVacancies=" + hotVacancies +
                ", standardPublications=" + standardPublications +
                ", premiumPublications=" + premiumPublications +
                ", anonymousVacancies=" + anonymousVacancies +
                ", premiumBrandingMonths=" + premiumBrandingMonths +
                ", mailingService=" + mailingService +
                ", topLogoMonths=" + topLogoMonths +
                ", serviceCounter=" + serviceCounter +
                ", hotVariants=" + Arrays.toString(hotVariants) +
                ", publicationVariants=" + Arrays.toString(publicationVariants) +
                ", anonymousVariants=" + Arrays.toString(anonymousVariants) +
                ", brandingMonths=" + Arrays.toString(brandingMonths) +
                '}';
    }
}
