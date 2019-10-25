package scr.main.view.frames.mainMenu;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;

@SuppressWarnings("serial")
public class MainMenuSideBar extends JScrollPane{
    
    private final int WIDTH = 276;
    private final int HEIGHT = 500;
    private JLabel lblTitle;
    private List<JButton> btnBdaList;
    
    public MainMenuSideBar(){
        init();
    }

    private void init() {
        defineProperties();
        initComponents();
        addComponents();
    }

    private void defineProperties() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.red);
    }

    private void initComponents() {
        lblTitle = new JLabel("Grade de Banco de Dados");
        btnBdaList = new ArrayList<>();
    }

    private void addComponents() {
        add(lblTitle);
    }
    
    
}