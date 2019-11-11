package core.dao.utils;

import java.util.ArrayList;
import java.util.List;

public class STMTCategory {

    private static STMTCategory instance;
    private static List<String> category;
    private static List<String> subCategory;

    private STMTCategory() {
        category = new ArrayList<>();
        subCategory = new ArrayList<>();

        category.add("create");
        category.add("select");
        category.add("insert");

        subCategory.add("into");
        subCategory.add("database");
        subCategory.add("table");
    }

    public static STMTCategory getInstance() {
        if (instance == null) {
            instance = new STMTCategory();
        }

        return instance;
    }

    public static boolean haveCategory(String str) {
        boolean answer = false;
        String[] array = str.split(" ");
        String fp = array[0];
        String sp;
        for (int i = 0; i < category.size(); i++) {
            String aux = category.get(i);
            if (fp.equals(aux)) {
                switch (fp) {
                    case "create":
                        if ((array.length - 1) >= 1) {
                            sp = array[1];
                            if (sp.equals(subCategory.get(1))) {
                                answer = true;
                            }
                            if (sp.equals(subCategory.get(2))) {
                                answer = true;
                            }
                        }
                        break;
                    case "insert":
                        if ((array.length - 1) >= 1) {
                            sp = array[1];
                            if (sp.equals(subCategory.get(0))) {
                                answer = true;
                            }
                        }
                        break;
                    case "select":
                        answer = true;
                        break;
                }
            }
        }

        return answer;
    }
}
