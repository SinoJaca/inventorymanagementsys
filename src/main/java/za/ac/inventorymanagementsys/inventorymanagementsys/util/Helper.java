package za.ac.inventorymanagementsys.inventorymanagementsys.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helper {

    public static final Pattern VALID_EMAIL_ADDRESS =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static  final  Pattern VALID_PHONE_NUMBER =
            Pattern.compile("(^[\\+27][ 0-9]{14})|(^[\\+27]( ?)[0-9]{11})|(^0[0-9]{9})|(^0[ 0-9]{11})$", Pattern.CASE_INSENSITIVE);

    // validate email
    public static boolean validate(String email){
        Matcher matcher = VALID_EMAIL_ADDRESS.matcher(email);
        return matcher.find();
    }

    // validate empty field
    public static boolean IsNullOrEmptyField(String value){
        return StringUtils.isEmpty(value);
    }

    // validate fullName on user register
    public static boolean IsNullOrEmptyFullName(String fullName){
        return StringUtils.isEmpty(fullName);
    }

    // validate Password on user creation
    public static boolean IsNullOrEmptyPassword(String password){
        return StringUtils.isEmpty(password);
    }

    // check if insertion is successfully or not and return code
    public static String declineCode(){
        String code = "DEC01";
        return code;
    }

    public static String approvedCode(){
        String code = "APP01";
        return code;
    }

    // validate phone number based on south africa
    public static boolean isValidPhoneNumber(String mobileNumber){
        Matcher matcher = VALID_PHONE_NUMBER.matcher(mobileNumber);
        return matcher.find();
    }
}
