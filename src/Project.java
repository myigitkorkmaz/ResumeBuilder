import java.util.ArrayList;
public class Project {
    private String title;
    private String startDate;
    private String endDate;
    private ArrayList<String> descriptionBullets;

    setTitle(String title) {
        this.title = title;
    }
    setStartDate(String startDate) {
        this.startDate = startDate;
    }
    setEndDate(String endDate) {
        this.endDate = endDate;
    }
    addDescriptionBullet(String bullet) {
        this.descriptionBullets.add(bullet);
    }
    removeDescriptionBullet(String bullet) {
        this.descriptionBullets.remove(bullet);
    }
    public String getTitle() {
        return title;
    }


}
