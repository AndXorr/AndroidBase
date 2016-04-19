package android.base.util;

import android.base.R;
import android.base.constant.Constant;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import java.util.Locale;

/**
 * Created by clickapps on 10/12/15.
 */
public class LocaleUtils extends org.apache.commons.lang3.LocaleUtils{

    public static String getLocaleApplication(Context context) {
        return context.getResources().getConfiguration().locale.toString();
    }

    public static Locale getLocaleCurrentApp(Context context) {
        return context.getResources().getConfiguration().locale;
    }

    public static String getPhoneNumber(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String countryCodeValue = tm.getSimCountryIso();
        if (!Validator.isEmptyOrNull(countryCodeValue)) {
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

    public static void updateLocale(@Nullable Context context, @NonNull String languageCode) {
        updateLocale(context, languageCode, false);
    }

    public static void updateLocale(@Nullable Context context,
                                    @NonNull String languageCode, boolean enableBroadCast) {
        if (context != null
                && Validator.isEmptyOrNull(languageCode)) {
            Locale locale = new Locale(languageCode);
            Resources res = context.getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            res.updateConfiguration(config, dm);
            if (enableBroadCast)
                LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(Constant.ACTION_BROADCAST_LANGUAGE_CHANGED));
        }
    }
}
