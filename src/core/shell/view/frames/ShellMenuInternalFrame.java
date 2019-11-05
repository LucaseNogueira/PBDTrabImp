package core.shell.view.frames;

import core.main.controller.MainControllerInterface;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import core.shell.view.frames.shellMenu.ShellMenuPanel;

public class ShellMenuInternalFrame extends JInternalFrame {

    private JScrollPane menuScrollPane;
    private JPanel shellMenuPanel;
    
    public ShellMenuInternalFrame(MainControllerInterface mc, String dbName) {
        shellMenuPanel = new ShellMenuPanel(mc,dbName);
//        menuScrollPane = new JScrollPane(shellMenuPanel);
//        
//        setContentPane(menuScrollPane);
        setContentPane(shellMenuPanel);
    }
}
