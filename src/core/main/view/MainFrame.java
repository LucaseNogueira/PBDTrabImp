package core.main.view;

import java.awt.Dimension;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import core.main.view.frames.MainMenuInternalFrame;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

    private static MainFrame instance;
    public static final int WIDTH = 1176;
    public static final int HEIGHT = 606;
    private JDesktopPane desktop;
    private JInternalFrame mainMenu;

    private MainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
    }

    public static MainFrame getInstance() {
        if (instance == null) {
            instance = new MainFrame();
        }

        return instance;
    }

    private void init() {
        defineProperties();
        initComponents();
        addComponents();
    }

    private void defineProperties() {
        setSize(new Dimension(WIDTH, HEIGHT));
        setLocationRelativeTo(null);
        setTitle("PBDTrabImp");
    }

    private void initComponents() {
        desktop = new JDesktopPane();

        mainMenu = new MainMenuInternalFrame();
    }

    private void addComponents() {
        setContentPane(desktop);
        desktop.add(mainMenu);
    }

    @Override
    public void setVisible(boolean b) {
        if (mainMenu != null) {
            mainMenu.setBorder(null);
            ((BasicInternalFrameUI) mainMenu.getUI()).setNorthPane(null);
            try {
                mainMenu.setSelected(true);
                mainMenu.setMaximum(true);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            mainMenu.setVisible(true);
        }
        super.setVisible(b);
    }
}
