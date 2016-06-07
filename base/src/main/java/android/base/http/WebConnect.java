package android.base.http;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Created by clickapps on 20/4/16.
 */
public class WebConnect {
    private static volatile WebConnect sWebConnect;

    private WebConnect() {

    }

    public static WebConnect get() {
        if (sWebConnect == null) {
            synchronized (WebConnect.class) {
                if (sWebConnect == null) {
                    sWebConnect = new WebConnect();
                }
            }
        }
        return sWebConnect;
    }

    public static Builder with(@NonNull Activity context, @NonNull String url) {
        return new Builder(context, url);

    }

    public static Builder with(@NonNull Context context, @NonNull String url) {
        return new Builder(context, url);
    }
}
