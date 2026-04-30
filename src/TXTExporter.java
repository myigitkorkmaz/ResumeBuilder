import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class TXTExporter implements Exporter{
    public void export(Resume resume){

        StringBuilder sb = new StringBuilder();
        Template template = resume.getTemplate();
        ArrayList<String> exportOrder = template.getOrder();

        //NOTE: Here I'm using a HashMap to reference different parts of the resume against the strings in the order.
        HashMap<String,Object> resumeData = new HashMap<>();
        resumeData.put("personalInfo", resume.getPersonalInfo());
        resumeData.put("education", resume.getEducation());
        resumeData.put("workExperience", resume.getWorkExperience());
        resumeData.put("skills", resume.getSkills());
        resumeData.put("projects", resume.getProjects());

        for (String s : exportOrder) {
            //NOTE: This part is subject to change depending on how order is implemented otherwise, I'm not fully aware of what we're doing with it.
            sb.append(resumeData.get(s).toString());
        }

        try{
            FileWriter writer = new FileWriter("Resume.txt");
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}