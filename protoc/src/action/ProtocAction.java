package action;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.vfs.VirtualFile;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.Icon;

public class ProtocAction extends AnAction {
  public static final Icon PROTO_ICON = IconLoader.getIcon("/icon.png");
  
  public static final Icon ERROR_ICON = IconLoader.getIcon("/error.png");
  
  public static final Icon PASS_ICON = IconLoader.getIcon("/passed.png");
  
  public ProtocAction() {
    super(null, null, PROTO_ICON);
  }
  
  public ProtocAction(String text, String desc, Icon icon) {
    super(text, desc, PROTO_ICON);
  }
  
  public void actionPerformed(AnActionEvent event) {
    String exePath = PropertiesComponent.getInstance().getValue(ConfigSwing.EXE_PATH);
    String outPath = PropertiesComponent.getInstance().getValue(ConfigSwing.OUT_PATH);
    File dir = new File(outPath);
      if (!dir.exists()) {
          dir.mkdirs();
      }
    VirtualFile[] files = (VirtualFile[])event.getData(PlatformDataKeys.VIRTUAL_FILE_ARRAY);
    int succNum = 0;
    File exeFile = new File(exePath);
      if (!exeFile.exists()) {
          Messages.showMessageDialog("not found protoc.exe", "Exception", ERROR_ICON);
      }
    for (VirtualFile file : files) {
      int status = run(file, exePath, outPath);
        if (status == 0) {
            succNum++;
        }
    }
      if (succNum == files.length) {
          Messages.showMessageDialog("protoc succ!!", "protoc", PASS_ICON);
      }
  }
  
  private int run(VirtualFile data, String exePath, String outPath) {
    if ("proto".equals(data.getExtension())) {
      String url = data.getUrl();
      url = url.split("//")[1];
      String out = outPath;
      String protoPath = calProtoPath(url);
      String cmd = String.format("%s -I=%s --java_out=%s %s", new Object[] { exePath, protoPath.substring(0, protoPath.length() - 1), out, url });
      try {
        int status = execCmd(cmd);
        return status;
      } catch (Exception e) {
        Messages.showMessageDialog(e.getMessage(), "protoc", ERROR_ICON);
        Messages.showMessageDialog(cmd, "protoc", ERROR_ICON);
        e.printStackTrace();
        return -1;
      } 
    } 
    Messages.showMessageDialog("only support proto files", "protoc", ERROR_ICON);
    return -1;
  }
  
  private String calProtoPath(String filePath) {
    return filePath.substring(0, filePath.lastIndexOf("/") + 1);
  }
  
  private String calOutPath(String filePath) {
    StringBuilder out = new StringBuilder();
    out.append(filePath.substring(0, filePath.lastIndexOf("src")));
    out.append("target/generated-sources/protobuf/java");
    File dir = new File(out.toString());
      if (!dir.exists()) {
          dir.mkdirs();
      }
    return out.toString();
  }
  
  private int execCmd(String cmd) throws IOException, InterruptedException {
    Process pr = Runtime.getRuntime().exec(cmd);
    BufferedReader reader = new BufferedReader(new InputStreamReader(pr.getErrorStream()));
    pr.waitFor();
    int status = pr.exitValue();
    StringBuilder sb = (new StringBuilder(status)).append("\n");
      for (; reader.ready(); sb.append(reader.readLine()).append("\n")) {
          ;
      }
    reader.close();
      if (status != 0) {
          Messages.showMessageDialog(sb.toString(), "protoc", ERROR_ICON);
      }
    pr.destroy();
    return status;
  }
}
