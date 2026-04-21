import javax.swing.*;
import java.awt.*;

public class ResumeUI extends JFrame {

    private JTabbedPane tabbedPane;

    // Personal Info
    private JTextField nameField, emailField, phoneField, linkedInField, gitHubField;

    // Education
    private JTextField schoolField, degreeField, gradYearField;
    private JTextArea educationList;

    // Work Experience
    private JTextField companyField, titleField, startDateField, endDateField, workBulletField;
    private JTextArea workList;

    // Projects
    private JTextField projectTitleField, projectStartField, projectEndField, projectBulletField;
    private JTextArea projectList;

    // Skills
    private JTextField skillField;
    private JTextArea skillList;

    // Template
    private JComboBox<String> templateComboBox;

    public ResumeUI() {
        super("Student Resume Builder");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(620, 520);
        setLayout(new BorderLayout());

        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Personal Info",  buildPersonalInfoPanel());
        tabbedPane.addTab("Education",      buildEducationPanel());
        tabbedPane.addTab("Work Experience",buildWorkExperiencePanel());
        tabbedPane.addTab("Projects",       buildProjectPanel());
        tabbedPane.addTab("Skills",         buildSkillsPanel());
        tabbedPane.addTab("Template",       buildTemplatePanel());
        tabbedPane.addTab("Export",         buildExportPanel());

        add(tabbedPane, BorderLayout.CENTER);
    }

    //Tab building methods

    private JPanel buildPersonalInfoPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        nameField     = new JTextField(25);
        emailField    = new JTextField(25);
        phoneField    = new JTextField(25);
        linkedInField = new JTextField(25);
        gitHubField   = new JTextField(25);

        addLabeledField(panel, "Name:",         nameField);
        addLabeledField(panel, "Email:",        emailField);
        addLabeledField(panel, "Phone Number:", phoneField);
        addLabeledField(panel, "LinkedIn:",     linkedInField);
        addLabeledField(panel, "GitHub:",       gitHubField);

        panel.add(Box.createVerticalStrut(10));
        JButton saveBtn = new JButton("Save Personal Info");
        saveBtn.addActionListener(e -> savePersonalInfo());
        panel.add(saveBtn);

