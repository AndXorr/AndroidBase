package android.base.pubnub;

import android.app.Activity;
import android.content.Context;

/**
 * Created by clickapps on 5/2/16.
 */
public class PubNubParam {
    public Context context;
    public Activity activity;
    public String publish_key, subscribe_key, secret_key, cipher_key;
    public boolean ssl_on, enableGCM;
    public Event event = Event.SUB;
    public String[] channels;
    public OnPushMessageListener listener;

    public enum Event {
        SUB, UNSUB, UNSUBALL;
    }

    public interface OnPushMessageListener {
        public void onSuccess(String channel, Object data);

        // If there is an error, don't just keep trying to register.
        public void onFailure(String channel, String exception);
    }

}
