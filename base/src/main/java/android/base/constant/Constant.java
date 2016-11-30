package android.base.constant;


/**
 * The type Constant.
 */
public class Constant {

    public static String PACKAGE_NAME = "";

    /**
     * External storage permission string
     */
    public static final String EXTERNAL_STORAGE_PERMISSION = "android.permission.WRITE_EXTERNAL_STORAGE";
    /**
     * The constant ACTION_BROADCAST_LANGUAGE_CHANGED.
     */
    public static final String ACTION_BROADCAST_LANGUAGE_CHANGED = PACKAGE_NAME + ".android.base.util.LanguageChanged";
    /**
     * The constant BUILD_VERSION_LOLLIPOP.
     */
    public static final int BUILD_VERSION_LOLLIPOP = 21;
    /**
     * The constant BUILD_VERSION_KITKAT.
     */
    public static final int BUILD_VERSION_KITKAT = 19;
    /**
     * The constant BUILD_VERSION_JELLY_BEAN_MR1.
     */
    public static final int BUILD_VERSION_JELLY_BEAN_MR1 = 17;
    /**
     * The constant BUILD_VERSION_ICE_CREAM_SANDWICH.
     */
    public static final int BUILD_VERSION_ICE_CREAM_SANDWICH = 14;

    private Constant() {
        // private constructor
    }
}