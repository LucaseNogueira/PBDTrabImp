package core.main.view.frames.mainMenu;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import core.main.controller.MainController;
import core.main.controller.MainControllerInterface;
import core.main.controller.MainControllerObserver;
import core.model.Database;
import core.shell.view.ShellFrame;
import core.utils.Alerts;

@SuppressWarnings("serial")
public class MainMenuPanel extends JPanel implements MainControllerObserver {

    private MainControllerInterface mainController;
    private MainMenuBar menuBar;
    private JPanel menuContent;
    private JScrollPane leftScroll;
    private JScrollPane rightScroll;
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
        
        ((FlowLayout) menuContent.getLayout()).setAlignment(FlowLayout.LEFT);

        menuSideBar = new MainMenuSideBar(mainController);

        resultShell = new ResultShellField();
        
        leftScroll = new JScrollPane(menuSideBar);
        
        rightScroll = new JScrollPane(resultShell);
    }

    private void addComponents() {
        add(menuBar);

        menuContent.add(leftScroll);
        menuContent.add(rightScroll);

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

    @Override
    public void shellNullPoint(String erro) {
        resultShell.showMessage(erro);
        updateUI();
    }

    @Override
    public void shellSintaxeErro(String erro) {
        resultShell.showMessage(erro);
        updateUI();
    }

    @Override
    public void shellSintaxeSuccess(String success) {
        resultShell.showMessage(success);
    }

    @Override
    public void showTable(Database banco) {
        resultShell.showTable(banco);
    }
}
