public class ResumeBuilder {
    private Resume resume;

    public ResumeBuilder() {
        this.resume = new Resume();
    }

    // Fluent setters (method chaining)
    public ResumeBuilder setPersonalInfo(PersonalInfo personalInfo) {
        resume.setPersonalInfo(personalInfo);
        return this;
    }

    public ResumeBuilder setTemplate(Template template) {
        resume.setTemplate(template);
        return this;
    }

    public ResumeBuilder addEducation(Education education) {
        resume.addEducation(education);
        return this;
    }

    public ResumeBuilder addWorkExperience(WorkExperience workExperience) {
        resume.addWorkExperience(workExperience);
        return this;
    }

    public ResumeBuilder addSkill(Skill skill) {
        resume.addSkill(skill);
        return this;
    }

    public ResumeBuilder addProject(Project project) {
        resume.addProject(project);
        return this;
    }

    public ResumeBuilder addHobby(Hobbies hobby) {
        resume.addHobby(hobby);
        return this;
    }

    // Remove methods with chaining
    public ResumeBuilder removeEducation(int index) {
        if (index >= 0 && index < resume.getEducation().size()) {
            resume.removeEducation(resume.getEducation().get(index));
        }
        return this;
    }

    public ResumeBuilder removeWorkExperience(int index) {
        if (index >= 0 && index < resume.getWorkExperience().size()) {
            resume.removeWorkExperience(resume.getWorkExperience().get(index));
        }
        return this;
    }

    public ResumeBuilder removeSkill(int index) {
        if (index >= 0 && index < resume.getSkills().size()) {
            resume.removeSkill(resume.getSkills().get(index));
        }
        return this;
    }

    public ResumeBuilder removeProject(int index) {
        if (index >= 0 && index < resume.getProjects().size()) {
            resume.removeProject(resume.getProjects().get(index));
        }
        return this;
    }

    public ResumeBuilder removeHobby(int index) {
        if (index >= 0 && index < resume.getHobbies().size()) {
            resume.removeHobby(resume.getHobbies().get(index));
        }
        return this;
    }

    // Clear all sections (useful for UI reset)
    public ResumeBuilder clearEducation() {
        resume.getEducation().clear();
        return this;
    }

    public ResumeBuilder clearWorkExperience() {
        resume.getWorkExperience().clear();
        return this;
    }

    public ResumeBuilder clearSkills() {
        resume.getSkills().clear();
        return this;
    }

    public ResumeBuilder clearProjects() {
        resume.getProjects().clear();
        return this;
    }

    public ResumeBuilder clearHobbies() {
        resume.getHobbies().clear();
        return this;
    }

    // Build method
    public Resume build() {
        return resume;
    }

    // Check if resume is valid/complete (useful before export)
    public boolean isValid() {
        return resume.getPersonalInfo() != null
                && resume.getPersonalInfo().getName() != null
                && !resume.getPersonalInfo().getName().isEmpty();
    }

    // Reset builder for new resume
    public void reset() {
        this.resume = new Resume();
    }

    // Get current resume (for preview in UI)
    public Resume getCurrentResume() {
        return resume;
    }
}
