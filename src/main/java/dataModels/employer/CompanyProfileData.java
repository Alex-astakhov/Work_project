package dataModels.employer;

import dataModels.DataGenerator;

import java.util.Random;

/**
 * Created by Asta on 13.03.2017.
 * Класс содержит данные о компании, а также методы генерации и сравнения этих данных
 */
public class CompanyProfileData extends DataGenerator {
    private String companyTitle;
    private String bin;
    private String staffAmountValue;
    private String industryValue;
    private String site;
    private String aboutCompany;
    private String cityValue;
    private String address;
    private String phone;

    public CompanyProfileData(String companyTitle, String bin, String staffAmountValue, String industryValue,
                              String site, String aboutCompany, String cityValue, String address, String phone) {
        this.companyTitle = companyTitle;
        this.bin = bin;
        this.staffAmountValue = staffAmountValue;
        this.industryValue = industryValue;
        this.site = site;
        this.aboutCompany = aboutCompany;
        this.cityValue = cityValue;
        this.address = address;
        this.phone = phone;
    }

    public CompanyProfileData() {
        Random random = new Random();
        String[] companyTitles = {"Google", "Яндекс", "Веселка", "Ромашка", "Nur.kz"};
        this.companyTitle = randomItemFromArray(companyTitles);
        this.bin = String.valueOf(random.nextInt(899999) + 100000) + String.valueOf(random.nextInt(89999) + 10000);
        this.staffAmountValue = String.valueOf(random.nextInt(7) + 1);
        this.industryValue = String.valueOf(random.nextInt(27) + 1);
        String[] sites = {"https://google.com/", "https://yandex.ua/", "http://веселка.уа", "http://ромашка.орг", "http://nur.kz"};
        this.site = randomItemFromArray(sites);
        this.aboutCompany = "Описание компании от " + getCurrentDateAndTimeString();
        this.cityValue = generateCityValue();
        String[] streets = {"проспект Победы", "ул. Тюратамская", "бул. Дружюы народов", "переулок Нефтянников", "Колокольный проезд"};
        this.address = randomItemFromArray(streets) + ", " + random.nextInt(100);
        this.phone = generatePhoneNumber();
    }

    public String getCompanyTitle() {
        return companyTitle;
    }

    public String getBin() {
        return bin;
    }

    public String getStaffAmountValue() {
        return staffAmountValue;
    }

    public String getIndustryValue() {
        return industryValue;
    }

    public String getSite() {
        return site;
    }

    public String getAboutCompany() {
        return aboutCompany;
    }

    public String getCityValue() {
        return cityValue;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "CompanyProfileData{" +
                "companyTitle='" + companyTitle + '\'' +
                ", bin='" + bin + '\'' +
                ", staffAmountValue='" + staffAmountValue + '\'' +
                ", industryValue='" + industryValue + '\'' +
                ", site='" + site + '\'' +
                ", aboutCompany='" + aboutCompany + '\'' +
                ", cityValue='" + cityValue + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            System.out.println("Object is null");
            return false;
        }
        if (!CompanyProfileData.class.isAssignableFrom(obj.getClass())) {
            System.out.println("Object is other class");
            return false;
        }
        final CompanyProfileData other = (CompanyProfileData) obj;
        if ((this.companyTitle == null) ? (other.companyTitle != null) : !this.companyTitle.equals(other.companyTitle)) {
            System.out.println("Expected companyTitle: " + this.companyTitle + ", Actual companyTitle: " + other.companyTitle);
            return false;
        }
        if ((this.bin == null) ? (other.bin != null) : !this.bin.equals(other.bin)) {
            System.out.println("Expected bin: " + this.bin + ", Actual bin: " + other.bin);
            return false;
        }
        if ((this.staffAmountValue == null) ? (other.staffAmountValue != null) : !this.staffAmountValue.equals(other.staffAmountValue)) {
            System.out.println("Expected staffAmountValue: " + this.staffAmountValue + ", Actual staffAmountValue: " + other.staffAmountValue);
            return false;
        }
        if ((this.industryValue == null) ? (other.industryValue != null) : !this.industryValue.equals(other.industryValue)) {
            System.out.println("Expected industryValue: " + this.industryValue + ", Actual industryValue: " + other.industryValue);
            return false;
        }
        if ((this.site == null) ? (other.site != null) : !this.site.equals(other.site)) {
            System.out.println("Expected site: " + this.site + ", Actual site: " + other.site);
            return false;
        }
        if ((this.aboutCompany == null) ? (other.aboutCompany != null) : !this.aboutCompany.equals(other.aboutCompany)) {
            System.out.println("Expected aboutCompany: " + this.aboutCompany + ", Actual aboutCompany: " + other.aboutCompany);
            return false;
        }
        if ((this.cityValue == null) ? (other.cityValue != null) : !this.cityValue.equals(other.cityValue)) {
            System.out.println("Expected cityValue: " + this.cityValue + ", Actual cityValue: " + other.cityValue);
            return false;
        }
        if ((this.address == null) ? (other.address != null) : !this.address.equals(other.address)) {
            System.out.println("Expected address: " + this.address + ", Actual address: " + other.address);
            return false;
        }
        if ((this.phone == null) ? (other.phone != null) : !this.phone.equals(other.phone)) {
            System.out.println("Expected phone: " + this.phone + ", Actual phone: " + other.phone);
            return false;
        }
        return true;
    }
}
