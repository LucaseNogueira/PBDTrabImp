package scr.main.view.frames.mainMenu;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import scr.main.controller.MainController;
import scr.main.controller.MainControllerInterface;
import scr.main.controller.MainControllerObserver;
import scr.shell.view.ShellFrame;

@SuppressWarnings("serial")
public class MainMenuPanel extends JPanel implements MainControllerObserver{
    
    private MainControllerInterface mainController;
    private MainMenuBar menuBar;
    private JPanel menuContent;
    private MainMenuSideBar menuSideBar;
    private ResultShellField resultShell;
    
    public MainMenuPanel(){
        mainController = new MainController();
        mainController.attach(this);
        init();
    }

    private void init() {
        defineProperties();
        initComponents();
        addComponents();
    }

    private void defineProperties(){
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    }
    
    private void initComponents() {
        menuBar = new MainMenuBar(mainController);
        
        menuContent = new JPanel();
        menuContent.setLayout(new FlowLayout());
//        menuContent.setPreferredSize(new Dimension(WIDTH, 584));
        ((FlowLayout)menuContent.getLayout()).setAlignment(FlowLayout.LEFT);
        
        menuSideBar = new MainMenuSideBar();
        
        resultShell = new ResultShellField();
    }

    private void addComponents() {
        add(menuBar);
        
        menuContent.add(menuSideBar);
        menuContent.add(resultShell);
        
        add(menuContent);
    }

    @Override
    public void openANewShell() {
        ShellFrame shellFrame = new ShellFrame(mainController);
        shellFrame.setVisible(true);
    }
}