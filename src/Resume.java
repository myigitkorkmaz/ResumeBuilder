import java.util.ArrayList;

public class Resume {
    private PersonalInfo personalInfo;
    private ArrayList<Education> education;
    private ArrayList<WorkExperience> workExperience;
    private ArrayList<Skill> skills;
    private ArrayList<Project> projects;
    private ArrayList<Hobbies> hobbies;
    private Template template;

    // Constructor
    public Resume() {
        this.education = new ArrayList<>();
        this.workExperience = new ArrayList<>();
        this.skills = new ArrayList<>();
        this.projects = new ArrayList<>();
        this.hobbies = new ArrayList<>();
    }

    // Setters
    public void setPersonalInfo(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    // Add methods
    public void addEducation(Education education) {
        this.education.add(education);
    }

    public void addWorkExperience(WorkExperience workExperience) {
        this.workExperience.add(workExperience);
    }

    public void addSkill(Skill skill) {
        this.skills.add(skill);
    }

    public void addProject(Project project) {
        this.projects.add(project);
    }

    public void addHobby(Hobbies hobby) {
        this.hobbies.add(hobby);
    }

    // Remove methods
    public void removeEducation(Education education) {
        this.education.remove(education);
    }

    public void removeWorkExperience(WorkExperience workExperience) {
        this.workExperience.remove(workExperience);
    }

    public void removeSkill(Skill skill) {
        this.skills.remove(skill);
    }

    public void removeProject(Project project) {
        this.projects.remove(project);
    }

    public void removeHobby(Hobbies hobby) {
        this.hobbies.remove(hobby);
    }

    // Getters
    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    public Template getTemplate() {
        return template;
    }

    public ArrayList<Education> getEducation() {
        return education;
    }

    public ArrayList<WorkExperience> getWorkExperience() {
        return workExperience;
    }

    public ArrayList<Skill> getSkills() {
        return skills;
    }

    public ArrayList<Project> getProjects() {
        return projects;
    }

    public ArrayList<Hobbies> getHobbies() {
        return hobbies;
    }
}