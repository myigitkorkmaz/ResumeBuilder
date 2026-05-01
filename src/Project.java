import java.util.ArrayList;
// Represents a single project — title, dates, and a list of description bullets.
public class Project {
    private String title;
    private String startDate;
    private String endDate;
    private ArrayList<String> descriptionBullets;

    public Project(String title, String startDate, String endDate) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.descriptionBullets = new ArrayList<>();
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
    public String getTitle()                          { return title; }
    public String getStartDate()                      { return startDate; }
    public String getEndDate()                        { return endDate; }
    public ArrayList<String> getDescriptionBullets()  { return descriptionBullets; }
}
