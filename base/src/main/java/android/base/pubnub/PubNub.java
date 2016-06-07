package android.base.pubnub;

import android.base.log.Log;
import android.base.util.ApplicationUtils;

import com.google.common.base.Optional;
import com.pubnub.api.Callback;
import com.pubnub.api.Pubnub;
import com.pubnub.api.PubnubError;
import com.pubnub.api.PubnubException;

import java.text.MessageFormat;

/**
 * Created by clickapps on 5/2/16.
 */
public class PubNub {

    private PubNub() {

    }

    private Pubnub pubnub;

    public PubNub(PubNubParam pubNubParam) {
        pubnub = new Pubnub(pubNubParam.publish_key, pubNubParam.subscribe_key,
                pubNubParam.secret_key, pubNubParam.cipher_key, pubNubParam.ssl_on);
        pubnub.setCacheBusting(false);
        pubnub.setResumeOnReconnect(true);
        pubnub.setOrigin(PubNubConstant.ORIGIN);
        pubnub.setAuthKey(pubNubParam.secret_key);
    }

    public Pubnub getPubNub() {
        return pubnub;
    }

    public void handleEvent(PubNubParam pubNubParam) {
        switch (pubNubParam.event) {
            case SUB:
                try {
                    pubnub.subscribe(pubNubParam.channels, new PubNubCallback(pubNubParam));
                } catch (PubnubException e) {
                    ApplicationUtils.Log.e(e.getMessage());
                }
                break;
            case UNSUB:
                pubnub.unsubscribe(pubNubParam.channels);
                break;
            case UNSUBALL:
                pubnub.unsubscribeAll();
                break;
            default:
                break;
        }
    }

    private class PubNubCallback extends Callback {
        private PubNubParam pubNubParam;
        private static final String FORMAT = "Channel : {0}\nMessage = {1}";

        private PubNubCallback(PubNubParam param) {
            this.pubNubParam = param;
        }

        @Override
        public void successCallback(String channel, Object message) {
            ApplicationUtils.Log.i(MessageFormat.format(FORMAT, channel, message));
            if (pubNubParam.listener != null
                    && message != null)
                pubNubParam.listener.onSuccess(channel, message);
        }

        @Override
        public void errorCallback(String channel, PubnubError error) {
            ApplicationUtils.Log.e(MessageFormat.format(FORMAT, channel, error.getErrorString()));
            if (pubNubParam.listener != null)
                pubNubParam.listener.onFailure(channel, error.getErrorString());
        }

        @Override
        public void connectCallback(String channel, Object message) {
            ApplicationUtils.Log.d(MessageFormat.format(FORMAT, channel, message));
        }

        @Override
        public void reconnectCallback(String channel, Object message) {
            ApplicationUtils.Log.d(MessageFormat.format(FORMAT, channel, message));
        }

        @Override
        public void disconnectCallback(String channel, Object message) {
            ApplicationUtils.Log.d(MessageFormat.format(FORMAT, channel, message));
        }
    }
}
