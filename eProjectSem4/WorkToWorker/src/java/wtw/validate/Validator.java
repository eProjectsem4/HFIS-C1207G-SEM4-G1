/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wtw.validate;

/**
 *
 * @author Khanh
 */
public class Validator {

    public static boolean checkStringEmpty(String[] str) {
        for (String item : str) {
            if (item.equals("")) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkLengthName(String name) {
        if (name.length() <= 0 || name.length() > 50) {
            return false;
        }
        return true;
    }

    public static boolean checkLengthNameSkills(String skills) {
        if (skills.length() <= 0 || skills.length() > 100) {
            return false;
        }
        return true;
    }
    
    public static boolean checkLengthDesctiption(String description) {
        if (description.length() <= 0 || description.length() > 500) {
            return false;
        }
        return true;
    }
    
    public static boolean checkLengthFullName(String fullName){
        if(fullName.length() <= 0 || fullName.length() > 50){
            return false;
        }
        return true;
    }
    
    public static boolean checkLengthPassword(String password){
        if(password.length() <= 0 || password.length() > 20){
            return false;
        }
        return true;
    }
    
    public static boolean checkRepassword(String repassword,String password){
        if(repassword.equals(password)){
            return true;
        }
        return false;
    }

}
