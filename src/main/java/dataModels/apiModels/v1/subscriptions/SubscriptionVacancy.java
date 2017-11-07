package dataModels.apiModels.v1.subscriptions;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import dataModels.DataGenerator;

import java.util.Random;

/**
 * Created by Asta on 14.07.2017.
 */
public class SubscriptionVacancy extends DataGenerator{
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("tag_id")
    @Expose
    public Integer tagId;
    @SerializedName("city_id")
    @Expose
    public Integer cityId;
    @SerializedName("interval_send")
    @Expose
    public Integer intervalSend;
    @SerializedName("params_search")
    @Expose
    public Integer paramsSearch;
    @SerializedName("created")
    @Expose
    public String created;
    @SerializedName("status")
    @Expose
    public Integer status;
    @SerializedName("status_push")
    @Expose
    public Integer statusPush;
    @SerializedName("salary")
    @Expose
    public Integer salary;
    @SerializedName("experience")
    @Expose
    public Integer experience;
    @SerializedName("availability_id")
    @Expose
    public Integer availabilityId;


    public SubscriptionVacancy() {
        Random random = new Random();
        String[] vacancies = {"Бухгалтер", "Экономист", "Директор по персоналу", "Php разработчик", "Экскаваторщик"};
        int index = random.nextInt(vacancies.length);
        this.title = vacancies[index];
        int[] tags = {765, 4733, 5314, 5495, 3519};
        this.tagId = tags[index];
        this.cityId = 0;
        if (random.nextBoolean())
            this.cityId = Integer.parseInt(generateCityValue());
        this.intervalSend = random.nextInt(3);
        this.paramsSearch = 1;
        this.status = 0;
        this.salary = 0;
        if (random.nextBoolean())
            this.salary = random.nextInt(200000) + 10;
        this.experience = 0;
        if (random.nextBoolean())
            this.experience = random.nextInt(5) + 1;
        this.availabilityId = 0;
        if (random.nextBoolean()) {
            int a = random.nextInt(4);
            if (a == 3)
                a = 5;
            this.availabilityId = a;
        }
    }

    public boolean validate(){
        if (this.id == null || this.id.equals(0)){
            System.out.println("id is empty!");
            return false;
        }
        if (this.title == null || this.title.equals("")){
            System.out.println("title is empty!");
            return false;
        }
        if (this.tagId == null || this.tagId.equals(0)){
            System.out.println("tagId is empty!");
            return false;
        }
        if (this.cityId == null){
            System.out.println("tagId is empty!");
            return false;
        }
        if (this.intervalSend == null){
            System.out.println("intervalSend is empty!");
            return false;
        }
        if (this.paramsSearch == null){
            System.out.println("paramsSearch is empty!");
            return false;
        }
        if (this.created == null || this.created.equals("0000-00-00")){
            System.out.println("created is empty or 0!");
            return false;
        }
        if (this.status == null){
            System.out.println("status is empty!");
            return false;
        }
        if (this.statusPush == null){
            System.out.println("statusPush is empty!");
            return false;
        }
        if (this.salary == null){
            System.out.println("salary is empty!");
            return false;
        }
        if (this.experience == null){
            System.out.println("experience is empty!");
            return false;
        }
        if (this.availabilityId == null){
            System.out.println("availabilityId is empty!");
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            System.out.println("Object is null");
            return false;
        }
        if (!SubscriptionVacancy.class.isAssignableFrom(obj.getClass())) {
            System.out.println("Object is other class");
            return false;
        }
        final SubscriptionVacancy other = (SubscriptionVacancy) obj;
        /*if (this.id != other.id) {
            System.out.println("Expected id: " + this.id + ", Actual id: " + other.id);
            return false;
        }*/
        if ((this.title == null) ? (other.title != null) : !this.title.equals(other.title)) {
            System.out.println("Expected title: " + this.title + ", Actual title: " + other.title);
            return false;
        }
        if ((this.tagId == null) ? (other.tagId != null) : !this.tagId.equals(other.tagId)) {
            System.out.println("Expected tagId: " + this.tagId + ", Actual tagId: " + other.tagId);
            return false;
        }
        if ((this.cityId == null) ? (other.cityId != null) : !this.cityId.equals(other.cityId)) {
            System.out.println("Expected cityId: " + this.cityId + ", Actual cityId: " + other.cityId);
            return false;
        }
        if ((this.intervalSend == null) ? (other.intervalSend != null) : !this.intervalSend.equals(other.intervalSend)) {
            System.out.println("Expected intervalSend: " + this.intervalSend + ", Actual intervalSend: " + other.intervalSend);
            return false;
        }
        if ((this.paramsSearch == null) ? (other.paramsSearch != null) : !this.paramsSearch.equals(other.paramsSearch)) {
            System.out.println("Expected paramsSearch: " + this.paramsSearch + ", Actual paramsSearch: " + other.paramsSearch);
            return false;
        }
       /* if ((this.created == null) ? (other.created != null) : !this.created.equals(other.created)) {
            System.out.println("Expected created: " + this.created + ", Actual created: " + other.created);
            return false;
        }*/
        if ((this.status == null) ? (other.status != null) : !this.status.equals(other.status)) {
            System.out.println("Expected status: " + this.status + ", Actual status: " + other.status);
            return false;
        }
        if ((this.salary == null) ? (other.salary != null) : !this.salary.equals(other.salary)) {
            System.out.println("Expected salary: " + this.salary + ", Actual salary: " + other.salary);
            return false;
        }
        if ((this.experience == null) ? (other.experience != null) : !this.experience.equals(other.experience)) {
            System.out.println("Expected experience: " + this.experience + ", Actual experience: " + other.experience);
            return false;
        }
        if ((this.availabilityId == null) ? (other.availabilityId != null) : !this.availabilityId.equals(other.availabilityId)) {
            System.out.println("Expected availabilityId: " + this.availabilityId + ", Actual availabilityId: " + other.availabilityId);
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SubscriptionVacancy{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", tagId=" + tagId +
                ", cityId=" + cityId +
                ", intervalSend=" + intervalSend +
                ", paramsSearch=" + paramsSearch +
                ", created='" + created + '\'' +
                ", status=" + status +
                ", statusPush=" + statusPush +
                ", salary=" + salary +
                ", experience=" + experience +
                ", availabilityId=" + availabilityId +
                '}';
    }


}
