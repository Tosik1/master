import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

public class MainForm {
    private JPanel mainPanel;
    private JButton collapseButton;
    private JTextField surnameField;
    private JTextField nameField;
    private JTextField patronymicField;

    public MainForm(JFrame mainForm){
        collapseButton.addActionListener(new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public void setEnabled(boolean b) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder fullName = new StringBuilder();
                String name = nameField.getText();
                String surname = surnameField.getText();
                if (name.length() != 0 && surname.length() != 0){
                    fullName.append(surname).append(" ").append(name);
                    if (patronymicField.getText().length() != 0) {
                        fullName.append(" ").append(patronymicField.getText());
                    }
                    JFrame secondForm = new JFrame();
                    secondForm.setSize(600, 400);

                    secondForm.add(new SecondForm(fullName, secondForm).getMainPanel());

                    secondForm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                    secondForm.setLocationRelativeTo(null);
                    secondForm.setVisible(true);
                    mainForm.setVisible(false);
                }
                else {
                    JOptionPane.showMessageDialog(mainForm,
                            "Введите фамилию и имя",
                            "Ошибка",
                            JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
    }

    public MainForm(StringBuilder fullName, JFrame mainForm){
        StringBuilder peredachik = new StringBuilder();
        String[] stringBuilder = fullName.toString().split(" ");
        surnameField.setText(stringBuilder[0]);
        nameField.setText(stringBuilder[1]);
        if (stringBuilder.length == 3){
            patronymicField.setText(stringBuilder[2]);
        }
        collapseButton.addActionListener(new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public void setEnabled(boolean b) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String surname = surnameField.getText();
                if (name.length() != 0 && surname.length() != 0){
                    peredachik.append(surname).append(" ").append(name);
                    if (patronymicField.getText().length() != 0) {
                        peredachik.append(" ").append(patronymicField.getText());
                    }
                    JFrame secondForm = new JFrame();
                    secondForm.setSize(600, 400);

                    secondForm.add(new SecondForm(peredachik, secondForm).getMainPanel());

                    secondForm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                    secondForm.setLocationRelativeTo(null);
                    secondForm.setVisible(true);
                    mainForm.setVisible(false);
                }
                else {
                    JOptionPane.showMessageDialog(mainForm,
                            "Введите фамилию и имя",
                            "Ошибка",
                            JOptionPane.PLAIN_MESSAGE);
                }

            }
        });
    }

    public JPanel getMainPanel(){
        return mainPanel;
    }

}
