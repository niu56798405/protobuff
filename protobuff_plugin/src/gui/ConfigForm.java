/*    */ package gui;
/*    */ import java.awt.Color;
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.JPanel;
/*    */ import javax.swing.JTextField;
/*    */ 
/*    */ public class ConfigForm {
/*    */   private JTextField exeValue;
/*    */   
/*    */   public ConfigForm() {
/* 11 */     super();
/*    */   }
/*    */   private JLabel exe;
/*    */   private JPanel jpanel;
/*    */   private JTextField outValue;
/*    */   private JLabel out;
/* 17 */   private static ConfigForm configForm = null;
/*    */   
/*    */   public static ConfigForm getConfigForm() {
/* 20 */     if (configForm == null) {
/* 21 */       configForm = new ConfigForm();
/*    */     }
/*    */     
/* 24 */     return configForm;
/*    */   }
/*    */   
/*    */   public JTextField getExeValue() {
/* 28 */     return this.exeValue;
/*    */   }
/*    */   
/*    */   public JLabel getExe() {
/* 32 */     return this.exe;
/*    */   }
/*    */   
/*    */   public JPanel getJpanel() {
/* 36 */     return configForm.jpanel;
/*    */   }
/*    */   
/*    */   public JTextField getOutValue() {
/* 40 */     return this.outValue;
/*    */   }
/*    */   
/*    */   public JLabel getOut() {
/* 44 */     return this.out;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Downloads\protobuff_plugin\protobuff plugin\protoc.jar!\gui\ConfigForm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */