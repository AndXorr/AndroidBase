package android.base.pubnub;

import android.app.Activity;
import android.content.Context;

/**
 * Created by clickapps on 5/2/16.
 */
public class PubNubParam {
    protected Context context;
    protected Activity activity;
    protected String publish_key, subscribe_key, secret_key, cipher_key;
    protected boolean ssl_on, enableGCM;
    protected Event event = Event.SUB;
    protected String[] channels;
    protected OnPushMessageListener listener;

    public enum Event {
        SUB, UNSUB, UNSUBALL
    }

    public interface OnPushMessageListener {
        void onSuccess(String channel, Object data);

        // If there is an error, don't just keep trying to register.
        void onFailure(String channel, String exception);
    }

}
