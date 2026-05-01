

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
// The main window of the app. Each tab handles a different section of the resume.
public class ResumeUI extends JFrame {

    private final JTabbedPane tabbedPane;

    private final ResumeBuilder builder;

    // Personal Info
    private JTextField nameField, emailField, phoneField, linkedInField, gitHubField;

    // Education
    private JTextField schoolField, degreeField, gradYearField;
    private DefaultListModel<String> educationModel = new DefaultListModel<>();
    private JList<String> educationListView;

    // Work Experience
    private JTextField companyField, titleField, startDateField, endDateField;
    private JTextArea workBulletField;
    private DefaultListModel<String> workModel = new DefaultListModel<>();
    private JList<String> workListView;

    // Projects
    private JTextField projectTitleField, projectStartField, projectEndField;
    private JTextArea projectBulletField;
    private DefaultListModel<String> projectModel = new DefaultListModel<>();
    private JList<String> projectListView;

    // Skills
    private JTextField skillField;
    private DefaultListModel<String> skillModel = new DefaultListModel<>();
    private JList<String> skillListView;

    // Template
    private JComboBox<String> templateComboBox;

    public ResumeUI() {
        super("Student Resume Builder");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(620, 520);
        setLayout(new BorderLayout());
        this.builder = new ResumeBuilder();

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

    // ── Tab builders ────────────────────────────────────────────────────────

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

        educationListView = new JList<>(educationModel);
        educationListView.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JPanel listButtons = new JPanel();
        JButton eduEdit = new JButton("Edit Selected");
        JButton eduRemove = new JButton("Remove Selected");
        eduEdit.addActionListener(e -> editEducation());
        eduRemove.addActionListener(e -> removeEducation());
        listButtons.add(eduEdit);
        listButtons.add(eduRemove);

        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.add(new JScrollPane(educationListView), BorderLayout.CENTER);
        listPanel.add(listButtons, BorderLayout.SOUTH);

        panel.add(form, BorderLayout.NORTH);
        panel.add(listPanel, BorderLayout.CENTER);
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
        workBulletField = new JTextArea(3, 25);
        workBulletField.setLineWrap(true);
        workBulletField.setWrapStyleWord(true);

        addLabeledField(form, "Company:",            companyField);
        addLabeledField(form, "Title:",              titleField);
        addLabeledField(form, "Start Date:",         startDateField);
        addLabeledField(form, "End Date:",           endDateField);
        form.add(new JLabel("Description Bullets (one per line):"));
        form.add(new JScrollPane(workBulletField));
        form.add(Box.createVerticalStrut(6));

        form.add(Box.createVerticalStrut(10));
        JButton addBtn = new JButton("Add Work Experience");
        addBtn.addActionListener(e -> addWorkExperience());
        form.add(addBtn);

        workListView = new JList<>(workModel);
        workListView.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JPanel listButtons = new JPanel();
        JButton workEdit = new JButton("Edit Selected");
        JButton workRemove = new JButton("Remove Selected");
        workEdit.addActionListener(e -> editWorkExperience());
        workRemove.addActionListener(e -> removeWorkExperience());
        listButtons.add(workEdit);
        listButtons.add(workRemove);

        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.add(new JScrollPane(workListView), BorderLayout.CENTER);
        listPanel.add(listButtons, BorderLayout.SOUTH);

        panel.add(form, BorderLayout.NORTH);
        panel.add(listPanel, BorderLayout.CENTER);
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
        projectBulletField = new JTextArea(3, 25);
        projectBulletField.setLineWrap(true);
        projectBulletField.setWrapStyleWord(true);

        addLabeledField(form, "Project Title:",      projectTitleField);
        addLabeledField(form, "Start Date:",         projectStartField);
        addLabeledField(form, "End Date:",           projectEndField);
        form.add(new JLabel("Description Bullets (one per line):"));
        form.add(new JScrollPane(projectBulletField));
        form.add(Box.createVerticalStrut(6));

        form.add(Box.createVerticalStrut(10));
        JButton addBtn = new JButton("Add Project");
        addBtn.addActionListener(e -> addProject());
        form.add(addBtn);

        projectListView = new JList<>(projectModel);
        projectListView.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JPanel listButtons = new JPanel();
        JButton projEdit = new JButton("Edit Selected");
        JButton projRemove = new JButton("Remove Selected");
        projEdit.addActionListener(e -> editProject());
        projRemove.addActionListener(e -> removeProject());
        listButtons.add(projEdit);
        listButtons.add(projRemove);

        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.add(new JScrollPane(projectListView), BorderLayout.CENTER);
        listPanel.add(listButtons, BorderLayout.SOUTH);

        panel.add(form, BorderLayout.NORTH);
        panel.add(listPanel, BorderLayout.CENTER);
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

        skillListView = new JList<>(skillModel);
        skillListView.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JPanel listButtons = new JPanel();
        JButton skillEdit = new JButton("Edit Selected");
        JButton skillRemove = new JButton("Remove Selected");
        skillEdit.addActionListener(e -> editSkill());
        skillRemove.addActionListener(e -> removeSkill());
        listButtons.add(skillEdit);
        listButtons.add(skillRemove);

        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.add(new JScrollPane(skillListView), BorderLayout.CENTER);
        listPanel.add(listButtons, BorderLayout.SOUTH);

        panel.add(form, BorderLayout.NORTH);
        panel.add(listPanel, BorderLayout.CENTER);
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

    // ── Action methods ───────────────────────────────────────────────────────

    private void savePersonalInfo() {
        String name  = nameField.getText().trim();
        String email = emailField.getText().trim();

        if (name.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Name and Email are required.",
                "Missing Fields", JOptionPane.ERROR_MESSAGE);
            return;
        }


        PersonalInfo info = new PersonalInfo(name, email, phoneField.getText(), linkedInField.getText(), gitHubField.getText());
        builder.setPersonalInfo(info);
        

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

        Education edu = new Education(school, year, degree);
        builder.addEducation(edu);
        

        educationModel.addElement(school + " — " + degree + " (" + year + ")");
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

        WorkExperience job = new WorkExperience(company, title, startDateField.getText(), endDateField.getText(), new ArrayList<>());
        for (String b : workBulletField.getText().split("\n"))
            if (!b.trim().isEmpty()) job.addDescriptionBullet(b.trim());

        builder.addWorkExperience(job);
        

        workModel.addElement(company + " — " + title
            + " (" + startDateField.getText() + " to " + endDateField.getText() + ")");
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

        Project proj = new Project(projTitle, projectStartField.getText(), projectEndField.getText());
        for (String b : projectBulletField.getText().split("\n"))
            if (!b.trim().isEmpty()) proj.addDescriptionBullet(b.trim());
        builder.addProject(proj);
        

        projectModel.addElement(projTitle
            + " (" + projectStartField.getText() + " to " + projectEndField.getText() + ")");
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

        Skill s = new Skill(skill);
        builder.addSkill(s);

        skillModel.addElement(skill);
        skillField.setText("");
    }

