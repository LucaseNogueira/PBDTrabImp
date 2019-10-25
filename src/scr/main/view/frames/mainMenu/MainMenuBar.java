package scr.main.view.frames.mainMenu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import scr.main.controller.MainControllerInterface;

@SuppressWarnings("serial")
public class MainMenuBar extends JPanel{
    
    private MainControllerInterface mainController;
    private JButton btnCreateBDA;
    private JButton btnShellBDA;
    
//    private final int HEIGHT = 22;
//    private final int B_HEIGHT = 20;
//    private final int B_WIDTH = 40;
    
    public MainMenuBar(MainControllerInterface mainController){
        this.mainController = mainController;
        init();
    }

    private void init() {
        defineProperties();
        initComponents();
        addComponents();
    }

    private void defineProperties() {
        setBackground(new Color(204, 255, 204));
        ((FlowLayout)getLayout()).setAlignment(FlowLayout.LEFT);
//        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    private void initComponents() {
        btnCreateBDA = new JButton("Criar Banco");
        btnCreateBDA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("btnCreateBDA");
            }
        });
        
        btnShellBDA = new JButton("Shell");
        btnShellBDA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainController.showShell();
            }
        });
        
    }

    private void addComponents() {
        add(btnCreateBDA);
        add(btnShellBDA);
    }
}