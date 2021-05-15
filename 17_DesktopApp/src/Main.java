import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame mainForm = new JFrame();
        mainForm.setSize(600, 400);

        mainForm.add(new MainForm(mainForm).getMainPanel());

        mainForm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainForm.setLocationRelativeTo(null);
        mainForm.setVisible(true);


    }
}
