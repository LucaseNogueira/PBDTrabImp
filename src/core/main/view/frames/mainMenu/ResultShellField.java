package core.main.view.frames.mainMenu;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ResultShellField extends JPanel{
    
    private final int WIDTH = 860;
    private final int HEIGHT = 500;
    private JLabel shellLabel;
    
    public ResultShellField(){
        init();
    }

    private void init() {
        defineProperties();
        initComponents();
        addComponents();
    }

    private void defineProperties() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    private void initComponents() {
        shellLabel = new JLabel("Seja Bem Vindo :D");
    }
    
    private void addComponents(){
        add(shellLabel);
    }
    
    public void showMessage(String message){
        shellLabel.setText(message);
        setBackground(Color.WHITE);
    }
    
    public void showTable(String table){
        shellLabel.setText("AQUI CHAMA AS TABELAS EM HTML");
        setBackground(Color.WHITE);
    }
}