        return panel;
    }

    private JPanel buildEducationPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));

        schoolField   = new JTextField(25);
        degreeField   = new JTextField(25);
        gradYearField = new JTextField(25);

        addLabeledField(form, "School:",          schoolField);
        addLabeledField(form, "Degree:",          degreeField);
        addLabeledField(form, "Graduating Year:", gradYearField);

        form.add(Box.createVerticalStrut(10));
        JButton addBtn = new JButton("Add Education");
        addBtn.addActionListener(e -> addEducation());
        form.add(addBtn);

        educationList = new JTextArea(6, 30);
        educationList.setEditable(false);

        panel.add(form, BorderLayout.NORTH);
        panel.add(new JScrollPane(educationList), BorderLayout.CENTER);
        return panel;
    }

    private JPanel buildWorkExperiencePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));

        companyField    = new JTextField(25);
        titleField      = new JTextField(25);
        startDateField  = new JTextField(25);
        endDateField    = new JTextField(25);
        workBulletField = new JTextField(25);

        addLabeledField(form, "Company:",            companyField);
        addLabeledField(form, "Title:",              titleField);
        addLabeledField(form, "Start Date:",         startDateField);
        addLabeledField(form, "End Date:",           endDateField);
        addLabeledField(form, "Description Bullet:", workBulletField);

        form.add(Box.createVerticalStrut(10));
        JButton addBtn = new JButton("Add Work Experience");
        addBtn.addActionListener(e -> addWorkExperience());
        form.add(addBtn);

        workList = new JTextArea(5, 30);
        workList.setEditable(false);

        panel.add(form, BorderLayout.NORTH);
        panel.add(new JScrollPane(workList), BorderLayout.CENTER);
        return panel;
    }

    private JPanel buildProjectPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));

        projectTitleField  = new JTextField(25);
        projectStartField  = new JTextField(25);
        projectEndField    = new JTextField(25);
        projectBulletField = new JTextField(25);

        addLabeledField(form, "Project Title:",      projectTitleField);
        addLabeledField(form, "Start Date:",         projectStartField);
        addLabeledField(form, "End Date:",           projectEndField);
        addLabeledField(form, "Description Bullet:", projectBulletField);

        form.add(Box.createVerticalStrut(10));
        JButton addBtn = new JButton("Add Project");
        addBtn.addActionListener(e -> addProject());
        form.add(addBtn);

        projectList = new JTextArea(5, 30);
        projectList.setEditable(false);

        panel.add(form, BorderLayout.NORTH);
        panel.add(new JScrollPane(projectList), BorderLayout.CENTER);
        return panel;
    }

    private JPanel buildSkillsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));

        skillField = new JTextField(25);
        addLabeledField(form, "Skill:", skillField);

        form.add(Box.createVerticalStrut(10));
        JButton addBtn = new JButton("Add Skill");
        addBtn.addActionListener(e -> addSkill());
        form.add(addBtn);

        skillList = new JTextArea(8, 30);
        skillList.setEditable(false);

        panel.add(form, BorderLayout.NORTH);
        panel.add(new JScrollPane(skillList), BorderLayout.CENTER);
        return panel;
    }

    private JPanel buildTemplatePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        templateComboBox = new JComboBox<>(new String[]{"Classic", "Modern", "Minimal"});

        panel.add(new JLabel("Select a Template:"));
        panel.add(Box.createVerticalStrut(6));
        panel.add(templateComboBox);
        panel.add(Box.createVerticalStrut(10));

        JButton selectBtn = new JButton("Apply Template");
        selectBtn.addActionListener(e -> selectTemplate());
        panel.add(selectBtn);

        return panel;
    }

    private JPanel buildExportPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        panel.add(new JLabel("Export your resume:"));
        panel.add(Box.createVerticalStrut(12));

        JButton pdfBtn = new JButton("Export as PDF");
        pdfBtn.addActionListener(e -> exportResume("PDF"));
        panel.add(pdfBtn);

        panel.add(Box.createVerticalStrut(8));

        JButton txtBtn = new JButton("Export as TXT");
        txtBtn.addActionListener(e -> exportResume("TXT"));
        panel.add(txtBtn);

        return panel;
    }

    //Action methods
        String name  = nameField.getText().trim();
        String email = emailField.getText().trim();

        if (name.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Name and Email are required.",
                "Missing Fields", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // TODO: PersonalInfo info = new PersonalInfo(name, email,
        //           phoneField.getText(), linkedInField.getText(), gitHubField.getText());
        // TODO: builder.setPersonalInfo(info);

        JOptionPane.showMessageDialog(this,
            "Personal info saved!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void addEducation() {
        String school = schoolField.getText().trim();
        String degree = degreeField.getText().trim();
        String year   = gradYearField.getText().trim();

        if (school.isEmpty() || degree.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "School and Degree are required.",
                "Missing Fields", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // TODO: Education edu = new Education(school, degree, year);
        // TODO: builder.addEducation(edu);

        educationList.append(school + " — " + degree + " (" + year + ")\n");
        schoolField.setText(""); degreeField.setText(""); gradYearField.setText("");
    }

    private void addWorkExperience() {
        String company = companyField.getText().trim();
        String title   = titleField.getText().trim();

        if (company.isEmpty() || title.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Company and Title are required.",
                "Missing Fields", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // TODO: WorkExperience job = new WorkExperience(
        //           company, title, startDateField.getText(), endDateField.getText());
        // TODO: job.addDescriptionBullet(workBulletField.getText());
        // TODO: builder.addWorkExperience(job);

        workList.append(company + " — " + title
            + " (" + startDateField.getText() + " to " + endDateField.getText() + ")\n");
        companyField.setText(""); titleField.setText("");
        startDateField.setText(""); endDateField.setText(""); workBulletField.setText("");
    }

    private void addProject() {
        String projTitle = projectTitleField.getText().trim();

        if (projTitle.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Project title is required.",
                "Missing Fields", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // TODO: Project proj = new Project(projTitle,
        //           projectStartField.getText(), projectEndField.getText());
        // TODO: proj.addDescriptionBullet(projectBulletField.getText());
        // TODO: builder.addProject(proj);

        projectList.append(projTitle
            + " (" + projectStartField.getText() + " to " + projectEndField.getText() + ")\n");
        projectTitleField.setText(""); projectStartField.setText("");
        projectEndField.setText(""); projectBulletField.setText("");
    }

    private void addSkill() {
        String skill = skillField.getText().trim();

        if (skill.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please enter a skill.",
                "Missing Fields", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // TODO: Skill s = new Skill(skill);
        // TODO: builder.addSkill(s);

        skillList.append(skill + "\n");
        skillField.setText("");
    }

    private void selectTemplate() {
        String selected = (String) templateComboBox.getSelectedItem();

        // TODO: Template t = new Template(selected, null, "Arial", "default");
        // TODO: builder.setTemplate(t);

        JOptionPane.showMessageDialog(this,
            "Template \"" + selected + "\" selected.",
            "Template", JOptionPane.INFORMATION_MESSAGE);
    }

    private void exportResume(String format) {
        // TODO: Resume resume = builder.buildResume();
        // TODO: if (format.equals("PDF")) new PDFExporter().export(resume);
        // TODO: else                       new TXTExporter().export(resume);

        JOptionPane.showMessageDialog(this,
            "Export as " + format + " — coming soon!",
            "Export", JOptionPane.INFORMATION_MESSAGE);
    }

    //Utility

    private void addLabeledField(JPanel panel, String label, JTextField field) {
        panel.add(new JLabel(label));
        panel.add(field);
        panel.add(Box.createVerticalStrut(6));
    }

    public void start() {
        setVisible(true);
    }
}
