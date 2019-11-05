package core.utils;

import javax.swing.JOptionPane;

public class Alerts {

    public static String showInputAlert(String message) {
        return JOptionPane.showInputDialog(message);
    }
    
    public static void systemErrorMessage(String title,String message){
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }
}
