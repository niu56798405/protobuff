/*     */ package action;
/*     */ 
/*     */ import com.intellij.codeInspection.InspectionsBundle;
import com.intellij.ide.util.PropertiesComponent;
/*     */ import com.intellij.internal.statistic.eventLog.EventLogBuild;
import com.intellij.notification.EventLog;
import com.intellij.openapi.actionSystem.AnAction;
/*     */ import com.intellij.openapi.actionSystem.AnActionEvent;
/*     */ import com.intellij.openapi.actionSystem.PlatformDataKeys;
/*     */ import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
/*     */ import com.intellij.openapi.util.IconLoader;
/*     */ import com.intellij.openapi.vcs.VcsNotifier;
import com.intellij.openapi.vfs.VirtualFile;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
/*     */ import javax.swing.Icon;
/*     */ 
/*     */ public class ProtocAction extends AnAction {
/*  17 */   public static final Icon PROTO_ICON = IconLoader.getIcon("/icon.png");
/*     */   
/*  19 */   public static final Icon ERROR_ICON = IconLoader.getIcon("/error.png");
/*     */   
/*  21 */   public static final Icon PASS_ICON = IconLoader.getIcon("/passed.png");
/*     */     public static final Logger LOG = Logger.getInstance("github");
/*     */   public ProtocAction() {
/*  24 */     super( PROTO_ICON);

/*     */   }
/*     */   
/*     */   public ProtocAction(String text, String desc, Icon icon) {
/*  28 */     super(text, desc, PROTO_ICON);
/*     */   }
/*     */   
/*     */   public void actionPerformed(AnActionEvent event) {
	Project project = event.getProject();
	LOG.info(">>>>>>>");
	VcsNotifier.getInstance(project).notifyError(null,"1111", ">>>>>>>11",false);
	System.out.println(111111111);
	if (1 == 1){
		return;
	}
/*  32 */     String exePath = PropertiesComponent.getInstance().getValue(ConfigSwing.EXE_PATH);
/*  33 */     String outPath = PropertiesComponent.getInstance().getValue(ConfigSwing.OUT_PATH);
/*  34 */     File dir = new File(outPath);
/*  35 */     if (!dir.exists()) {
/*  36 */       dir.mkdirs();
/*     */     }
/*  38 */     VirtualFile[] files = (VirtualFile[])event.getData(PlatformDataKeys.VIRTUAL_FILE_ARRAY);
/*  39 */     int succNum = 0;
/*  40 */     File exeFile = new File(exePath);
/*  41 */     if (!exeFile.exists()) {
/*  42 */       Messages.showMessageDialog("not found protoc.exe", "Exception", ERROR_ICON);
/*     */     }
/*  44 */     for (VirtualFile file : files) {
/*  45 */       int status = run(file, exePath, outPath);
/*  46 */       if (status == 0) {
/*  47 */         succNum++;
/*     */       }
/*     */     } 
/*  50 */     if (succNum == files.length) {
/*  51 */       Messages.showMessageDialog("protoc succ!!", "protoc", PASS_ICON);
/*     */     }
/*     */   }
/*     */   
/*     */   private int run(VirtualFile data, String exePath, String outPath) {

/*  56 */     if ("proto".equals(data.getExtension())) {
/*  57 */       String url = data.getUrl();
/*  58 */       url = url.split("//")[1];
/*  59 */       String out = outPath;
/*  60 */       String protoPath = calProtoPath(url);
/*  61 */       String cmd = String.format("%s -I=%s --java_out=%s %s", new Object[] { exePath, protoPath.substring(0, protoPath.length() - 1), out, url });
/*     */       try {
/*  63 */         int status = execCmd(cmd);
/*  64 */         return status;
/*  65 */       } catch (Exception e) {
/*  66 */         Messages.showMessageDialog(e.getMessage(), "protoc", ERROR_ICON);
/*  67 */         Messages.showMessageDialog(cmd, "protoc", ERROR_ICON);
/*  68 */         e.printStackTrace();
/*  69 */         return -1;
/*     */       } 
/*     */     } 
/*  72 */     Messages.showMessageDialog("only support proto files", "protoc", ERROR_ICON);
/*  73 */     return -1;
/*     */   }
/*     */   
/*     */   private String calProtoPath(String filePath) {
/*  77 */     return filePath.substring(0, filePath.lastIndexOf("/") + 1);
/*     */   }
/*     */   
/*     */   private String calOutPath(String filePath) {
/*  81 */     StringBuilder out = new StringBuilder();
/*  82 */     out.append(filePath.substring(0, filePath.lastIndexOf("src")));
/*  83 */     out.append("target/generated-sources/protobuf/java");
/*  84 */     File dir = new File(out.toString());
/*  85 */     if (!dir.exists()) {
/*  86 */       dir.mkdirs();
/*     */     }
/*  88 */     return out.toString();
/*     */   }
/*     */   
/*     */   private int execCmd(String cmd) throws IOException, InterruptedException {
/*  92 */     Process pr = Runtime.getRuntime().exec(cmd);
/*  93 */     BufferedReader reader = new BufferedReader(new InputStreamReader(pr.getErrorStream()));
/*  94 */     pr.waitFor();
/*  95 */     int status = pr.exitValue();
/*  96 */     StringBuilder sb = (new StringBuilder(status)).append("\n");
/*  97 */     for (; reader.ready(); sb.append(reader.readLine()).append("\n"));
/*     */ 
/*     */     
/* 100 */     reader.close();
/* 101 */     if (status != 0) {
/* 102 */       Messages.showMessageDialog(sb.toString(), "protoc", ERROR_ICON);
/*     */     }
/* 104 */     pr.destroy();
/* 105 */     return status;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Downloads\protobuff_plugin\protobuff plugin\protoc.jar!\action\ProtocAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */