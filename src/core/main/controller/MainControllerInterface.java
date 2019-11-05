/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.main.controller;

import java.util.List;

/**
 *
 * @author lucas
 */
public interface MainControllerInterface {

    void attach(MainControllerObserver obs);
    
    void detach(MainControllerObserver obs);
    
    void showShell();

    void showInputDatabase();

    void createDatabase(String dbName);
    
    void addDatabaseName(String dbName);

    void showError(String error_Não_foi_possivel_criar_um_novo_banc, String message);
    
    List<String> checkRepositories();

    List<String> getDatabaseName();

    void iWantThisBD(String name,boolean status);
}
