/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.main.controller;

/**
 *
 * @author lucas
 */
public interface MainControllerObserver {

    void openANewShell(String shellTitle);

    void openDatabaseInputAlert(String message);

    void systemError(String title, String message);

    void createDBButton(String dbName);

    public void chosenBD(String name, boolean status);

    public void rejectedBD(String name, boolean status);

    public void shellNullPoint(String erro);

    public void shellSintaxeErro(String erro);
    
}
