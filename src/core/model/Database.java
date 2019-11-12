package core.model;

import java.util.ArrayList;
import java.util.List;

public class Database {

    private String dbName;
    private List<Tables> tables;

    public Database(String name) {
        this.dbName = name;
        tables = new ArrayList<>();
    }

    public String getName() {
        return this.dbName;
    }

    public void addTable(Tables line) {
        tables.add(line);
    }

    public int tablesSize() {
        return tables.size();
    }
    
    public Tables getTableById(int i){
        return tables.get(i);
    }
}
