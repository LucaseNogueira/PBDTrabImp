package core.shell.controller;

import core.dao.PersistDB;
import core.main.controller.MainControllerInterface;
import core.model.Database;

public class ShellController implements ShellControllerInterface{
    
    private MainControllerInterface mainController;
    private PersistDB persist;
    private String dbName;
    
    public ShellController(MainControllerInterface mc, String dbName){
        this.mainController = mc;
        this.persist = new PersistDB(this);
        this.dbName = dbName;
    }

    @Override
    public void textAreaResult(String txt) {
        if(mainController.getShellText(txt)){
            persist.executeDB(txt);
        }
    }

    @Override
    public String getDBName() {
        return this.dbName;
    }

    @Override
    public void success(String message) {
        mainController.ShellSuccessMessage(message);
    }

    @Override
    public void failed(String message) {
        mainController.ShellErrorMessage(message);
    }

    @Override
    public void showTable(Database banco) {
        mainController.showTable(banco);
    }
}