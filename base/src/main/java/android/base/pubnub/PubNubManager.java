package android.base.pubnub;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;

import com.google.common.base.Optional;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by clickapps on 5/2/16.
 */
public class PubNubManager {
    private PubNubManager() {

    }

    public static Builder with(@NonNull Context context) {
        return new Builder(context);
    }

    public static Builder with(@NonNull Activity context) {
        return new Builder(context);
    }

    public static Builder with(@NonNull Context context, @NonNull PubNubParam.Event event) {
        return new Builder(context, event);
    }

    public static Builder with(@NonNull Activity context, @NonNull PubNubParam.Event event) {
        return new Builder(context, event);
    }

    public static Builder with(@NonNull Context context, String publish_key, String subscribe_key, String secret_key,
                               String cipher_key, boolean ssl_on) {
        return new Builder(context, publish_key, subscribe_key, secret_key, cipher_key, ssl_on);
    }

    public static Builder with(@NonNull Activity context, String publish_key, String subscribe_key, String secret_key,
                               boolean ssl_on) {
        return new Builder(context, publish_key, subscribe_key, secret_key, ssl_on);
    }
}
