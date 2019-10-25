package scr.shell.view.frames;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import scr.shell.view.frames.shellMenu.ShellMenuPanel;

public class ShellMenuInternalFrame extends JInternalFrame {

    private JScrollPane menuScrollPane;
    private JPanel shellMenuPanel;
    
    public ShellMenuInternalFrame() {
        shellMenuPanel = new ShellMenuPanel();
//        menuScrollPane = new JScrollPane(shellMenuPanel);
//        
//        setContentPane(menuScrollPane);
        setContentPane(shellMenuPanel);
    }
}
