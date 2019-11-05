package core.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CheckRepositories{
    
    public List<String> findAll(){
        File here = new File(System.getProperty("user.dir"));
        File data = new File(here.getParent()+"\\Data");
        File[] files = data.listFiles();
        
        List<String> list = new ArrayList<>();
        
        for(int i = 0; i < files.length; i++){
            list.add(files[i].getName());
        }
        return list;
    }
}