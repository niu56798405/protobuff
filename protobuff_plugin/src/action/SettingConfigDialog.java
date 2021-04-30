/*    */ package action;
/*    */ 
/*    */ import com.intellij.openapi.project.Project;
/*    */ import com.intellij.openapi.ui.DialogWrapper;
/*    */ import javax.swing.JComponent;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public class SettingConfigDialog
/*    */   extends DialogWrapper {
/*    */   private String projectName;
/* 11 */   private ConfigSwing configSwing = new ConfigSwing();
/*    */   
/*    */   public SettingConfigDialog(@Nullable Project project) {
/* 14 */     super(true);
/* 15 */     setTitle("protoc setting");
/* 16 */     this.projectName = project.getName();
/* 17 */     init();
/*    */   }
/*    */   
/*    */   protected JComponent createNorthPanel() {
/* 21 */     return this.configSwing.initNorth();
/*    */   }
/*    */   
/*    */   protected JComponent createSouthPanel() {
/* 25 */     return this.configSwing.initSouth();
/*    */   }
/*    */   
/*    */   protected JComponent createCenterPanel() {
/* 29 */     return this.configSwing.initCenter();
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Downloads\protobuff_plugin\protobuff plugin\protoc.jar!\action\SettingConfigDialog.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */