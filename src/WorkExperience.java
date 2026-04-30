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

    public WorkExperience(String company, String title, String startDate, String endDate, ArrayList<String> descriptionBullets) {
        this.company = company;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.descriptionBullets = descriptionBullets;
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
