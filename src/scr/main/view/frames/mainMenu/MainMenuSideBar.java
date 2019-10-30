package scr.main.view.frames.mainMenu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;
import scr.main.controller.MainControllerInterface;
import scr.utils.DatabaseButton;

@SuppressWarnings("serial")
public class MainMenuSideBar extends JPanel {

    private MainControllerInterface mainController;
    private final int WIDTH = 276;
    private final int HEIGHT = 500;
    private JLabel lblTitle;
    private List<DatabaseButton> btnBdaList;
    int size;

    public MainMenuSideBar(MainControllerInterface mc) {
        this.mainController = mc;
        size = 0;
        init();
    }

    private void init() {
        defineProperties();
        initComponents();
        addComponents();
    }

    private void defineProperties() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLayout(new GridBagLayout());
    }

    private void initComponents() {
        lblTitle = new JLabel("Grade de Banco de Dados");

        List<String> btnNames = mainController.getDatabaseName();
        btnBdaList = new ArrayList<>();
        for (String name : btnNames) {
            btnBdaList.add(createButton(name));
        }
    }

    private void addComponents() {
        add(lblTitle, createButtonConstraints(size));
        size++;

        for (JButton btn : btnBdaList) {
            add(btn, createButtonConstraints(size));
            size++;
        }
    }

    private DatabaseButton createButton(String name) {
        DatabaseButton button = new DatabaseButton();
        button.setText(name);
        button.setBackground(Color.CYAN);
        button.setStatus(false);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainController.iWantThisBD(name, button.getStatus());
            }
        });
        return button;
    }

    private GridBagConstraints createButtonConstraints(int y) {
        GridBagConstraints cons = new GridBagConstraints();
        cons.gridx = 0;
        cons.gridy = y;
        cons.gridwidth = 1;
        cons.anchor = GridBagConstraints.NORTH;
        cons.fill = GridBagConstraints.VERTICAL;
        cons.insets = new Insets(5, 0, 5, 0);
        return cons;
    }

    public void addNewButton(String name) {
        DatabaseButton btn = createButton(name);
        btnBdaList.add(btn);
        add(btn, createButtonConstraints(size));
        size++;
    }

    public void bdChosen(String name, boolean status) {
        for (DatabaseButton bd : btnBdaList) {
            bd.setStatus(!status);
            bd.setBackground(Color.CYAN);
            bd.setForeground(Color.BLACK);
        }

        for (DatabaseButton bd : btnBdaList) {
            if (bd.getText() == name) {
                bd.setStatus(status);
                bd.setBackground(Color.BLUE);
                bd.setForeground(Color.WHITE);
            }
        }
    }

    public void bdReject(String name, boolean status) {
        for(DatabaseButton bd : btnBdaList){
            if (bd.getText() == name) {
            bd.setStatus(status);
            bd.setBackground(Color.CYAN);
            bd.setForeground(Color.BLACK);
            }
        }
    }

}
