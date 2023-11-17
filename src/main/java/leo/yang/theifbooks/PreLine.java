package leo.yang.theifbooks;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

/**
 * @author leo.yang
 * @date 2021/11/19
 */
public class PreLine extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Project project = e.getRequiredData(CommonDataKeys.PROJECT);
        Editor editor = e.getData(CommonDataKeys.EDITOR);
        assert editor != null;
        Document document = editor.getDocument();
        Caret primaryCaret = editor.getCaretModel().getPrimaryCaret();
        WriteCommandAction.runWriteCommandAction(project, () -> {
                    StringBuilder sb = new StringBuilder("//- ");
                    sb.append(Book.preLine());
                    sb.append(" \n");
                    for (int i = 100; i < sb.length(); i += 100) {
                        sb.insert(i, "\n//- ");
                    }
                    document.replaceString(primaryCaret.getVisualLineStart(), primaryCaret.getVisualLineEnd(), sb.toString());
                }
        );
        primaryCaret.removeSelection();
    }
}
