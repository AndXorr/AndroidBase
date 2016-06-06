package android.base.pubnub;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;

import com.google.common.base.Optional;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sahni on 7/6/16.
 */
public class Builder {

    private PubNubParam pubNubParam;

    public Builder(@NonNull Context context) {
        pubNubParam = new PubNubParam();
        pubNubParam.context = context;
        defaultParam();
    }

    public Builder(@NonNull Activity context) {
        pubNubParam = new PubNubParam();
        pubNubParam.context = context;
        pubNubParam.activity = context;
        defaultParam();
    }

    public Builder(@NonNull Context context, @NonNull PubNubParam.Event event) {
        pubNubParam = new PubNubParam();
        pubNubParam.context = context;
        pubNubParam.event = event;
        defaultParam();
    }

    public Builder(@NonNull Activity context, @NonNull PubNubParam.Event event) {
        pubNubParam = new PubNubParam();
        pubNubParam.context = context;
        pubNubParam.activity = context;
        pubNubParam.event = event;
        defaultParam();
    }

    public Builder(@NonNull Context context, String publish_key, String subscribe_key, String secret_key,
                   String cipher_key, boolean ssl_on) {
        pubNubParam = new PubNubParam();
        pubNubParam.context = context;
        pubNubParam.secret_key = secret_key;
        pubNubParam.cipher_key = cipher_key;
        pubNubParam.subscribe_key = subscribe_key;
        pubNubParam.publish_key = publish_key;
        pubNubParam.ssl_on = ssl_on;
    }

    public Builder(@NonNull Activity context, String publish_key, String subscribe_key, String secret_key,
                   boolean ssl_on) {
        pubNubParam = new PubNubParam();
        pubNubParam.context = context;
        pubNubParam.activity = context;
        pubNubParam.secret_key = secret_key;
        pubNubParam.subscribe_key = subscribe_key;
        pubNubParam.publish_key = publish_key;
        pubNubParam.ssl_on = ssl_on;
    }

    public void defaultParam() {
        pubNubParam.secret_key = PubNubConstant.SECRET_KEY;
        pubNubParam.cipher_key = PubNubConstant.CIPHER_KEY;
        pubNubParam.subscribe_key = PubNubConstant.SUBSCRIBE_KEY;
        pubNubParam.publish_key = PubNubConstant.PUBLISH_KEY;
        pubNubParam.ssl_on = true;
    }

    public Builder enableGCM(boolean enableGCM) {
        pubNubParam.enableGCM = enableGCM;
        return this;
    }

    public Builder callback(@NonNull PubNubParam.OnPushMessageListener callback) {
        pubNubParam.listener = callback;
        return this;
    }

    public Builder event(@NonNull PubNubParam.Event event) {
        pubNubParam.event = event;
        return this;
    }

    public Builder channels(@NonNull String[] channels) {
        pubNubParam.channels = channels;
        return this;
    }

    // comma seprate
    public Builder channels(@NonNull String channels) {
        pubNubParam.channels = StringUtils.split(channels, ",");
        return this;
    }

    public Builder channels(List<String> channels) {
        List<String> list = Optional.fromNullable(channels).or(new ArrayList<String>());
        if (!list.isEmpty()) {
            String[] temp = new String[list.size()];
            temp = list.toArray(temp);
            pubNubParam.channels = temp;
        }
        return this;
    }

    public void build() {
        PubNub pubNub = new PubNub(pubNubParam);
        pubNub.handleEvent(pubNubParam);
    }
}
