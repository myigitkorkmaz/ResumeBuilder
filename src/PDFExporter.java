import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

// Takes the resume data and writes it to a real PDF file using PDFBox. Supports Classic, Modern, and Minimal styles.
public class PDFExporter implements Exporter {

    private static final float PAGE_WIDTH  = PDRectangle.LETTER.getWidth();
    private static final float PAGE_HEIGHT = PDRectangle.LETTER.getHeight();

    // Per-template style settings
    private static final class Style {
        float margin, nameSize, contactSize, sectionSize, bodySize, lineSpacing;
        PDType1Font nameFont, sectionFont, bodyFont, boldBodyFont;
        boolean drawSectionLines, centerHeader;

        Style(float margin, float nameSize, float contactSize, float sectionSize, float bodySize,
              float lineSpacing, PDType1Font nameFont, PDType1Font sectionFont,
              PDType1Font bodyFont, PDType1Font boldBodyFont,
              boolean drawSectionLines, boolean centerHeader) {
            this.margin = margin;
            this.nameSize = nameSize; this.contactSize = contactSize;
            this.sectionSize = sectionSize; this.bodySize = bodySize;
            this.lineSpacing = lineSpacing;
            this.nameFont = nameFont; this.sectionFont = sectionFont;
            this.bodyFont = bodyFont; this.boldBodyFont = boldBodyFont;
            this.drawSectionLines = drawSectionLines;
            this.centerHeader = centerHeader;
        }
    }

    private static Style getStyle(String templateName) {
        switch (templateName == null ? "" : templateName) {
            case "Modern":
                // Helvetica, larger name, no divider lines, left-aligned header
                return new Style(50, 20, 11, 13, 11, 3,
                    PDType1Font.HELVETICA_BOLD, PDType1Font.HELVETICA_BOLD,
                    PDType1Font.HELVETICA, PDType1Font.HELVETICA_BOLD,
                    false, false);
            case "Minimal":
                // Helvetica, smaller and compact, no lines, centered header
                return new Style(70, 14, 10, 11, 10, 2,
                    PDType1Font.HELVETICA, PDType1Font.HELVETICA_BOLD,
                    PDType1Font.HELVETICA, PDType1Font.HELVETICA_OBLIQUE,
                    false, true);
            default: // Classic
                // Times Roman, divider lines, centered header
                return new Style(60, 16, 10, 12, 11, 4,
                    PDType1Font.TIMES_BOLD, PDType1Font.TIMES_BOLD,
                    PDType1Font.TIMES_ROMAN, PDType1Font.TIMES_BOLD,
                    true, true);
        }
    }