    private void removeEducation() {
        int i = educationListView.getSelectedIndex();
        if (i < 0) { JOptionPane.showMessageDialog(this, "Select an entry to remove."); return; }
        builder.removeEducation(i);
        educationModel.remove(i);
    }

    private void editEducation() {
        int i = educationListView.getSelectedIndex();
        if (i < 0) { JOptionPane.showMessageDialog(this, "Select an entry to edit."); return; }
        Education e = builder.getCurrentResume().getEducation().get(i);
        schoolField.setText(e.getSchool());
        degreeField.setText(e.getDegree());
        gradYearField.setText(e.getGraduationYear());
        builder.removeEducation(i);
        educationModel.remove(i);
    }

    private void removeWorkExperience() {
        int i = workListView.getSelectedIndex();
        if (i < 0) { JOptionPane.showMessageDialog(this, "Select an entry to remove."); return; }
        builder.removeWorkExperience(i);
        workModel.remove(i);
    }

    private void editWorkExperience() {
        int i = workListView.getSelectedIndex();
        if (i < 0) { JOptionPane.showMessageDialog(this, "Select an entry to edit."); return; }
        WorkExperience w = builder.getCurrentResume().getWorkExperience().get(i);
        companyField.setText(w.getCompany());
        titleField.setText(w.getTitle());
        startDateField.setText(w.getStartDate());
        endDateField.setText(w.getEndDate());
        workBulletField.setText(String.join("\n", w.getDescriptionBullets()));
        builder.removeWorkExperience(i);
        workModel.remove(i);
    }

    private void removeProject() {
        int i = projectListView.getSelectedIndex();
        if (i < 0) { JOptionPane.showMessageDialog(this, "Select an entry to remove."); return; }
        builder.removeProject(i);
        projectModel.remove(i);
    }

    private void editProject() {
        int i = projectListView.getSelectedIndex();
        if (i < 0) { JOptionPane.showMessageDialog(this, "Select an entry to edit."); return; }
        Project p = builder.getCurrentResume().getProjects().get(i);
        projectTitleField.setText(p.getTitle());
        projectStartField.setText(p.getStartDate());
        projectEndField.setText(p.getEndDate());
        projectBulletField.setText(String.join("\n", p.getDescriptionBullets()));
        builder.removeProject(i);
        projectModel.remove(i);
    }

    private void removeSkill() {
        int i = skillListView.getSelectedIndex();
        if (i < 0) { JOptionPane.showMessageDialog(this, "Select an entry to remove."); return; }
        builder.removeSkill(i);
        skillModel.remove(i);
    }

    private void editSkill() {
        int i = skillListView.getSelectedIndex();
        if (i < 0) { JOptionPane.showMessageDialog(this, "Select an entry to edit."); return; }
        Skill s = builder.getCurrentResume().getSkills().get(i);
        skillField.setText(s.getDescription());
        builder.removeSkill(i);
        skillModel.remove(i);
    }

    private void selectTemplate() {
        String selected = (String) templateComboBox.getSelectedItem();


            Template t = new Template(selected, new ArrayList<>(java.util.Arrays.asList("personalInfo", "education", "workExperience", "projects", "skills")), "Arial", "default");
            builder.setTemplate(t);
       

        JOptionPane.showMessageDialog(this,
            "Template \"" + selected + "\" selected.",
            "Template", JOptionPane.INFORMATION_MESSAGE);
    }

    private void exportResume(String format) {

        Resume resume = builder.build();
        

        if (format.equals("PDF")) {
            PDFExporter.export(resume);
            JOptionPane.showMessageDialog(this,
                "Exporting as " + format,
                "Export", JOptionPane.INFORMATION_MESSAGE);
        } else {
            TXTExporter.export(resume);
        }

        
    }

    // ── Utility ─────────────────────────────────────────────────────────────

    private void addLabeledField(JPanel panel, String label, JTextField field) {
        panel.add(new JLabel(label));
        panel.add(field);
        panel.add(Box.createVerticalStrut(6));
    }

    public void start() {
        setVisible(true);
    }
}
