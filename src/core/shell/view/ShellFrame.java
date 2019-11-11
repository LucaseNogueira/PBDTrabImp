package core.shell.view;

import java.awt.Dimension;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import core.main.controller.MainControllerInterface;
import core.shell.view.frames.ShellMenuInternalFrame;

public class ShellFrame extends JFrame{
    
    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;
    private MainControllerInterface mainController;
    private JDesktopPane desktop;
    private ShellMenuInternalFrame shellMenu;
    private String name;

    public ShellFrame(MainControllerInterface mainController,String name) {
        this.mainController = mainController;
        this.name = name;
        init();
    }

    private void init() {
        defineProperties();
        initComponents();
        addComponents();
    }

    private void defineProperties() {
        setSize(new Dimension(WIDTH, HEIGHT));
        setLocationRelativeTo(null);
        setTitle("PBDTrabImp - "+name+" SHELL");
    }

    private void initComponents() {
        desktop = new JDesktopPane();

        shellMenu = new ShellMenuInternalFrame(mainController,name);
    }

    private void addComponents() {
        setContentPane(desktop);
        desktop.add(shellMenu);
    }

    @Override
    public void setVisible(boolean b) {
        if (shellMenu != null) {
            shellMenu.setBorder(null);
            ((BasicInternalFrameUI) shellMenu.getUI()).setNorthPane(null);
            try {
                shellMenu.setSelected(true);
                shellMenu.setMaximum(true);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            shellMenu.setVisible(true);
        }
        super.setVisible(b);
    }
}