    public static void export(Resume resume) {
        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(new File("Resume.pdf"));
        chooser.setFileFilter(new FileNameExtensionFilter("PDF Files (*.pdf)", "pdf"));
        if (chooser.showSaveDialog(null) != JFileChooser.APPROVE_OPTION) return;

        File file = chooser.getSelectedFile();
        if (!file.getName().endsWith(".pdf"))
            file = new File(file.getAbsolutePath() + ".pdf");

        Template template = resume.getTemplate();
        Style s = getStyle(template != null ? template.getName() : "Classic");

        try (PDDocument doc = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.LETTER);
            doc.addPage(page);
            PDPageContentStream cs = new PDPageContentStream(doc, page);

            float y = PAGE_HEIGHT - s.margin;

            // ── Header ──────────────────────────────────────────────────────
            PersonalInfo info = resume.getPersonalInfo();
            if (info != null) {
                String name = info.getName().toUpperCase();
                if (s.centerHeader) {
                    y = writeCentered(cs, name, s.nameFont, s.nameSize, y, s);
                } else {
                    y = writeLine(cs, name, s.nameFont, s.nameSize, s.margin, y);
                }
                y -= s.lineSpacing;

                String contact = info.getEmail();
                if (!info.getPhoneNumber().isEmpty()) contact += "  |  " + info.getPhoneNumber();

                if (s.centerHeader)
                    y = writeCentered(cs, contact, s.bodyFont, s.contactSize, y, s);
                else
                    y = writeLine(cs, contact, s.bodyFont, s.contactSize, s.margin, y);

                String links = "";
                if (!info.getLinkedIn().isEmpty()) links += info.getLinkedIn();
                if (!info.getGitHub().isEmpty())   links += (links.isEmpty() ? "" : "  |  ") + info.getGitHub();
                if (!links.isEmpty()) {
                    if (s.centerHeader)
                        y = writeCentered(cs, links, s.bodyFont, s.contactSize, y, s);
                    else
                        y = writeLine(cs, links, s.bodyFont, s.contactSize, s.margin, y);
                }

                y -= s.lineSpacing;
                if (s.drawSectionLines) y = drawLine(cs, y, s);
                y -= 8;
            }

            // ── Education ───────────────────────────────────────────────────
            if (!resume.getEducation().isEmpty()) {
                y = writeSection(cs, "EDUCATION", y, s);
                for (Education e : resume.getEducation())
                    y = writeLine(cs, e.getSchool() + " - " + e.getDegree()
                        + "  (" + e.getGraduationYear() + ")",
                        s.bodyFont, s.bodySize, s.margin + 10, y);
                y -= 8;
            }

            // ── Work Experience ─────────────────────────────────────────────
            if (!resume.getWorkExperience().isEmpty()) {
                y = writeSection(cs, "WORK EXPERIENCE", y, s);
                for (WorkExperience w : resume.getWorkExperience()) {
                    y = writeLine(cs, w.getCompany() + " - " + w.getTitle()
                        + "  (" + w.getStartDate() + " to " + w.getEndDate() + ")",
                        s.boldBodyFont, s.bodySize, s.margin + 10, y);
                    for (String b : w.getDescriptionBullets())
                        y = writeLine(cs, "o  " + b, s.bodyFont, s.bodySize, s.margin + 22, y);
                }
                y -= 8;
            }

            // ── Projects ────────────────────────────────────────────────────
            if (!resume.getProjects().isEmpty()) {
                y = writeSection(cs, "PROJECTS", y, s);
                for (Project p : resume.getProjects()) {
                    y = writeLine(cs, p.getTitle()
                        + "  (" + p.getStartDate() + " to " + p.getEndDate() + ")",
                        s.boldBodyFont, s.bodySize, s.margin + 10, y);
                    for (String b : p.getDescriptionBullets())
                        y = writeLine(cs, "o  " + b, s.bodyFont, s.bodySize, s.margin + 22, y);
                }
                y -= 8;
            }

            // ── Skills ──────────────────────────────────────────────────────
            if (!resume.getSkills().isEmpty()) {
                y = writeSection(cs, "SKILLS", y, s);
                for (Skill sk : resume.getSkills())
                    y = writeLine(cs, "o  " + sk.getDescription(), s.bodyFont, s.bodySize, s.margin + 10, y);
            }

            cs.close();
            doc.save(file);
            JOptionPane.showMessageDialog(null, "Resume saved to " + file.getName(),
                "Export", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Export failed: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static float writeSection(PDPageContentStream cs, String title, float y, Style s) throws IOException {
        y = writeLine(cs, title, s.sectionFont, s.sectionSize, s.margin, y);
        if (s.drawSectionLines) {
            y = drawLine(cs, y, s);
        } else {
            y -= 3;
        }
        y -= s.lineSpacing;
        return y;
    }

    private static float writeCentered(PDPageContentStream cs, String text,
                                        PDType1Font font, float size, float y, Style s) throws IOException {
        float textWidth = font.getStringWidth(sanitize(text)) / 1000 * size;
        float x = (PAGE_WIDTH - textWidth) / 2;
        return writeLine(cs, text, font, size, x, y);
    }

    private static float writeLine(PDPageContentStream cs, String text,
                                    PDType1Font font, float size, float x, float y) throws IOException {
        y -= size + 2;
        cs.beginText();
        cs.setFont(font, size);
        cs.newLineAtOffset(x, y);
        cs.showText(sanitize(text));
        cs.endText();
        return y;
    }

    private static float drawLine(PDPageContentStream cs, float y, Style s) throws IOException {
        y -= 4;
        cs.moveTo(s.margin, y);
        cs.lineTo(PAGE_WIDTH - s.margin, y);
        cs.stroke();
        return y;
    }

    private static String sanitize(String text) {
        if (text == null) return "";
        return text.replaceAll("[^\\x00-\\x7E]", "?");
    }
}
