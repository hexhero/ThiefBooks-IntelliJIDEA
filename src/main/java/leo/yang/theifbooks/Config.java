package leo.yang.theifbooks;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

/**
 * TheifBooks - Config
 *
 * @author Leo Yang create on 2023/11/16
 */
public class Config extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        ConfigDialog configDialog = new ConfigDialog();
        if(configDialog.showAndGet()){
            String path = configDialog.path.getText();
            System.out.println(path);
            String line = configDialog.line.getText();
            System.out.println(line);
            Book.config(path, line);
        }
    }

    public static class ConfigDialog extends DialogWrapper{

        JTextField path;
        JTextField line;

        public ConfigDialog() {
            super(true); // use current window as parent
            setTitle("Book Config");
            init();
        }

        @Override
        protected @Nullable JComponent createCenterPanel() {
            Map<String, String> info = Book.info();
            JPanel panel = new JPanel();
            JPanel pathPanel = new JPanel(new BorderLayout());
            JLabel pathLabel = new JLabel("Path");
            pathLabel.setPreferredSize(new Dimension(50, 20));
            pathPanel.add(pathLabel,BorderLayout.WEST);
            path = new JTextField(info.get("path"));
            path.setPreferredSize(new Dimension(300, 20));
            pathPanel.add(path, BorderLayout.CENTER);

            JPanel linePanel = new JPanel(new BorderLayout());
            JLabel lineLabel = new JLabel("Line");
            lineLabel.setPreferredSize(new Dimension(50, 20));
            linePanel.add(lineLabel, BorderLayout.WEST);
            line = new JTextField(info.get("line"));
            line.setPreferredSize(new Dimension(300, 20));
            linePanel.add(line,BorderLayout.CENTER);

            panel.setLayout(new GridLayout(2,1));
            panel.add(pathPanel);
            panel.add(linePanel);
            return panel;
        }
    }
}
