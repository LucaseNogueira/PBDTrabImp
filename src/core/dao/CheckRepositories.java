package core.dao;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CheckRepositories {

    public List<String> findAll() {
        File here = new File(System.getProperty("user.dir"));
        File data = new File(here.getParent() + "\\Data");
        File[] files = data.listFiles();

        List<String> list = new ArrayList<>();

        for (int i = 0; i < files.length; i++) {
            list.add(files[i].getName());
        }
        return list;
    }

    public static String getStrDatabasePath(String dbName) {
        File here = new File(System.getProperty("user.dir"));
        File data = new File(here.getParent() + "\\Data");
        
        return data.getPath() + "\\" +dbName;
    }
    
    public static String getStrMetadataPath(String dbName){
        File here = new File(System.getProperty("user.dir"));
        File data = new File(here.getParent() + "\\Data");
        
        return data.getPath()+ "\\" + dbName + "\\Metadata";
    }
    
    public static boolean tableExists(String dbName,String name) {
        boolean aux = true;
        
        File here = new File(System.getProperty("user.dir"));
        Path pBin = Paths.get(here.getParent()+"\\Data\\"+dbName+"\\"+name+".dat");
        Path pXml = Paths.get(here.getParent()+"\\Data\\"+dbName+"\\Metadata\\"+name+".xml");
        
        if(!Files.exists(pBin) || !Files.exists(pXml)){
            aux = false;
        }
        
        return aux;
    }

    public static String getTablePath(String dbName, String name) {
        File here = new File(System.getProperty("user.dir"));
        return here.getParent()+"\\Data\\"+dbName+"\\"+name+".dat";
    }

    public static String getTableMetaPath(String dbName, String name) {
        File here = new File(System.getProperty("user.dir"));
        return here.getParent()+"\\Data\\"+dbName+"\\Metadata\\"+name+".xml";
    }
}
