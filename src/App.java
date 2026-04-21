import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ResumeUI ui = new ResumeUI();
            ui.start();
        });
    }
}
