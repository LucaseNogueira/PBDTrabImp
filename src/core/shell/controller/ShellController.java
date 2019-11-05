package core.shell.controller;

import core.main.controller.MainControllerInterface;

public class ShellController implements ShellControllerInterface{
    
    private MainControllerInterface mainController;
    private String dbName;
    
    public ShellController(MainControllerInterface mc, String dbName){
        this.mainController = mc;
        this.dbName = dbName;
    }

    @Override
    public void textAreaResult(String txt) {
        if(txt != null){
            
        }
//        System.out.println(txt);
    }
}