package action;

import com.intellij.execution.testframework.PoolOfTestIcons;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.ui.Messages;
import gui.ConfigForm;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ConfigSwing {
  public static String EXE_PATH = "EXE_PATH";
  
  public static String OUT_PATH = "OUT_PATH";
  
  private String exePath;
  
  private String outPath;
  
  private JPanel north = new JPanel();
  
  private JPanel south = new JPanel();
  
  private JLabel exe;
  
  private JTextField exeValue;
  
  private JLabel out;
  
  private JTextField outValue;
  
  public JPanel initNorth() {
    return this.north;
  }
  
  public JPanel initCenter() {
    ConfigForm configForm = ConfigForm.getConfigForm();
    JPanel jpanel = configForm.getJpanel();
    this.exe = configForm.getExe();
    this.out = configForm.getOut();
    this.exeValue = configForm.getExeValue();
    String value = PropertiesComponent.getInstance().getValue(EXE_PATH);
      if (value != null) {
          this.exeValue.setText(value);
      }
    this.outValue = configForm.getOutValue();
    String out = PropertiesComponent.getInstance().getValue(OUT_PATH);
      if (out != null) {
          this.outValue.setText(out);
      }
    return jpanel;
  }
  
  public JPanel initSouth() {
    JButton submit = new JButton("submit");
    submit.setHorizontalAlignment(0);
    submit.setVerticalAlignment(0);
    this.south.add(submit);
    submit.addActionListener(e -> {
          String outValueText = this.outValue.getText();
          outValueText = outValueText.replaceAll("\\\\", "/");
          this.outValue.setText(outValueText);
          String exeValueText = this.exeValue.getText();
          exeValueText = exeValueText.replaceAll("\\\\", "/");
          this.exeValue.setText(exeValueText);
          PropertiesComponent.getInstance().setValue(EXE_PATH, exeValueText);
          PropertiesComponent.getInstance().setValue(OUT_PATH, outValueText);
          Messages.showMessageDialog("succ ^ V ^!!", "protoc", PoolOfTestIcons.PASSED_ICON);
        });
    return this.south;
  }
}
