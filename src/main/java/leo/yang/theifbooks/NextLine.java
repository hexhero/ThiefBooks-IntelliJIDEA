package leo.yang.theifbooks;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;

/**
 * @author leo.yang
 * @date 2021/7/2
 */
public class NextLine extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getRequiredData(CommonDataKeys.PROJECT);
        Editor editor = e.getData(CommonDataKeys.EDITOR);
        assert editor != null;
        Document document = editor.getDocument();
        Caret primaryCaret = editor.getCaretModel().getPrimaryCaret();
        WriteCommandAction.runWriteCommandAction(project, () -> {
                    StringBuilder sb = new StringBuilder(Book.prefix);
                    sb.append(Book.nextLine());
                    sb.append(" \n");
                    for(int i =Book.lineLength; i < sb.length(); i += Book.lineLength){
                        sb.insert(i, "\n" + Book.prefix);
                    }
                    document.replaceString(primaryCaret.getVisualLineStart(), primaryCaret.getVisualLineEnd(), sb.toString());
                }
        );
        primaryCaret.removeSelection();
    }
}
