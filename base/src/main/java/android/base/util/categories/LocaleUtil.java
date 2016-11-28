package android.base.util.categories;

import android.base.R;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.LayoutDirection;

import org.apache.commons.lang3.LocaleUtils;

import java.util.Locale;


/**
 * The type Locale utils.
 */
public class LocaleUtil extends LocaleUtils {

    /**
     * Gets locale application.
     *
     * @param context the context
     * @return the locale application
     */
    public static String getLocaleApplication(Context context) {
        return context.getResources().getConfiguration().locale.toString();
    }

    /**
     * Gets locale current app.
     *
     * @param context the context
     * @return the locale current app
     */
    public static Locale getLocaleCurrentApp(Context context) {
        return context.getResources().getConfiguration().locale;
    }

    /**
     * Gets phone number.
     *
     * @param context the context
     * @return the phone number
     */
    public static String getPhoneNumber(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String countryCodeValue = tm.getSimCountryIso();
        if (!ValidatorUtil.isEmptyOrNull(countryCodeValue)) {
            countryCodeValue = countryCodeValue.toUpperCase();
            String[] rl = context.getResources().getStringArray(R.array.CountryCodes);
            for (int i = 0; i < rl.length; i++) {
                String[] g = rl[i].split(",");
                if (g[1].trim().equals(countryCodeValue.trim())) {
                    countryCodeValue = g[0];
                    countryCodeValue = "+" + countryCodeValue;
                    break;
                }
            }
        }

        return countryCodeValue;
    }

    /**
     * Update locale.
     *
     * @param context      the context
     * @param languageCode the language code
     */
    public static void updateLocale(@Nullable Context context, @NonNull String languageCode) {
        updateLocale(context, languageCode, false);
    }

    /**
     * Update locale.
     *
     * @param context         the context
     * @param languageCode    the language code
     * @param enableBroadCast the enable broad cast
     */
    public static void updateLocale(@Nullable Context context,
                                    @NonNull String languageCode, boolean enableBroadCast) {
        if (context != null
                && !ValidatorUtil.isEmptyOrNull(languageCode)) {
            Locale locale = new Locale(languageCode);
            Resources res = context.getResources();
            Locale.setDefault(locale);
            Resources resources = context.getResources();
            Configuration config = resources.getConfiguration();
            config.setLocale(locale);
            config.setLayoutDirection(locale);
            res.updateConfiguration(config, resources.getDisplayMetrics());
            if (enableBroadCast)
                LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(Intent.ACTION_LOCALE_CHANGED));
        }
    }
}
