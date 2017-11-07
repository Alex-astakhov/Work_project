package dataModels.apiModels.v1.resume;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Skill {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("title")
    @Expose
    public String title;

    public boolean validate(){
        if (this.id == null || this.id.equals(0)){
            System.out.println("id is empty!");
            return false;
        }
        if (this.title == null || this.title.equals("")){
            System.out.println("title is empty!");
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
        if (!Skill.class.isAssignableFrom(obj.getClass())) {
            System.out.println("Object is other class");
            return false;
        }
        final Skill other = (Skill) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            System.out.println("Expected id: " + this.id + ", Actual id: " + other.id);
            return false;
        }
        if ((this.title == null) ? (other.title != null) : !this.title.equals(other.title)) {
            System.out.println("Expected title: " + this.title + ", Actual title: " + other.title);
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
