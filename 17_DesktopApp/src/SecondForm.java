import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

public class SecondForm {
    private JPanel secondPanel;
    private JButton expandButton;
    private JTextField fullNameField;

    public SecondForm(StringBuilder fullName, JFrame secondForm){
        StringBuilder peredachik = new StringBuilder();
        fullNameField.setText(fullName.toString());
        expandButton.addActionListener(new Action() {
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
                String[] priemnik = fullNameField.getText().split(" ");
                if (priemnik.length >= 2 && priemnik[0] != null && priemnik[1] != null){
                    JFrame mainForm = new JFrame();
                    peredachik.append(fullName);
                    mainForm.setSize(600, 400);

                    mainForm.add(new MainForm(peredachik, mainForm).getMainPanel());

                    mainForm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                    mainForm.setLocationRelativeTo(null);
                    mainForm.setVisible(true);
                    secondForm.setVisible(false);
                }
                else {
                    JOptionPane.showMessageDialog(secondForm,
                            "Введите фамилию и имя",
                            "Ошибка",
                            JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
    }

    public JPanel getMainPanel(){
        return secondPanel;
    }
}
