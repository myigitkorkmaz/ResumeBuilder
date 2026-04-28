import java.util.ArrayList;
public class Project {
    private String title;
    private String startDate;
    private String endDate;
    private ArrayList<String> descriptionBullets;

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
    public String getTitle() {
        return title;
    }


}
