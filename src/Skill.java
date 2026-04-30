

// A single skill entry. Kept simple on purpose — just a name/description string.
public class Skill {
    private String description;

    public Skill(String description) {
        this.description = description;
    }

    public Skill() {
        this.description = "";
    }


    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
}
