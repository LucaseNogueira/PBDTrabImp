package core.main.view.frames.mainMenu;

import core.model.Database;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ResultShellField extends JPanel{
    
    private final int WIDTH = 860;
    private final int HEIGHT = 500;
    private JLabel shellLabel;
    private List<JLabel> lineLabels;
    
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
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    private void initComponents() {
        shellLabel = new JLabel("Seja Bem Vindo :D");
        lineLabels = new ArrayList<>();
    }
    
    private void addComponents(){
        add(shellLabel);
    }
    
    public void showMessage(String message){
        shellLabel.setText(message);
        setBackground(Color.WHITE);
    }

    void showTable(Database banco) {
        if(!lineLabels.isEmpty()){
            lineLabels.clear();
        }
        
        shellLabel.setText("Tabela: "+banco.getName());
        add(shellLabel);
        for(int i = 0; i < banco.tablesSize();i++){
            JLabel label1 = new JLabel("line: "+i);
            lineLabels.add(label1);
            add(label1);
            int largura = banco.getTableById(i).getColumnNames().size();
            for(int j = 0; j < largura;j++){
                String nome = banco.getTableById(i).getColumnNames().get(j);
                String dado = banco.getTableById(i).getColumnDatas().get(j);
                JLabel label2 = new JLabel(nome+" : "+dado);
                lineLabels.add(label2);
                add(label1);
            }
        }
        setBackground(Color.WHITE);
    }
}