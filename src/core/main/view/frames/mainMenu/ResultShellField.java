package core.main.view.frames.mainMenu;

import java.awt.Dimension;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class ResultShellField extends JScrollPane{
    
    private final int WIDTH = 860;
    private final int HEIGHT = 500;
    
    public ResultShellField(){
        init();
    }

    private void init() {
        defineProperties();
        initComponents();
    }

    private void defineProperties() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    private void initComponents() {
        
    }
}