package dataModels.apiModels.v1.resume;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import dataModels.applicant.cvParts.TrainingData;

public class Course {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("resume_id")
    @Expose
    public Integer resumeId;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("organizer")
    @Expose
    public String organizer;
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("graduation_year")
    @Expose
    public String graduationYear;

    public boolean validate(){
        if (this.id == null || this.id.equals(0)){
            System.out.println("id is empty!");
            return false;
        }
        if (this.resumeId == null || this.resumeId.equals(0)){
            System.out.println("resumeId is empty!");
            return false;
        }
        if (this.title == null || this.title.equals("")){
            System.out.println("resumeId is empty!");
            return false;
        }
        if (this.organizer == null || this.organizer.equals("")){
            System.out.println("organizer is empty!");
            return false;
        }
        if (this.url == null || this.url.equals("")){
            System.out.println("url is empty!");
            return false;
        }
        if (this.graduationYear == null || this.graduationYear.equals("") || this.graduationYear.equals("0")){
            System.out.println("graduationYear is empty!");
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
        if (!Course.class.isAssignableFrom(obj.getClass())) {
            System.out.println("Object is other class");
            return false;
        }
        final Course other = (Course) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            System.out.println("Expected id: " + this.id + ", Actual id: " + other.id);
            return false;
        }
        if ((this.resumeId == null) ? (other.resumeId != null) : !this.resumeId.equals(other.resumeId)) {
            System.out.println("Expected resumeId: " + this.resumeId + ", Actual resumeId: " + other.resumeId);
            return false;
        }
        if ((this.title == null) ? (other.title != null) : !this.title.equals(other.title)) {
            System.out.println("Expected title: " + this.title + ", Actual title: " + other.title);
            return false;
        }
        if ((this.organizer == null) ? (other.organizer != null) : !this.organizer.equals(other.organizer)) {
            System.out.println("Expected organizer: " + this.organizer + ", Actual organizer: " + other.organizer);
            return false;
        }
        if ((this.url == null) ? (other.url != null) : !this.url.equals(other.url)) {
            System.out.println("Expected url: " + this.url + ", Actual url: " + other.url);
            return false;
        }
        if ((this.graduationYear == null) ? (other.graduationYear != null) : !this.graduationYear.equals(other.graduationYear)) {
            System.out.println("Expected graduationYear: " + this.graduationYear + ", Actual graduationYear: " + other.graduationYear);
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", resumeId='" + resumeId + '\'' +
                ", title='" + title + '\'' +
                ", organizer='" + organizer + '\'' +
                ", url='" + url + '\'' +
                ", graduationYear='" + graduationYear + '\'' +
                '}';
    }

    public TrainingData getTrainingData(){
        return new TrainingData(this.title, this.organizer, this.url, this.graduationYear);
    }
}
