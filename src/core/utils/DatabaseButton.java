package core.utils;

import javax.swing.JButton;

public class DatabaseButton extends JButton{

    public DatabaseButton() {
        super();
    }
    
    private boolean status;

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
}