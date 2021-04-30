package action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

public class FromAction extends AnAction {
  public void actionPerformed(@NotNull AnActionEvent e) {
      if (e == null) {
         return;
      }
    SettingConfigDialog settingConfigDialog = new SettingConfigDialog(e.getProject());
    settingConfigDialog.setResizable(true);
    settingConfigDialog.show();
  }
}
