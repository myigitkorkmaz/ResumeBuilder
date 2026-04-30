import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.util.HashMap;
import java.util.ArrayList;
import java.io.IOException;


public class PDFExporter implements Exporter {
    public void export(Resume resume){

        Template template = resume.getTemplate();
        ArrayList<String> exportOrder = template.getOrder();

        //NOTE: Here I'm using a HashMap to reference different parts of the resume against the strings in the order.
        HashMap<String,Object> resumeData = new HashMap<>();
        resumeData.put("personalInfo", resume.getPersonalInfo());
        resumeData.put("education", resume.getEducation());
        resumeData.put("workExperience", resume.getWorkExperience());
        resumeData.put("skills", resume.getSkills());
        resumeData.put("projects", resume.getProjects());

        try(PDDocument doc = new PDDocument()){
            PDPage pdPage = new PDpage();
            doc.addPage(pdPage);

            try (PDPageContentStream contentStream = new PDPageContentStream(doc, pdPage)){
                contentStream.beginText();

                //NOTE: Fonts are not yet implemented.
                contentStream.setFont(PDType1Font.TIMES_ROMAN, resume.getFontSize());
                contentStream.setLeading(14.5f);
                contentStream.newLine();

                for (String s : template.getOrder()) {
                    contentStream.showText(s);
                    contentStream.newLine();
                }
                contentStream.endText();
            }

            doc.save("Resume.pdf");

        } catch(IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}