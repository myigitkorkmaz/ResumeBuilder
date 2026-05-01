import java.util.ArrayList;

// Controls how the resume looks when exported — font, style, and section order. Pick Classic, Modern, or Minimal.
public class Template {
    private String templateName;
    private ArrayList<String> order = new ArrayList<>();
    private String font;
    private float fontSize;
    private String style;

    public Template(String templateName, ArrayList<String> order, String font, String style) {
        this.templateName = templateName;
        this.order = order;
        this.font = font;
        this.style = style;
    }

    public void setTemplateName(String templateName){
        this.templateName = templateName;
    }

    public void setOrder(ArrayList<String> order){
        this.order = order;
    }

    public void setFont(String font){
        this.font = font;
    }

    public void setFontSize(float fontSize) {
        this.fontSize = fontSize;
    }

    public void setStyle(String style){
        this.style = style; 
    }

    public String getName() {
        return templateName;
    }

    public ArrayList<String> getOrder(){
        return order;
    }

    public String getFont(){
        return font;
    }

    public float getFontSize() {
        return fontSize;
    }

    public String getStyle(){
        return style;
    }
    
}