package android.base.util;

import android.text.TextUtils;

/**
 * Created by clickapps on 2/7/15.
 * This class is mainly used for validations for the application
 */
public class Validator {


    // alphanumeric parttern for password
//    private static final String PASSWORDPARTTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$";
    private static final String PASSWORDPARTTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\\W)(?=\\S+$).{8,20})";
    private static final String USERNAMEPARTTEN = "(?=\\S+$).{3,}";

    private Validator() {
    }

    /**
     * @param email email address that need to check
     * @return true if email address is matched with defined pattern.
     * Here pattern is used from api level 22 android
     */
    public static boolean isEmailValid(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


    /**
     * @param password that need to check
     * @return true if password is matched with defined pattern.
     * @link http://stackoverflow.com/questions/3802192/regexp-java-for-password-validation
     */
    public static boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.matches(PASSWORDPARTTERN);
    }


    /**
     * @param password
     * @param confirmPassword that need to match
     * @return true if email address is matched with defined pattern.
     * Here pattern is used from api level 22 android
     */
    public static boolean isPasswordMatches(String password, String confirmPassword) {
        return isMatches(password, confirmPassword);
    }

    /**
     * @param username that need to check
     * @return true if email address is matched with defined pattern.
     * Here pattern is used from api level 22 android
     */
    public static boolean isUsernameValid(String username) {
        return username.matches(USERNAMEPARTTEN);
    }

    /**
     * This method is used to check if the entered string is null, blank, or
     * "null"
     *
     * @param str set String to check
     * @return true if null else false
     */
    public static boolean isEmptyOrNull(String str) {
        return !(!TextUtils.isEmpty(str) && !str.equals("null"));
    }

    public static boolean isMatches(String str1, String str2) {
        return TextUtils.equals(str1, str2);
    }
}
