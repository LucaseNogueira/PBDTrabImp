package scr.main.view.frames.mainMenu;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import scr.main.controller.MainController;
import scr.main.controller.MainControllerInterface;
import scr.main.controller.MainControllerObserver;
import scr.shell.view.ShellFrame;
import scr.utils.Alerts;

@SuppressWarnings("serial")
public class MainMenuPanel extends JPanel implements MainControllerObserver {

    private MainControllerInterface mainController;
    private MainMenuBar menuBar;
    private JPanel menuContent;
    private JScrollPane leftScroll;
    private MainMenuSideBar menuSideBar;
    private ResultShellField resultShell;

    public MainMenuPanel() {
        mainController = new MainController();
        mainController.attach(this);
        init();
    }

    private void init() {
        defineProperties();
        initComponents();
        addComponents();
    }

    private void defineProperties() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    }

    private void initComponents() {
        menuBar = new MainMenuBar(mainController);

        menuContent = new JPanel();
        menuContent.setLayout(new FlowLayout());
//        menuContent.setPreferredSize(new Dimension(WIDTH, 584));
        ((FlowLayout) menuContent.getLayout()).setAlignment(FlowLayout.LEFT);

        menuSideBar = new MainMenuSideBar(mainController);

        resultShell = new ResultShellField();
        
        leftScroll = new JScrollPane(menuSideBar);
    }

    private void addComponents() {
        add(menuBar);

        menuContent.add(leftScroll);
        menuContent.add(resultShell);

        add(menuContent);
    }

    @Override
    public void openANewShell(String shellTitle) {
        ShellFrame shellFrame = new ShellFrame(mainController,shellTitle);
        shellFrame.setVisible(true);
    }

    @Override
    public void openDatabaseInputAlert(String message) {
        String dbName = Alerts.showInputAlert(message);
        mainController.createDatabase(dbName);
    }

    @Override
    public void systemError(String title, String message) {
        Alerts.systemErrorMessage(title, message);
    }

    @Override
    public void createDBButton(String name) {
        menuSideBar.addNewButton(name);
        updateUI();
    }

    @Override
    public void chosenBD(String name, boolean status) {
        menuSideBar.bdChosen(name,status);
        updateUI();
    }

    @Override
    public void rejectedBD(String name, boolean status) {
        menuSideBar.bdReject(name,status);
        updateUI();
    }
}
