package dataModels.apiModels.v1.errorModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import dataModels.DataGenerator;

import java.util.List;

public class CoursesErrorData extends ErrorData {
    @SerializedName("title")
    @Expose
    public List<String> title = null;
    @SerializedName("url")
    @Expose
    public List<String> url = null;
    @SerializedName("graduation_year")
    @Expose
    public List<String> graduationYear = null;

    public CoursesErrorData(List<String> title, List<String> url, List<String> graduationYear) {
        this.title = title;
        this.url = url;
        this.graduationYear = graduationYear;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            System.out.println("Object is null");
            return false;
        }
        if (!CoursesErrorData.class.isAssignableFrom(obj.getClass())) {
            System.out.println("Object is other class");
            return false;
        }
        final CoursesErrorData other = (CoursesErrorData) obj;
        if ((this.title == null) ? (other.title != null) :
                !DataGenerator.collectionStrictEquals(this.title, other.title)) {
            System.out.println("Expected title: " + this.title + ", Actual title: " + other.title);
            return false;
        }
        if ((this.url == null) ? (other.url != null) :
                !DataGenerator.collectionStrictEquals(this.url, other.url)) {
            System.out.println("Expected url: " + this.url + ", Actual url: " + other.url);
            return false;
        }
        if ((this.graduationYear == null) ? (other.graduationYear != null) :
                !DataGenerator.collectionStrictEquals(this.graduationYear, other.graduationYear)) {
            System.out.println("Expected graduationYear: " + this.graduationYear + ", Actual graduationYear: " + other.graduationYear);
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CoursesErrorData{" +
                "title=" + title +
                ", url=" + url +
                ", graduationYear=" + graduationYear +
                '}';
    }
}
