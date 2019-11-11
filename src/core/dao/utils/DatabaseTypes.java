package core.dao.utils;

public class DatabaseTypes {

    public static boolean typeApproved(String type) {
        boolean aux = false;
        switch (type) {
            case "int":
                aux = true;
                break;
            case "float":
                aux = true;
                break;
        }
        if (type.length() > 4) {
            String str = type.substring(0, 4);
            System.out.println("Substring: " + str);
            if ("char".equals(str)) {
                str = type.substring(4);
                System.out.println("char Substring: "+str);
                aux = sizeApproved(str);
            }
        }

        return aux;
    }
    
    private static boolean sizeApproved(String str){
        boolean aux = false;
        
        str = str.replace("(", "");
        str = str.replace(")", "");
        
        int size = Integer.parseInt(str);
        
        if(size <= 20){
            aux = true;
        }
        return aux;
    }

}
