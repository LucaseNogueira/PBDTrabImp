package scr.shell.view.frames.shellMenu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ShellMenuPanel extends JPanel {

    private JPanel shellBarPanel;
    private JScrollPane shellPanel;
    private JButton btnExecute;
    private JTextArea textArea;

    public ShellMenuPanel() {
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
        shellBarPanel = new JPanel();
//        shellBarPanel.setBackground(new Color(204, 255, 204));
        ((FlowLayout)shellBarPanel.getLayout()).setAlignment(FlowLayout.LEFT);
        
        
        
        btnExecute = new JButton("Executar");
        btnExecute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("btnExecute");
            }
        });
        
        textArea = new JTextArea();
    }

    private void addComponents() {
        shellBarPanel.add(btnExecute);
        
//        shellPanel.add(textArea);
shellPanel = new JScrollPane(textArea);
        
        add(btnExecute);
        add(shellPanel);
    }
}
