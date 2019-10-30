package scr.dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import scr.main.controller.MainControllerInterface;

public class CreateDatabase implements PersistDB{
    
    private MainControllerInterface mainController;

    public CreateDatabase(MainControllerInterface mc) {
        this.mainController = mc;
    }
    
    @Override
    public void visit(String dbName) {
        File here = new File(System.getProperty("user.dir"));
        String root = here.getParent()+"\\Data\\"+dbName;
        
        Path p1 = Paths.get(root);
        if(!Files.exists(p1)){
            try {
                Files.createDirectories(p1);
            } catch (IOException ex) {
                mainController.showError(ex.getMessage(),"Error!!! Não foi possivel criar um novo banco de dados");
            }
        }
        
        root += "\\Metadata";
        p1 = Paths.get(root);
        if(!Files.exists(p1)){
            try {
                Files.createDirectories(p1);
                mainController.addDatabaseName(dbName);
            } catch (IOException ex) {
                mainController.showError(ex.getMessage(),"Error!!! Não foi possivel criar um diretório de metadados");
            }
        }
        
    }
    
}