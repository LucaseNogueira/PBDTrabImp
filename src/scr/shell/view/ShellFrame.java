package scr.shell.view;

import java.awt.Dimension;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import scr.main.controller.MainControllerInterface;
import scr.shell.view.frames.ShellMenuInternalFrame;

public class ShellFrame extends JFrame{
    
    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;
    private JDesktopPane desktop;
    private ShellMenuInternalFrame shellMenu;

    public ShellFrame(MainControllerInterface mainController) {
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
        setTitle("SHELL - PBDTrabImp");
    }

    private void initComponents() {
        desktop = new JDesktopPane();

        shellMenu = new ShellMenuInternalFrame();
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