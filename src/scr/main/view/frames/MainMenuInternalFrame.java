package scr.main.view.frames;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;
import scr.main.view.frames.mainMenu.MainMenuPanel;

@SuppressWarnings("serial")
public class MainMenuInternalFrame extends JInternalFrame{

    private JScrollPane menuScrollPane;
    private MainMenuPanel mainMenuPanel;
    
    public MainMenuInternalFrame(){
        mainMenuPanel = new MainMenuPanel();
        menuScrollPane = new JScrollPane(mainMenuPanel);
        
        setContentPane(menuScrollPane);
    }
}