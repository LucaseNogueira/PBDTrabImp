package core.main.view.frames.mainMenu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import core.main.controller.MainControllerInterface;

@SuppressWarnings("serial")
public class MainMenuBar extends JPanel{
    
    private MainControllerInterface mainController;
    private JButton btnCreateBDA;
    private JButton btnShellBDA;
    
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
    }

    private void initComponents() {
        btnCreateBDA = new JButton("Criar Banco");
        btnCreateBDA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainController.showInputDatabase();
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