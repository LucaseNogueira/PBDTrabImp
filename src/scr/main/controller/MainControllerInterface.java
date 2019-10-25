/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scr.main.controller;

/**
 *
 * @author lucas
 */
public interface MainControllerInterface {

    void attach(MainControllerObserver obs);
    
    void detach(MainControllerObserver obs);
    
    void showShell();
    
}
