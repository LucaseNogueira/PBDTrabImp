package core.model;

import java.util.ArrayList;
import java.util.List;

public class Tables {

    private List<String> columnNames;
    private List<String> columnDatas;

    public Tables() {
        this.columnNames = new ArrayList<>();
        this.columnDatas = new ArrayList<>();
    }

    public List<String> getColumnNames() {
        return columnNames;
    }

    public List<String> getColumnDatas() {
        return columnDatas;
    }

    public void addColumnNames(String str) {
        boolean aux = false;
        for (String name : columnNames) {
            if (name == str) {
                aux = true;
            }
        }
        if (!aux) {
            columnNames.add(str);
        }
    }

    public void addColumnDatas(String str) {
        columnDatas.add(str);
    }
}
