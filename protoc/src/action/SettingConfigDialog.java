package action;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import javax.swing.JComponent;
import org.jetbrains.annotations.Nullable;

public class SettingConfigDialog extends DialogWrapper {
  private String projectName;
  
  private ConfigSwing configSwing = new ConfigSwing();
  
  public SettingConfigDialog(@Nullable Project project) {
    super(true);
    setTitle("protoc setting");
    this.projectName = project.getName();
    init();
  }
  
  protected JComponent createNorthPanel() {
    return this.configSwing.initNorth();
  }
  
  protected JComponent createSouthPanel() {
    return this.configSwing.initSouth();
  }
  
  protected JComponent createCenterPanel() {
    return this.configSwing.initCenter();
  }
}
