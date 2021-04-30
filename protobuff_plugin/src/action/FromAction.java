/*    */ package action;
/*    */ 
/*    */ import com.intellij.openapi.actionSystem.AnAction;
/*    */ import com.intellij.openapi.actionSystem.AnActionEvent;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class FromAction extends AnAction {
/*    */   public void actionPerformed(@NotNull AnActionEvent e) {
/*  9 */     if (e == null) return;

/* 12 */     SettingConfigDialog settingConfigDialog = new SettingConfigDialog(e.getProject());
/* 13 */     settingConfigDialog.setResizable(true);
/* 14 */     settingConfigDialog.show();
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Downloads\protobuff_plugin\protobuff plugin\protoc.jar!\action\FromAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */