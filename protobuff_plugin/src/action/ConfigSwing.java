/*    */ package action;
/*    */ 
/*    */ import com.intellij.execution.testframework.PoolOfTestIcons;
/*    */ import com.intellij.ide.util.PropertiesComponent;
/*    */ import com.intellij.openapi.ui.Messages;
/*    */ import gui.ConfigForm;
/*    */ import java.awt.event.ActionEvent;
/*    */ import javax.swing.JButton;
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.JPanel;
/*    */ import javax.swing.JTextField;
/*    */ 
/*    */ public class ConfigSwing {
/* 14 */   public static String EXE_PATH = "EXE_PATH";
/*    */   
/* 16 */   public static String OUT_PATH = "OUT_PATH";
/*    */   
/*    */   private String exePath;
/*    */   
/*    */   private String outPath;
/*    */   
/* 22 */   private JPanel north = new JPanel();
/*    */   
/* 24 */   private JPanel south = new JPanel();
/*    */   
/*    */   private JLabel exe;
/*    */   
/*    */   private JTextField exeValue;
/*    */   
/*    */   private JLabel out;
/*    */   
/*    */   private JTextField outValue;
/*    */   
/*    */   public JPanel initNorth() {
/* 35 */     return this.north;
/*    */   }
/*    */   
/*    */   public JPanel initCenter() {
/* 39 */     ConfigForm configForm = ConfigForm.getConfigForm();
/* 40 */     JPanel jpanel = configForm.getJpanel();
/* 41 */     this.exe = configForm.getExe();
/* 42 */     this.out = configForm.getOut();
/* 43 */     this.exeValue = configForm.getExeValue();
/* 44 */     String value = PropertiesComponent.getInstance().getValue(EXE_PATH);
/* 45 */     if (value != null) {
/* 46 */       this.exeValue.setText(value);
/*    */     }
/* 48 */     this.outValue = configForm.getOutValue();
/* 49 */     String out = PropertiesComponent.getInstance().getValue(OUT_PATH);
/* 50 */     if (out != null) {
/* 51 */       this.outValue.setText(out);
/*    */     }
/* 53 */     return jpanel;
/*    */   }
/*    */   
/*    */   public JPanel initSouth() {
/* 57 */     JButton submit = new JButton("submit");
/* 58 */     submit.setHorizontalAlignment(0);
/* 59 */     submit.setVerticalAlignment(0);
/* 60 */     this.south.add(submit);
/* 61 */     submit.addActionListener(e -> {
/*    */           String outValueText = this.outValue.getText();
/*    */           outValueText = outValueText.replaceAll("\\\\", "/");
/*    */           this.outValue.setText(outValueText);
/*    */           String exeValueText = this.exeValue.getText();
/*    */           exeValueText = exeValueText.replaceAll("\\\\", "/");
/*    */           this.exeValue.setText(exeValueText);
/*    */           PropertiesComponent.getInstance().setValue(EXE_PATH, exeValueText);
/*    */           PropertiesComponent.getInstance().setValue(OUT_PATH, outValueText);
/*    */           Messages.showMessageDialog("succ ^ V ^!!", "protoc", PoolOfTestIcons.PASSED_ICON);
/*    */         });
/* 72 */     return this.south;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Downloads\protobuff_plugin\protobuff plugin\protoc.jar!\action\ConfigSwing.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */