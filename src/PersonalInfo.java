

// Holds all the contact details that go at the top of the resume — name, email, phone, LinkedIn, GitHub.
public class PersonalInfo {
    private String name;
    private String email;
    private String phoneNumber;
    private String LinkedIn;
    private String gitHub;

    public PersonalInfo(String name, String email, String phoneNumber, String LinkedIn, String gitHub) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.LinkedIn = LinkedIn;
        this.gitHub = gitHub;
    } 

    public PersonalInfo() {
        this.name = "";
        this.email = "";
        this.phoneNumber = "";
        this.LinkedIn = "";
        this.gitHub = "";
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setLinkedIn(String LinkedIn) {
        this.LinkedIn = LinkedIn;
    }

    public void setGitHub(String gitHub) {
        this.gitHub = gitHub;
    }



    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getLinkedIn() {
        return LinkedIn;
    }
    public String getGitHub() {
        return gitHub;
    }


}
