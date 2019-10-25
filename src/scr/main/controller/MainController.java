package scr.main.controller;

import java.util.ArrayList;
import java.util.List;

public class MainController implements MainControllerInterface{
    
    private List<MainControllerObserver> observers;
    private String nameBDA;
    
    public MainController(){
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
        notifyShowShell();
    }

    private void notifyShowShell() {
        for(MainControllerObserver obs : observers){
            obs.openANewShell();
        }
    }
    
}