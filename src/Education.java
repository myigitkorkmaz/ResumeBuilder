
// Stores a single education entry — school, degree, and graduation year.
public class Education {
    private String school;
    private String graduationYear;
    private String degree;

    public Education(String school, String graduationYear, String degree) {
        this.school = school;
        this.graduationYear = graduationYear;
        this.degree = degree;
    }

    public Education() {
        this.school = "";
        this.graduationYear = "";
        this.degree = "";
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setGraduatingYear(String graduationYear) {
        this.graduationYear = graduationYear;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getSchool() {
        return school;
    }

    public String getGraduationYear() {
        return graduationYear;
    }

    public String getDegree() {
        return degree;
    }
}
