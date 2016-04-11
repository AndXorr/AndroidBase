package android.base.util.categories;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by cesarferreira on 01/11/14.
 */
public class validator extends StringUtils {


    // alphanumeric parttern for password
//    private static final String PASSWORDPARTTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$";
    private static final String PASSWORDPARTTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\\W)(?=\\S+$).{8,20})";
    private static final String USERNAMEPARTTEN = "(?=\\S+$).{3,}";

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
     * @param username that need to check
     * @return true if email address is matched with defined pattern.
     * Here pattern is used from api level 22 android
     */
    public static boolean isUsernameValid(String username) {
        return username.matches(USERNAMEPARTTEN);
    }

}
