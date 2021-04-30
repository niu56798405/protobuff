package gui;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author by niu
 */
public class ConfigForm {
    private JTextField exeValue;
    private JLabel exe;
    private JPanel jpanel;
    private JTextField outValue;
    private JLabel out;
    private static ConfigForm configForm = null;

    public static ConfigForm getConfigForm() {
        if(configForm == null) {
            configForm = new ConfigForm();
        }

        return configForm;
    }

    public JTextField getExeValue() {
        return exeValue;
    }

    public JLabel getExe() {
        return exe;
    }

    public JPanel getJpanel() {
        return configForm.jpanel;
    }

    public JTextField getOutValue() {
        return outValue;
    }

    public JLabel getOut() {
        return out;
    }
}
