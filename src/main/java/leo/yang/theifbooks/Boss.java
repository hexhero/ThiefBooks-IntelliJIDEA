package leo.yang.theifbooks;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * @author leo.yang
 * @date 2021/11/19
 */
public class Boss extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Project project = e.getRequiredData(CommonDataKeys.PROJECT);
        Editor editor = e.getData(CommonDataKeys.EDITOR);
        assert editor != null;
        Document document = editor.getDocument();
        Caret primaryCaret = editor.getCaretModel().getPrimaryCaret();
        WriteCommandAction.runWriteCommandAction(project, () -> {
            String text = document.getText();

            String lineText = document.getText(TextRange.create(primaryCaret.getVisualLineStart(), primaryCaret.getVisualLineEnd()));
//            if(lineText != null){
//                if(lineText.contains("path=")){
//                    String[] str = lineText.split("=");
//                    try {
//                        document.replaceString(primaryCaret.getVisualLineStart(), primaryCaret.getVisualLineEnd(), Book.loadBook(str[1]));
//                        Book.line = 0;
//                    } catch (IOException ex) {
//                        ex.printStackTrace();
//                        document.replaceString(primaryCaret.getVisualLineStart(), primaryCaret.getVisualLineEnd(), "Error:" +ex);
//                    }
//                    return;
//                }
//                if(lineText.contains("line=")){
//                    String[] str = lineText.split("=");
//                    Book.line = Integer.valueOf(str[1]);
//                    document.replaceString(primaryCaret.getVisualLineStart(), primaryCaret.getVisualLineEnd(), "line set success");
//                    return;
//                }
//            }
            if (text != null) {
                document.setText(text.replaceAll("//-.+\\n", "\n"));
            }
        });
        primaryCaret.removeSelection();
    }
}
