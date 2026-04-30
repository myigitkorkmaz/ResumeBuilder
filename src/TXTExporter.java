import javax.swing.*;
import java.io.*;

// Exports the resume as a plain text file formatted to fit a letter-size page. Good for quick previews.
public class TXTExporter implements Exporter {

    public static void export(Resume resume) {
        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(new File("resume.txt"));
        if (chooser.showSaveDialog(null) != JFileChooser.APPROVE_OPTION) return;

        Template template = resume.getTemplate();
        String templateName = (template != null) ? template.getName() : "Classic";

        try (PrintWriter pw = new PrintWriter(new FileWriter(chooser.getSelectedFile()))) {
            switch (templateName) {
                case "Modern"  -> writeModern(pw, resume);
                case "Minimal" -> writeMinimal(pw, resume);
                default        -> writeClassic(pw, resume);
            }
            JOptionPane.showMessageDialog(null,
                "Resume saved to " + chooser.getSelectedFile().getName(),
                "Export", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                "Export failed: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ── Classic ─────────────────────────────────────────────────────────────
    // Centered name, = border, - section lines, bullet points
    private static void writeClassic(PrintWriter pw, Resume resume) {
        final int W = 80;
        final String DIVIDER      = "=".repeat(W);
        final String SECTION_LINE = "-".repeat(W - 2);

        pw.println(DIVIDER);
        PersonalInfo info = resume.getPersonalInfo();
        if (info != null) {
            pw.println(center(info.getName().toUpperCase(), W));
            String contact = info.getEmail();
            if (!info.getPhoneNumber().isEmpty()) contact += "  |  " + info.getPhoneNumber();
            pw.println(center(contact, W));
            String links = buildLinks(info);
            if (!links.isEmpty()) pw.println(center(links, W));
        }
        pw.println(DIVIDER);
        pw.println();

        if (!resume.getEducation().isEmpty()) {
            pw.println("  EDUCATION");
            pw.println("  " + SECTION_LINE);
            for (Education e : resume.getEducation())
                pw.println("    " + e.getSchool() + " — " + e.getDegree() + "  (" + e.getGraduationYear() + ")");
            pw.println();
        }
        if (!resume.getWorkExperience().isEmpty()) {
            pw.println("  WORK EXPERIENCE");
            pw.println("  " + SECTION_LINE);
            for (WorkExperience w : resume.getWorkExperience()) {
                pw.println("    " + w.getCompany() + " — " + w.getTitle()
                    + "  (" + w.getStartDate() + " to " + w.getEndDate() + ")");
                for (String b : w.getDescriptionBullets()) pw.println("      •  " + b);
            }
            pw.println();
        }
        if (!resume.getProjects().isEmpty()) {
            pw.println("  PROJECTS");
            pw.println("  " + SECTION_LINE);
            for (Project p : resume.getProjects()) {
                pw.println("    " + p.getTitle() + "  (" + p.getStartDate() + " to " + p.getEndDate() + ")");
                for (String b : p.getDescriptionBullets()) pw.println("      •  " + b);
            }
            pw.println();
        }
        if (!resume.getSkills().isEmpty()) {
            pw.println("  SKILLS");
            pw.println("  " + SECTION_LINE);
            for (Skill s : resume.getSkills()) pw.println("    •  " + s.getDescription());
            pw.println();
        }
        pw.println(DIVIDER);
    }

    // ── Modern ──────────────────────────────────────────────────────────────
    // Left-aligned name, ## section headers, no outer border, > bullets
    private static void writeModern(PrintWriter pw, Resume resume) {
        final int W = 80;

        PersonalInfo info = resume.getPersonalInfo();
        if (info != null) {
            pw.println(info.getName().toUpperCase());
            String contact = info.getEmail();
            if (!info.getPhoneNumber().isEmpty()) contact += "  |  " + info.getPhoneNumber();
            pw.println(contact);
            String links = buildLinks(info);
            if (!links.isEmpty()) pw.println(links);
        }
        pw.println("*".repeat(W));
        pw.println();

        if (!resume.getEducation().isEmpty()) {
            pw.println("## EDUCATION");
            pw.println();
            for (Education e : resume.getEducation())
                pw.println("  " + e.getSchool() + " — " + e.getDegree() + "  (" + e.getGraduationYear() + ")");
            pw.println();
        }
        if (!resume.getWorkExperience().isEmpty()) {
            pw.println("## WORK EXPERIENCE");
            pw.println();
            for (WorkExperience w : resume.getWorkExperience()) {
                pw.println("  " + w.getCompany() + " — " + w.getTitle()
                    + "  (" + w.getStartDate() + " to " + w.getEndDate() + ")");
                for (String b : w.getDescriptionBullets()) pw.println("    >  " + b);
                pw.println();
            }
        }
        if (!resume.getProjects().isEmpty()) {
            pw.println("## PROJECTS");
            pw.println();
            for (Project p : resume.getProjects()) {
                pw.println("  " + p.getTitle() + "  (" + p.getStartDate() + " to " + p.getEndDate() + ")");
                for (String b : p.getDescriptionBullets()) pw.println("    >  " + b);
                pw.println();
            }
        }
        if (!resume.getSkills().isEmpty()) {
            pw.println("## SKILLS");
            pw.println();
            for (Skill s : resume.getSkills()) pw.println("  >  " + s.getDescription());
        }
    }

    // ── Minimal ─────────────────────────────────────────────────────────────
    // Centered name, no borders, plain blank-line separators, - bullets
    private static void writeMinimal(PrintWriter pw, Resume resume) {
        final int W = 80;

        PersonalInfo info = resume.getPersonalInfo();
        if (info != null) {
            pw.println(center(info.getName(), W));
            String contact = info.getEmail();
            if (!info.getPhoneNumber().isEmpty()) contact += "  |  " + info.getPhoneNumber();
            pw.println(center(contact, W));
            String links = buildLinks(info);
            if (!links.isEmpty()) pw.println(center(links, W));
        }
        pw.println();
        pw.println();

        if (!resume.getEducation().isEmpty()) {
            pw.println("Education");
            pw.println();
            for (Education e : resume.getEducation())
                pw.println("  " + e.getSchool() + ", " + e.getDegree() + " (" + e.getGraduationYear() + ")");
            pw.println();
        }
        if (!resume.getWorkExperience().isEmpty()) {
            pw.println("Work Experience");
            pw.println();
            for (WorkExperience w : resume.getWorkExperience()) {
                pw.println("  " + w.getCompany() + ", " + w.getTitle()
                    + " (" + w.getStartDate() + " - " + w.getEndDate() + ")");
                for (String b : w.getDescriptionBullets()) pw.println("    - " + b);
                pw.println();
            }
        }
        if (!resume.getProjects().isEmpty()) {
            pw.println("Projects");
            pw.println();
            for (Project p : resume.getProjects()) {
                pw.println("  " + p.getTitle() + " (" + p.getStartDate() + " - " + p.getEndDate() + ")");
                for (String b : p.getDescriptionBullets()) pw.println("    - " + b);
                pw.println();
            }
        }
        if (!resume.getSkills().isEmpty()) {
            pw.println("Skills");
            pw.println();
            for (Skill s : resume.getSkills()) pw.println("  " + s.getDescription());
        }
    }

    // ── Helpers ─────────────────────────────────────────────────────────────

    private static String buildLinks(PersonalInfo info) {
        String links = "";
        if (!info.getLinkedIn().isEmpty()) links += info.getLinkedIn();
        if (!info.getGitHub().isEmpty())   links += (links.isEmpty() ? "" : "  |  ") + info.getGitHub();
        return links;
    }

    private static String center(String text, int width) {
        if (text == null || text.length() >= width) return text;
        int pad = (width - text.length()) / 2;
        return " ".repeat(pad) + text;
    }
}
