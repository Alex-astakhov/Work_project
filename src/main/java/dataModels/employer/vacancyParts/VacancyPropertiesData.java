package dataModels.employer.vacancyParts;

import core.Constants;
import dataModels.DataGenerator;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by Asta on 31.01.2017.
 * Класс содержит доп. данные вакансии, а также методы генерации и сравнения этих данных
 */
public class VacancyPropertiesData extends DataGenerator {
    private boolean premium;
    private String responseDestinationValue;
    private Set<String> emailsDestination;
    private String applicationUrl;
    private boolean renewPublication;
    private boolean hot;
    private String hotDaysValue;

    public VacancyPropertiesData(boolean premium, String responseDestinationValue, Set<String> emailsDestination,
                                 String applicationUrl, boolean renewPublication, boolean hot, String hotDaysValue) {
        this.premium = premium;
        this.responseDestinationValue = responseDestinationValue;
        this.emailsDestination = emailsDestination;
        this.applicationUrl = applicationUrl;
        this.renewPublication = renewPublication;
        this.hot = hot;
        this.hotDaysValue = hotDaysValue;
    }

    public VacancyPropertiesData() {
        Random random = new Random();
        this.premium = random.nextBoolean();
        if (this.premium)
            this.responseDestinationValue = String.valueOf(random.nextInt(3)).replace("2",
                    "redirect_on_site_after_response");
        else
            this.responseDestinationValue = String.valueOf(random.nextInt(2));
        if (this.responseDestinationValue.equals("0")){
            int count = random.nextInt(5);
            this.emailsDestination = new HashSet<>();
            this.emailsDestination.add(Constants.EMPLOYER_EMAIL);
            for (int i = 0; i < count; i++) {
                boolean ok;
                do {
                    String[] emails = {"destination@gmail.com", "destination@mail.ru", "destination@yandex.ru",
                            "destination@list.ru", "destination@yahoo.com", "destination@rambler.ru"};
                    ok = this.emailsDestination.add(randomItemFromArray(emails));
                } while (!ok);
            }
        }
        if (this.responseDestinationValue.equals("redirect_on_site_after_response")){
            this.applicationUrl = "http://rabotadev.nur.kz/%D0%A4%D0%BB%D0%B8%D1%80%D1%87%D0%B8-jobs-14157";
        }
        this.renewPublication = random.nextBoolean();
        this.hot = false;//random.nextBoolean();
        if (this.hot){
            this.hotDaysValue = String.valueOf(random.nextInt(4) + 1);
        }
    }

    public boolean isPremium() {
        return premium;
    }

    public String getResponseDestinationValue() {
        return responseDestinationValue;
    }

    public Set<String> getDestinationEmails() {
        return emailsDestination;
    }

    public String getApplicationUrl() {
        return applicationUrl;
    }

    public boolean isRenewPublication() {
        return renewPublication;
    }

    public boolean isHot() {
        return hot;
    }

    public String getHotDaysValue() {
        return hotDaysValue;
    }

    public VacancyPropertiesData setPremium(boolean premium) {
        this.premium = premium;
        return this;
    }

    public VacancyPropertiesData setResponseDestinationValue(String responseDestinationValue) {
        this.responseDestinationValue = responseDestinationValue;
        return this;
    }

    public VacancyPropertiesData setEmailsDestination(Set<String> emailsDestination) {
        this.emailsDestination = emailsDestination;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            System.out.println("Object is null");
            return false;
        }
        if (!VacancyPropertiesData.class.isAssignableFrom(obj.getClass())) {
            System.out.println("Object is other class");
            return false;
        }
        final VacancyPropertiesData other = (VacancyPropertiesData) obj;
        if (this.premium != other.premium) {
            System.out.println("Expected premium: " + this.premium + ", Actual premium: " + other.premium);
            return false;
        }
        if ((this.responseDestinationValue == null) ? (other.responseDestinationValue != null) : !this.responseDestinationValue.equals(other.responseDestinationValue)) {
            System.out.println("Expected responseDestinationValue: " + this.responseDestinationValue + ", Actual responseDestinationValue: " + other.responseDestinationValue);
            return false;
        }
        if ((this.emailsDestination == null) ? (other.emailsDestination != null) : !(this.emailsDestination.containsAll(other.emailsDestination) && other.emailsDestination.containsAll(this.emailsDestination))) {
            System.out.println("Expected emailsDestination: " + this.emailsDestination.toString() + ", Actual emailsDestination: " + other.emailsDestination.toString());
            return false;
        }
        if ((this.applicationUrl == null) ? (other.applicationUrl != null) : !this.applicationUrl.equals(other.applicationUrl)) {
            System.out.println("Expected applicationUrl: " + this.applicationUrl + ", Actual applicationUrl: " + other.applicationUrl);
            return false;
        }
        if (this.renewPublication != other.renewPublication) {
            System.out.println("Expected renewPublication: " + this.renewPublication + ", Actual renewPublication: " + other.renewPublication);
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "VacancyPropertiesData{" +
                "premium=" + premium +
                ", responseDestinationValue='" + responseDestinationValue + '\'' +
                ", emailsDestination=" + emailsDestination +
                ", applicationUrl='" + applicationUrl + '\'' +
                ", renewPublication=" + renewPublication +
                ", hot=" + hot +
                ", hotDaysValue='" + hotDaysValue + '\'' +
                '}';
    }
}
