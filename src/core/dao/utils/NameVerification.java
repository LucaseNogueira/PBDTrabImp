package core.dao.utils;

import java.util.regex.Matcher; 
import java.util.regex.Pattern;

public class NameVerification{
    
    public static boolean nameApproved(String name){
        boolean aproved = true;
        
        if(name.length() > 20){
            aproved = false;
        }
        if(Pattern.compile("[^a-z0-9/z._]").matcher(name).find()){
            aproved = false;
        }
        
        return aproved;
    }
}