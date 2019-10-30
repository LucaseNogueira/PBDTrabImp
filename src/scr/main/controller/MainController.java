package scr.main.controller;

import java.util.ArrayList;
import java.util.List;
import scr.dao.CheckRepositories;
import scr.dao.CreateDatabase;

public class MainController implements MainControllerInterface {

    private List<MainControllerObserver> observers;
    private List<String> databaseList;
    private String dbActing;

    public MainController() {
        this.databaseList = checkRepositories();
        this.observers = new ArrayList<>();
    }

    @Override
    public void attach(MainControllerObserver obs) {
        this.observers.add(obs);
    }

    @Override
    public void detach(MainControllerObserver obs) {
        this.observers.remove(obs);
    }

    @Override
    public void showShell() {
        if (dbActing == null) {
            notifySystemError("Error de acesso a shell", "Para ter acesso a shell, é preciso criar um banco de dados e seleciona-lo.\n"
                    + "Por favor, verifique se há banco de dados criados no sistema.\n"
                    + "Por favor, verifique se algum banco de dados esta habilidado (bancos habilidados se encontram nas cores azuis).");
        } else {
            notifyShowShell(dbActing);
        }
    }

    @Override
    public void showInputDatabase() {
        notifyShowInputDatabase("Insira o nome do seu banco de dados");
    }

    @Override
    public void createDatabase(String dbName) {
        if (dbName != null) {

            if (dbName.equals("")) {
                notifyErrorInputDatabase("Verifique se o campo de texto não esta vazio.\n\nInsira o nome do seu banco de dados.");
            } else {
                boolean theSame = false;
                for (String name : databaseList) {
                    if (dbName.equals(name)) {
                        theSame = true;
                    }
                }

                if (theSame == false) {
                    CreateDatabase cd = new CreateDatabase(this);
                    cd.visit(dbName);
                } else {
                    notifyErrorInputDatabase("Verifique se um banco de dados com o nome requerido já existe.\n\nInsira o nome do seu banco de dados.");
                }
            }
        }
    }

    @Override
    public void addDatabaseName(String dbName) {
        databaseList.add(dbName);
        notifyAddDatabase(dbName);
    }

    @Override
    public void showError(String title, String message) {
        notifySystemError(title, message);
    }

    @Override
    public List<String> checkRepositories() {
        CheckRepositories check = new CheckRepositories();
        return check.findAll();
    }

    @Override
    public List<String> getDatabaseName() {
        return databaseList;
    }

    @Override
    public void iWantThisBD(String name, boolean status) {
        if (status == false) {
            dbActing = name;
            status = true;
            notifyChosenBD(name,status);
        }else{
            dbActing = null;
            status = false;
            notifyRejectedBD(name,status);
        }
    }

    private void notifyShowShell(String shellTitle) {
        for (MainControllerObserver obs : observers) {
            obs.openANewShell(shellTitle);
        }
    }

    private void notifyShowInputDatabase(String message) {
        for (MainControllerObserver obs : observers) {
            obs.openDatabaseInputAlert(message);
        }
    }

    private void notifyErrorInputDatabase(String verification) {
        for (MainControllerObserver obs : observers) {
            obs.openDatabaseInputAlert(verification);
        }
    }

    private void notifySystemError(String title, String message) {
        for (MainControllerObserver obs : observers) {
            obs.systemError(title, message);
        }
    }

    private void notifyAddDatabase(String dbName) {
        for (MainControllerObserver obs : observers) {
            obs.createDBButton(dbName);
        }
    }

    private void notifyChosenBD(String name,boolean status) {
        for (MainControllerObserver obs : observers) {
            obs.chosenBD(name,status);
        }
    }

    private void notifyRejectedBD(String name, boolean status) {
        for (MainControllerObserver obs : observers) {
            obs.rejectedBD(name,status);
        }
    }

}
