package android.base.util;

import android.base.R;
import android.content.Context;
import android.telephony.TelephonyManager;

import java.util.Locale;

/**
 * Created by clickapps on 10/12/15.
 */
public class LocaleUtils {

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

}
