import java.util.ArrayList;


public class WorkExperience {
    private String company;
    private String title;
    private String startDate;
    private String endDate;
    private ArrayList<String> descriptionBullets;

    public WorkExperience() {
        this.descriptionBullets = new ArrayList<>();
    }

    public void setCompany(String company) {
        this.company = company;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public void addDescriptionBullet(String bullet) {
        this.descriptionBullets.add(bullet);
    }
    public void removeDescriptionBullet(String bullet) {
        this.descriptionBullets.remove(bullet);
    }

    public String getCompany() {
        return company;
    }

    public String getTitle() {
        return title;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public ArrayList<String> getDescriptionBullets() {
        return descriptionBullets;
    }

}
